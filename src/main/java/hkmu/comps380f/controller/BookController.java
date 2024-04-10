package hkmu.comps380f.controller;

import hkmu.comps380f.model.*;
import hkmu.comps380f.view.DownloadingView;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/book")
public class BookController {
    // Fake DB Setup
    private volatile long BOOK_ID_SEQUENCE = 1;
    private synchronized long getNextBookId() {
        return this.BOOK_ID_SEQUENCE++;
    }
    private Map<Long, Book> bookDB = new ConcurrentHashMap<>();
    @GetMapping(value = {"", "/list"})
    public String list(ModelMap model) {
        model.addAttribute("bookDB", bookDB);
        return "list";
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("add", "bookForm", new Form());
    }
    public static class Form {
        private String name;
        private String author;
        private double price;
        private String description;
        private boolean available;
        private List<MultipartFile> attachments;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public List<MultipartFile> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<MultipartFile> attachments) {
            this.attachments = attachments;
        }
    }

    @PostMapping("/create")
    public View create(Form form) throws IOException {
        Book book = new Book();
        book.setId(this.getNextBookId());
        book.setName(form.getName());
        book.setAuthor(form.getAuthor());
        book.setPrice(form.getPrice());
        book.setDescription(form.getDescription());
        book.setAvailable(form.isAvailable());

        for (MultipartFile filePart : form.getAttachments()) {
            Attachment attachment = new Attachment();
            attachment.setId("cover");
            attachment.setName(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            if (attachment.getName() != null && attachment.getName().length() > 0
                    && attachment.getContents() != null && attachment.getContents().length > 0)
                book.addAttachment(attachment);
        }
        this.bookDB.put(book.getId(), book);
        return new RedirectView("/book/view/" + book.getId(), true);
    }

    @GetMapping("/view/{bookId}")
    public String view(@PathVariable("bookId") long bookId,
                       ModelMap model) {
        Book book = this.bookDB.get(bookId);
        if (book == null) {
            return "redirect:/book/list";
        }
        Attachment attachment = book.getAttachment("cover");
        byte[] imageBytes = attachment.getContents();
        String imageData = Base64.getEncoder().encodeToString(imageBytes);
        model.addAttribute("imageData", imageData);
        model.addAttribute("bookId", bookId);
        model.addAttribute("book", book);
        return "view";
    }

    @GetMapping("/{bookId}/attachment/{attachment:.+}")
    public View download(@PathVariable("bookId") long bookId,
                         @PathVariable("attachment") String AttachmentId) {
        Book book = this.bookDB.get(bookId);
        if (book != null) {
            Attachment attachment = book.getAttachment(AttachmentId);
            if (attachment != null)
                return new DownloadingView(attachment.getName(),
                        attachment.getMimeContentType(), attachment.getContents());
        }
        return new RedirectView("/book/list", true);
    }
}

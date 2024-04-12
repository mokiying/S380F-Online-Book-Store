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
import java.util.HashMap;
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
        private int availability;
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

        public int getAvailability() {
            return availability;
        }

        public void setAvailability(int availability) {
            this.availability = availability;
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
        book.setAvailability(form.getAvailability());

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
        // book Data
        model.addAttribute("bookId", bookId);
        model.addAttribute("book", book);
        // cover image
        Attachment attachment = book.getAttachments().get(0);
        String imageData = "";
        if (attachment!=null) {
            byte[] imageBytes = attachment.getContents();
            imageData = Base64.getEncoder().encodeToString(imageBytes);
        }
        model.addAttribute("imageData", imageData);
        // comments
        Map<Integer, Comment> comments = new HashMap<>(); //book.getComments();
        model.addAttribute("comments", comments);
        return "view";
    }

    @GetMapping("/{bookId}/attachment/{attachment:.+}")
    public View download(@PathVariable("bookId") long bookId,
                         @PathVariable("attachment") String AttachmentId) {
        Book book = this.bookDB.get(bookId);
        if (book != null) {
            Attachment attachment = book.getAttachments().get(0);
            if (attachment != null)
                return new DownloadingView(attachment.getName(),
                        attachment.getMimeContentType(), attachment.getContents());
        }
        return new RedirectView("/book/list", true);
    }

    @GetMapping("/view/{bookId}/comment/add")
    public ModelAndView addComment(@PathVariable("bookId") long bookId, ModelMap model) {
        Book book = bookDB.get(bookId);
        model.addAttribute("bookId", bookId);
        model.addAttribute("book", book);
        return new ModelAndView("addComment", "commentForm", new CommentForm());
    }
    public static class CommentForm {
        private String username;
        private long bookId;
        private String content;

        public long getBookId() {
            return bookId;
        }

        public void setBookId(long bookId) {
            this.bookId = bookId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
    @PostMapping("/view/{bookId}/comment/add")
    public View addComment(@PathVariable("bookId") long bookId, CommentForm form) throws IOException {
        Book book = bookDB.get(bookId);
        Comment comment = new Comment();
        comment.setUsername(form.getUsername());
        comment.setBookId(bookId);
        comment.setContent(form.getContent());
        book.addComment(comment);
        return new RedirectView("/book/view/" + book.getId(), true);
    }
}

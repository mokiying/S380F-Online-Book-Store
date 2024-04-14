package hkmu.comps380f.controller;

import hkmu.comps380f.dao.Service.BookService;
import hkmu.comps380f.exception.AttachmentNotFound;
import hkmu.comps380f.exception.BookNotFound;
import hkmu.comps380f.exception.CommentNotFound;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.model.*;
import hkmu.comps380f.view.DownloadingView;
import jakarta.annotation.Resource;
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
import java.security.Principal;

@Controller
@RequestMapping("/book")
public class BookController {
    // Fake DB Setup
    private volatile long BOOK_ID_SEQUENCE = 1;
    private synchronized long getNextBookId() {
        return this.BOOK_ID_SEQUENCE++;
    }
    @Resource
    private BookService bService;
    @GetMapping(value = {"", "/list"})
    public String list(ModelMap model) {
        model.addAttribute("bookDB", bService.getBooks());
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
        long bookId = bService.createBook(
                form.getName(),
                form.getAuthor(),
                form.getPrice(),
                form.getDescription(),
                form.getAvailability(),
                form.getAttachments()
        );
        return new RedirectView("/book/view/" + bookId, true);
    }

    @GetMapping("/view/{bookId}")
    public String view(@PathVariable("bookId") long bookId,
                       ModelMap model) throws BookNotFound {
        Book book = bService.getBook(bookId);
        if (book == null) {
            return "redirect:/book/list";
        }
        // book Data
        model.addAttribute("bookId", bookId);
        model.addAttribute("book", book);
        // cover image
        String imageData = "";
        if (book.getAttachments().size() > 0) {
            Attachment attachment = book.getAttachments().get(0);
            byte[] imageBytes = attachment.getContents();
            imageData = Base64.getEncoder().encodeToString(imageBytes);
        }
        model.addAttribute("imageData", imageData);
        // comments
        List<Comment> comments = book.getComments();
        System.out.println("Comments:"+comments.toString());
        model.addAttribute("comments", comments);
        return "view";
    }
    @GetMapping("/delete/{bookId}")
    public View delete(@PathVariable("bookId") long bookId) throws BookNotFound, AttachmentNotFound {
        bService.deleteBook(bookId);
        return new RedirectView("/book/list", true);
    }

    @GetMapping("/{bookId}/attachment/{attachment:.+}")
    public View download(@PathVariable("bookId") long bookId,
                         @PathVariable("attachment") String AttachmentId) throws BookNotFound {
        Book book = bService.getBook(bookId);
        if (book != null) {
            Attachment attachment = book.getAttachments().get(0);
            if (attachment != null)
                return new DownloadingView(attachment.getName(),
                        attachment.getMimeContentType(), attachment.getContents());
        }
        return new RedirectView("/book/list", true);
    }

    @GetMapping("/comment/add/{bookId}")
    public ModelAndView addComment(@PathVariable("bookId") long bookId, ModelMap model) throws BookNotFound {
        Book book = bService.getBook(bookId);
        model.addAttribute("bookId", bookId);
        model.addAttribute("book", book);
        return new ModelAndView("addComment", "commentForm", new CommentForm());
    }
    public static class CommentForm {
        private long bookId;
        private String content;

        public long getBookId() {
            return bookId;
        }

        public void setBookId(long bookId) {
            this.bookId = bookId;
        }
        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
    @PostMapping("/comment/add/{bookId}")
    public View addComment(@PathVariable("bookId") long bookId, CommentForm form, Principal principal)
            throws BookNotFound, UserNotFound {
        Book book = bService.getBook(bookId);
        bService.addComment(bookId, principal.getName(), form.getContent());
        return new RedirectView("/book/view/" + book.getId(), true);
    }
    @GetMapping("/comment/delete/{commentId}/")
    public View deleteComemnt(@PathVariable("commentId") long commentId) throws CommentNotFound {
        Comment comment = bService.getCommment(commentId);
        bService.deleteComment(commentId);
        return new RedirectView("/book/view/" + comment.getBookId(), true);
    }
}

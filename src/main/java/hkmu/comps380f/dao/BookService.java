package hkmu.comps380f.dao;

import hkmu.comps380f.exception.AttachmentNotFound;
import hkmu.comps380f.exception.BookNotFound;
import hkmu.comps380f.model.Attachment;
import hkmu.comps380f.model.Book;
import hkmu.comps380f.model.Comment;
import jakarta.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {
    // Resources
    @Resource
    private BookRepository bRepo;
    @Resource
    private AttachmentRepository aRepo;
    // Transaction of Book
    @Transactional
    public List<Book> getBooks() {
        return bRepo.findAll();
    }
    @Transactional
    public Book getBook(long id)
            throws BookNotFound {
        Book book = bRepo.findById(id).orElse(null);
        if (book == null) {
            throw new BookNotFound(id);
        }
        return book;
    }
    @Transactional
    public long createBook(String name, String author,
                           double price,String description,int availability, List<MultipartFile> attachments)
            throws IOException {

        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setPrice(price);
        book.setDescription(description);
        book.setAvailability(availability);
        for (MultipartFile filePart : attachments) {
            Attachment attachment = new Attachment();
            attachment.setName(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            attachment.setBook(book);
            if (attachment.getName() != null && attachment.getName().length() > 0
                    && attachment.getContents() != null
                    && attachment.getContents().length > 0) {
                book.getAttachments().add(attachment);
            }
        }
        Book savedBook = bRepo.save(book);
        return savedBook.getId();
    }
    @Transactional
    public void deleteBook(long bookId) throws BookNotFound, AttachmentNotFound{
        // 1. Find the book by its ID;
        Book book = bRepo.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with ID: " + bookId));
        for (Attachment attachment : book.getAttachments()) {
            attachment.setBook(null);
            aRepo.delete(attachment);
        }
        bRepo.delete(book);
    }
    // Transaction of Attachment
    @Transactional
    public Attachment getAttachment(long bookId, UUID attachmentId)
            throws BookNotFound, AttachmentNotFound {
        Book book = bRepo.findById(bookId).orElse(null);
        if (book == null) {
            throw new BookNotFound(bookId);
        }
        Attachment attachment = aRepo.findById(attachmentId).orElse(null);
        if (attachment == null) {
            throw new AttachmentNotFound(attachmentId);
        }
        return attachment;
    }
    // Transaction of Comment
    @Transactional
    public void addComment(long bookId, String username, String content) throws BookNotFound {
        Book book = bRepo.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with ID: " + bookId));
        List<Comment> comments = book.getComments();
        Comment newComment = new Comment();
        newComment.setUsername(username);
        newComment.setContent(content);
        newComment.setBookId(bookId);
        newComment.setBook(book);
        comments.add(newComment);
        bRepo.save(book);
    }
}



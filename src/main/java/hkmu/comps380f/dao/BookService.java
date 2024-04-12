package hkmu.comps380f.dao;

import hkmu.comps380f.exception.AttachmentNotFound;
import hkmu.comps380f.exception.BookNotFound;
import hkmu.comps380f.model.Attachment;
import hkmu.comps380f.model.Book;
import jakarta.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {
    @Resource
    private BookRepository bRepo;
    @Resource
    private AttachmentRepository aRepo;
    @Transactional
    public List<Book> get() {
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
    @Transactional(rollbackFor = BookNotFound.class)
    public void delete(long id) throws BookNotFound {
        Book deletedbook = bRepo.findById(id).orElse(null);
        if (deletedbook == null) {
            throw new BookNotFound(id);
        }
        bRepo.delete(deletedbook);
    }
    @Transactional(rollbackFor = AttachmentNotFound.class)
    public void deleteAttachment(long bookId, UUID attachmentId)
            throws BookNotFound, AttachmentNotFound {
        Book book = bRepo.findById(bookId).orElse(null);
        if (book == null) {
            throw new BookNotFound(bookId);
        }
        for (Attachment attachment : book.getAttachments()) {
            if (attachment.getId().equals(attachmentId)) {
                book.deleteAttachment(attachment);
                bRepo.save(book);
                return;
            }
        }
        throw new AttachmentNotFound(attachmentId);
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
            System.out.println("Looping attachments");
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
        Book savedTicket = bRepo.save(book);
        return savedTicket.getId();
    }
}

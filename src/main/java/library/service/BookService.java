package library.service;

import library.entity.Book;
import library.exception.BookNotFoundException;
import library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(long id){
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("id: "+Long.toString(id)));
    }

    public Book updateBook(long id, Book book){
        Book bookToBeUpdated = bookRepository.findById(id).orElseThrow(()->new BookNotFoundException("id: "+Long.toString(id)));
        bookToBeUpdated.setTitle(book.getTitle());
        bookToBeUpdated.setLaunchYear(book.getLaunchYear());
        bookToBeUpdated.setPublisher(book.getPublisher());
        return bookRepository.save(book);
    }

    public void deleteBookById(long id){
        bookRepository.deleteById(id);
    }

    public List<Book> searchBooksByTitle(String title){
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
    public List<Book> searchBooksByPublisher(String publisher){
        return bookRepository.findByPublisherContainingIgnoreCase(publisher);
    }
}

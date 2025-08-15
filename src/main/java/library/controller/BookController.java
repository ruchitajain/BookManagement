package library.controller;

import library.entity.Book;
import library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/library")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("addBooks")
    Book addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @GetMapping
    List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/searchById/{id}")
    Book getBookById(@PathVariable long id){
        return bookService.getBookById(id);
    }

    @PutMapping("/updateBookId/{id}")
    Book updateBookById(@PathVariable long id, @RequestBody Book book){
        return bookService.updateBook(id,book);
    }

    @DeleteMapping("/deleteBookId/{id}")
    void deleteBook(@PathVariable long id){
        bookService.deleteBookById(id);
    }

    @GetMapping("/searchByTitle")
    List<Book> getBookByTitle(@RequestParam String searchTitleKeyword){
        return bookService.searchBooksByTitle(searchTitleKeyword);
    }

    @GetMapping("/searchByPublisher")
    List<Book> getBookByPublisher(@RequestParam String searchPublisherKeyword){
        return bookService.searchBooksByPublisher(searchPublisherKeyword);
    }

}

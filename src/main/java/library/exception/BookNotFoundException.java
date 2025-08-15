package library.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super("Book with " + message + " not found");
    }
}

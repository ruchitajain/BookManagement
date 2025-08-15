package library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import library.entity.Book;
import library.exception.BookNotFoundException;
import library.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import javax.annotation.Resource;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = BookController.class)
public class BookControllerTest {
    @Resource
    MockMvc mockMvc;

    @Resource
    ObjectMapper objectMapper;

    @MockBean
    BookService bookService;

    @Test
    void get_returns200_withBody_whenFound() throws Exception {
        Book b = new Book();
        b.setId(1L);
        b.setTitle("Clean Code");
        b.setPublisher("publisher");
        b.setLaunchYear("2020");

        when(bookService.getBookById(1L)).thenReturn(b);

        mockMvc.perform(get("/api/library/searchById/1").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Clean Code"));
    }

    @Test
    void get_returns404_whenNotFound() throws Exception {
        when(bookService.getBookById(99L))
                .thenThrow(new BookNotFoundException("id: 99"));

        mockMvc.perform(get("/api/library/searchById/99").accept(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}

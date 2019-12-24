package com.ebook.book;

import com.ebook.book.response.BaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.ebook.book.Book;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = EbookApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

    @Autowired
    private WebApplicationContext context;

    @LocalServerPort
    private int port;

    private MockMvc mockMvc;

    private Book book;

    @Mock
    BookService bookService;

    @InjectMocks
    BookController bookController;


    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void createBookTest() throws Exception {
        Book book = new Book();
        book.setAuthor("mikkk");
        book.setNotes("nijokkk");
        book.setTitle("mybook");
        book.setPublished(new Date());

        when(bookService.create(book)).thenReturn(book);

        // when
        BaseResponse<Book> result = bookController.create(book);

        // then
        Book data = Objects.requireNonNull(result.getBody()).getData();
        assertThat(data.getAuthor()).isEqualTo(book.getAuthor());
        assertThat(data.getTitle()).isEqualTo(book.getTitle());

        // mockmvc test
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(book);
        this.mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isCreated());

    }

    @Test
    public void createBookExceptionTest() throws Exception {

        Book book = new Book();
        book.setNotes("nijokkk");
        book.setPublished(new Date());

        when(bookService.create(book)).thenReturn(book);

        // when
        BaseResponse<Book> result = bookController.create(book);

        // then
        Book data = Objects.requireNonNull(result.getBody()).getData();
        assertThat(data.getAuthor()).isEqualTo(book.getAuthor());
        assertThat(data.getTitle()).isEqualTo(book.getTitle());

        // mockmvc test
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(book);
        this.mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest());

    }

    @Test
    public void getBookByIdTest() throws Exception {

        Book book = new Book();
        book.setId(1);
        book.setAuthor("mikkk");
        book.setNotes("nijokkk");
        book.setTitle("mybook");
        book.setPublished(new Date());

        when(bookService.findById(book.getId())).thenReturn(book);

        // when
        BaseResponse<Book> result = bookController.findOne(book.getId());

        // then
        Book data = Objects.requireNonNull(result.getBody()).getData();
        assertThat(data.getTitle()).isEqualTo(book.getTitle());

        // mockmvc test
        this.mockMvc.perform(get("/books/"+book.getId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    public void getBooksTest() throws Exception {

        Book book = new Book();
        book.setId(1);
        book.setAuthor("mikkk");
        book.setNotes("nijokkk");
        book.setTitle("mybook");
        book.setPublished(new Date());

        Book book1 = new Book();
        book.setId(2);
        book.setAuthor("mikkk2");
        book.setNotes("nijokkk2");
        book.setTitle("mybook");
        book.setPublished(new Date());


        List<Book> bookList = Arrays.asList(book,book1);


        Mockito.when(bookService.findAll()).thenReturn(bookList);

        // when
        BaseResponse<List<Book>> result = bookController.findAll();

        // then
        List<Book> data = Objects.requireNonNull(result.getBody()).getData();
        assertThat(data.get(0).getTitle()).isEqualTo(book.getTitle());


        // mockmvc test
        this.mockMvc.perform(get("/books").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }



}

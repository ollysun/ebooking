package com.ebook.book;

import com.ebook.book.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/books", produces = "application/json")
@Api(value="Books")
@Validated
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "Add a Book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Created Book"),
            @ApiResponse(code = 400, message = "Bad Request")})
    @PostMapping
    public BaseResponse<Book> create(@Valid @RequestBody Book book){
        return new BaseResponse<>(bookService.create(book), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Search a book with an ID",response = Book.class)
    @GetMapping("/{id}")
    public BaseResponse<Book> findOne(@PathVariable("id") int id){
        Book book = bookService.findById(id);
        return  (book == null) ?
                new BaseResponse<>(null,"Book details not found", HttpStatus.NOT_FOUND) :
                new BaseResponse<>(book);
    }

    @ApiOperation(value = "View a list of available books",response = Iterable.class)
    @GetMapping
    public BaseResponse<List<Book>> findAll(){
        return new BaseResponse<>(bookService.findAll());
    }

}

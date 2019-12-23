package com.ebook.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {

    @Autowired
    IBookRepository iBookRepository;

    @Transactional
    public Book create(Book book) {
        return iBookRepository.save(book);
    }

    @Transactional
    public List<Book> findAll() {
        return iBookRepository.findAll();
    }

    @Transactional
    public Book findById(int id) {
        return iBookRepository.findById(id);
    }
}

package com.hendisantika.springbootrestapipostgresql.controller;

import com.hendisantika.springbootrestapipostgresql.entity.Book;
import com.hendisantika.springbootrestapipostgresql.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/bookordersdec")
public class BookOrderController {

    @Autowired
    private BookRepository repository;
    @GetMapping
    public ResponseEntity<Collection<Book>> getAllBooksDec() {
        List<Book> books = repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}

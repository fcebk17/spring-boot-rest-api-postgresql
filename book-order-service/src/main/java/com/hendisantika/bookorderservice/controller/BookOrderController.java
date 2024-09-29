package com.hendisantika.bookorderservice.controller;

import com.hendisantika.bookorderservice.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookordersdec")
public class BookOrderController {

    @Autowired
    private RestTemplate restTemplate;

    private final String BOOK_MANAGE_SERVICE_URL = "http://localhost:8080/api/books";

    @GetMapping
    public ResponseEntity<Collection<Book>> getAllBooksDec() {
        String url = BOOK_MANAGE_SERVICE_URL;
        ResponseEntity<Book[]> response = restTemplate.getForEntity(url, Book[].class);

        if(response.getStatusCode().is2xxSuccessful()) {
            List<Book> books = Arrays.stream(response.getBody())
                                     .sorted((book1, book2) -> book2.getId().compareTo(book1.getId()))
                                     .collect(Collectors.toList());
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}

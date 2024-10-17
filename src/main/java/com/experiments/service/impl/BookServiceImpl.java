package com.experiments.service.impl;

import com.experiments.entity.Book;
import com.experiments.repository.BookRepository;
import com.experiments.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book saveBook(Book book) {

        return bookRepository.save(book);
    }
}

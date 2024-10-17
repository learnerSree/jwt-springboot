package com.experiments.controller;


import com.experiments.entity.Book;
import com.experiments.entity.Employee;
import com.experiments.entity.User;
import com.experiments.service.BookService;
import com.experiments.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    List<Employee> employees = new ArrayList<>();

    @GetMapping("/ping")
    public String ping( HttpServletRequest httpServletRequest ){

        return "ping from application: " + httpServletRequest.getSession().getId();
    }

    @GetMapping("/user")
    public User getUserByName(@RequestParam(name = "username") String userName){

        return userService.findByUserName( userName);
    }


    @PostMapping(value = "/book")
    public ResponseEntity<Book> saveBook(@RequestBody Book book){

        Book createdBook = bookService.saveBook(book);

        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }


    @GetMapping("/getemployee")
    public List<Employee> getEmployees(){

        return employees;
    }

    @PostMapping("/saveemployee")
    public Employee saveEmployee( @RequestBody Employee employee ){

        employees.add( employee );
        return employee;
    }

    @GetMapping("/csrf")
    public CsrfToken getCSRFToken(HttpServletRequest request){

        return (CsrfToken) request.getAttribute("_csrf");
    }
}

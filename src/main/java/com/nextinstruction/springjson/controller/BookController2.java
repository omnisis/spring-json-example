package com.nextinstruction.springjson.controller;

import com.nextinstruction.springjson.domain.Book;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/ajax2")
public class BookController2 {

    @RequestMapping(value = "books", method = RequestMethod.GET)
    public
    @ResponseBody
    Book getBooks() {
        final Book book = new Book();
        book.setAuthor("Stephen King");
        book.setTitle("The Shining");
        //book.setPubDate();
        book.setIsbn("1231232");
        return book;
    }

    @RequestMapping(value = "exception", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Book getBookException() {
        throw new RuntimeException("Can't find that book");
    }
}

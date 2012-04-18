package com.nextinstruction.springjson.controller;

import com.nextinstruction.springjson.domain.Book;
import com.nextinstruction.springjson.domain.JsonError;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ajax")
public class BookController {

    @RequestMapping(value = "book/{isbn}", method = RequestMethod.GET)
    public
    @ResponseBody
    Book getBookDetails(@PathVariable String isbn) throws Exception {
        Book aBook = new Book();
        aBook.setAuthor("Nicholas Sparks");
        aBook.setTitle("The Notebook");
        aBook.setIsbn(isbn);
        //aBook.setPubDate(DateUtils.parseDate("1997", new String[]{"YYYY"}));
        return aBook;
    }

    @RequestMapping(value = "exception", method = RequestMethod.GET)
    public
    @ResponseBody
    void getBookDetails() {
        throw new RuntimeException("crap");
    }

    // Intercepts all instances of exception, ferreting to a JsonError as MAV Object for
    // the Jackson Mapper
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView globalExceptionHandler(Exception ex) {
        return new JsonError(225, "Oops something went wrong!").asModelAndView();
    }


}

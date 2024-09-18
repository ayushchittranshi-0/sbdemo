package com.example.demo.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class BookController {

    private BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping(value="{reader}",method=RequestMethod.GET)
    public String readerBooks(@PathVariable("reader") String reader,Model model) {
        List<BookEntity> readingList = bookRepository.findByReader(reader);
        if(readingList !=null) {
            model.addAttribute("readingListBooks",readingList);
        }
        return "readingList";
    }

    @RequestMapping(value="/{reader}", method=RequestMethod.POST)
    public String addToReadingList(
        @PathVariable("reader") String reader, BookEntity book) {
        book.setReader(reader);
        bookRepository.save(book);
        return "redirect:/{reader}";
    }

}

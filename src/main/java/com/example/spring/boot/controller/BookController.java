package com.example.spring.boot.controller;

import com.example.spring.boot.dto.BookDto;
import com.example.spring.boot.entity.Book;
import com.example.spring.boot.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public String getAllBooks(Model model) {
        List<BookDto> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "booksList";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") BookDto bookDTO) {
        bookService.addBook(bookDTO);
        return "redirect:/books";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteBookById(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public BookDto getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id)
                .orElseThrow(() -> new RuntimeException("Book with id = %s not found".formatted(id)));
    }
}

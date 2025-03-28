package com.example.spring.boot.controller;

import com.example.spring.boot.dto.BookDto;
import com.example.spring.boot.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PreAuthorize("hasAnyRole(ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/books")
    public String getAllBooks(Model model) {
        List<BookDto> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "booksList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/books/add")
    public String addForm() {
        return "addBook";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/books/add")
    public String addBook(@ModelAttribute("book") BookDto bookDTO) {
        bookService.addBook(bookDTO);
        return "redirect:/booksList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/books/delete/{id}")
    public String deleteBookById(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
        return "redirect:/booksList";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/books/get/{id}")
    @ResponseBody
    public BookDto getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id)
                .orElseThrow(() -> new RuntimeException("Book with id = %s not found".formatted(id)));
    }
}

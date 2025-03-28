package com.example.spring.boot.service;

import com.example.spring.boot.dto.BookDto;
import com.example.spring.boot.entity.Book;
import com.example.spring.boot.mapper.BookMapper;
import com.example.spring.boot.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public void addBook(BookDto bookDTO) {
        Book book = bookMapper.toBook(bookDTO);
        bookRepository.save(book);
    }

    public Optional<BookDto> getBookById(Long id) {
        return bookRepository.findById(id).
                map(bookMapper::toBookDto);
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toBookDto)
                .toList();
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
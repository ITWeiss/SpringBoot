package com.example.spring.boot.mapper;

import com.example.spring.boot.dto.BookDto;
import com.example.spring.boot.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toBookDto(Book book);

    @Mapping(target = "id", ignore = true)
    Book toBook(BookDto bookDto);
}

package net.xla.bookstore.model;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, max = 50, message = "Title should be between 2 and 50 characters")
    private String title;

    @NotEmpty(message = "Author should not be empty")
    @Size(min = 2, max = 50, message = "Author should be between 2 and 50 characters")
    private String author;

    @Min(value = 0, message = "Price should not be less than 0")
    @Max(value = 9999, message = "Price should not be greater than 9999")
    @Digits(integer = 4, fraction = 2, message = "Price not valid number, valid digits: ****.**")
    private Double price;
}

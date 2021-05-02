package net.xla.bookstore.controller;

import net.xla.bookstore.model.Book;
import net.xla.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("books")
    public String findAll(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "book-list";
    }

    @GetMapping("book-create")
    public String createBookForm(Book book, Model model) {
        model.addAttribute("book", book);
        return "book-create";
    }

    @PostMapping("book-create")
    public String createBook(@Valid Book book,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "book-create";
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("book-delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("book-update/{id}")
    public String updateBookForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "book-update";
    }

    @PostMapping("book-update")
    public String updateBook(@Valid Book book,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "book-update";
        bookService.saveBook(book);
        return "redirect:/books";
    }
}

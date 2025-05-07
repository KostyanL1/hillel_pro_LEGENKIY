package org.lesson31;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
class LibraryTest {

    Library library;

    @BeforeEach
    void createLibrary(){
        this.library = new Library();
    }

    @Test
    void testAddBook(){
        Book book = new Book("Title", "Name");
        int sizeBefore = library.getBooks().size();
        library.addBook(book);
        int sizeAfter = library.getBooks().size();
        assertEquals(sizeBefore + 1 , sizeAfter);
        assertThrows(NullPointerException.class, () ->
        {
            library.addBook(null);
        });

    }

    @Test
    void testRemoveBook()  {
        Book book = new Book("Title", "Name");
        library.addBook(book);
        int sizeBefore = library.getBooks().size();
        library.removeBook(book);
        int sizeAfter = library.getBooks().size();
        assertEquals(sizeBefore, sizeAfter + 1);
        library.addBook(book);
        assertTrue(library.removeBook(book));
        assertFalse(library.removeBook(new Book("dfasdadas", "dsds")));
    }

    @Test
    void testGetBookCount() {
        library.addBook(new Book("dsafsd", "dsad"));
        library.addBook(new Book("dsafsd", "dsad"));
        library.addBook(new Book("dsafsd", "dsad"));
        library.addBook(new Book("dsafsd", "dsad"));
        assertEquals(library.getBooks().size(), library.getBookCount());
    }

    @Test
    void testGetAllBooks() {
        assertEquals(library.getBooks(), library.getAllBooks());
    }
}
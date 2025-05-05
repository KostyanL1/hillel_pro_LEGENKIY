package org.lesson31;



import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Getter
@Setter
public class Library {

    private final List<Book> books = new ArrayList<>();


    public void addBook(Book book){
        if (book != null){
            this.books.add(book);
        }else throw  new NullPointerException();


    }

    public boolean removeBook(Book book) {
        Optional<Book> bookOptional = this.books.stream().filter(book1 -> book1.equals(book)).findFirst();
        if (bookOptional.isPresent()){
            this.books.remove(book);
            return true;
        }
        else return false;
    }

    public List<Book> getAllBooks() {
        return this.books;
    }


    public int getBookCount() {
        return books.size();
    }
}

package java_streams;

import java.util.stream.Stream;

public class Book {
    private String title;
    private String author;
    private Double price;

    public Book(String title, String author, Double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }


    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }

    public Double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }
}

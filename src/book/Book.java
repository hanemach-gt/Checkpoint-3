package book;

public class Book {

  private String ISBN;
  private String author;
  private String title;
  private String publisher;
  private Integer publicationYear;
  private Float price;
  private String typeBook;

  public Book(String ISBN,
                String author,
                String title,
                String publisher,
                Integer publicationYear,
                Float price,
                String typeBook) {

    this.ISBN = ISBN;
    this.author = author;
    this.title = title;
    this.publisher = publisher;
    this.publicationYear = publicationYear;
    this.price = price;
    this.typeBook = typeBook;
  }
}

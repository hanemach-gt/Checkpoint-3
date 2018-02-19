package book;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;

import exceptionlog.ExceptionLog;

public class BookDao {

  private Connection con;
  private static String JDBC = "jdbc:sqlite:database/database.db";

  public void initializeConnection() {

    try {

        Class.forName("org.sqlite.JDBC");
    } catch(ClassNotFoundException e) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        return;
    }
    try {

      con = DriverManager.getConnection(BookDao.JDBC);
    } catch(SQLException e) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }
  }

  public void finalizeConnection() {

    if (con != null) {

      try {

        con.close();
      } catch(SQLException e) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
    }
  }

  public boolean addBook(Book book) {

    return true;
  }

  public boolean updateBook(Book book) {

    return true;
  }

  public boolean deleteBook(Book book) {

    return true;
  }

  public Book getBookByName(String name) {

    return null;
  }

  public Book getBookByStringOrYear(String phrase) {

    return null;
  }

  public List<Book> getAllBooks() {

    String query = "select ISBN,"
                  +  "authors.name AS author_name,"
                  +  "title,"
                  +  "publishers.name AS publisher_name,"
                  +  "publication_year,"
                  +  "price,"
                  +  "type_books.type AS type_name FROM books"
                  +  "inner join authors on author = authors.author_id"
                  +  "inner join publishers on publisher = publishers.publisher_id"
                  +  "inner join type_books on books.type = type_books.type_id"
                  +  ";";

    Statement stmt = null;
    ArrayList<Book> books = new ArrayList<>();
    try {
        stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(query);

        while(rs.next()) {

          String ISBN = rs.getString("ISBN");
          String author = rs.getString("author_name");
          String title = rs.getString("title");
          String publisher = rs.getString("publisher_name");
          Integer year = rs.getInt("publication_year");
          Float price = rs.getFloat("price");
          String type = rs.getString("type_name");

          Book book = new Book(ISBN,
                                author,
                                title,
                                publisher,
                                year,
                                price,
                                type);
          books.add(book);
        }
    } catch (SQLException e) {

        ExceptionLog.add(e);
    } finally {

        try {

          if (stmt != null) {

            stmt.close();
          }
        } catch (SQLException e) {

          ExceptionLog.add(e);
        }
    }
    return books;
  }
  public List<Book> getBooksByAuthorId() {

    return null;
  }


}

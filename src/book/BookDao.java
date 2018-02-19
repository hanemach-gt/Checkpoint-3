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

    return updateBook(book);
  }

  public boolean updateBook(Book book) {
    /* insert or replace */

    return true;//succeeded
  }

  public boolean deleteBook(Book book) {

    String deleteString = "DELETE FROM books WHERE title = ?";
    PreparedStatement deleteStmt = null;
    boolean succeeded = false;
    try {

        deleteStmt = con.prepareStatement(deleteString);
        deleteStmt.setString(1, book.getName());
        succeeded = deleteStmt.executeUpdate() > 0;

    } catch (SQLException e) {

        ExceptionLog.add(e);
    } finally {

        try {

          if (deleteStmt != null) {

            deleteStmt.close();
          }
        } catch (SQLException e) {

          ExceptionLog.add(e);
        }
    }
    return succeeded;
  }

  public Book getBookByName(String name) {

    String query = "SELECT ISBN,"
                  +  "authors.name AS author_name,"
                  +  "title,"
                  +  "publishers.name AS publisher_name,"
                  +  "publication_year,"
                  +  "price,"
                  +  "type_books.type AS type_name FROM books"
                  +  "INNER JOIN authors ON author = authors.author_id"
                  +  "INNER JOIN publishers ON publisher = publishers.publisher_id"
                  +  "INNER JOIN type_books ON books.type = type_books.type_id"
                  +  "WHERE title = ? LIMIT 1;";

    PreparedStatement queryStmt = null;
    boolean succeeded = false;
    try {

        queryStmt = con.prepareStatement(query);
        queryStmt.setString(1, name);
        ResultSet rs = queryStmt.executeQuery();
        if(rs.next()) {

            String ISBN = rs.getString("ISBN");
            String author = rs.getString("author_name");
            String title = rs.getString("title");
            String publisher = rs.getString("publisher_name");
            Integer year = rs.getInt("publication_year");
            Float price = rs.getFloat("price");
            String type = rs.getString("type_name");

            return new Book(ISBN,
                            author,
                            title,
                            publisher,
                            year,
                            price,
                            type);
        }

    } catch (SQLException e) {

        ExceptionLog.add(e);
    } finally {

        try {

          if (queryStmt != null) {

            queryStmt.close();
          }
        } catch (SQLException e) {

          ExceptionLog.add(e);
        }
    }
    return null;
  }

  public Book getBookByStringOrYear(String phrase) {

    List<Book> books = getAllBooks();
    for (Book book : books) {

      if(book.toString().contains(phrase)) {

        return book;
      }
    }
    return null;
  }

  public List<Book> getAllBooks() {

    String query = "SELECT ISBN,"
                  +  "authors.name AS author_name,"
                  +  "title,"
                  +  "publishers.name AS publisher_name,"
                  +  "publication_year,"
                  +  "price,"
                  +  "type_books.type AS type_name FROM books"
                  +  "INNER JOIN authors ON author = authors.author_id"
                  +  "INNER JOIN publishers ON publisher = publishers.publisher_id"
                  +  "INNER JOIN type_books ON books.type = type_books.type_id"
                  +  ";";

    Statement stmt = null;
    ArrayList<Book> books = new ArrayList<>();
    try {
        stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(query);

        while(rs.next()) {

          books.add(resultToBook(rs));
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

  private Book resultToBook(ResultSet rs) {

    try {

      String ISBN = rs.getString("ISBN");
      String author = rs.getString("author_name");
      String title = rs.getString("title");
      String publisher = rs.getString("publisher_name");
      Integer year = rs.getInt("publication_year");
      Float price = rs.getFloat("price");
      String type = rs.getString("type_name");

      return new Book(ISBN,
                        author,
                        title,
                        publisher,
                        year,
                        price,
                        type);
    } catch (SQLException e) {

        ExceptionLog.add(e);
    }
    return null;
  }

  public List<Book> getBooksByAuthorName(String authorName) {
    String query = "SELECT ISBN,"
                  +  "authors.name AS author_name,"
                  +  "title,"
                  +  "publishers.name AS publisher_name,"
                  +  "publication_year,"
                  +  "price,"
                  +  "type_books.type AS type_name FROM books"
                  +  "INNER JOIN authors ON author = authors.author_id"
                  +  "INNER JOIN publishers ON publisher = publishers.publisher_id"
                  +  "INNER JOIN type_books ON books.type = type_books.type_id"
                  +  "WHERE author_name = ?;";

    PreparedStatement stmt = null;
    ArrayList<Book> books = new ArrayList<>();
    try {
        stmt = con.prepareStatement(query);
        stmt.setString(1, authorName);

        ResultSet rs = stmt.executeQuery(query);

        while(rs.next()) {

          books.add(resultToBook(rs));
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
}

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

  public Book getBookByStringOrYear(String phrase) {

    return null;
  }

  public List<Book> getAllBooks() {

    return null;
  }
  public List<Book> getBooksByAuthorId() {

    return null;
  }


}

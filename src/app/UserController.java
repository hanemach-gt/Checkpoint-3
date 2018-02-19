package app;

import java.util.List;
import java.util.ArrayList;
import book.Book;
import book.BookDao;

public class UserController {

    protected UserView view;

    public UserController(UserView view) {

        this.view = view;
    }

    public void start() {

        boolean requestedExit = false;
        do {

            MenuOption userOption = view.getMenuOptionFromUserInput(" Please choose option: ");
            if (userOption.getId().equals("0")) {
                requestedExit = true;
                view.clearScreen();
            } else {

                handleUserChoice(userOption.getId());
            }
        } while (!requestedExit);
    }

    private void handleUserChoice(String userChoice) {

        switch (userChoice) {
            case "1":
                unimplemented();
                break;

            case "2":
                unimplemented();
                break;

            case "3":
                deleteBook();
                break;

            case "4":
                searchBookByPhrase();
                break;

            case "5":
                seeAllBooks();
                break;

            case "6":
                seeAllByAuthorName();
                break;
        }
    }

    private void deleteBook() {

      BookDao bookDao = new BookDao();
      bookDao.initializeConnection();

      view.print(bookCollectionToString(bookDao.getAllBooks()));

      Book book = getBookFromUserInput(bookDao, "\n Please provide book name: ", "\n Such book does not exist in database");

      if (bookDao.deleteBook(book)) {

        view.printLine("Deletion succeeded");
      } else {

        view.printLine("Deletion failed");
      }

      bookDao.finalizeConnection();

    }

    private void searchBookByPhrase() {unimplemented();}

    private void seeAllBooks() {unimplemented();}

    private void seeAllByAuthorName() {unimplemented();}

    private void unimplemented() {

      view.printLine("\n sorry, unimplemented \n");
    }

    private String bookCollectionToString(List<Book> books) {

      String result = "\n";
      for (Book book : books) {

        result += book.toString();
      }
      return result + "\n";
    }

    private String getNonexistentBookName() {

        BookDao bookDao = new BookDao();
        bookDao.initializeConnection();

        String name;
        boolean providedValidName = false;
        do {
            name = this.view.getStringFromUserInput(view.newBookNameQuestion);
            if (bookDao.getBookByName(name) == null) {

                providedValidName = true;
            } else {

                view.printLine(view.nameAlreadyTaken);
            }

        } while(!providedValidName);

        bookDao.finalizeConnection();
        return name;
    }

    public String getNameFromUserInput(String prompt, String disallowedMessage, List<String> allowedNames) {

        String name;
        boolean providedValidName = false;
        do {

            name = view.getStringFromUserInput(prompt);
            if (allowedNames.contains(name)) {

                providedValidName = true;
            } else {

                view.printLine(disallowedMessage);
            }

        } while(!providedValidName);
        return name;
    }


    /* Returns the first input string that does not occur in disallowedNames */
    public String getExclusiveNameFromUserInput(String prompt, String disallowedMessage, List<String> disallowedNames) {

        String name;
        boolean providedValidName = false;
        do {

            name = view.getStringFromUserInput(prompt);
            if (!disallowedNames.contains(name)) {

                providedValidName = true;
            } else {

                view.printLine(disallowedMessage);
            }

        } while(!providedValidName);
        return name;
    }

    private List<String> bookCollectionToStringCollection(List<Book> books) {

      List<String> names = new ArrayList<>();
      for (Book book : books) {

        names.add(book.getName());
      }
      return names;
    }

    public Book getBookFromUserInput(String nameQuestion, String outOfRangeError) {

        BookDao bookDao = new BookDao();
        bookDao.initializeConnection();

        Book book = getBookFromUserInput(bookDao, nameQuestion, outOfRangeError);

        bookDao.finalizeConnection();
        return book;
    }

    public Book getBookFromUserInput(BookDao bookDao, String nameQuestion, String outOfRangeError) {

      List<Book> books = bookDao.getAllBooks();
      List<String> allowedBookNames = bookCollectionToStringCollection(books);

      String name = getNameFromUserInput(nameQuestion, outOfRangeError, allowedBookNames);

      Book book = bookDao.getBookByName(name);
      return book;
    }
}

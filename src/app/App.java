package app;


public class App {

  public static void main(String[] args) {

    UserView view = new UserView(
                      new Menu(
                        new MenuOption("0", "Exit"),
                        new MenuOption("1", "Add new book"),
                        new MenuOption("2", "Edit a book"),
                        new MenuOption("3", "Delete a book"),
                        new MenuOption("4", "Search book by phrase or year"),
                        new MenuOption("5", "See all books"),
                        new MenuOption("6", "See all books of an author")
                      )
                    );

    new UserController(view).start();
  }
}

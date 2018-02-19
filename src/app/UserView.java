package app;

import java.util.Scanner;

public class UserView {

    public String nameAlreadyTaken = "\n This name cannot be used.\n";
    public String nameOutOfRange = "\n You need to provide an already existing name.\n";
    public String newBookNameQuestion = "Please provide new book name";

    protected Menu menu;

    private static final String ESCAPE_SEQ_CLEAR_SCREEN = "\033[H\033[2J";

    private static Scanner scanner;

    protected UserView(Menu menu) {

        this.menu = menu;
        scanner = new Scanner(System.in);
    }

    public static void closeScanner() {

        UserView.scanner.close();
    }

    public void clearScreen() {

        print(UserView.ESCAPE_SEQ_CLEAR_SCREEN);
    }

    public void printLine(String message) {

        System.out.println(message);
    }

    public void print(String message) {

        System.out.print(message);
        System.out.flush();
    }

    private void printMenu(Menu menu) {

        printLine(menu.toString());
    }

    public Integer getIntFromUserInput(String prompt) {
        Integer userInput = null;

        boolean validInput;
        do {
            validInput = true;
            try {
                userInput = Integer.valueOf(getStringFromUserInput(prompt));
            } catch (Exception e) {
                validInput = false;
                printLine("\n Invalid input.\n");
            }
        } while(!validInput);
        return userInput;
    }

    public String getStringFromUserInput(String prompt) {

        print(prompt);
        return scanner.nextLine();
    }

    public MenuOption getMenuOptionFromUserInput(String prompt) {

        MenuOption chosenOption = null;

        boolean providedValidAnswer = false;
        while (!providedValidAnswer) {

            printMenu(menu);
            String userInput = getStringFromUserInput(prompt);

            for (MenuOption mo : menu.getMenuOptions()) {

              if (mo.getId().equals(userInput)) {
                  providedValidAnswer = true;
                  chosenOption = mo;
                  break;
              }
            }
        }
        return chosenOption;
    }
}

package app;

import java.util.List;
import book.Book;

public class UserController {

    protected UserView view;

    public UserController(UserView view) {

        this.view = view;
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

    public Book getBookFromUserInput(String nameQuestion, String outOfRangeError, String groupName) {

        List<Book> books = null;
        List<String> allowedBookNames = null;//bookCollectionToStringCollection();

        String name = getNameFromUserInput(nameQuestion, outOfRangeError, allowedBookNames);

        return null;
    }
}

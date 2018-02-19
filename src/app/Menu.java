package app;

import java.util.List;
import java.util.ArrayList;

public class Menu {

    private static final String INDENT = "    ";
    private List<MenuOption> menuOptions;

    public Menu(MenuOption... options) {

        menuOptions = new ArrayList<>();
        for (MenuOption mo : options) {

            menuOptions.add(mo);
        }
    }

    public String toString() {

        String result = "";
        for (MenuOption mo : menuOptions) {

          result += "\n" + Menu.INDENT + mo.toString();
        }
        return result;
    }

    public List<MenuOption> getMenuOptions() {

      return menuOptions;
    }
}

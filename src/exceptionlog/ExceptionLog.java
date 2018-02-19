package exceptionlog;

import java.util.List;
import java.util.ArrayList;

public class ExceptionLog {

    private static List<Exception> exceptions = new ArrayList<>();

    public static void add(Exception e) {

        ExceptionLog.exceptions.add(e);
    }

    public static List<Exception> getExceptions() {

        return ExceptionLog.exceptions;
    }
}

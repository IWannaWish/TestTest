package MainPackage;


import java.util.Scanner;

class Parser {
    private final String input;
    private String a;
    private String b;

    public Parser(String input) {
        this.input = input;
    }

    public void sumDelimiter() {
        try (Scanner scanner = new Scanner(input)) {
            scanner.useDelimiter("\\+");
            a = scanner.next();
            b = scanner.next();
        }
    }

    public void minusDelimiter() {
        try (Scanner scanner = new Scanner(input)) {
            scanner.useDelimiter("-");
            a = scanner.next();
            b = scanner.next();
        }
    }

    public void multiplyDelimiter() {
        try (Scanner scanner = new Scanner(input)) {
            scanner.useDelimiter("\\*");
            a = scanner.next();
            b = scanner.next();
        }
    }

    public void divisionDelimiter() {
        try (Scanner scanner = new Scanner(input)) {
            scanner.useDelimiter("/");
            a = scanner.next();
            b = scanner.next();
        }
    }

    public boolean checkFirstNumber() {//проверяем число ли это
        return a.charAt(0) > 47 && a.charAt(0) < 58;
    }

    public boolean checkSecondNumber() {//проверяем число ли это
        return b.charAt(0) > 47 && b.charAt(0) < 58;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

}

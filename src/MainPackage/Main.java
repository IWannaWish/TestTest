package MainPackage;
import java.util.List;
import java.util.Scanner;


public class Main {
    final static String DOUBLE_OPERAND_EXC = "т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)";
    final static String NOT_MATH_EXC = "т.к. строка не является математической операцией";
    final static String DIFF_NUM_EXC = "т.к. используются одновременно разные системы счисления";
    final static String NEGATIVE_NUM_EXC = "т.к. в римской системе нет отрицательных чисел";
    final static String ILLEGAL_NUM_EXC = "т.к. калькулятор должен принимать на вход числа от 1 до 10 включительно, не более ";

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println(calc(sc.nextLine()));
        }
    }

    public static String calc(String input) {

        input = input.replaceAll(" +", "");
        char[] chars = input.toCharArray();
        checkTwoOperand(chars);
        Parser parser = new Parser(input);
        for (int i = 0; i < input.length(); i++) {
            if (chars[i] == '+') {
                parser.sumDelimiter();
                if (parser.checkFirstNumber() == parser.checkSecondNumber()) {
                    if (parser.checkFirstNumber()) {//Arabian operations
                        checkValues(Integer.parseInt(parser.getA()));
                        checkValues(Integer.parseInt(parser.getB()));
                        return String.valueOf((Integer.parseInt(parser.getA()) + Integer.parseInt(parser.getB())));
                    } else {//Roma Operations
                        checkValues(romanToArabic(parser.getA()));
                        checkValues(romanToArabic(parser.getB()));
                        return arabicToRoman(romanToArabic(parser.getA()) + romanToArabic(parser.getB()));
                    }
                } else throw new DifferentNumberSystemException(DIFF_NUM_EXC);
            }
            if (chars[i] == '-') {
                parser.minusDelimiter();
                if (parser.checkFirstNumber() == parser.checkSecondNumber()) {
                    if (parser.checkFirstNumber()) {
                        checkValues(Integer.parseInt(parser.getA()));
                        checkValues(Integer.parseInt(parser.getB()));
                        return String.valueOf((Integer.parseInt(parser.getA()) - Integer.parseInt(parser.getB())));
                    } else {
                        checkValues(romanToArabic(parser.getA()));
                        checkValues(romanToArabic(parser.getB()));
                        return arabicToRoman(romanToArabic(parser.getA()) - romanToArabic(parser.getB()));
                    }
                } else throw new DifferentNumberSystemException(DIFF_NUM_EXC);
            }
            if (chars[i] == '*') {
                parser.multiplyDelimiter();
                if (parser.checkFirstNumber() == parser.checkSecondNumber()) {
                    if (parser.checkFirstNumber()) {
                        checkValues(Integer.parseInt(parser.getA()));
                        checkValues(Integer.parseInt(parser.getB()));
                        return String.valueOf((Integer.parseInt(parser.getA()) * Integer.parseInt(parser.getB())));
                    } else {
                        checkValues(romanToArabic(parser.getA()));
                        checkValues(romanToArabic(parser.getB()));
                        return arabicToRoman(romanToArabic(parser.getA()) * romanToArabic(parser.getB()));
                    }
                } else throw new DifferentNumberSystemException(DIFF_NUM_EXC);
            }
            if (chars[i] == '/') {
                parser.divisionDelimiter();
                if (parser.checkFirstNumber() == parser.checkSecondNumber()) {
                    if (parser.checkFirstNumber()) {
                        checkValues(Integer.parseInt(parser.getA()));
                        checkValues(Integer.parseInt(parser.getB()));
                        return String.valueOf((Integer.parseInt(parser.getA()) / Integer.parseInt(parser.getB())));
                    } else {
                        checkValues(romanToArabic(parser.getA()));
                        checkValues(romanToArabic(parser.getB()));
                        return arabicToRoman(romanToArabic(parser.getA()) / romanToArabic(parser.getB()));
                    }
                } else throw new DifferentNumberSystemException(DIFF_NUM_EXC);
            }
        }
        throw new NotMathOperationException(NOT_MATH_EXC);
    }

    private static void checkTwoOperand(char[] chars) {
        int count = 0;
        for (char c : chars) {
            if (c == '+' || c == '-' || c == '*' || c == '/') count++;
            if (count > 1) throw new MoreTwoOperandsExceptions(DOUBLE_OPERAND_EXC);
        }
    }
    private static void checkValues(int a) {
        if (!(a > 0 && a < 11)){
            throw new IllegalArgumentException(ILLEGAL_NUM_EXC);
        }
    }
    private static int romanToArabic(String input) {
        String romanNumbers = input.toUpperCase();
        int result = 0;

        List<RomanNumbers> romanNumerals = RomanNumbers.getReverseSortedValues();

        int i = 0;

        while ((romanNumbers.length() > 0) && (i < romanNumerals.size())) {
            RomanNumbers symbol = romanNumerals.get(i);
            if (romanNumbers.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumbers = romanNumbers.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumbers.length() > 0) {
            throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
        }

        return result;
    }


    private static String arabicToRoman(int number) {
        if(number<0) throw new NegativeNumberException(NEGATIVE_NUM_EXC);
        if ( number > 100) {
            throw new IllegalArgumentException(number + " is not in range [0,10]");
        }

        List<RomanNumbers> romanNumbers = RomanNumbers.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumbers.size())) {
            RomanNumbers currentSymbol = romanNumbers.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }
}
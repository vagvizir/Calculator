import java.io.IOException;
import java.util.Scanner;
// Деление на 0
// Ответ римскими
// Исключения

public class Main {
    public static void main(String[] args) throws IOException, NegativeNumberRoman {
        Scanner scanner = new Scanner(System.in);

        String[] numericalExpression = scanner.nextLine().split(" ");
        System.out.println(calc(numericalExpression));
    }
    public static String calc(String[] numericalExpression) throws IOException, NegativeNumberRoman {
        if (numericalExpression.length != 3) {
            System.out.println("Ошибка! Недопустимое количество элементов выражения.");
            throw new IOException();
        }

        String leftOperand = numericalExpression[0];
        String operator = numericalExpression[1];
        String rightOperator = numericalExpression[2];

        if (leftOperand.matches("\\d+") && rightOperator.matches("\\d+")) {
            return "" + ArithmeticOperationsArabic.expressionCalculation(leftOperand, operator, rightOperator);
        } else if (leftOperand.matches("\\d+") ^ rightOperator.matches("\\d+")) {
            System.out.println("Ошибка! Вы ввели не допустимое выражение!");
            throw new IOException();
        }

        return "" + ArithmeticOperationRoman.expressionCount(leftOperand, operator, rightOperator);
    }
}

    class ArithmeticOperationsArabic {

        public static int expressionCalculation(String leftOperand, String operator, String rightOperand) throws IOException {
            int integerLeftOperand = Integer.parseInt(leftOperand);
            int integerRightOperand = Integer.parseInt(rightOperand);

            if (integerLeftOperand > 10 || integerRightOperand > 10) {
                System.out.println("Вы ввели недопустимое число");
                throw new IOException();
            } else if (integerLeftOperand < 1 || integerRightOperand < 1) {
                System.out.println("Вы ввели недопустимое число");
                throw new IOException();
            }

            switch (operator) {
                case "+":
                    return integerLeftOperand + integerRightOperand;
                case "-":
                    return integerLeftOperand - integerRightOperand;
                case "*":
                    return integerLeftOperand * integerRightOperand;
                case "/":
                    return integerLeftOperand / integerRightOperand;
                default:
                    System.out.println("Калькулятор не поддерживает данный оператор");
                    throw new IOException();
            }
        }
    }

    class ArithmeticOperationRoman {
        static int integerLeftOperand = 0;
        static int integerRightOperator = 0;

        public static String expressionCount(String leftOperand, String operator, String rightOperator) throws IOException,
                NegativeNumberRoman {

            for(int i = 0; i < 10; i++) {

                if (RomeNumbers.values()[i].name().equalsIgnoreCase(leftOperand)) {
                    integerLeftOperand = RomeNumbers.values()[i].getArabicNumber();
                    break;
                }

                if (i == 9) {
                    System.out.println("Вы ввели не допустимое число");
                    throw new IOException();
                }

            }

            for(int i = 0; i < 10; i++) {

                if (RomeNumbers.values()[i].name().equalsIgnoreCase(rightOperator)) {
                    integerRightOperator = RomeNumbers.values()[i].getArabicNumber();
                    break;
                }

                if (i == 9) {
                    System.out.println("Вы ввели не допустимое число");
                    throw new IOException();
                }

            }



            int numberArabic = ArithmeticOperationsArabic.expressionCalculation("" + integerLeftOperand, operator,
                    "" + integerRightOperator);
            String romeNumberSmall = "";
            String nameRomeNumber = "";

            if (numberArabic <= 0) {
                throw new NegativeNumberRoman("Ошибка! Значение выражения не может быть отрицательным.");
            } else if (numberArabic / 100 == 1) {
                return RomeNumbers.C.name();
            } else if (numberArabic / 10 < 1) {

                for (RomeNumbers numbers : RomeNumbers.values()) {
                    if (numberArabic == numbers.getArabicNumber()) {
                        return romeNumberSmall = numbers.name();
                    }
                }

            } else {
                int numberLessThanTen = 0;
                for (NumbersRomeSix number : NumbersRomeSix.values()) {
                    if (numberArabic / number.getNumberArabic() >= 1) {
                        numberLessThanTen = numberArabic % number.getNumberArabic();
                        nameRomeNumber = number.name();
                        break;
                    }
                }
                if (numberLessThanTen % 10 > 0) {
                    for (RomeNumbers num : RomeNumbers.values()) {
                        if (numberLessThanTen == num.getArabicNumber()) {  // Находим число до 10
                            return nameRomeNumber + num.name();
                        }
                    }
                }
            }
            return nameRomeNumber;
        }
    }


import java.util.InputMismatchException;
import java.util.Scanner;

class Validator {
    public static int getValidIntInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: Введите целое число.");
                scanner.next(); // Очищаем некорректный ввод
            }
        }
    }

    public static int getValidChoice(Scanner scanner) {
        while (true) {
            int choice = getValidIntInput(scanner);
            if (choice == 1 || choice == 2 || choice == 3) {
                return choice;
            } else {
                System.out.println("Ошибка: Введите число 1, 2 или 3.");
            }
        }
    }
}

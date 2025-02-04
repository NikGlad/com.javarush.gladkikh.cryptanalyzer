import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

        // Логика меню.
        // 1. Шифрование с ключом
        // 2. Расшифровка с ключом
        // 3. Выйти

         /*
         Не обязательно
         4. Brute force
         5. Статистический анализ
         */


public class AppCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1 - Зашифровать");
            System.out.println("2 - Расшифровать");
            System.out.println("3 - Выйти");
            System.out.print("Ваш выбор: ");

            int choice = Validator.getValidChoice(scanner);

            if (choice == 3) {
                System.out.println("Выход из программы.");
                break;
            }

            System.out.print("Введите ключ: ");
            int shift = Validator.getValidIntInput(scanner) % Constants.ALPHABET.length();

            String inputFilePath;
            String outputFilePath;

            if (choice == 1) {
                inputFilePath = "input.txt";
                outputFilePath = "encrypted.txt";
            } else {
                inputFilePath = "encrypted.txt";
                outputFilePath = "decrypted.txt";
            }

            try {
                String text = new String(Files.readAllBytes(Paths.get(inputFilePath)), StandardCharsets.UTF_8);
                String resultText = (choice == 1) ? Encryptor.encrypt(text, shift) : Decryptor.decrypt(text, shift);

                Files.write(Paths.get(outputFilePath), resultText.getBytes(StandardCharsets.UTF_8));
                System.out.println((choice == 1 ? "Текст зашифрован" : "Текст расшифрован") + " и записан в " + outputFilePath);
            } catch (IOException e) {
                System.err.println("Ошибка при работе с файлами: " + e.getMessage());
            }
        }
    }
}
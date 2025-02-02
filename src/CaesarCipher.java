import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

        // Логика меню. не завершать программу, пока не нажмут выход
        // 1. Шифрование с ключом
        // 2. Расшифровка с ключом
        // 3. Выйти

         /*
         Не обязательно
         4. Brute force
         5. Статистический анализ
         */


public class CaesarCipher {
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
//    public class Constants {
//        private static final String rus = "ЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ";
//        private static final String eng = "QWERTYUIOPASDFGHJKLZXCVBNM";
//        private static final String cypher = "0123456789";
//        private static final String z = "~!@#$%^&*()_+-={}[]|:'\"";
//        public static final String ALPHABET = rus + eng + rus.toLowerCase()+ eng.toLowerCase() + cypher + z;
//    }
    private static final int ALPHABET_SIZE = ALPHABET.length();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1 - Зашифровать");
            System.out.println("2 - Расшифровать");
            System.out.println("3 - Выйти");
            System.out.print("Ваш выбор: ");
            int choice = scanner.nextInt();

            if (choice == 3) {
                System.out.println("Выход из программы.");
                break;
            }

            System.out.print("Введите ключ: ");
            int shift = scanner.nextInt() % ALPHABET_SIZE;

            String inputFilePath;
            String outputFilePath;

            if (choice == 1) {
                inputFilePath = "input.txt";
                outputFilePath = "encrypted.txt";
            } else if (choice == 2) {
                inputFilePath = "encrypted.txt";
                outputFilePath = "decrypted.txt";
            } else {
                System.out.println("Некорректный выбор, попробуйте снова.");
                continue;
            }

            try {
                String text = new String(Files.readAllBytes(Paths.get(inputFilePath)), StandardCharsets.UTF_8);
                String resultText = (choice == 1) ? encrypt(text, shift) : decrypt(text, shift);

                Files.write(Paths.get(outputFilePath), resultText.getBytes(StandardCharsets.UTF_8));
                System.out.println((choice == 1 ? "Текст зашифрован" : "Текст расшифрован") + " и записан в " + outputFilePath);
            } catch (IOException e) {
                System.err.println("Ошибка при работе с файлами: " + e.getMessage());
            }
        }
    }

    private static String encrypt(String text, int shift) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : text.toLowerCase().toCharArray()) {
            int index = ALPHABET.indexOf(c);
            if (index != -1) {
                int newIndex = (index + shift) % ALPHABET_SIZE;
                encrypted.append(ALPHABET.charAt(newIndex));
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }

    private static String decrypt(String text, int shift) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : text.toLowerCase().toCharArray()) {
            int index = ALPHABET.indexOf(c);
            if (index != -1) {
                int newIndex = (index - shift + ALPHABET_SIZE) % ALPHABET_SIZE;
                decrypted.append(ALPHABET.charAt(newIndex));
            } else {
                decrypted.append(c);
            }
        }
        return decrypted.toString();
    }
}

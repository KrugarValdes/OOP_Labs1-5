import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/((16[0-9]{2}|17[0-9]{2}|18[0-9]{2}|19[0-9]{2}|20[0-9]{2}|9999))$";
        Scanner scanner = new Scanner(System.in);
        String input;
        String choice;

        do {
            System.out.println("Введите дату в формате dd/mm/yyyy:");
            input = scanner.nextLine();

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            if (matcher.matches()) {
                int day = Integer.parseInt(matcher.group(1));
                int month = Integer.parseInt(matcher.group(2));
                int year = Integer.parseInt(matcher.group(3));

                if (month == 2) {
                    // Проверка на високосный год
                    if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) {
                        if (day > 29) {
                            System.out.println("Некорректная дата");
                        }
                    } else {
                        if (day > 28) {
                            System.out.println("Некорректная дата");
                        }
                    }
                } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                    // Проверка на месяцы с 30 днями
                    if (day > 30) {
                        System.out.println("Некорректная дата");
                    }
                } else {
                    System.out.println("Дата в формате dd/mm/yyyy");
                }
            } else {
                System.out.println("Некорректная дата");
            }

            System.out.println("Хотите продолжить? (ok/exit)");
            choice = scanner.nextLine();
        } while (choice.equalsIgnoreCase("ok"));
    }
}

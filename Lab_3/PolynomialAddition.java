import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class PolynomialAddition {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите степень первого многочлена:");
        int degree1 = scanner.nextInt();
        HashMap<Integer, Integer> poly1 = getPolynomial(scanner, degree1);

        System.out.println("Введите степень второго многочлена:");
        int degree2 = scanner.nextInt();
        HashMap<Integer, Integer> poly2 = getPolynomial(scanner, degree2);

        HashMap<Integer, Integer> result = addPolynomials(poly1, poly2);

        System.out.println("Результат: " + simplifyPolynomial(result));
    }

    public static HashMap<Integer, Integer> getPolynomial(Scanner scanner, int degree) {
        HashMap<Integer, Integer> polynomial = new HashMap<>();
        for (int i = 0; i <= degree; i++) {
            System.out.println("Введите коэффициент для x^" + i + ":");
            int coefficient = scanner.nextInt();
            polynomial.put(i, coefficient);
        }
        return polynomial;
    }

    public static HashMap<Integer, Integer> addPolynomials(HashMap<Integer, Integer> poly1, HashMap<Integer, Integer> poly2) {
        HashMap<Integer, Integer> result = new HashMap<>();

        for (Integer key : poly1.keySet()) {
            result.put(key, poly1.get(key));
        }

        for (Integer key : poly2.keySet()) {
            result.put(key, result.getOrDefault(key, 0) + poly2.get(key));
        }

        // Удалить члены с нулевым коэффициентом
        Iterator<Integer> it = result.keySet().iterator();
        while (it.hasNext()) {
            Integer key = it.next();
            if (result.get(key) == 0) {
                it.remove();
            }
        }

        return result;
    }

    public static String simplifyPolynomial(HashMap<Integer, Integer> polynomial) {
        StringBuilder simplified = new StringBuilder();
        for (Integer key : polynomial.keySet()) {
            simplified.append(polynomial.get(key));
            if (key > 0) {
                simplified.append("*x^").append(key);
            }
            simplified.append(" + ");
        }
        return simplified.substring(0, simplified.length() - 3);  // Удалить последний "+"
    }
}
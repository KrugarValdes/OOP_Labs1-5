import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SonnetClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8080);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        String sonnet;
        while ((sonnet = in.readLine()) != null) {
            System.out.println(sonnet);
            System.out.println("Подтвердите получение сонета, введя 'ok', или введите 'exit' для выхода.");
            String userInput = stdIn.readLine();
            out.println(userInput); // отправляем подтверждение на сервер
            if (userInput.equalsIgnoreCase("exit")) {
                break;
            } else while (!userInput.equalsIgnoreCase("ok")) {
                System.out.println("Пожалуйста, подтвердите получение сонета, введя 'ok', или введите 'exit' для выхода.");
                userInput = stdIn.readLine();
                out.println(userInput); // отправляем подтверждение на сервер
                if (userInput.equalsIgnoreCase("exit")) {
                    break;
                }
            }
        }

        out.close();
        in.close();
        socket.close();
    }
}
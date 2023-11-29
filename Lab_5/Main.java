import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Сервер запущен и ожидает подключения...");

        ArrayList<String> sonnets = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader("sonnets.txt"));
        String sonnet;
        while ((sonnet = in.readLine()) != null) {
            sonnets.add(sonnet);
        }
        in.close();

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Клиент подключился!");

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            int randomSonnet = new Random().nextInt(sonnets.size());
            out.println(sonnets.get(randomSonnet));

            String userInput;
            while ((userInput = clientIn.readLine()) != null) {
                if (userInput.equalsIgnoreCase("ok")) {
                    randomSonnet = new Random().nextInt(sonnets.size());
                    out.println(sonnets.get(randomSonnet));
                } else if (userInput.equalsIgnoreCase("exit")) {
                    clientSocket.close();
                    break;
                }
            }

            out.close();
            clientSocket.close();
        }
    }
}
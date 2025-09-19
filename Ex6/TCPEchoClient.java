import java.io.*;
import java.net.*;

public class TCPEchoClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;
        try (Socket socket = new Socket(host, port);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String msg;
            while (true) {
                System.out.print("Enter message: ");
                msg = userInput.readLine();
                if (msg.equalsIgnoreCase("bye")) break;
                out.println(msg);
                System.out.println("Server says: " + in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

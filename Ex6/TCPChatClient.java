import java.io.*;
import java.net.*;

public class TCPChatClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 6000;
        try (Socket socket = new Socket(host, port);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String msg;
            while (true) {
                System.out.print("You: ");
                msg = userInput.readLine();
                out.println(msg);
                if (msg.equalsIgnoreCase("bye")) break;

                String reply = in.readLine();
                if (reply == null || reply.equalsIgnoreCase("bye")) break;
                System.out.println("Server: " + reply);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

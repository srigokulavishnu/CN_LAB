import java.io.*;
import java.net.*;

public class TCPChatServer {
    public static void main(String[] args) {
        int port = 6000;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("TCP Chat Server started on port " + port);
            Socket clientSocket = serverSocket.accept();

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            String msg;
            while (true) {
                msg = in.readLine();
                if (msg == null || msg.equalsIgnoreCase("bye")) break;
                System.out.println("Client: " + msg);

                System.out.print("You: ");
                String reply = keyboard.readLine();
                out.println(reply);
                if (reply.equalsIgnoreCase("bye")) break;
            }
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

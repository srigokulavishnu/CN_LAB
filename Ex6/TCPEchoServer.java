import java.io.*;
import java.net.*;

public class TCPEchoServer {
    public static void main(String[] args) {
        int port = 5000;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("TCP Echo Server started on port " + port);
            Socket clientSocket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String msg;
            while ((msg = in.readLine()) != null) {
                System.out.println("Received: " + msg);
                out.println("Echo: " + msg);
            }
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

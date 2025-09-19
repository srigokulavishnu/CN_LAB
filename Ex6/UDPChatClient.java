import java.net.*;
import java.io.*;

public class UDPChatClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8000;
        try (DatagramSocket socket = new DatagramSocket();
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            byte[] buffer = new byte[1024];
            InetAddress serverAddr = InetAddress.getByName(host);

            while (true) {
                System.out.print("You: ");
                String msg = userInput.readLine();
                DatagramPacket request = new DatagramPacket(msg.getBytes(), msg.length(), serverAddr, port);
                socket.send(request);
                if (msg.equalsIgnoreCase("bye")) break;

                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                socket.receive(reply);
                String response = new String(reply.getData(), 0, reply.getLength());
                System.out.println("Server: " + response);
                if (response.equalsIgnoreCase("bye")) break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

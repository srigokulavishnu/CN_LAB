import java.net.*;
import java.io.*;

public class UDPEchoClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 7000;
        try (DatagramSocket socket = new DatagramSocket();
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            byte[] buffer = new byte[1024];
            String msg;

            while (true) {
                System.out.print("Enter message: ");
                msg = userInput.readLine();
                if (msg.equalsIgnoreCase("bye")) break;

                DatagramPacket request = new DatagramPacket(msg.getBytes(), msg.length(),
                        InetAddress.getByName(host), port);
                socket.send(request);

                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                socket.receive(reply);
                System.out.println("Server says: " + new String(reply.getData(), 0, reply.getLength()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

import java.net.*;
import java.io.*;

public class UDPChatServer {
    public static void main(String[] args) {
        int port = 8000;
        try (DatagramSocket socket = new DatagramSocket(port);
             BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))) {

            byte[] buffer = new byte[1024];
            System.out.println("UDP Chat Server started on port " + port);

            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);
                String msg = new String(request.getData(), 0, request.getLength());
                System.out.println("Client: " + msg);
                if (msg.equalsIgnoreCase("bye")) break;

                System.out.print("You: ");
                String reply = keyboard.readLine();
                DatagramPacket response = new DatagramPacket(reply.getBytes(), reply.length(),
                        request.getAddress(), request.getPort());
                socket.send(response);
                if (reply.equalsIgnoreCase("bye")) break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

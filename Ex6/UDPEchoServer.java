import java.net.*;

public class UDPEchoServer {
    public static void main(String[] args) {
        int port = 7000;
        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("UDP Echo Server started on port " + port);
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                String msg = new String(request.getData(), 0, request.getLength());
                System.out.println("Received: " + msg);

                String response = "Echo: " + msg;
                DatagramPacket reply = new DatagramPacket(response.getBytes(), response.length(),
                        request.getAddress(), request.getPort());
                socket.send(reply);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

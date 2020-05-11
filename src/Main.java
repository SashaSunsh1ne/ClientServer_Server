import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Main {

    private static ServerSocket server;
    private static ServerHandler serverHandler;
    public static List<ClientHandler> handlers = new LinkedList<>();

    public static void main(String[] args) {
        start();
        handle();
        end();
    }

    private static void start() {
        try {
            server = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handle() {
        serverHandler = new ServerHandler(server);
        serverHandler.start();
    }

    private static void end() {
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public static void sendPacket(Socket receiver, Packet packet) {
        try {
            DataOutputStream dos = new DataOutputStream(receiver.getOutputStream());
            packet.write(dos);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

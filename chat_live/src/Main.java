import sockets.ClientSocket;
import sockets.ServerApp;


public class Main {
    public static void main(String[] args) {
        Thread clientThread = new Thread(() -> {
            ServerApp reverseServer = new ServerApp();
            reverseServer.start();

        });

        clientThread.start();

        try {
            Thread.sleep(1000); // Give some time for the client to start
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ClientSocket reverseClient = new ClientSocket("localhost"); // Changed "lucas" to "localhost"
        reverseClient.start();
    }
}
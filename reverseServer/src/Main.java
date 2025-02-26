import sockets.ReverseClient;
import sockets.ReverseServer;

public class Main {
    public static void main(String[] args) {
        Thread clientThread = new Thread(() -> {
            ReverseServer reverseServer = new ReverseServer(6868);
            reverseServer.start();

        });

        clientThread.start();

        try {
            Thread.sleep(1000); // Give some time for the client to start
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ReverseClient reverseClient = new ReverseClient("localhost"); // Changed "lucas" to "localhost"
        reverseClient.start(6868);
    }
}
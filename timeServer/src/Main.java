import socket.TimeClient;
import socket.TimeServer;

public class Main {
    public static void main(String[] args) {
        // Start the server in a new thread to allow the client to run concurrently
        Thread serverThread = new Thread(() -> {
            TimeServer timeServer = new TimeServer();
            timeServer.start(6868);
        });
        serverThread.start();

        // Give the server a brief moment to start before client connects
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Start the client
        TimeClient timeClient = new TimeClient("localhost");  // Fix: Use "localhost"
        timeClient.start(6868);
    }
}

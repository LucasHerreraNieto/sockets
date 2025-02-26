package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class TimeClient {
    private String hostname;

    public TimeClient(String hostname) {
        this.hostname = hostname;
    }

    public void start(int port) {
        try (Socket socket = new Socket(hostname, port);
             InputStream input = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {

            String time = reader.readLine();
            System.out.println("Server Time: " + time);

        } catch (UnknownHostException e) {
            System.out.println("Error: Unknown host " + hostname);
        } catch (IOException e) {
            System.out.println("Error: Unable to connect to " + hostname + " on port " + port);
        }
    }
}

package sockets;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ReverseClient {
    String hostname;

    public ReverseClient(String hostname) {
        this.hostname = hostname;
    }
    public void start(int aPort){
        try (Socket socket = new Socket(this.hostname,aPort)) {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output,true);

            Console console = System.console();
            String text;

            do {
                text = console.readLine("Enter text: ");

                writer.println(text);

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                String time = reader.readLine();
                System.out.println(time);
            }while (!text.equals("bye"));
        }catch (UnknownHostException e){
            System.out.println("Server not found: " +e.getMessage());
        }catch (IOException e){
            System.out.println("I/O error: " +e.getMessage());
        }
    }
}

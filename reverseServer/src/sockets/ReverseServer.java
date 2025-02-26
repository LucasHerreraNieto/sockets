package sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ReverseServer {
    int port;

    public ReverseServer(int port) {
        this.port = port;
    }

    public  void start(){
    try (ServerSocket serverSocket = new ServerSocket(this.port)) {
        System.out.println("Server is listening on port: " + this.port);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("New client connected");

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output,true);
            String text;

            do {
                text = reader.readLine();
                String reverseText = new StringBuilder(text).reverse().toString();
                writer.println("Server: " + reverseText);
            }while (!text.equals("bye"));
            socket.close();
        }
    }catch (IOException e){
        System.out.println("Server exception: " +e.getMessage());
        e.printStackTrace();
    }
    }
}

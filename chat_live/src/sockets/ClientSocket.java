package sockets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class ClientSocket extends JFrame{
    private JPanel jPanel;
    private JButton connectButton;
    private JTextField portText;
    private JTextArea mensajeArea;
    private JTextField mensajeText;
    private JButton sendButton;
    String hostname;
    private BufferedWriter writer;
    private Socket socket;


    public ClientSocket(String hostname){
        this.hostname = hostname;
        jPanel = new JPanel();
        connectButton = new JButton("start");
        portText = new JTextField(10);
        mensajeArea = new JTextArea(10, 30);
        mensajeText = new JTextField(20);
        sendButton = new JButton("Send");

        // Set Layout
        jPanel.setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Port:"));
        topPanel.add(portText);
        topPanel.add(connectButton);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(mensajeText);
        bottomPanel.add(sendButton);

        jPanel.add(topPanel, BorderLayout.NORTH);
        jPanel.add(new JScrollPane(mensajeArea), BorderLayout.CENTER);
        jPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Configure JFrame
        this.setTitle("Server Socket");
        this.setSize(400, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(jPanel);
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int port = Integer.parseInt(portText.getText());
                    connect(port);
                }catch (NumberFormatException er){
                    JOptionPane.showMessageDialog(null, "Invalid port number");
                }
            }
        });
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarMensaje();
            }
        });
    }

    public void start() {
        this.setVisible(true);

    }

    public void connect(int puerto){

        try {
            socket = new Socket(hostname,puerto);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            mensajeArea.append("Connected to server on port " + puerto + "\n");

        }catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void enviarMensaje(){
        try {
            String mensaje = mensajeText.getText();
            if (!mensaje.isEmpty()){
                writer.write(mensaje+"\n");
                writer.flush();
                mensajeArea.append("Me: " + mensaje + "\n");
                mensajeText.setText("");
            }
        }catch (IOException e){
            JOptionPane.showMessageDialog(this, "Error sending message: " + e.getMessage());
        }
    }

}

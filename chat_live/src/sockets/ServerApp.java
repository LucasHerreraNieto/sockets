package sockets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp extends JFrame {
    private JPanel jPanel;
    private JButton connectButton;
    private JTextField portText;
    private JTextArea mensajeArea;


    public ServerApp() {
        // Initialize components
        jPanel = new JPanel();
        connectButton = new JButton("start");
        portText = new JTextField(10);
        mensajeArea = new JTextArea(10, 30);

        // Set Layout
        jPanel.setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Port:"));
        topPanel.add(portText);
        topPanel.add(connectButton);

        JPanel bottomPanel = new JPanel();

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
                try {
                    int port = Integer.parseInt(portText.getText());
                    new Thread(() -> serverStart(port)).start();
                    connectButton.setEnabled(false);
                    mensajeArea.append("Server connected on port: " + port + "\n");
                } catch (NumberFormatException er) {
                    JOptionPane.showMessageDialog(null, "Invalid port number");
                }
            }
        });
    }

    public void start() {
        this.setVisible(true);

    }

    public void serverStart(int aPort) {
        try (ServerSocket serverSocket = new ServerSocket(aPort)) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente connected: " + socket.getPort());
                new Thread(() -> manejoCliente(socket)).start();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void manejoCliente(Socket unSocket) {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(unSocket.getInputStream(), "UTF-8"));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(unSocket.getOutputStream(), "UTF-8"))
        ) {
            String mensaje;
            
        }
    }
}
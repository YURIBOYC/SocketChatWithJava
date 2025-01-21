import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class User1 extends Frame implements Runnable, ActionListener {
    TextField textField;
    TextArea textArea;
    Button send;

    ServerSocket serverSocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    Thread chat;

    User1() {
        textField = new TextField(40);
        textArea = new TextArea(20, 50);
        send = new Button("Send message");
        send.addActionListener(this);

        add(textField);
        add(send);
        add(textArea);

        setLayout(new FlowLayout());
        setSize(600, 400);
        setVisible(true);

        try {
            serverSocket = new ServerSocket(12000);
            textArea.append("Waiting for connection...\n");
            socket = serverSocket.accept();
            textArea.append("Connected to a client.\n");
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            chat = new Thread(this);
            chat.start();
        } catch (IOException e) {
            System.out.println(e);;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = textField.getText();
        textArea.append("Abishek: " + msg + "\n");
        textField.setText("");
        try {
            dataOutputStream.writeUTF(msg);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String msg = dataInputStream.readUTF();
                textArea.append("Deephak: " + msg + "\n");
            } catch (IOException e) {
                System.out.println(e);;
                break;
            }
        }
    }

    public static void main(String[] args) {
        new User1();
    }
}

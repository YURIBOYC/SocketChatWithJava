import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class User2 extends Frame implements Runnable, ActionListener {
    TextField textField;
    TextArea textArea;
    Button send;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    Thread chat;

    User2() {
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
            socket = new Socket("localhost", 12000);
            textArea.append("Connected to the server.\n");
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            chat = new Thread(this);
            chat.start();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = textField.getText();
        textArea.append("Deephak: " + msg + "\n");
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
                textArea.append("Abishek: " + msg + "\n");
            } catch (IOException e) {
                System.out.println(e);
                break;
            }
        }
    }

    public static void main(String[] args) {
        new User2();
    }
}

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User1 extends Frame implements ActionListener {
    TextField textField;
    TextArea textArea;
    Button send;
    User1(){
        textField = new TextField();
        textArea = new TextArea();
        send = new Button("Send message");
        send.addActionListener(this);
        add(textField);
        add(send);
        add(textArea);
        setSize(500,700);
        setLayout(new FlowLayout());
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {

    }
}
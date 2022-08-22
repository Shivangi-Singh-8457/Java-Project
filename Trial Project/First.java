import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
public class First extends JFrame implements ActionListener, Serializable{
    JButton b;
    String msg=new String();
    First()
    {
        setSize(200,200);
        b=new JButton("Registration");
        b.setBounds(70,70,60,60);
        add(b);
        b.addActionListener(this);
    }
    synchronized public void actionPerformed(ActionEvent e)
    {
        msg=e.getActionCommand();
        setVisible(false);
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import java.io.Serializable;
public class Second extends JFrame implements ActionListener, Serializable{
    String name, email, password;
    String msg=new String();
    java.sql.Date dob;
    int contact_no;
    JTextField tname, temail, tmob;
    JPasswordField tpwd;
    JFormattedTextField tdob;
    DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
    JLabel l=new JLabel();
    Second(){
        setSize(500,500);
        tname=new JTextField(15);
        tdob=new JFormattedTextField(df); 
        tdob.setColumns(15);
        tdob.setValue(new java.util.Date());
        tmob=new JTextField(15);
        temail=new JTextField(15);
        tpwd=new JPasswordField(15);
        JLabel lname=new JLabel("Name:");
        JLabel ldob=new JLabel("DOB:");
        JLabel lmob=new JLabel("Contact No:");
        JLabel lemail=new JLabel("Email Id:");
        JLabel lpwd=new JLabel("Password:");
        JButton b=new JButton("Submit");
        lname.setLabelFor(tname);
        ldob.setLabelFor(tdob);
        lmob.setLabelFor(tmob);
        lemail.setLabelFor(temail);
        lpwd.setLabelFor(tpwd);
        b.addActionListener(this);
        setLayout(new FlowLayout());
        add(lname);
        add(tname);
        add(ldob);
        add(tdob);
        add(lmob);
        add(tmob);
        add(lemail);
        add(temail);
        add(lpwd);
        add(tpwd);
        add(b);
        add(l);
    }
    synchronized public void actionPerformed(ActionEvent evt){
        name=tname.getText();
        
        try{
        dob=new java.sql.Date(df.parse(tdob.getText()).getTime());
        contact_no=Integer.parseInt(tmob.getText());
        }catch(Exception e){}
        email=temail.getText();
        password=new String(tpwd.getPassword());
        msg=evt.getActionCommand();
        setVisible(false);
    }
}

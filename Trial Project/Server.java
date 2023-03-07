import java.io.*;  
import java.net.*;
import javax.swing.*;
import java.sql.*;
class ServiceManager extends Thread{
    Socket s;
    OutputStream os;
    InputStream is;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    DataOutputStream dos;
    DataInputStream dis;
    String msg;

    public ServiceManager(Socket s)
    {this.s=s;}
    
    synchronized public void startFirst()
    {
        First first=new First();
        try{
            os=s.getOutputStream();
            oos=new ObjectOutputStream(os);
            oos.writeObject(first);
            is=s.getInputStream();
            dis=new DataInputStream(is);
            msg=(String)dis.readUTF();
            if(msg.equals("Registration"))
            {
                startSecond();
            }
        }catch(Exception e){}
    }
    
    synchronized public void startSecond()
    {
        Second second=new Second();
        try{
            os=s.getOutputStream();
            oos=new ObjectOutputStream(os);
            oos.writeObject(second);
        
            is=s.getInputStream();
            ois=new ObjectInputStream(is);
            SecondData seconddata=(SecondData)ois.readObject();
            
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctrial","root","password");
            String sql="insert into registration(name,dob,contact_no,email,password) values(?,?,?,?,?)";
            PreparedStatement pstmt=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,seconddata.name);
            pstmt.setDate(2,seconddata.dob);
            pstmt.setInt(3,seconddata.contact_no);
            pstmt.setString(4,seconddata.email);
            pstmt.setString(5,seconddata.password);
            pstmt.executeUpdate();
        }catch(Exception e){System.out.println(e);}
    }

    public void run()
    {
        startFirst();
    }
}

public class Server extends Thread{
    public static void main(String[] args){  
    try{  
        ServerSocket ss=new ServerSocket(6666);
        System.out.println("Server is running...");
        int i=1;
        ServiceManager SM;
        while(true){  
            Socket s=ss.accept();
            System.out.println("Client "+(i++)+" is connected");
            SM=new ServiceManager(s);
            new Thread(SM).start();
        }
    }
    catch(Exception e){System.out.println(e);} 
    } 
}  
  

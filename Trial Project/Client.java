import java.io.*;  
import java.net.*;  
public class Client {  
public static void main(String[] args) {  
try{      
    Socket s=new Socket("localhost",6666);
    OutputStream os;
    InputStream is;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    DataOutputStream dos;
    DataInputStream dis;
    
    is=s.getInputStream();    
    ois=new ObjectInputStream(is);
    First first=(First)ois.readObject();
    first.setVisible(true);
    while(true)
    {
        if((first.msg).equals("Registration"))
        {   
            try{
                os=s.getOutputStream();
                dos=new DataOutputStream(os);
                dos.writeUTF("Registration");
                dos.flush();
                break;
            }catch(Exception e){}
        }
    }

    is=s.getInputStream();    
    ois=new ObjectInputStream(is);
    Second second=(Second)ois.readObject();
    second.setVisible(true);
    while(true)
    {
        if((second.msg).equals("Submit"))
        { try{
            SecondData seconddata=new SecondData(second.name,second.email,second.password,second.dob,second.contact_no);  
            os=s.getOutputStream();
            oos=new ObjectOutputStream(os);
            oos.writeObject(seconddata);
            break;
            }catch(Exception e){}
        }
    }
    dos.close();
    ois.close();
    oos.close();
    s.close();  
}catch(Exception e){System.out.println(e);}  
}  
}  
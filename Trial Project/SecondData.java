import java.io.Serializable;
public class SecondData implements Serializable
{
    String name, email, password; int contact_no;
    java.sql.Date dob;
    public SecondData(String name, String email, String password, java.sql.Date dob, int contact_no )
    {
        this.name=name;
        this.email=email;
        this.password=password;
        this.dob=dob;
        this.contact_no=contact_no;
    }
}
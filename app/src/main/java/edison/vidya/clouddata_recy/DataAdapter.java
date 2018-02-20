package edison.vidya.clouddata_recy;

/**
 * Created by Deepak Rao J on 2/7/2018.
 */
import java.util.ArrayList;


public class DataAdapter {

    int Id;
    String name;
    String phone_number;
    String subject;


    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getId() {

        return Id;
    }

    public void setId(int Id1) {

        this.Id = Id1;
    }


    public String getPhone_number() {

        return phone_number;
    }

    public void setPhone_number(String phone_number1) {

        this.phone_number = phone_number1;
    }

    public String getSubject() {

        return subject;
    }

    public void setSubject(String subject1) {

        this.subject = subject1;
    }

}
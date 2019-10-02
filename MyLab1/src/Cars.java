/**
 * Created by t00036478 on 02/02/2018.
 */
import java.util.Date;

public class Cars {
    private int id = 0;
    private String  name = null;
    private String  type = null;
    private String  discription = null;

    public Cars(){
        setName("");
        setType("");
        setDiscription("");
    }
    public Cars(String n, String t, String d){
        setName(n);
        setType(t);
        setDiscription(d);
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setDiscription(String discription) {
        this.discription = discription;
    }
    public String getDiscription() {
        return discription;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
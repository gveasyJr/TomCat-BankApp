
public class Checking extends Account {

    public Checking (Double balance, String name){
        super(balance, name);
        type = "Checkings Account";
    }

    public String getType(){
        return type;
    }
    
}

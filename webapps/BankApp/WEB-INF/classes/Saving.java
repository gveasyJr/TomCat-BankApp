
public class Saving extends Account {

    public Saving (Double balance, String name){
        super(balance, name);
        type = "Savings Account";
    }

    public String getType(){
        return type;
    }
    
}

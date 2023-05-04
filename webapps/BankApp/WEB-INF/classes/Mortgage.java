
public class Mortgage extends Account {

    public Mortgage (Double balance, String name){
        super(balance, name);
        type = "Mortgages Account";
    }

    public String getType(){
        return type;
    }
}

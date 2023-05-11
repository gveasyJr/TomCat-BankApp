import java.io.Serializable;

public class History implements Serializable{
    String action;
    double amount;
    String name;
    private static final long serialVersionUID = 1L;


    public History(String name, String action, double amount){
        this.action = action;
        this.amount = amount;
        this.name = name;
    }

    public String printHistory(){
        return name + " " + action + ": $" + amount;
    }
}

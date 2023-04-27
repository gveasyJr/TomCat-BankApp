public class History {
    String action;
    double amount;


    public History(String action, double amount){
        this.action = action;
        this.amount = amount;
    }

    public String printHistory(){
        return action + ": $" + amount;
    }
}

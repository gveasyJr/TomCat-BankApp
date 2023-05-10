public class History {
    String action;
    double amount;
    String name;


    public History(String name, String action, double amount){
        this.action = action;
        this.amount = amount;
        this.name = name;
    }

    public String printHistory(){
        return name + " " + action + ": $" + amount;
    }
}

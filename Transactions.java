import java.io.Serializable;

public class Transactions implements Serializable {
    double amount;
    String type;

    Transactions(double amount, String type){
        this.amount = amount;
        this.type=type;
    }
}

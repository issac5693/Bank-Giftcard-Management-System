public class Giftcard {
    int pin;
    int customer_ID;
    int ID;
    int balance;
    String status="active"; 
    String block= "unblocked";
    String type= "";
    int reward_points= 0;
    Giftcard(int ID, int pin, int balance){
        this.ID= ID;
        this.pin= pin;
        this.balance= balance;
    }
    public void top_up(int amount){
        balance+=amount;
    }
    public int get_balance(){
        return balance;
    }
}

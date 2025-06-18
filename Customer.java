import java.util.*;

public class Customer {
    static Scanner scan= new Scanner(System.in);
    int account_balance;
    int customer_ID;
    ArrayList<Giftcard> giftcards = new ArrayList<>();

    Customer(int customer_ID, int account_balance) {
        this.customer_ID = customer_ID;
        this.account_balance = account_balance;
    }

    public void create_giftcard() {
        while (true) {
            System.out.println("\nCreate you giftcard\n*******\n");
            System.out.print("Enter a unique giftcard ID: ");
            int giftcard_id = scan.nextInt();
            System.out.print("Enter a PIN: ");
            int pin = scan.nextInt();
            System.out.print("Enter a starting balance: ");
            int balance = scan.nextInt();
            for (int i = 0; i < giftcards.size(); i++) {
                if (giftcards.get(i).ID == giftcard_id) {
                    System.out.println("Sorry giftcard ID already exists, please try again");
                    continue;
                }

            }
            if (balance > account_balance) {
                System.out.println("Sorry low balance");
                return;
            }
            account_balance -= balance;
            giftcards.add(new Giftcard(giftcard_id, pin, balance));

            System.out.println("\nYour giftcard has been created");
            break;
        }
    }

    public void access_giftcard() {
        
        while (true) {
            if(giftcards.size()==0){
                System.out.println("\nSorry there are no giftcards");
                return;
            }
            System.out.println("\nEnter you giftcard ID");
            int giftcard_id= scan.nextInt();
            System.out.println("Enter pin");
            int pin= scan.nextInt();
            
            //getting index
            int index= Integer.MIN_VALUE;
            for(int i=0; i<giftcards.size();i++){
                if(giftcards.get(i).ID==giftcard_id){
                    
                    if(pin==giftcards.get(i).pin){
                        index=i;
                        access_giftcard_menu(index);
                        return;
                    }
                }

            }
            if(index==Integer.MIN_VALUE){
                System.out.println("Sorry giftcard doesn't exist");
                return;
            }
            


        }
    }
    public void access_giftcard_menu(int index){
        while(true){
            System.out.println("1.Top up giftcard");
            System.out.println("2.Make a purchase");
            System.out.println("3.Close giftcard");
            System.out.println("4.Block/Unblock giftcard");
            System.out.println("5.Generate giftcard summary");
            System.out.println("6.Go back");
            System.out.println("7.Exit");
            System.out.print("please enter the number that your prefered action is listed to: ");
            int option= scan.nextInt();
            switch(option){
                case 1:
                    System.out.println("\n*********\nTop up your giftcard\n*********\n");
                    System.out.print("Enter your top up amount: ");
                    int top_up_amount= scan.nextInt();
                    if(top_up_amount>account_balance){
                        System.out.println("Your account balance is low");
                        break;
                    }
                    else if(top_up_amount<account_balance){
                        giftcards.get(index).top_up(top_up_amount);
                        System.out.println("Your current giftcard balance: "+giftcards.get(index).get_balance()+"\n");
                        break;
                    }
                case 2:
                    System.out.println("\n*********\nMake a purchase\n*********\n");
                    System.out.print("Enter your purchase amount: ");
                    int purchase_amount= scan.nextInt();
                    if(purchase_amount>giftcards.get(index).balance){
                        System.out.println("\nWarning: Low balance\n");
                        break;
                    }
                    giftcards.get(index).reward_points+=(50*(purchase_amount/500));
                    giftcards.get(index).balance-=purchase_amount;
                    if(giftcards.get(index).reward_points>=200){
                        if(giftcards.get(index).type==""){
                            giftcards.get(index).type="silver";
                            giftcards.get(index).reward_points=0;
                        }
                        else if(giftcards.get(index).type=="siver"){
                            giftcards.get(index).type="gold";
                            giftcards.get(index).reward_points=0;
                        }
                        else if(giftcards.get(index).type=="gold"){
                            giftcards.get(index).type="platinum";
                            giftcards.get(index).reward_points=0;
                        }
                    }
                    System.out.println("Your purchase has been made");
                    System.out.println("Your current balance is: "+giftcards.get(index).balance);
                    System.out.println("Your reward points are: "+giftcards.get(index).reward_points+"\n");
                    break;
                case 3:
                    System.out.println("\n*********\nClose giftcard\n*********\n");
                    System.out.print("Do you really wish to close your giftcard?\n1.Yes\n2.No\nplease enter the number to which the desired option is listed: ");
                    int opt=scan.nextInt();
                    if(opt==1){
                        if(giftcards.get(index).status=="closed"){
                            System.out.println("Your giftcard is already closed");
                            break;
                        }
                        account_balance+=giftcards.get(index).balance;
                        giftcards.get(index).balance=0;
                    }
                    else if(opt ==2){
                        break;
                    }
                case 4:
                    System.out.println("\n*********\nBlock/Unblock giftcard\n*********\n");
                    System.out.print("1.Block\n2.Unblock\nplease enter the number to which the desired option is listed: ");
                    int opt_bl=scan.nextInt();
                    if(opt_bl==1){
                        if(giftcards.get(index).block=="blocked"){
                            System.out.println("your giftcard is already blocked \n");
                            break;
                        }
                        giftcards.get(index).block="blocked";
                        System.out.println("Your giftcard has been blocked\n");
                        break;
                        
                    }
                    else if(opt_bl==2){
                        if(giftcards.get(index).block=="unblocked"){
                            System.out.println("your giftcard is already unblocked \n");
                            break;
                        }
                        giftcards.get(index).block="unblocked";
                        break;
                    }
                case 5:
                    System.out.println("\n*********\nGiftcard summary\n*********\n");
                    System.out.println("Giftcard ID: "+giftcards.get(index).ID+"\nGiftcard balance: "+giftcards.get(index).balance+"\nGiftcard status: "+giftcards.get(index).status+"\nGiftcard block/unblock status: "+giftcards.get(index).block+"\nGiftcard type: "+giftcards.get(index).type+"\nReward points: "+giftcards.get(index).reward_points+"\n");
                    break;
                case 6:
                    return;
                case 7:
                    System.exit(0);
                    
            }


        }
        }
    }


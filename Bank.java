import java.util.*;

public class Bank {
    static ArrayList<Customer> customers = new ArrayList<>();

    public static void main(String[] args) {
        customers.add(new Customer(1, 10000));
        customers.add(new Customer(2, 20000));
        customers.add(new Customer(3, 30000));
        auth_cred();
    }

    public static void main_menu(int i) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n\n1.Create a new giftcard");
            System.out.println("2.Access you giftcard");
            System.out.println("3.Generate giftcards summary");
            System.out.println("4.Go to login page");
            System.out.print("please enter the number to which your desired option is listed to: ");
            int option= scanner.nextInt();
            switch(option){
                case 1:
                    customers.get(i).create_giftcard();
                    break;
                case 2:
                    customers.get(i).access_giftcard();
                    break;
                case 4:
                    return;
                case 3:
                    if(customers.get(i).giftcards.size()==0){
                        System.out.println("\nThere are no giftcards");
                        break;
                    }
                    for(int j=0; j<customers.get(i).giftcards.size();j++){
                        System.out.println("\n*********\nGiftcard "+(j+1)+"\n*********\n");
                        System.out.println("Giftcard ID: "+customers.get(i).giftcards.get(j).ID+"\nGiftcard balance: "+customers.get(i).giftcards.get(j).balance+"\nGiftcard status: "+customers.get(i).giftcards.get(j).status+"\nGiftcard block/unblock status: "+customers.get(i).giftcards.get(j).block+"\nGiftcard type: "+customers.get(i).giftcards.get(j).type+"\n");
                    }
                    break;
                default:
                    System.out.println("Invalid input");

            }
        }

    }

    public static void auth_cred() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to the State Bank of India💸");
            System.out.println("********\n");
            System.out.print("please enter your credentials to proceed: ");
            int n = scanner.nextInt();
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).customer_ID == n) {
                    main_menu(i);
                    continue;
                }
            }
            System.out.println("Sorry your ID was not found please try again");
        }

    }
}

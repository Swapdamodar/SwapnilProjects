import java.util.Scanner;

public class GarageBillingApp {
    public static void main(String[] args) {
        GarageService garageService = new GarageService(5);
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("-----Bharti Car Service Center-----");
            System.out.println("1.Add Customer");
            System.out.println("2.Display Services");
            System.out.println("3.Exit");
            System.out.println("Enter your choice");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Enter Customer Name");
                    String name = sc.next();
                    System.out.println("Enter Phone number");
                    String phoneNo = sc.next();
                    System.out.println("Enter Car Number");
                    String carNum = sc.next();
                    System.out.println("Enter Car Model");
                    String model = sc.next();
                    System.out.println("Entering entry time of the car");
                    long entryTime = System.currentTimeMillis();
                    garageService.addCustomer(name,phoneNo,carNum,model,entryTime);

                    break;
                case 2:
                    System.out.println("Enter Car Number");
                    String carNo = sc.next();
                    garageService.createInvoice(carNo);
                    garageService.unParkCar(carNo);
                    break;
                case 3:
                    System.out.println("Exiting...thank you!!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice....Try again");

            }


        }
    }
}

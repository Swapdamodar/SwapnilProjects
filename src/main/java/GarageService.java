import javax.sound.midi.Soundbank;
import java.util.*;

public class GarageService {
    private Map<String,Customer> customersMap;
    private List<Service> availableServices;

    public GarageService() {
        this.customersMap = new HashMap<>();
        this.availableServices = new ArrayList<>();
        loadServices();
    }
    public void loadServices(){
        availableServices.add(new Service("Car wash",500));
        availableServices.add(new Service("Oil change",700));
        availableServices.add(new Service("Wheel alignment",500));
        availableServices.add(new Service("Tyre Replacement",3000));
        availableServices.add(new Service("Puncture",50));

    }
    public void addCustomer(String name,String phone,String carNumber,String model){
        Car car = new Car(carNumber,model);
        Customer customer = new Customer(name,phone,car);
        customersMap.put(carNumber,customer);
        System.out.println("-----Customer added successfully-----");
    }
    public void createInvoice(String carNumber){
        if(!customersMap.containsKey(carNumber)){
            System.out.println("No customer found with car Number:"+carNumber);
            return;
        }
        Scanner sc = new Scanner(System.in);
        Customer customer = customersMap.get(carNumber);
        InvoiceDisplay invoice = new InvoiceDisplay(customer);
        System.out.println("Available Services...");
        for (int i = 0; i < availableServices.size(); i++) {
            System.out.println((i+1)+"."+availableServices.get(i).getService()+ "$"+availableServices.get(i).getPrice());
        }

        while(true){
            System.out.println("Enter service number to add or 0 for finish");
            int choice = sc.nextInt();
            if(choice==0){
                break;
            }
            if(choice>0 && choice <= availableServices.size()){
                invoice.addService(availableServices.get(choice-1));
                System.out.println("Service done");

            }else{
                System.out.println("Invalid choice");
            }
            invoice.printInvoice();
        }
    }
}

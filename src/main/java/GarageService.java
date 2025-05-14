import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.*;

public class GarageService {
    private Map<String,Customer> customersMap;
    private List<Service> availableServices;
    private List<ParkingSlot> slots;
    private final int maxSlots;
    private double parkingBill;


    public GarageService(int maxSlots) {
        this.customersMap = new HashMap<>();
        this.availableServices = new ArrayList<>();
        loadServices();
        this.maxSlots= maxSlots;
        slots = new ArrayList<>();
        for (int i=1;i<=maxSlots;i++){
            slots.add(new ParkingSlot(i));
        }

    }
    public boolean parkCar(Car car){
        for(ParkingSlot slot: slots){
            if(!slot.isOccupied()){
                slot.occupySlot(car);
                System.out.println("Your car is parked at :"+slot.getSlotNumber());
                return true;
            }
        }
        System.out.println("Garage is Full. No Available slots!");
        return false;
    }
    public boolean unParkCar(String numberPlate){
        for(ParkingSlot slot: slots){
            if(slot.isOccupied() && slot.getCar().getCarNumber().equals(numberPlate)){
                long duration =System.currentTimeMillis()-slot.getCar().getEntryTime();
                double hours = duration / (1000.0*60*60);
                this.parkingBill = calculateBill(hours);
                System.out.println("Car " + numberPlate + " unparked from slot " + slot.getSlotNumber());
                System.out.println("Parking Duration: " + hours + " hours, Bill: ₹" + this.parkingBill);
                slot.freeSlot();
                return true;
            }
        }
        System.out.println("Car not found.");
        return false;
    }
    private double calculateBill(double hours) {
        double ratePerHour = 50.0;
        return Math.ceil(hours) * ratePerHour;
    }
    public void displaySlotStatus() {
        for (ParkingSlot slot : slots) {
            System.out.println("Slot " + slot.getSlotNumber() + " is " +
                    (slot.isOccupied() ? "Occupied by " + slot.getCar().getCarNumber() : "Free"));
        }
    }
    public void loadServices(){
        availableServices.add(new Service("Car wash",500));
        availableServices.add(new Service("Oil change",700));
        availableServices.add(new Service("Wheel alignment",500));
        availableServices.add(new Service("Tyre Replacement",3000));
        availableServices.add(new Service("Puncture",50));

    }
    public void addCustomer(String name,String phone,String carNumber,String model,long entryTime){
        Car car = new Car(carNumber,model,entryTime);
        parkCar(car);
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
                invoice.printInvoice();
                unParkCar(carNumber);
                displaySlotStatus();
                break;
            }
            if(choice>0 && choice <= availableServices.size()){
                invoice.addService(availableServices.get(choice-1));
                System.out.println("Service done");

            }else{
                System.out.println("Invalid choice");
            }

        }
    }
}

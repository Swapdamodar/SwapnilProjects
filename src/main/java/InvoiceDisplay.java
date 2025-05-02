import java.util.ArrayList;
import java.util.List;

public class InvoiceDisplay {
    private Customer customer;
    private List<Service> serviceList;
    private double totalAmount;

    public InvoiceDisplay(Customer customer) {
        this.customer = customer;
        this.serviceList = new ArrayList<>();
        this.totalAmount = 0;
    }
    public void addService(Service service) {
        serviceList.add(service);
        totalAmount += service.getPrice();
    }
    public void printInvoice(){
        System.out.println("----------Invoice----------");
        System.out.println("Customer: "+customer.getName()+ "| phone no: "+customer.getPhone());
        System.out.println("Car: "+customer.getCar().getModel()+ "| Number: "+customer.getCar().getModel());
        System.out.println("Services.......");
        for(Service service :serviceList){
            System.out.println("# Service: "+service.getService()+ "$ price: "+service.getPrice());
        }
        System.out.println("Total amount: "+totalAmount);
        System.out.println("---------Thank you---------");
    }
}

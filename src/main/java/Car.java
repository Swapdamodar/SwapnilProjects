public class Car {
    private String carNumber;
    private String model;
    private long entryTime;

    public Car(String carNumber, String model,long entryTime) {
        this.carNumber = carNumber;
        this.model = model;
        this.entryTime = System.currentTimeMillis();
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getModel() {
        return model;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public void setModel(String model) {

        this.model = model;
    }
}

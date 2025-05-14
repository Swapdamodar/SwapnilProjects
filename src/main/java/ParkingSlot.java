public class ParkingSlot {
  private int slotNumber;
  private boolean isOccupied;
  private Car car;

    public ParkingSlot(int slotNumber) {
        this.slotNumber = slotNumber;
        this.isOccupied = false;
    }
    public boolean isOccupied(){
        return isOccupied;
    }
    public void occupySlot(Car car){
        this.car= car;
        this.isOccupied= true;
    }
    public void freeSlot(){
        this.car= null;
        this.isOccupied = false;
    }
    public int getSlotNumber() {
        return slotNumber;
    }

    public Car getCar() {
        return car;
    }
}

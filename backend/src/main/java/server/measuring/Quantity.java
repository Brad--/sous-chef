package server.measuring;

public class Quantity {
    private String name; // eg Tablespoon
    private double quantity;

    public Quantity() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void add(Quantity quantity) throws QuantityMismatchException{
        if(!this.getName().toLowerCase().equals(quantity.getName().toLowerCase())){
            throw new QuantityMismatchException();
        }

        this.quantity += quantity.getQuantity();
    }
}

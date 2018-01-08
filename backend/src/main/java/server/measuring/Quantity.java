package server.measuring;

import javax.persistence.Embeddable;

@Embeddable
public class Quantity {
    private String name; // eg Tablespoon
    private double quantity;

    public Quantity() {}

    public Quantity(String name, double quantity) {
        this.name = name;
        this.quantity = quantity;
    }

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

package model;

public class Product {
    private String code;
    private String name;
    private String description;
    private double price;
    private int quantity;

    //Constructors
    public Product(String code, String name, String description, double price, int quantity) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    //Getters
    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }

    //Setters
    public void setCode(String newCode) {
        code = newCode;
    }
    public void setName(String newName) {
        name = newName;
    }
    public void setDescription(String newDescription) {
        description = newDescription;
    }
    public void setPrice(double newPrice) {
        price = newPrice;
    }
    public void setQuantity(int newQuantity) {
        quantity = newQuantity;
    }
}

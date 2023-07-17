package data;

import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Product> products;

    //Constructors
    public Inventory() {
        products = new ArrayList<>();
    }

    //Getters
    public List<Product> getProducts() {
        return products;
    }

    //Methods
    public void addProduct(Product product) {
        products.add(product);
    }

    public Product findProduct(String code) {
        for (Product product : products) {
            if (product.getCode().equals(code)) {
                return product;
            }
        }
        return null;
    }

    public void modifyProduct(String code, String name, String newDescription, double price, int quantity) {
        Product product = findProduct(code);
        if(product != null) {
            product.setName(name);
            product.setDescription(newDescription);
            product.setPrice(price);
            product.setQuantity(quantity);
        } else {
            throw new IllegalArgumentException("Product not found in inventory.");
        }
    }

    public void removeProduct(String code) {
        Product product = findProduct(code);
        if (product != null) {
            products.remove(product);
        } else {
            throw new IllegalArgumentException("Product not found in inventory.");
        }
    }

    public int getNumProducts() {
        return products.size();
    }

    //File methods
    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Product product : products) {
                writer.write(product.getCode() + "," +
                        product.getName() + "," +
                        product.getDescription() + "," +
                        product.getPrice() + "," +
                        product.getQuantity());
                writer.newLine();
            }
            System.out.println("Inventory data saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error while saving inventory data to file: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    String code = data[0].trim();
                    String name = data[1].trim();
                    String description = data[2].trim();
                    double price = Double.parseDouble(data[3].trim());
                    int quantity = Integer.parseInt(data[4].trim());
                    Product product = new Product(code, name, description, price, quantity);
                    products.add(product);
                }
            }
            System.out.println("Inventory data loaded from file: " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error while reading inventory data from file: " + e.getMessage());
        }
    }
}

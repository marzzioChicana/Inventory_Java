package ui;
import data.Inventory;
import model.Product;
import util.ReportGenerator;

import javax.swing.JOptionPane;

public class Menu {
    private Inventory inventory;

    public Menu(Inventory inventory) {
        this.inventory = inventory;
    }

    public void showMenu() {
        boolean exit = false;

        while (!exit) {
            String[] options = { "Agregar producto", "Modificar producto", "Eliminar producto", "Buscar producto", "Generar informe", "Salir" };
            int choice = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Menú", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0: // Agregar producto
                    addProduct();
                    break;
                case 1: // Modificar producto
                    modifyProduct();
                    break;
                case 2: // Eliminar producto
                    removeProduct();
                    break;
                case 3: // Buscar producto
                    searchProduct();
                    break;
                case 4: // Generar informe
                    generateReport();
                    break;
                case 5: // Salir
                    exit = true;
                    break;
                default:
                    break;
            }
        }
    }

    private void addProduct() {
        String code = JOptionPane.showInputDialog("Ingrese el código del producto:");
        if (code == null) { // Se ha seleccionado Cancelar
            return;
        }

        String name = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
        if (name == null) { // Se ha seleccionado Cancelar
            return;
        }

        String description = JOptionPane.showInputDialog("Ingrese la descripción del producto:");
        if (description == null) { // Se ha seleccionado Cancelar
            return;
        }

        double price = 0;
        boolean validPrice = false;
        while (!validPrice) {
            String priceInput = JOptionPane.showInputDialog("Ingrese el precio del producto:");
            if (priceInput == null) { // Se ha seleccionado Cancelar
                return;
            }

            try {
                price = Double.parseDouble(priceInput);
                validPrice = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Precio inválido. Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        int quantity = 0;
        boolean validQuantity = false;
        while (!validQuantity) {
            String quantityInput = JOptionPane.showInputDialog("Ingrese la cantidad del producto:");
            if (quantityInput == null) { // Se ha seleccionado Cancelar
                return;
            }

            try {
                quantity = Integer.parseInt(quantityInput);
                validQuantity = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Cantidad inválida. Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        Product product = new Product(code, name, description, price, quantity);
        inventory.addProduct(product);

        JOptionPane.showMessageDialog(null, "Producto agregado correctamente.");
    }

    private void modifyProduct() {
        String modifyCode = JOptionPane.showInputDialog("Ingrese el código del producto a modificar:");
        if (modifyCode == null) { // Se ha seleccionado Cancelar
            return;
        }

        Product modifyProduct = inventory.findProduct(modifyCode);
        if (modifyProduct != null) {
            String newName = JOptionPane.showInputDialog("Ingrese el nuevo nombre del producto:");
            if (newName == null) { // Se ha seleccionado Cancelar
                return;
            }

            String newDescription = JOptionPane.showInputDialog("Ingrese la nueva descripción del producto:");
            if (newDescription == null) { // Se ha seleccionado Cancelar
                return;
            }

            double newPrice = modifyProduct.getPrice();
            boolean validPrice = false;
            while (!validPrice) {
                String priceInput = JOptionPane.showInputDialog("Ingrese el nuevo precio del producto:");
                if (priceInput == null) { // Se ha seleccionado Cancelar
                    return;
                }

                try {
                    newPrice = Double.parseDouble(priceInput);
                    validPrice = true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Precio inválido. Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            int newStock = modifyProduct.getQuantity();
            boolean validStock = false;
            while (!validStock) {
                String stockInput = JOptionPane.showInputDialog("Ingrese la nueva cantidad del producto:");
                if (stockInput == null) { // Se ha seleccionado Cancelar
                    return;
                }

                try {
                    newStock = Integer.parseInt(stockInput);
                    validStock = true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Cantidad inválida. Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            inventory.modifyProduct(modifyCode, newName, newDescription, newPrice, newStock);

            JOptionPane.showMessageDialog(null, "Producto modificado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Producto no encontrado.");
        }
    }

    private void removeProduct() {
        String deleteCode = JOptionPane.showInputDialog("Ingrese el código del producto a eliminar:");
        if (deleteCode == null) { // Se ha seleccionado Cancelar
            return;
        }

        Product deleteProduct = inventory.findProduct(deleteCode);
        if (deleteProduct != null) {
            inventory.removeProduct(deleteCode);
            JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Producto no encontrado.");
        }
    }

    private void searchProduct() {
        String searchCode = JOptionPane.showInputDialog("Ingrese el código del producto a buscar:");
        Product searchProduct = inventory.findProduct(searchCode);
        if (searchProduct != null) {
            JOptionPane.showMessageDialog(null, "Producto encontrado:\n" +
                    "Código: " + searchProduct.getCode() + "\n" +
                    "Nombre: " + searchProduct.getName() + "\n" +
                    "Descripción: " + searchProduct.getDescription() + "\n" +
                    "Precio: " + searchProduct.getPrice() + "\n" +
                    "Cantidad: " + searchProduct.getQuantity());
        } else {
            JOptionPane.showMessageDialog(null, "Producto no encontrado.");
        }
    }

    private void generateReport() {
        String report = ReportGenerator.generateStockReport(inventory);
        JOptionPane.showMessageDialog(null, report, "Informe de inventario", JOptionPane.INFORMATION_MESSAGE);
    }
}


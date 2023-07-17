package util;

import data.Inventory;

public class ReportGenerator {
    public static String generateStockReport(Inventory inventory) {
        StringBuilder report = new StringBuilder();
        report.append("Stock Report\n");

        for(int i = 0; i < inventory.getNumProducts(); i++) {
            report.append("Product " + (i + 1) + ": " + "\n");
            report.append(" -- Codigo: " + inventory.getProducts().get(i).getCode() + " ");
            report.append(" -- Nombre: " + inventory.getProducts().get(i).getName() + " ");
            report.append(" -- Descripcion: " + inventory.getProducts().get(i).getDescription() + " ");
            report.append(" -- Precio: " + inventory.getProducts().get(i).getPrice() + " ");
            report.append(" -- Stock: " + inventory.getProducts().get(i).getQuantity() + "\n\n");
        }

        return report.toString();
    }
}

package util;

import data.Inventory;

public class ReportGenerator {
    public static String generateStockReport(Inventory inventory) {
        StringBuilder report = new StringBuilder();
        report.append("Stock Report\n");

        for(int i = 0; i < inventory.getNumProducts(); i++) {
            report.append(inventory.getProducts().get(i).getCode() + " ");
            report.append(inventory.getProducts().get(i).getName() + " ");
            report.append(inventory.getProducts().get(i).getDescription() + " ");
            report.append(inventory.getProducts().get(i).getPrice() + " ");
            report.append(inventory.getProducts().get(i).getQuantity() + "\n");
        }

        return report.toString();
    }
}

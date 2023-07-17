import data.Inventory;
import model.Product;
import ui.Menu;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Menu menu = new Menu(inventory);
        menu.showMenu();
    }
}
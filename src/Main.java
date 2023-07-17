import data.Inventory;
import ui.Menu;

public class Main {
    public static void main(String[] args) {
        String filename = "inventory.txt"; // Nombre del archivo de inventario
        Inventory inventory = new Inventory();
        Menu menu = new Menu(inventory);

        // Cargar los datos del inventario desde el archivo
        inventory.loadFromFile(filename);

        // Mostrar menú después de cargar los datos del inventario
        menu.showMenu();

        // Guardar los datos actualizados del inventario en el archivo antes de finalizar
        inventory.saveToFile(filename);

        // Finalizar el programa después de salir del menú
        System.exit(0);
    }
}
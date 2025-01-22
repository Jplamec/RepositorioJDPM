import java.io.*;
import java.util.*;

public class AgendaTelefonica {
    private static final String AGENDA_FILE = "agenda.dat";
    private static Map<String, String> agenda = new HashMap<>();

    public static void main(String[] args) {
        cargarAgenda();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            switch (opcion) {
                case 1:
                    agregarContacto(scanner);
                    break;
                case 2:
                    eliminarContacto(scanner);
                    break;
                case 3:
                    mostrarAgenda();
                    break;
                case 4:
                    guardarAgenda();
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú de Agenda Telefónica ---");
        System.out.println("1. Agregar contacto");
        System.out.println("2. Eliminar contacto");
        System.out.println("3. Mostrar agenda");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void agregarContacto(Scanner scanner) {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        agenda.put(nombre, telefono);
        System.out.println("Contacto agregado.");
    }

    private static void eliminarContacto(Scanner scanner) {
        System.out.print("Nombre del contacto a eliminar: ");
        String nombre = scanner.nextLine();
        if (agenda.containsKey(nombre)) {
            agenda.remove(nombre);
            System.out.println("Contacto eliminado.");
        } else {
            System.out.println("Contacto no encontrado.");
        }
    }

    private static void mostrarAgenda() {
        System.out.println("\n--- Agenda Telefónica ---");
        for (Map.Entry<String, String> entry : agenda.entrySet()) {
            System.out.println("Nombre: " + entry.getKey() + " - Teléfono: " + entry.getValue());
        }
    }

    private static void guardarAgenda() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(AGENDA_FILE))) {
            oos.writeObject(agenda);
            System.out.println("Agenda guardada correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar la agenda: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void cargarAgenda() {
        File archivo = new File(AGENDA_FILE);
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(AGENDA_FILE))) {
                agenda = (Map<String, String>) ois.readObject();
                System.out.println("Agenda cargada correctamente.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al cargar la agenda: " + e.getMessage());
            }
        }
    }
}

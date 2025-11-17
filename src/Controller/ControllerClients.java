/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Controller.ControllerList;
import Enum.Countries;
import Model.Client;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;

/**
 *
 * @author USER
 */
public class ControllerClients {
    private static ControllerClients instance;
    private ControllerList<Client> clients = new ControllerList<>();
    private Client client;
    
    // generados por todas las instancias de la clase durante la ejecución de la aplicación.
    private static final Set<String> USED_CODES = new HashSet<>();
    
    private static final String ALPHANUMERIC_CHARS = 
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    
    private static final int MAX_ATTEMPTS = 10000;

    public ControllerClients() {
        this.client = null;
    }
    
    //Método estático público para obtener la única instancia
    public static ControllerClients getInstance() {
        if (instance == null) {
            instance = new ControllerClients();
        }
        return instance;
    }
    
    public void addClientsInList(Client user) {
        this.clients.addList(user);
    }
    
    public boolean deleteClientInList(Client user) {
        boolean removed = this.clients.getAllTheLista().removeIf(
                c -> c.getCodClient().equalsIgnoreCase(user.getCodClient())
        );

        if (removed) {
            this.clients.deleteList(user);
        return removed;
        }
        
        return removed;
    }

    // --- MÉTODO: MODIFICACIÓN PARCIAL (Con Lógica Merge) ---
    // El cliente pasa el código y el objeto con los campos a cambiar
    public boolean updatePartially(String code, Client clienteConCambios) {
        // Lógica de búsqueda (corregida con .equals())
        Predicate<Client> predicateClient = c -> c.getCodClient() == code;

        return this.clients.getAllTheLista().stream()
        .filter(predicateClient)
        .findFirst()
        .map(clienteOriginal -> {
            this.mergeClientData(clienteOriginal, clienteConCambios);
            return true; 
        })
        .orElse(false);
    
    }
    // Método privado auxiliar: Lógica de Fusión (Merge)
    private void mergeClientData(Client clienteOriginal, Client nuevosDatos) {
        
        // --- UTILIDAD: Función auxiliar para evitar la redundancia ---
        java.util.function.BiConsumer<String, java.util.function.Consumer<String>> updateStringField = (nuevoValor, setter) -> {
            if (nuevoValor != null) {
                if (nuevoValor.equals("[BORRAR]")) { // Lógica para BORRADO explícito
                    setter.accept(null); 
                } else if (!nuevoValor.isEmpty()) {
                    setter.accept(nuevoValor); // Lógica de CAMBIO
                }
            }
        };

        // 1. Campos String
        updateStringField.accept(nuevosDatos.getName(), clienteOriginal::setName);
        updateStringField.accept(nuevosDatos.getLastName(), clienteOriginal::setLastName);
        updateStringField.accept(nuevosDatos.getCountry(), clienteOriginal::setCountry);
        updateStringField.accept(nuevosDatos.getGmail(), clienteOriginal::setGmail);
        updateStringField.accept(nuevosDatos.getUsername(), clienteOriginal::setUsername);
        updateStringField.accept(nuevosDatos.getPassword(), clienteOriginal::setPassword);
        updateStringField.accept(nuevosDatos.getDescription(), clienteOriginal::setDescription);
        
        // 2. Campos Numéricos (Edad)
        // Se asume que 0 o -1 indica que no hay cambio o que se ignora
        if (nuevosDatos.getAge()> 0) { 
            clienteOriginal.setAge(nuevosDatos.getAge());
        }
        
        //3. Campos de Carecteres(char)
        char nuevoGenero = nuevosDatos.getGender();
        if (nuevoGenero != '\u0000') {
            // Asumimos que si se envía '\u0000' significa "no hay cambio"
            // Cualquier otro char (ej: 'M', 'F', o un indicador de borrado si lo implementas)
            // sobrescribe el valor.
            clienteOriginal.setGender(nuevoGenero);
        }
        
//        // 4. Campos de enumeradores(class enum)
//        Countries country = nuevosDatos.getCountry();
//        if (country != null) {
//            // Si el objeto de cambios tiene un valor de País, lo actualiza.
//            clienteOriginal.setCountry(country);
//        }

    }
    
    
//        // Lógica para el campo Nombre
//        String nuevoNombre = nuevosDatos.getName();
//        if (nuevoNombre != null) {
//            // Lógica adicional para BORRAR: si se envía un valor especial
//            if (nuevoNombre.equals("[BORRAR]")) {
//                clienteOriginal.setName(null); 
//            } 
//            // Lógica normal de CAMBIO: si no está vacío
//            else if (!nuevoNombre.isEmpty()) {
//                clienteOriginal.setName(nuevoNombre);
//            }
//        }
//        
//        // Lógica para el campo Dirección (ejemplo simplificado)
//        if (nuevosDatos.getGmail()!= null && !nuevosDatos.getGmail().isEmpty()) {
//            clienteOriginal.setGmail(nuevosDatos.getGmail());
//        }
//        
//        // Lógica para el campo Edad (ejemplo numérico)
//        if (nuevosDatos.getEdad() > 0) { 
//            clienteOriginal.setEdad(nuevosDatos.getEdad());
//        }
    
    public Client findClientByCode(String code) {
         return this.clients.getAllTheLista().stream() 
                            .filter(c -> c.getCodClient().equalsIgnoreCase(code))
                            .findFirst().orElse(null);
    }
    
    public boolean validateLogin(String username, String password) {
    
        // 1. Define el predicado (criterio de búsqueda)
        Predicate<Client> predicateValidateLogin = c -> c.getUsername().equals(username)
            && c.getPassword().equals(password);

        // 2. Utiliza Stream para encontrar el primer cliente que coincida
        Client foundClient = this.clients.getAllTheLista().stream()
                                         .filter(predicateValidateLogin)
                                         .findFirst() 
                                         .orElse(null);

        
        if (foundClient != null) {
            this.client = foundClient; // ¡Éxito! Guarda el objeto Client
            return true;
        }

        // Falla. Limpia la referencia.
        this.client = null;
        return false;
    }
    
//        Predicate<Client> predicateValidateLogin = c -> c.getUsername().equals(username) 
//                && c.getPassword().equals(password);
//        
//        Client foundClient = this.clients.existMatch(predicateValidateLogin);
//        
//        if (foundClient != null) {
//            this.client = foundClient; // Guarda el cliente en el Singleton
//            return true;
//        }
//        return false;
    
    //método para cerrar la sesión
    public void logout() {
        this.client = null;
    }
    
    //codigo para generar un codigo unico para los clientes cuando se registran
    public String generateCodeUniqueClient(int length) {
        
        if (length <= 0) {
            throw new IllegalArgumentException("La longitud debe ser positiva.");
        }
        
        Random random = new Random();
        String newCode;
        int attempts = 0;

        // Bucle do-while: Garantiza que se genera un código hasta que sea único
        do {
            if (attempts >= MAX_ATTEMPTS) {
                // Previene un bucle infinito si el espacio de códigos está agotado
                throw new IllegalStateException("El espacio de códigos está agotado o es demasiado pequeño.");
            }
            
            // Lógica de Generación
            StringBuilder codeBuilder = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                int randomIndex = random.nextInt(ALPHANUMERIC_CHARS.length());
                codeBuilder.append(ALPHANUMERIC_CHARS.charAt(randomIndex));
            }
            newCode = codeBuilder.toString();
            
            attempts++;
            
        } while (USED_CODES.contains(newCode)); // Verificación: ¿Ya existe en el Set?

        // Registro: Añade el código al Set para que no pueda ser generado de nuevo
        USED_CODES.add(newCode); 
        
        return newCode;
    }

    public ControllerList<Client> getClients() {
        return this.clients;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SaveData;

import Model.Client;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class ClientRepositoryCSV {
    private static final String NOMBRE_ARCHIVO = "clientes.csv";
    private static final String CSV_HEADER = "Code,Name,Lastname,Country,Gender,Age,Gmail,Username,Password,Description";

    /**
     * Guarda la lista completa de clientes en el archivo CSV.
     */
    public void guardarDatos(List<Client> lista) {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO)))) {
            pw.println(CSV_HEADER);
            for (Client cliente : lista) {
                pw.println(cliente.toCsvString());
            }
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo CSV: " + e.getMessage());
        }
    }

    /**
     * Carga todos los clientes desde el archivo CSV.
     */
    public List<Client> cargarDatos() {
        List<Client> lista = new ArrayList<>();
        File archivo = new File(NOMBRE_ARCHIVO);

        if (!archivo.exists()) {
            return lista; // Devuelve lista vacía si no hay archivo
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            br.readLine(); // Leemos y descartamos la línea de encabezado
            
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                
                if (datos.length == 10) { 
                    try {
                        Client cliente = new Client(datos);
                        lista.add(cliente);
                    } catch (Exception e) {
                        System.err.println("Error al parsear línea (se omite): " + linea + " -> " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }
        return lista;
    }
}

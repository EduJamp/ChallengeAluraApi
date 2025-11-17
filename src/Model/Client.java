/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author USER
 */
public class Client extends Person{
    private String codClient;
    private String description;

    public Client() {
    }
    
    public Client(String[] datosCsv) throws NumberFormatException {
        if (datosCsv.length != 10) {
            throw new IllegalArgumentException("La línea del CSV no tiene 10 campos.");
        }
        this.codClient = datosCsv[0];
        this.setName(datosCsv[1]);
        this.setLastName(datosCsv[2]);
        this.setCountry(datosCsv[3]);
        this.setGender(Character.valueOf(datosCsv[4].charAt(0)));
        this.setAge(Integer.parseInt(datosCsv[5]));
        this.setGmail(datosCsv[6]);
        this.setUsername(datosCsv[7]);
        this.setPassword(datosCsv[8]);
        this.setDescription(datosCsv[9]);
    }

    /**
     * Convierte el objeto Cliente a una cadena en formato CSV.
     */
    public String toCsvString() {
        return this.codClient + "," + this.getName() + "," + this.getLastName() + "," + this.getCountry() + "," +
               this.getGender() + "," + this.getAge() + "," + this.getGmail() + "," + this.getUsername() + "," +
               this.getPassword() + "," + this.description;
    }

    /**
     * Método toString() para imprimir el objeto de forma legible.
     */
    @Override
    public String toString() {
        return "Cliente{" +
                "code='" + codClient + '\'' +
                ", name='" + this.getName() + '\'' +
                ", lastname='" + this.getLastName() + '\'' +
                ", age=" + this.getAge() +
                '}';
    }

    public String getCodClient() {
        return codClient;
    }

    public void setCodClient(String codClient) {
        this.codClient = codClient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

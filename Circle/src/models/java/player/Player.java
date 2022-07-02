package src.models.java.player;

import java.util.UUID;

/**
 * Esta clase es el jugador y guarda los datos de un jugador.
 * 
 * @author Rodrigo Oliveira 
 * @version 2022.06.29-stable
 */

public class Player {
    private String id; // Identificador unico
    private int position; // Posicion del jugador
    private String name; // Nombre del jugador
    private String surname; // Apellido del jugador
    private String sex; // Sexo del jugador
    private int age; // Edad del jugador
    
    /**
     * Constructor de jugador por defecto
     */
    public Player() {
        this.id = null;
        this.position = 0;
        this.name = null;
        this.surname = null;
        this.sex = null;
        this.age = 0;
    }
    
    /**
     * Constructor de jugador con los datos
     * 
     * @param pos Posicion de este jugador
     * @param n Nombre de este jugador
     * @param sn Apellido de este jugador
     * @param sex Sexo de este jugador
     * @param age Edad de este jugador
     */
    public Player(int pos, String n, String sn, String sex, int age) {
        this.id = UUID.randomUUID().toString(); // Genera un ID unico
        this.position = pos;
        this.name = n;
        this.surname = sn;
        this.sex = sex;
        this.age = age;
    }
    
    /**
     * Constructor del jugador con un string de los datos
     * 
     * @param datesPlayer Un string con los datos almacenados de un jugador
     */
    public Player(String datesPlayer) {
        String[] separator = datesPlayer.split("-"); // Separa los datos
        this.id = UUID.randomUUID().toString();
        this.position = Integer.parseInt(separator[0]);
        this.name = separator[1];
        this.surname = separator[2];
        this.sex = separator[3];
        this.age = Integer.parseInt(separator[4]);
    }
    
    /**
     * Metodo que obtiene la posicion de un jugador
     * 
     * @return this.position La posicion de este jugador
     */
    public int getPosition() {
        return this.position;
    }
    
    /**
     * Metodo que obtiene el nombre de un jugador
     * 
     * @return this.name El nombre de este jugador
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Metodo que obtiene el apellidoe de un jugador
     * 
     * @return this.surname El apellido de este jugador
     */
    public String getSurname() {
        return this.surname;
    }
    
    /**
     * Metodo que obtiene un String de los datos de un jugador
     * 
     * @return string Un string con los datos de un jugador
     */
    @Override
    public String toString() {
        String string =  null;
        string = "Player nº " + this.position + "\n" + "ID: " + this.id + "\n" +  "Nombre: " + this.name + "\n" + "Apellido: " + this.surname + "\n" + "Sexo: " + this.sex + "\n" + "Edad: " + this.age + "\n";
        return string;
    }
}

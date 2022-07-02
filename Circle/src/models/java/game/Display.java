package src.models.java.game;

import src.models.java.cdllist.CircleDLL;
import src.models.java.player.Player;

/**
 * Esta clase imprime l
 * 
 * @author Rodrigo Oliveira 
 * @version 2022.06.28-beta
 */
public class Display {
    /**
     * Metodo que imprime la bienvenida al usuario
     */
    public void welcomeUser() {
        System.out.println("Bienvenido al juego \"El Circulo\"\n");
        printCircle();
    }

    /**
     * Metodo que imprime el circulo
     */
    public void printCircle() {
        String circle = "\t   ******\n" + 
            "\t  ********\n" + 
            "\t ****  ****\n" +
            "\t*****  *****\n" +
            "\t ****  ****\n" +
            "\t  ********\n" +
            "\t   ******\n";
        System.out.println(circle);
    }
    
    /**
     * Metodo que imprime el menu
     */
    public void printMenu() {
        System.out.println("\nMenu de opciones");
        System.out.println("[1]\tSalir del juego.");
        System.out.println("[2]\tVotar por un jugador contrario.");
        System.out.println("[3]\tSaltar turno.");
    }

    /**
     * Metodo que imprime la lista de jugadores
     */
    public void printListPlayers(CircleDLL cdll) {
        String list = cdll.toString();
        System.out.println("");
        System.out.print(list);
    }

    /**
     * Metodo que imprime el jugador eliminado
     */
    public void printPlayerDead(Player playerDead) {
        System.out.println("\nHa muerto en la posicion: " + playerDead.getPosition());
        System.out.println("Nombre: " + playerDead.getName());
        System.out.println("Apellido: " + playerDead.getSurname());
    }

    /**
     * Metodo que imprime el jugador ganador
     */
    public void printPlayerWinner(Player playerWinner) {
        System.out.println("\nHa ganado en la posicion: " + playerWinner.getPosition());
        System.out.println("Nombre: " + playerWinner.getName());
        System.out.println("Apellido: " + playerWinner.getSurname());
    }
}

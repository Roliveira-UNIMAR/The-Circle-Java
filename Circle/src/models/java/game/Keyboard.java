package src.models.java.game;

import src.models.java.player.Player;

import java.util.Scanner;

/**
 * Esta clase lee las opciones del jugador principal
 * 
 * @author Rodrigo Oliveira 
 * @version 2022.06.26-stable
 */
public class Keyboard {
    Scanner sc = new Scanner(System.in);
    
    /**
     * Metodo que lee y devuelve el voto de un jugador principal
     *
     * @return option El voto del jugador pricipal
     */
    public int readVote() {
        int vote = 0;
        do {
            System.out.println("\nEmpieza la votacion.");
            System.out.print("introduce el numero jugador: ");
            vote = sc.nextInt();
        } while ((vote < 1) && (vote < CircleGame.getSizeListGame()));
        return vote;
    }

    /**
     * Method que obtiene la opcion tomada por el jugador principal
     *
     * @return option La opcion tomada por el jugador principal
     */
    public int getReadOption(ReadOption threadReadOption) {
        int option = threadReadOption.getOption();
        return option;
    }
    
    /**
     * Metodo solicita y lee los datos de un jugador y retorna un jugador
     *
     * @return playerMain Nuevo jugador principal
     */
    public Player newPlayer() {
        System.out.print("\nIntroduzca su nombre: ");
        String name = sc.nextLine();
        System.out.print("\nIntroduzca su apellido: ");
        String surname = sc.nextLine();
        System.out.print("\nIntroduzca su sexo M/F: ");
        String sex = sc.nextLine().toUpperCase();
        int age = 0;
        do {
            System.out.print("\nIntroduzca su edad: ");
            age = sc.nextInt();
        } while (age < 1);
        Player playerMain = new Player(54, name, surname, sex, age);
        return playerMain;
    }
}

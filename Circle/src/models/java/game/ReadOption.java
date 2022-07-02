package src.models.java.game;

import java. util.Scanner;
/**
 * Esta clase lee la opcion del jugador princiapal aparte
 * 
 * @author Rodrigo Oliveira 
 * @version 2022.06.26-beta
 */
public class ReadOption extends Thread {
    private static int option;
    Scanner sc = new Scanner(System.in);
    
    /**
     * Method que inicia la lectura
     *
     */
    @Override
    public void run() {
        option = 3;
        System.out.print("\nTienes 2:00 minutos para decidir: ");
        do {
            option = Integer.parseInt(sc.nextLine());
        }while ((option < 1) && (option > 3));
    }

    /**
     * Metodo que obtiene la opcion del player
     *
     * @return option La opcion del jugador
     */
    public static int getOption() {
        return option;
    }

}

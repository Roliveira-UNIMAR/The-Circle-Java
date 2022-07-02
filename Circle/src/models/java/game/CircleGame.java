package src.models.java.game;

import src.models.java.file.File;
import src.models.java.cdllist.CircleDLL;
import src.models.java.player.Player;
import src.models.java.votation.Votation;

import java.util.StringTokenizer;
import java.util.Random;

/**
 * Esta clase tiene los metodos para iniciar el juego "El Circulo".
 * 
 * @author Rodrigo Oliveira
 * @version 2022.06.29-stable
 */
public class CircleGame {
    private static boolean isAliveMain; // El jugador principal esta vivo
    private static int countRound = 0; // Numero de la ronda
    private static CircleDLL listGame = new CircleDLL(); // Lista circular
    private static Player player = new Player(); // Jugador temporal
    private static ReadOption threadReadOption; // Leer la opcion;
    private static Display display = new Display(); // Imprimir en pantalla
    private static Keyboard keyboard = new Keyboard(); // Leer el teclado
    private static Votation votation = new Votation(); // Votacion
    private static File file = new File(); // El archivo
    private static Random random = new Random(); // El random
    
    /**
     * Metodo que inicia la lista
     */
    public static void startList() {
        fillList(file.readDB());
    }
    
    /**
     * Metodo que llena la lista circular con los datos de un archivo
     * 
     * @param datas Un string con los datos de cada jugador
     */
    public static void fillList(String datas){
        StringTokenizer string = new StringTokenizer(datas, "\n");
        while (string.hasMoreTokens()) {
            String datasPlayer = string.nextToken();
            Player newPlayer = new Player(datasPlayer);
            listGame.addPlayer(newPlayer);
        }
    }
    
    /**
     * Metodo que corre el juego completo
     */
    public static void runGame() throws InterruptedException {
        startList(); // Iniciar la lista
        display.welcomeUser(); // Dar la bienvenida al usuario
        listGame.addPlayer(keyboard.newPlayer()); // Crear un jugador nuevo y lo añade a la lista
        do {
            isAliveMain = true;
            countRound++;
            display.printMenu(); // Mostar el menu
            threadReadOption = new ReadOption();
            threadReadOption.start();
            int count = 0;
            int option;
            // Detener mientra el jugador este tomando una opcion o hasta que pasen 110 segundos, 1 minuto y 50 segundos
            do {
                count++;
                Thread.sleep(1000); // 1 segundo
            } while ((threadReadOption.isAlive()) && (count <= 120));
            option = keyboard.getReadOption(threadReadOption); // Opcion sera la opcion del usuario, sino se salta turno
            threadReadOption.interrupt();
            switch (option) {
                case 1: // caso salir
                    votation.exitPlayer(54); // Se saca de la votacion
                    isAliveMain = false; // El principal ya no esta vivo
                    player = listGame.removePlayer(54); // Remover player a ser eliminado
                    break;
                case 2: // caso votar
                    display.printListPlayers(listGame); // Imprimir la lista de jugadores
                    votation.automatic(listGame.getSize()); // Votacion automatica
                    int vote = keyboard.readVote(); // Leer el voto del player principal
                    votation.playerVote(vote); // Voto del jugador principal
                    System.out.println("\nYa votaste, espere que los demas voten");
                    player = listGame.removePlayer(votation.getPlayerToDie()); // Remover player a ser eliminado
                    break;
                case 3: // caso saltar turno
                    System.out.println("\nSaltaste turno, espere que los demas voten");
                    votation.automatic(listGame.getSize()); // Votacion automatica
                    player = listGame.removePlayer(votation.getPlayerToDie()); // Remover player a ser eliminado
                    break;
            }
            display.printPlayerDead(player); // Imprimir el jugador eliminado
            file.savePlayerDead(player, countRound, votation.getAmountVotes()); // Guardar el jugador eliminado
            // Si el jugador desea salir, es candidato a ser elimino o ya no quedan mas rondas, se termina el do-while
        } while ((isAliveMain == true) && (votation.getPlayerToDie() != 54) && (countRound != 53));
        // Si el jugador principal decidio salir o la votacion lo mato
        if ((isAliveMain == false) || (votation.getPlayerToDie() == 54)) {
            System.out.println("\nHaz salido del juego." + "\nEmpezara el juego en automatico a mayor velocidad");
            runAutomaticGame(); // Correr juego automatico
        }
        player = listGame.getPlayer(votation.getPlayerWinner()); // Obtener el jugador ganador
        display.printPlayerWinner(player); // Imprimir el jugador ganador
    }
    
    /**
     * Metodo que corre el juego en automatico
     */
    public static void runAutomaticGame() {
        do {
            countRound++;
            try {
                Thread.sleep(2000); // 2 segundos
            } catch (InterruptedException e) { }
            votation.automatic(listGame.getSize());
            player = listGame.removePlayer(votation.getPlayerToDie());
            display.printPlayerDead(player);
            file.savePlayerDead(player, countRound, votation.getAmountVotes());
        } while (countRound != 53);
    }
    
    /**
     * Metodo que obtiene el tamaño de la lista
     * 
     * @return listGame.getSize() Tamaño actual de la lista
     */
    public static int getSizeListGame() {
        return listGame.getSize();
    }
}

package src.models.java.votation;

import java.util.Random;
import java.util.Arrays;

/**
 * Esta clase se encarga de realizar los procesos relacionados a la votación.
 * 
 * @author Rodrigo Oliveira 
 * @version 2022.06.28-stable
 */
public class Votation {
    private final int AMOUNT_PLAYERS = 54; // Cantidad maxima de jugadores.
    private int[] players;
    private int playerToDie; // Jugador que sera eliminado
    private int[] deadPlayers = {0}; // Array que almacena los jugadores eliminados. 
    private Random random = new Random(); // El random
    private int amountVotes = 0; // la cantidad de votos mas alta
    private boolean successful;
    
    /**
     * Metodo que decide si un jugador salta su turno de forema aleatoria
     * 
     * @return true Si se decide que saltara - false Se decide que no saltara
     */
    public boolean isSkipTurn() {
        return Math.random() == 0.5;
    }
    
    /**
     * Metodo que decide si el jugador quiere seguir viviendo o prefiere morir
     * 
     * @return true Si se decide que quiere morir - false Si se decide que no quiere morir
     */
    public boolean isWantingLife() {
        return Math.random() < 1; // Para evitar las ganas 
    }
    
    /**
     * Metodo que decide de manera aleatoria por que jugador votar
     * 
     * @return randomInt La posicion del jugador por el que se decidio aleatoriamente
     */
    public int atRandom() {
        int randomInt = random.nextInt(AMOUNT_PLAYERS) + 1;
        return randomInt;
    }
    
    /**
     * Metodo que permite salir y morir a un jugador
     * 
     * @param pos Posicion del jugador que desea salir y morir
     */
    public void exitPlayer(int pos) {
       amountVotes = 0; // No murio por votos
       playerToDie = pos; // El jugador en esta posicion sera el que se eliminara
    }
    
    /**
     * Metodo que permite a u jugador votar por otro
     * 
     * @param pos Posicion del jugador por el que se desea votar
     */
    public void playerVote(int pos) {
       players[pos - 1]++; // Le sumamos un voto a esta posición
    }
    
    /**
     * Metodo que automatiza la votacion
     * 
     * @param playersVoting Cantidad de jugadores que pueden votar
     */
    public void automatic(int playersVoting) {
        players = new int[AMOUNT_PLAYERS];
        int playerIndex = 0;
        for (int i = 1; i < playersVoting; i++) { 
            if (isWantingLife()) { // No quiere morir
                if (!isSkipTurn()){ // No quiere saltar turno
                    do {
                        playerIndex = atRandom(); // Elegir un jugador aleatoriamente
                        // Evitar que el jugador elegido no este ya eliminado y no sea autovoto
                    } while ((Arrays.asList(deadPlayers).contains(playerIndex)) || (i == playerIndex)); 
                    playerVote(playerIndex); // Votar por ese jugador
                }
            } else { // Si desea morir
                exitPlayer(i); //Se saca del juego y muere
                successful = false;
                break; // Se detiene el for
            }
        }
        successful = true;
    }
    
    /**
     * Metodo que obtiene el player con mas votos y que va a morir
     * 
     * @return playerToDie Posicion del jugador a morir
     */
    public int getPlayerToDie() {
        if (successful) { // Si nadie decidio morir
            amountVotes = 0; // Empezar en 0
            playerToDie = 1;
            for (int i = 1; i <= AMOUNT_PLAYERS; i++) { // Contar los votos de todos los jugadores
                if (players[i - 1] > players[playerToDie - 1]) { // Si este jugador tiene mas votos
                    playerToDie = i; // Ese jugador debe morir
                    amountVotes = players[i - 1]; // Guardar la cantidad de votos
                } else if (players[i - 1] == players[playerToDie - 1]) { // Si dos jugadores tienen cantidades de votos iguales
                    if (random.nextBoolean()) { // decidimos aleatoriamente
                        playerToDie = i;
                        amountVotes = players[i - 1];
                    } else {
                        amountVotes = players[playerToDie - 1];
                    }
                }
            }
        }
        deadPlayers = addPlayer(deadPlayers, playerToDie); // Añadir el jugador con más votos a los jugadores eliminados
        return playerToDie;
    }
    
    /**
     * Metodo que obtiene la cantidad de votos por la cual un jugador fue eliminado
     * 
     * @return amountVotes La cantidad de votos
     */
    public int getAmountVotes() {
        return amountVotes;
    }
    
    /**
     * Metodo que verifica si la votacion salio bien
     * 
     * @return true Si nadie decidio morir - false si Alguien decidio morir
     */
    public boolean isSuccessful() {
        return amountVotes != 0;
    }
    
    /**
     * Metodo que aumenta un array y añade la posicion de un jugador eliminado
     * 
     * @param srcArray El array a aumentar
     * @param srcArray Posicion del jugador eliminado
     * 
     * @return destArray Un nuevo array mas grande que contiene al jugador eliminado
     */
    public int[] addPlayer(int[] srcArray, int player) {
        int[] destArray = new int[srcArray.length + 1]; // Crear un nuevo array mas grande
        for(int i = 0; i < srcArray.length; i++) { // Rellenar con los viejos datos
            destArray[i] = srcArray[i];
        }
        destArray[destArray.length - 1] = player; // Añadir nuevo eliminado
        return destArray;
    }
    
    /**
     * Metodo que obtiene la posicion del jugador ganador
     * 
     * @return winner Posicion del jugador que gano
     */
    public int getPlayerWinner() {
        int winner =  0;
        for (int i = 1; i <= AMOUNT_PLAYERS; i++) { // Bucamos al unico jugador que no fue eliminado
            if (!Arrays.asList(deadPlayers).contains(i)) { // Si no fue eliminado
                winner = i; // Entonces gano
                break;
            }
        }
        return winner;
    }
}

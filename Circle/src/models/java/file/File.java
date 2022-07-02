package src.models.java.file;

import src.models.java.player.Player;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Esta clase se encarga del manejo completo de la lectura y escritura de archivos
 * 
 * @author Rodrigo Oliveira - 29.655.609
 * @version 2022.06.29-stable
 */
public class File {
    Path path = Paths.get("");
    private final String FILENAMEDB = "src\\models\\java\\file\\PEOPLE_DATABASE.txt"; // Nombre del archivo con los jugadores
    private final String FILENAMEPE = "src\\models\\java\\file\\PLAYERS_ELIMINATED.txt"; // Nombre del archivo con los jugadores
    
    /**
     * Metodo que se encarga de leer los datos en un archivo .txt
     * 
     * @return Un String con los datos de cada jugador, divididos por un salta de linea entre cada jugador
     */
    public String readDB() {
        String datesPeoples = ""; // El String a retornar
        try { // Probar leer el archivo
            FileReader file = new FileReader(FILENAMEDB);
            BufferedReader br = new BufferedReader(file);
            String line = br.readLine(); // La primera linea tiene datos irelevantes 
            while((line = br.readLine()) != null) { // Mientras no sea el final del archivo 
                datesPeoples = datesPeoples + line + "\n"; // Guardar los datos y separa por una linea
            }
        } catch (FileNotFoundException e) { // No se encuentra el archivo
            System.out.println("Error el archivo: PEOPLE_DATABASE.txt no se ha encontrado.");
        } catch (IOException e) { // No se pudo leer el archivo
            System.out.println("Error el archivo: PEOPLE_DATABASE.txt no se puede leer.");
        }
        return datesPeoples;
    }
    
    /**
     * Metodo que se encarga de escribir los datos de un jugador eliminado en un archivo .txt
     * 
     * @param p El jugador que fue eliminado, 
     * @param round La ronda en la cual se elimino el jugador, 
     * @param amountVotes Cantidad de votos por la que el jugador fue eliminado
     */
    public void savePlayerDead(Player p, int round, int amountVotes) {
        FileWriter file = null;
        BufferedWriter bw = null;
        try { // Probar escribir en el archivo
            file = new FileWriter("PEOPLE.txt", true); // Se escribira al final del archivo
            bw = new BufferedWriter(file);
            bw.newLine(); // Nueva linea
            bw.write("Ronda de eliminacion: " + round);
            bw.newLine(); // Nueva linea
            if (amountVotes == 0) {
                bw.write("\nDecidio Salir del juego");
            } else {
                bw.write("\nSalio por una cantidad de votos: " + amountVotes);
            }
            bw.newLine(); // Nueva linea
            StringTokenizer string = new StringTokenizer(p.toString(), "\n");
            while (string.hasMoreTokens()) {
                String out = string.nextToken();
                bw.write(out);
                bw.newLine(); // Nueva linea

            }
            bw.flush();
        } catch (FileNotFoundException e) { // No se encuentra el archivo
            System.out.println("Error el archivo: PLAYERS_ELIMINATED.txt no se ha encontrado.");
        } catch (IOException e) { // No se pudo leer el archivo
            System.out.println("Error el archivo: PLAYERS_ELIMINATED.txt no se puede escribir.");
        } finally {
            try {
                if(bw != null) { 
                    bw.close(); // Cerrar el buffered
                }
                if(file != null) { 
                    file.close(); // Cerrar el archivo
                } 
            } catch (IOException e) { }
        }
    }
}
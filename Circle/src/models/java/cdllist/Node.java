package src.models.java.cdllist;

import src.models.java.player.Player;

/**
 * Esta clase es el nodo de la lista circular doblemente enlazada y sus respectivos metodos de control
 * 
 * @author Rodrigo Oliveira 
 * @version 2022.06.24-stable
 */
public class Node {

    private Node previousNode, nextNode; // Nodo anterior y siguiente
    private Player player; // El jugador
    
    /**
     * Constructor del nodo por defecto
     */
    public Node(){
        this.player = new Player();
        this.previousNode = null;
        this.nextNode = null;
    }
    
    /**
     * Constructor del con los datos del jugador
     * 
     * @param p Jugador a añadir
     */
    public Node(Player p) {
        this.player = p;
        this.previousNode = null;
        this.nextNode = null;
    }
    
    /**
     * Metodo para obtener los datos del jugador
     * 
     * @return this.player El jugador dentro del actual nodo
     */
    public Player getPlayer() {
        return this.player;
    }
    
    /**
     * Metodo que obtiene el nodo anterior del actual nodo
     * 
     * @return this.previousNode El nodo anteriror del actual nodo
     */
    public Node getPrevious() {
        return this.previousNode;
    }
    
    /**
     * Metodo que modifica el nodo anterior del actual nodo
     */
    public void setPrevious(Node pre) {
        this.previousNode = pre;
    }
    
    /**
     * Metodo que obtiene el nodo siguiente del actual nodo
     * 
     * @return this.previousNode El nodo siguiente del actual nodo
     */
    public Node getNext() {
        return this.nextNode;
    }
    
    /**
     * Metodo que modifica el nodo siguiente del actual nodo
     */
    public void setNext(Node next) {
        this.nextNode = next;
    }
}

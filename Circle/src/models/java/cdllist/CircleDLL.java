package src.models.java.cdllist;

import src.models.java.player.Player;

import java.util.*;

/**
 * Esta clase es la lista circular doblemente enlazada, contine los metodos nesesarios para manejarla
 * 
 * @author  Rodrigo Oliveira 
 * @version 2022.06.29-stable
 */
public class CircleDLL {
    private Node headNode, tailNode; // Nodos de inicio y final
    private Node currNode; // Nodo actual
    private int size; // Tamaño de la lista
    private boolean found; // Encontado

    /**
     * Constructor de la lista circular doblemente enlazada
     */
    public void CircleDLL(){
        headNode = null;
        tailNode = null;
        currNode = null;
        size = 0;
    }
    
    /**
     * Metodo que verifica si la lista lista circular doblemente enlazada esta vacia
     * 
     * @return true Si la lista esta vacia - false Si la lista no esta vacia
     */
    public boolean isEmpty(){
        return headNode == null;
    }
    
     /**
     * Metodo que obtiene el tamaño de la lista circular doblemente enlazada
     * 
     * @return size El tamaño de la lista
     */
    public int getSize(){
        return size;
    }
    
    /**
     * Metodo que busca a un jugador en la lista circular doblemente enlazada
     * 
     * @param p El Jugador a buscar en la lista
     */
    public void findPlayer(Player p) {
        currNode = headNode;
        found = false;
        if (!isEmpty()) {     
            do {
                if (currNode.getPlayer().equals(p)) {
                    found = true;
                    return;
                } else {
                    currNode = currNode.getNext();
                }
            } while(currNode != headNode.getNext());
        }
    }
    
    /**
     * Metodo que busca a un jugador segun su posicion en la lista circular doblemente enlazada
     * 
     * @param pos La posision del jugador a buscar en la lista
     */
    public void findPosition(int pos) {
        int count = 0;
        currNode = headNode;
        found = false;
        if (!isEmpty()) {
           do {
                if (currNode.getPlayer().getPosition() == pos) {
                    return;
                } else {
                    currNode = currNode.getNext();
                }
            } while(currNode != headNode);
        }
    }
    
     /**
     * Metodo que añade a un jugador al final de la lista circular doblemente enlazada
     * 
     * @param p El jugador a añadir en la lista
     */
    public void addPlayer(Player p){
        Node newNode = new Node(p);
        if (isEmpty()) {
            headNode = newNode;
            tailNode = newNode;
            headNode.setPrevious(tailNode);
            tailNode.setNext(headNode);
        } else {
            tailNode.setNext(newNode);
            newNode.setPrevious(tailNode);
            tailNode = newNode;
            tailNode.setNext(headNode);
        }
        size++;
    }
    
     /**
     * Metodo que remueve a un jugador segun su posicion en la lista circular doblemente enlazada
     * 
     * @param pos La Posicion del jugador a remover de la lista
     * @return eliminatedPlayer El jugador que se elimino
     */
    public Player removePlayer(int pos) {
        findPosition(pos); // Busca al jugador
        Player eliminatedPlayer = currNode.getPlayer(); // Guardar el jugador que se eliminara
        if (currNode == headNode && getSize() == 1) { // Eliminar si solo tiene inicio
            headNode = null;
            tailNode = null;
        } else if (currNode == headNode) { // Eliminar al inicio
            headNode = headNode.getNext(); 
            headNode.setPrevious(tailNode);
            tailNode.setNext(headNode); 
        } else if (currNode == tailNode) { // Eliminar al final
            tailNode = tailNode.getPrevious();
            tailNode.setNext(headNode);
            headNode.setPrevious(tailNode); 
        } else { // Eliminar el caso general
            currNode.getPrevious().setNext(currNode.getNext());  
            currNode.getNext().setPrevious(currNode.getPrevious());     
        }
        size--;
        return eliminatedPlayer;
    }
    
     /**
     * Metodo que obtiene un jugador segun su posicion en la lista circular doblemente enlazada
     * 
     * @param pos La Posicion del jugador a obtener de la lista
     * @return p El jugador que se encuentra en pos
     */
    public Player getPlayer(int pos) {
        findPosition(pos); // Busca al jugador
        return currNode.getPlayer();
    }
    
     /**
     * Metodo que obtiene un String de la lista circular doblemente enlazada de jugadores
     * 
     * @return string Un string con la lista de jugadores
     */
    @Override
    public String toString() {
        String string = "Lista de Jugadores: \n"; 
        currNode = headNode;
        int count = 1;
        if (!isEmpty()) {
            do {
                string += currNode.getPlayer().getPosition() + ". " + currNode.getPlayer().getName() + " " + currNode.getPlayer().getSurname() + "\n";
                currNode = currNode.getNext();
                count++;
            } while (currNode != headNode);
        }
        return string;
    }
}

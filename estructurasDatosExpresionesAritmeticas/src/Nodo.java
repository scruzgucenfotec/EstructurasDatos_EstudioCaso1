public class Nodo {

    //Atributos
    private char dato;
    private Nodo siguiente;

    //Constructor
    public Nodo(char dato){
        this.dato = dato;
        this.siguiente = null;
    }

    //Getters
    public char getDato() {
        return dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    //Setters
    public void setDato(char dato) {
        this.dato = dato;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}

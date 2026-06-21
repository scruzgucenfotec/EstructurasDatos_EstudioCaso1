/*
Implementación de una pila dinámica utilizando
nodos enlazados.

La estructura sigue el principio LIFO
(Last In, First Out), donde el último elemento
insertado es el primero en salir.

Esta pila es utilizada para apoyar la validación
de expresiones aritméticas mediante el manejo de
paréntesis de apertura y cierre.
*/
public class Pila {

    // Referencia al elemento ubicado en el tope de la pila.
    private Nodo top;

    /*
    Inicializa una pila vacía.

    Al crearse la estructura, no existen nodos
    almacenados, por lo que el tope apunta a null.
    */
    public Pila() {
        top = null;
    }

    /*
    Determina si la pila contiene elementos.

    Retorna true cuando la pila está vacía
    y false cuando contiene al menos un nodo.
    */
    public boolean estaVacia() {
        return top == null;
    }

    /*
    Inserta un nuevo elemento en la parte superior
    de la pila.

    Se crea un nuevo nodo que pasa a convertirse
    en el nuevo tope de la estructura.
    */
    public void push(char elemento) {

        Nodo nuevo = new Nodo(elemento);
        nuevo.setSiguiente(top);
        top = nuevo;
    }

    /*
    Elimina y retorna el elemento ubicado en el
    tope de la pila.

    Si la pila está vacía, se muestra un mensaje
    informativo y se retorna null.
    */
    public Character pop() {

        if (estaVacia()) {
            System.out.println("La pila ya esta vacia.");
            return null;
        }

        char dato = top.getDato();
        top = top.getSiguiente();

        return dato;
    }

    /*
    Retorna el elemento ubicado en el tope de la
    pila sin eliminarlo de la estructura.

    Si la pila está vacía, se muestra un mensaje
    informativo y se retorna null.
    */
    public Character peek() {

        if (estaVacia()) {
            System.out.println("La pila ya esta vacia.");
            return null;
        }

        return top.getDato();
    }
}

import java.io.*;

public class Main {

    static PrintStream out = System.out;
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    /*
    Almacena la expresión ingresada por el usuario.
    Se inicializa vacía porque al iniciar el programa
    todavía no existe una expresión registrada.
    */
    static String expresion = "";

    //MAIN
    public static void main(String[] args) throws IOException {
        /*
        Controla el ciclo principal del programa.

        Muestra el menú, solicita una opción al usuario
        y delega el procesamiento de dicha opción a la
        rutina correspondiente.

        El programa continúa ejecutándose hasta que el
        usuario seleccione la opción de salida.
        */
        int opcion = -1;
        do {
            menu();
            opcion = Integer.parseInt(in.readLine());
            procesarOpcion(opcion);
        } while (opcion != 0);
    }

    //MENU
    public static void menu() {
        out.println("""
                ============================
                ANALIZADOR DE EXPRESIONES
                ============================
                1. Ingresar expresion
                2. Validar expresion
                3. Mostrar expresion
                4. Limpiar expresion
                0. Salir
                ============================
                """);
        out.print("Seleccione una opcion: ");
    }

    //PROCESAR OPCION
    public static void procesarOpcion(int opcion) throws IOException{
        /*
        Procesa la opción seleccionada por el usuario.

        Dependiendo de la opción ingresada, invoca la
        rutina correspondiente para ingresar, validar,
        mostrar o limpiar la expresión.

        Si la opción no pertenece al menú, se informa
        al usuario que la selección es inválida.
        */
        switch (opcion) {
            case 1:
                ingresarExpresion();
                break;
            case 2:
                validarExpresion();
                break;
            case 3:
                mostrarExpresion();
                break;
            case 4:
                limpiarExpresion();
                break;
            case 0:
                out.println("Saliendo del programa...");
                break;
            default:
                out.println("Opcion invalida.");
        }
    }

    //INGRESAR EXPRESION
    public static void ingresarExpresion() throws IOException {
        /*
        Permite al usuario registrar una expresión
        aritmética que posteriormente podrá ser
        mostrada, validada o eliminada.
        */
        out.print("Digite una expresion aritmetica: ");
        expresion = in.readLine();

        out.println("Expresion registrada.");
    }

    //VALIDAR EXPRESION
    public static void validarExpresion() {
        /*
        Valida la expresión actualmente almacenada.

        La validación se realiza en el siguiente orden:

        1. Verifica que exista una expresión registrada.
        2. Verifica que los paréntesis estén balanceados.
        3. Verifica que la expresión no inicie ni termine
           con un operador.
        4. Verifica que no existan operadores consecutivos.
        5. Verifica que no exista un operador inmediatamente
           después de un paréntesis de apertura.
        6. Si todas las validaciones son correctas,
           la expresión se considera válida.
           */
        if (expresion.isEmpty()) {
            out.println("Primero debe ingresar una expresion.");
            return;
        }

        if (!validarParentesis(expresion)) {
            out.println("La expresion utiliza parentesis de manera erronea.");
            return;
        }

        if (!validarInicioFin(expresion)) {
            out.println("La expresion inicia o termina con un operador.");
            return;
        }

        if (!validarOperadores(expresion)) {
            out.println("La expresion tiene operadores consecutivos.");
            return;
        }

        if (!validarDespuesParentesis(expresion)) {
            out.println("Hay un operador despues de un parentesis de apertura.");
            return;
        }

        out.println("La expresion es valida.");
    }

    //MOSTRAR EXPRESION
    public static void mostrarExpresion() {
        /*
        Muestra la expresión almacenada actualmente.

        Si no existe una expresión registrada, se
        informa al usuario mediante un mensaje.
        */
        if (expresion.isEmpty()) {
            out.println("No hay expresion registrada.");
            return;
        }

        out.println("\nExpresion actual:");
        out.println(expresion);
    }

    //LIMPIAR EXPRESION
    public static void limpiarExpresion() {
        /*
        Elimina la expresión almacenada.

        La variable global se reinicia asignándole una
        cadena vacía, dejando el programa listo para
        registrar una nueva expresión.
        */
        expresion = "";
        out.println("Expresion eliminada.");
    }

    //VALIDAR PARENTESIS
    public static boolean validarParentesis(String expresion) {
        /*
        Verifica que los paréntesis de la expresión
        estén correctamente balanceados utilizando una pila.

        Funcionamiento:

        1. Se crea una pila vacía.
        2. Se recorre la expresión carácter por carácter.
        3. Cada paréntesis de apertura '(' se inserta
           en la pila mediante push().
        4. Cuando se encuentra un paréntesis de cierre ')',
           se verifica que exista un paréntesis de apertura
           pendiente en la pila.
        5. Si la pila está vacía, la expresión es inválida.
        6. Si existe un paréntesis pendiente, se elimina
           mediante pop().
        7. Al finalizar el recorrido, la pila debe quedar
           vacía para considerar la expresión válida.
        */
        Pila pila = new Pila();

        for (int i = 0; i < expresion.length(); i++) {

            char caracter = expresion.charAt(i);

            if (caracter == '(') {
                pila.push(caracter);

            } else if (caracter == ')') {

                if (pila.estaVacia()) {
                    return false;
                }
                pila.pop();
            }
        }
        return pila.estaVacia();
    }

    //VALIDAR OPERADORES
    public static boolean validarOperadores(String expresion){
        /*
        Verifica que no existan operadores consecutivos
        en la expresión.

        La rutina analiza pares de caracteres adyacentes.
        Si encuentra dos operadores seguidos (+, -, * o /),
        la expresión se considera inválida.

        Ejemplos inválidos:

        5++3
        7+-2
        4/*8
        */
        for (int i = 0; i < expresion.length() - 1; i++){
            char actual = expresion.charAt(i);
            char siguiente = expresion.charAt(i+1);

            if (esOperador(actual) && esOperador(siguiente)){
                return false;
            }
        }
        return true;
    }

    //ES OPERADOR
    public static boolean esOperador(char caracter){
        /*
        Determina si el carácter recibido corresponde
        a uno de los operadores aritméticos soportados
        por el programa.

        Retorna true para:
        +, -, * y /

        Retorna false para cualquier otro carácter.
        */
        return caracter == '+' ||
                caracter == '-' ||
                caracter == '*' ||
                caracter == '/';
    }

    //VALIDAR INICIO Y FIN
    public static boolean validarInicioFin(String expresion){
       /*
       Verifica que la expresión no comience ni termine
       con un operador aritmético.

        Obtiene el primer y el último carácter de la
        expresión y comprueba si alguno corresponde
        a un operador.

        Retorna false si la expresión inicia o finaliza
        con:
        +, -, * o /

        Retorna true si ambos extremos contienen
        caracteres válidos.
        */

        char primero = expresion.charAt(0);
        char ultimo = expresion.charAt(expresion.length() - 1);

        if(esOperador(primero) || esOperador(ultimo)){
            return false;
        }
        return true;
    }

    //VALIDAR DESPUES PARENTESIS
    public static boolean validarDespuesParentesis(String expresion){

        /*
        Verifica que después de un paréntesis de apertura
        no aparezca inmediatamente un operador.

        Recorre la expresión comparando cada carácter
        con el siguiente.

        Si encuentra la secuencia:

        (+
        (-
        (*
        (/

        retorna false, ya que una expresión aritmética
        no debería comenzar con un operador dentro de
        un paréntesis.

        Retorna true si no encuentra ninguna de estas
        situaciones.
        */

        for(int i = 0; i < expresion.length() - 1; i++){

            char actual = expresion.charAt(i);
            char siguiente = expresion.charAt(i + 1);

            if(actual == '(' && esOperador(siguiente)){
                return false;
            }
        }
        return true;
    }
}

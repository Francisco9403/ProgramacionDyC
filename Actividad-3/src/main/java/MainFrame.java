import javax.swing.*;

public class MainFrame extends JFrame {
    public static void main(String[] args){
        MainFrame mainFrame = new MainFrame();
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        DosContadoresV2 dosContadores =
                new DosContadoresV2(mainFrame.getContentPane());
        Observador observador = new Observador(dosContadores);
        mainFrame.pack();
        mainFrame.setVisible(true);
        dosContadores.start();
        observador.start();
    }
}


//Respuestas a las preguntas (DosContadores):
//a.- ¿Las variables contador1 y contador2 mantienen los mismos valores siempre?
//No, en algunos casos los valores pueden diferir. Esto ocurre debido a problemas de
// concurrencia: aunque ambos contadores se incrementan en el mismo hilo (DosContadores),
// la lectura y escritura de las variables no están garantizadas para ejecutarse atómicamente.


//b.- ¿De qué manera puede asegurar que las variables contador1 y contador2 contengan siempre el mismo valor? (DosContadoresV2)
//Existen varias formas de asegurarlo por ejemplo usando Sincronización con synchronized
//Podemos hacer que los incrementos de contador1 y contador2 sean atómicos usando un bloque synchronized:

//public synchronized void incrementarContadores() {
//    contador1++;
//    contador2++;
//}
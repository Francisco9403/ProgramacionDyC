import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class DosContadores extends Thread {
    private boolean started = false;
    private JTextField textContador1 = new JTextField(5),
            textContador2 = new JTextField(5);
    private JLabel label = new JLabel("contador 1 == contador 2");
    private int contador1 = 0;
    private int contador2 = 0;
    public DosContadores(Container container) {
        JPanel panel = new JPanel();
        panel.add(textContador1);
        panel.add(textContador2);
        panel.add(label);
        container.add(panel);
    }
    @Override
    public void start() {
        if (!started) {
            started = true;
            super.start();
        }
    }
    @Override
    public void run() {
        while (true) {
            textContador1.setText(Integer.toString(contador1++));
            textContador2.setText(Integer.toString(contador2++));
            try {
                sleep(500);
            } catch (InterruptedException e) {}
        }
    }
    public void testSincro() {
        if (contador1 != contador2) {
            label.setText("No Sincronizados");
        }else{
            label.setText("Sincronizados");
        }
    }
}

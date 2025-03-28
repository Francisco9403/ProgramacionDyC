import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AppInteractua extends JFrame {
    public static void main(String[] args) {
        AppInteractua applet = new AppInteractua();
        applet.init();
    }

    private JPanel panel = new JPanel();
    private JButton start = new JButton("Empezar");
    private JButton onoff = new JButton("On/Off");
    private JTextField t = new JTextField(10);
    private boolean runflag = true;
    private int count = 0;
    private Thread workerThread; // Hilo de trabajo

    public void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start.addActionListener((e) -> startCounting());
        onoff.addActionListener((e) -> runflag = !runflag);
        panel.add(t);
        panel.add(start);
        panel.add(onoff);
        this.getContentPane().add(panel);
        this.pack();
        this.setVisible(true);
    }

    public void startCounting() {
        if (workerThread == null || !workerThread.isAlive()) {
            workerThread = new Thread(() -> go());
            workerThread.start();
        }
    }

    public void go() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                return; // Terminar el hilo si es interrumpido
            }
            if (runflag) {
                t.setText(Integer.toString(count++));
            }
        }
    }
}

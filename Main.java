import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Ensure that the GUI creation is done on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create and display the GUI frame
                MyFrame frame = new MyFrame();
                frame.setVisible(true);
            }
        });
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MyFrame extends JFrame {

    private JTextArea textArea;
    private Color currentColor;

    public MyFrame() {
        // Set the title of the frame
        setTitle("Java Assignment GUI");

        // Ensure the application exits when the frame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the frame
        setSize(400, 300);

        // Initialize the text area for output
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // Create a scroll pane to contain the text area
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Set initial background color
        currentColor = Color.WHITE;

        // Create the content pane panel with overridden paintComponent method
        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(currentColor);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        contentPane.setLayout(new BorderLayout());
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Set this panel as the content pane
        setContentPane(contentPane);

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create the menu
        JMenu menu = new JMenu("Options");

        // Create the menu items
        JMenuItem dateTimeItem = new JMenuItem("Print Date and Time");
        JMenuItem saveToFileItem = new JMenuItem("Save to File");
        JMenuItem changeColorItem = new JMenuItem("Change Background Color");
        JMenuItem exitItem = new JMenuItem("Exit");

        // Add action listeners for each menu item
        dateTimeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get current date and time
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                // Print date and time in the text area
                textArea.setText(formatter.format(date));
            }
        });

        saveToFileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save the contents of the text area to a file
                try {
                    FileWriter writer = new FileWriter("log.txt", true);
                    writer.write(textArea.getText() + "\n");
                    writer.close();
                    JOptionPane.showMessageDialog(null, "Text saved to log.txt");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        changeColorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Change the background color to a random shade of green
                Random rand = new Random();
                float hue = 0.25f + rand.nextFloat() * 0.2f; // Green hue range
                currentColor = Color.getHSBColor(hue, 1.0f, 1.0f);
                contentPane.repaint(); // Force repaint to apply the new background color
                changeColorItem.setText(String.format("Change Background Color (Hue: %.2f)", hue));
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit the program
                System.exit(0);
            }
        });

        // Add the menu items to the menu
        menu.add(dateTimeItem);
        menu.add(saveToFileItem);
        menu.add(changeColorItem);
        menu.add(exitItem);

        // Add the menu to the menu bar
        menuBar.add(menu);

        // Set the menu bar for the frame
        setJMenuBar(menuBar);

        // Set the frame to be visible
        setVisible(true);
    }
}
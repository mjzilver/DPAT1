package view;

import engine.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ControlView extends JPanel {
    private int width;
    private int height;

    private final JButton switchStateButton;
    private final JButton checkButton;
    private final JButton uncheckButton;
    private final JButton fileButton;

    public ControlView(Sudoku sudoku, int width) {
        this.width = width;
        this.height = 50;

        switchStateButton = new JButton("Switch State");
        switchStateButton.addActionListener(e -> {
            sudoku.switchState();
            sudoku.getView().requestFocusInWindow();
        });

        checkButton = new JButton("Check Answer");
        checkButton.addActionListener(e -> {
            sudoku.checkAll();
            sudoku.getView().requestFocusInWindow();
        });

        uncheckButton = new JButton("Stop checking");
        uncheckButton.addActionListener(e -> {
            sudoku.uncheckAll();
            sudoku.getView().requestFocusInWindow();
        });

        fileButton = new JButton("Select file");
        fileButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser("resources/");
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setAcceptAllFileFilterUsed(true);

            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                // Get the selected file and display its name
                File selectedFile = chooser.getSelectedFile();
                sudoku.openBoard(selectedFile.getName());
            }
        });

        this.add(switchStateButton);
        this.add(checkButton);
        this.add(uncheckButton);
        this.add(fileButton);
    }

    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("Arial", Font.BOLD, 16));

        switchStateButton.setBounds(10, 10, 150, 30);
        switchStateButton.setForeground(Color.black);
        switchStateButton.setBackground(Color.blue);

        checkButton.setBounds(170, 10, 150, 30);
        checkButton.setForeground(Color.black);
        checkButton.setBackground(Color.green);

        uncheckButton.setBounds(340, 10, 150, 30);
        uncheckButton.setForeground(Color.black);
        uncheckButton.setBackground(Color.yellow);

        fileButton.setBounds(510, 10, 150, 30);
        fileButton.setForeground(Color.black);
        fileButton.setBackground(Color.pink);
    }
}

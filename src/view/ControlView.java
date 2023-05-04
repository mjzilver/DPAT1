package view;

import engine.Sudoku;

import javax.swing.*;
import java.awt.*;

public class ControlView extends JPanel {
    public int width;
    public int height;

    private final JButton switchStateButton;
    private final JButton checkButton;
    private final JButton uncheckButton;

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

        this.add(switchStateButton);
        this.add(checkButton);
        this.add(uncheckButton);
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
        switchStateButton.setForeground(Color.BLACK);
        switchStateButton.setBackground(Color.BLUE);

        checkButton.setBounds(170, 10, 150, 30);
        checkButton.setForeground(Color.BLACK);
        checkButton.setBackground(Color.GREEN);

        uncheckButton.setBounds(340, 10, 150, 30);
        uncheckButton.setForeground(Color.BLACK);
        uncheckButton.setBackground(Color.yellow);
    }
}

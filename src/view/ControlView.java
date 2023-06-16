package view;

import javax.swing.*;

import engine.GUIController;

import java.awt.*;
import java.io.File;

public class ControlView extends JPanel {
    private int width;
    private int height;

    JButton[] buttons = new JButton[4];

    public ControlView(GUIController guiController, int width) {
        this.width = width;
        this.height = 50;

        JButton switchStateButton = new JButton("Switch State");
        switchStateButton.addActionListener(e -> {
            guiController.switchState();
            guiController.getView().requestFocusInWindow();
        });

        JButton checkButton = new JButton("Check Answer");
        checkButton.addActionListener(e -> {
            guiController.getBoardController().checkAll();
            guiController.getView().requestFocusInWindow();
        });

        JButton uncheckButton = new JButton("Stop checking");
        uncheckButton.addActionListener(e -> {
            guiController.getBoardController().uncheckAll();
            guiController.getView().requestFocusInWindow();
        });

        JButton fileButton = new JButton("Select file");
        fileButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser("resources/");
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setAcceptAllFileFilterUsed(true);

            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                guiController.openBoard(selectedFile.getName());
            }
        });

        this.add(switchStateButton);
        this.add(checkButton);
        this.add(uncheckButton);
        this.add(fileButton);

        buttons[0] = switchStateButton;
        buttons[1] = checkButton;
        buttons[2] = uncheckButton;
        buttons[3] = fileButton;
    }

    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("Arial", Font.BOLD, 12));

        for (JButton button : buttons) {
            button.setBounds(button.getX(), 10, 120, 30);
            button.setForeground(Color.black);
            button.setBackground(Color.blue);
        }
    }
}

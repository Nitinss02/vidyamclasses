import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MemoryGame extends JFrame {

    private JButton[][] buttons;
    private List<String> symbols;
    private int matches;
    private boolean firstClick;
    private JButton firstButton;
    private Timer timer;

    public MemoryGame() {
        super("Memory Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        setLocationRelativeTo(null);

        // Initialize symbols
        symbols = new ArrayList<>();
        symbols.add("A");
        symbols.add("B");
        symbols.add("C");
        symbols.add("D");
        symbols.add("E");
        symbols.add("F");
        symbols.add("G");
        symbols.add("H");
        symbols.add("A");
        symbols.add("B");
        symbols.add("C");
        symbols.add("D");
        symbols.add("E");
        symbols.add("F");
        symbols.add("G");
        symbols.add("H");

        // Shuffle symbols
        Collections.shuffle(symbols);

        // Create buttons
        buttons = new JButton[4][4];
        JPanel gridPanel = new JPanel(new GridLayout(4, 4));
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                gridPanel.add(buttons[i][j]);
            }
        }

        // Add grid to frame
        add(gridPanel);
        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Reveal symbol
            buttons[row][col].setText(symbols.get(row * 4 + col));
            buttons[row][col].setEnabled(false);

            // First click
            if (!firstClick) {
                firstClick = true;
                firstButton = buttons[row][col];
            } else {
                // Second click
                firstClick = false;

                // Check for match
                if (firstButton.getText().equals(buttons[row][col].getText())) {
                    matches++;
                    if (matches == 8) {
                        // Game over
                        JOptionPane.showMessageDialog(null, "Congratulations! You won!");
                        timer.stop();
                        return;
                    }
                } else {
                    // No match, hide symbols after a delay
                    timer = new Timer(1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            firstButton.setText("");
                            buttons[row][col].setText("");
                            firstButton.setEnabled(true);
                            buttons[row][col].setEnabled(true);
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MemoryGame();
            }
        });
    }
}
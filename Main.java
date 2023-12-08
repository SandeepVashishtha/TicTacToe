import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToe tictactoe = new TicTacToe();
        });
    }
}

class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    JButton resetButton = new JButton("Reset");
    boolean player1_turn;

    int player1Score = 0;
    int player2Score = 0;
    JLabel player1ScoreLabel = new JLabel("Player 1: 0");
    JLabel player2ScoreLabel = new JLabel("Player 2: 0");

    TicTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());

        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(25, 255, 0));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        title_panel.add(player1ScoreLabel, BorderLayout.WEST);
        title_panel.add(player2ScoreLabel, BorderLayout.EAST);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        resetButton.setFont(new Font("MV Boli", Font.BOLD, 30));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        title_panel.add(textfield, BorderLayout.CENTER);
        title_panel.add(resetButton, BorderLayout.EAST);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        firstTurn();

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            resetGame();
        } else {
            for (int i = 0; i < 9; i++) {
                if (e.getSource() == buttons[i]) {
                    if (player1_turn) {
                        if (buttons[i].getText().equals("")) {
                            buttons[i].setForeground(new Color(255, 0, 0));
                            buttons[i].setText("X");
                            player1_turn = false;
                            textfield.setText("O turn");
                            check();
                        }
                    } else {
                        if (buttons[i].getText().equals("")) {
                            buttons[i].setForeground(new Color(0, 0, 255));
                            buttons[i].setText("O");
                            player1_turn = true;
                            textfield.setText("X turn");
                            check();
                        }
                    }
                }
            }
        }
    }

    private void firstTurn() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (random.nextInt(2) == 0) {
            player1_turn = true;
            textfield.setText("X turn");
        } else {
            player1_turn = false;
            textfield.setText("O turn");
        }
    }

    private void resetGame() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
            buttons[i].setBackground(null);
        }
        player1_turn = (random.nextInt(2) == 0);
        if (player1_turn) {
            textfield.setText("X turn");
        } else {
            textfield.setText("O turn");
        }
        player1Score = 0;
        player2Score = 0;
        player1ScoreLabel.setText("Player 1: 0");
        player2ScoreLabel.setText("Player 2: 0");
    }
    public void check() {
        // check X win conditions
        if ((buttons[0].getText() == "X") &&
                (buttons[1].getText() == "X") &&
                (buttons[2].getText() == "X")) {
            xWins(0, 1, 2);
        }
        if ((buttons[3].getText() == "X") &&
                (buttons[4].getText() == "X") &&
                (buttons[5].getText() == "X")) {
            xWins(3, 4, 5);
        }
        if ((buttons[6].getText() == "X") &&
                (buttons[7].getText() == "X") &&
                (buttons[8].getText() == "X")) {
            xWins(6, 7, 8);
        }
        if ((buttons[0].getText() == "X") &&
                (buttons[3].getText() == "X") &&
                (buttons[6].getText() == "X")) {
            xWins(0, 3, 6);
        }
        if ((buttons[1].getText() == "X") &&
                (buttons[4].getText() == "X") &&
                (buttons[7].getText() == "X")) {
            xWins(1, 4, 7);
        }
        if ((buttons[2].getText() == "X") &&
                (buttons[5].getText() == "X") &&
                (buttons[8].getText() == "X")) {
            xWins(2, 5, 8);
        }
        if ((buttons[0].getText() == "X") &&
                (buttons[4].getText() == "X") &&
                (buttons[8].getText() == "X")) {
            xWins(0, 4, 8);
        }
        if ((buttons[2].getText() == "X") &&
                (buttons[4].getText() == "X") &&
                (buttons[6].getText() == "X")) {
            xWins(2, 4, 6);
        }
        // check O win conditions
        if ((buttons[0].getText() == "O") &&
                (buttons[1].getText() == "O") &&
                (buttons[2].getText() == "O")) {
            oWins(0, 1, 2);
        }
        if ((buttons[3].getText() == "O") &&
                (buttons[4].getText() == "O") &&
                (buttons[5].getText() == "O")) {
            oWins(3, 4, 5);
        }
        if ((buttons[6].getText() == "O") &&
                (buttons[7].getText() == "O") &&
                (buttons[8].getText() == "O")) {
            oWins(6, 7, 8);
        }
        if ((buttons[0].getText() == "O") &&
                (buttons[3].getText() == "O") &&
                (buttons[6].getText() == "O")) {
            oWins(0, 3, 6);
        }
        if ((buttons[1].getText() == "O") &&
                (buttons[4].getText() == "O") &&
                (buttons[7].getText() == "O")) {
            oWins(1, 4, 7);
        }
        if ((buttons[2].getText() == "O") &&
                (buttons[5].getText() == "O") &&
                (buttons[8].getText() == "O")) {
            oWins(2, 5, 8);
        }
        if ((buttons[0].getText() == "O") &&
                (buttons[4].getText() == "O") &&
                (buttons[8].getText() == "O")) {
            oWins(0, 4, 8);
        }
        if ((buttons[2].getText() == "O") &&
                (buttons[4].getText() == "O") &&
                (buttons[6].getText() == "O")) {
            oWins(2, 4, 6);
        }
    }

    public void xWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("X wins");
        player1Score++;
        player1ScoreLabel.setText("Player 1: " + player1Score);
        textfield.setText("Player 1 wins");
    }

    public void oWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("O wins");
        player2Score++;
        player2ScoreLabel.setText("Player 2: " + player2Score);
        textfield.setText("Player 2 wins");
    }
}

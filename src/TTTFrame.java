import javax.swing.*;
import java.awt.*;

public class TTTFrame extends JFrame {
    private final TTTBoard board;
    private final TTTPlayer playerX;
    private final TTTPlayer playerO;
    private TTTPlayer currentPlayer;
    private boolean gameEnded;

    public TTTFrame() {
        playerX = new TTTPlayer("Player X", 'X');
        playerO = new TTTPlayer("Player O", 'O');
        currentPlayer = playerX;
        gameEnded = false;

        setTitle("Tic-Tac-Toe");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        board = new TTTBoard(this::handleTileClick);
        add(board, BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        JButton resetButton = new JButton("Reset");
        JButton quitButton = new JButton("Quit");


        resetButton.addActionListener(e -> {
            int confirmed = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to reset the game?", "Quit Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                resetGame();
            }

        });
        quitButton.addActionListener(e -> {
            int confirmed = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to quit?", "Quit Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (confirmed == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        panel.add(resetButton);
        panel.add(quitButton);

        return panel;
    }

    private void handleTileClick(TTTTileButton tile) {
        if (gameEnded || !tile.getText().isEmpty()) return;

        tile.setText(String.valueOf(currentPlayer.getSymbol()));

        if (board.checkWinner(currentPlayer.getSymbol())) {
            gameEnded = true;
            JOptionPane.showMessageDialog(this, currentPlayer.getName() + " wins!");
            askPlayAgain();
        } else if (board.isFull()) {
            gameEnded = true;
            JOptionPane.showMessageDialog(this, "It's a tie!");
            askPlayAgain();
        } else {
            switchPlayer();
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
    }

    private void resetGame() {
        board.resetBoard();
        gameEnded = false;
        currentPlayer = playerX;
    }
    private void askPlayAgain() {
        int option = JOptionPane.showConfirmDialog(this,
                "Do you want to play again?", "Play Again",
                JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

}
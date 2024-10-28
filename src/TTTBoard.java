import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class TTTBoard extends JPanel {
    private final TTTTileButton[][] tiles;

    public TTTBoard(Consumer<TTTTileButton> tileClickHandler) {
        setLayout(new GridLayout(3, 3));
        tiles = new TTTTileButton[3][3];

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                TTTTileButton tile = new TTTTileButton(row, col);
                tile.addActionListener(e -> tileClickHandler.accept(tile));
                tiles[row][col] = tile;
                add(tile);
            }
        }
    }

    public void resetBoard() {
        for (TTTTileButton[] row : tiles) {
            for (TTTTileButton tile : row) {
                tile.reset();
            }
        }
    }

    public boolean isFull() {
        for (TTTTileButton[] row : tiles) {
            for (TTTTileButton tile : row) {
                if (tile.getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWinner(char symbol) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (checkRow(i, symbol) || checkColumn(i, symbol)) {
                return true;
            }
        }
        return checkDiagonals(symbol);
    }

    private boolean checkRow(int row, char symbol) {
        return tiles[row][0].getText().equals(String.valueOf(symbol)) &&
                tiles[row][1].getText().equals(String.valueOf(symbol)) &&
                tiles[row][2].getText().equals(String.valueOf(symbol));
    }

    private boolean checkColumn(int col, char symbol) {
        return tiles[0][col].getText().equals(String.valueOf(symbol)) &&
                tiles[1][col].getText().equals(String.valueOf(symbol)) &&
                tiles[2][col].getText().equals(String.valueOf(symbol));
    }

    private boolean checkDiagonals(char symbol) {
        return (tiles[0][0].getText().equals(String.valueOf(symbol)) &&
                tiles[1][1].getText().equals(String.valueOf(symbol)) &&
                tiles[2][2].getText().equals(String.valueOf(symbol))) ||
                (tiles[0][2].getText().equals(String.valueOf(symbol)) &&
                        tiles[1][1].getText().equals(String.valueOf(symbol)) &&
                        tiles[2][0].getText().equals(String.valueOf(symbol)));
    }

}

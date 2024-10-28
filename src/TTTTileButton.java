import javax.swing.*;

public class TTTTileButton extends JButton {
    private final int row;
    private final int col;

    public TTTTileButton(int row, int col) {
        this.row = row;
        this.col = col;
        setFont(getFont().deriveFont(60f));
        setFocusPainted(false);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void reset() {
        setText("");
    }
}

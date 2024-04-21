package Models;

public class Cell {
    private int row;
    private int col;
    private Player player;

    CellState cellState;

    public int getRow() {
        return row;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public CellState getCellState() {
        return cellState;
    }

    public Player getPlayer() {
        return player;
    }

    public int getCol() {
        return col;
    }

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.cellState = CellState.EMPTY;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }
}

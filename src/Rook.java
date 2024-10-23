public class Rook extends ChessPiece {

    public Rook(String color) {
        super(color);
        this.check = true;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }
        if (line != toLine && column != toColumn) {
            return false;
        }

        int step = line == toLine ? (toColumn > column ? 1 : -1) : (toLine > line ? 1 : -1);
        int current = line == toLine ? column + step : line + step;
        int end = line == toLine ? toColumn : toLine;

        while (current != end) {
            if (line == toLine && chessBoard.board[line][current] != null) return false;
            if (column == toColumn && chessBoard.board[current][column] != null) return false;
            current += step;
        }

        return chessBoard.board[toLine][toColumn] == null ||
                !chessBoard.board[toLine][toColumn].getColor().equals(this.color);
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
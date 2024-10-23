public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }
        if (Math.abs(toLine - line) != Math.abs(toColumn - column)) {
            return false;
        }

        int lineStep = (toLine - line) > 0 ? 1 : -1;
        int columnStep = (toColumn - column) > 0 ? 1 : -1;

        int currentLine = line + lineStep;
        int currentColumn = column + columnStep;

        while (currentLine != toLine && currentColumn != toColumn) {
            if (chessBoard.board[currentLine][currentColumn] != null) {
                return false;
            }
            currentLine += lineStep;
            currentColumn += columnStep;
        }

        return chessBoard.board[toLine][toColumn] == null ||
                !chessBoard.board[toLine][toColumn].getColor().equals(this.color);
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
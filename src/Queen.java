public class Queen extends ChessPiece {

    public Queen(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        if (line == toLine || column == toColumn) {
            Rook rookEquivalent = new Rook(this.color);
            return rookEquivalent.canMoveToPosition(chessBoard, line, column, toLine, toColumn);
        } else if (Math.abs(toLine - line) == Math.abs(toColumn - column)) {
            Bishop bishopEquivalent = new Bishop(this.color);
            return bishopEquivalent.canMoveToPosition(chessBoard, line, column, toLine, toColumn);
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }
        if (line == toLine && column == toColumn) {
            return false;
        }

        int direction = this.color.equals("White") ? 1 : -1;
        int startLine = this.color.equals("White") ? 1 : 6;

        if (column == toColumn) {

            if ((toLine - line) == direction && chessBoard.board[toLine][toColumn] == null) {
                return true;
            }
            if (line == startLine && (toLine - line) == 2 * direction &&
                    chessBoard.board[toLine][toColumn] == null &&
                    chessBoard.board[line + direction][toColumn] == null) {
                return true;
            }
        }
        else if (Math.abs(column - toColumn) == 1 && (toLine - line) == direction) {
            return chessBoard.board[toLine][toColumn] != null &&
                    !chessBoard.board[toLine][toColumn].getColor().equals(this.color);
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
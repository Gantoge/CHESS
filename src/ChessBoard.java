public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8];
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn) && checkPos(endLine) && checkPos(endColumn)) {
            ChessPiece piece = board[startLine][startColumn];

            if (piece != null && nowPlayer.equals(piece.getColor())) {
                if (piece.canMoveToPosition(this, startLine, startColumn, endLine, endColumn)
                        && (piece instanceof Horse || isPathClear(startLine, startColumn, endLine, endColumn))) {

                    board[endLine][endColumn] = piece;
                    board[startLine][startColumn] = null;

                    if (piece instanceof King || piece instanceof Rook) {
                        piece.check = false;
                    }

                    nowPlayer = nowPlayer.equals("White") ? "Black" : "White";
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isPathClear(int startLine, int startColumn, int endLine, int endColumn) {
        int stepLine = Integer.signum(endLine - startLine);
        int stepColumn = Integer.signum(endColumn - startColumn);

        int currentLine = startLine + stepLine;
        int currentColumn = startColumn + stepColumn;

        while (currentLine != endLine || currentColumn != endColumn) {
            if (board[currentLine][currentColumn] != null) {
                return false;
            }
            currentLine += stepLine;
            currentColumn += stepColumn;
        }
        return true;
    }

    public void printBoard() {
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2 (Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1 (White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling0() {
        return attemptCastling(0, 0, 4); // Для белых
    }

    public boolean castling7() {
        return attemptCastling(0, 7, 4); // Для белых
    }

    private boolean attemptCastling(int row, int rookColumn, int kingColumn) {
        ChessPiece rook = board[row][rookColumn];
        ChessPiece king = board[row][kingColumn];

        if (!(rook instanceof Rook) || !(king instanceof King) || !rook.check || !king.check) {
            return false;
        }

        int direction = rookColumn > kingColumn ? -1 : 1;
        for (int i = kingColumn + direction; i != rookColumn; i += direction) {
            if (board[row][i] != null) {
                return false;
            }
        }

        int newKingColumn = kingColumn + 2 * direction;

        if (((King) king).isUnderAttack(this, row, kingColumn) ||
                ((King) king).isUnderAttack(this, row, kingColumn + direction) ||
                ((King) king).isUnderAttack(this, row, newKingColumn)) {
            return false;
        }

        board[row][newKingColumn] = king;
        board[row][kingColumn] = null;
        board[row][rookColumn - direction] = rook;
        board[row][rookColumn] = null;

        king.check = false;
        rook.check = false;

        nowPlayer = nowPlayer.equals("White") ? "Black" : "White";
        return true;
    }
}
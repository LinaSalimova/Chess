package org.example;

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
            if (board[startLine][startColumn] == null) {
                return false;
            }

            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) {
                return false;
            }

            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                ChessPiece movingPiece = board[startLine][startColumn];
                board[endLine][endColumn] = movingPiece;
                board[startLine][startColumn] = null;
                movingPiece.isFirstMove = false;
                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";
                return true;
            }
        }
        return false;
    }
    public void printBoard() {
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
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
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling0() {
        return doCastling(0);
    }

    public boolean castling7() {
        return doCastling(7);
    }

    private boolean doCastling(int rookColumn) {
        int kingColumn = 4;
        int kingTargetColumn = rookColumn == 0 ? 2 : 6;
        int rookTargetColumn = rookColumn == 0 ? 3 : 5;
        int row = nowPlayer.equals("White") ? 0 : 7;

        King king = (King) board[row][kingColumn];
        Rook rook = (Rook) board[row][rookColumn];

        if (king == null || rook == null || !king.isFirstMove || !rook.isFirstMove) {
            return false;
        }

        if (!isPathClear(row, Math.min(kingColumn, rookColumn) + 1, Math.max(kingColumn, rookColumn))) {
            return false;
        }

        for (int col = Math.min(kingColumn, kingTargetColumn); col <= Math.max(kingColumn, kingTargetColumn); col++) {
            if (((King) board[row][kingColumn]).isUnderAttack(this, row, col)) {
                return false;
            }
        }

        board[row][kingTargetColumn] = king;
        board[row][rookTargetColumn] = rook;
        board[row][kingColumn] = null;
        board[row][rookColumn] = null;
        king.isFirstMove = false;
        rook.isFirstMove = false;
        this.nowPlayer = nowPlayer.equals("White") ? "Black" : "White";
        return true;
    }

    private boolean isPathClear(int line, int startCol, int endCol) {
        for (int col = startCol; col < endCol; col++) {
            if (board[line][col] != null) {
                return false;
            }
        }
        return true;
    }
}
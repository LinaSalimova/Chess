package org.example;

public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) return false;
        if (line == toLine && column == toColumn) return false;

        if (Math.abs(toLine - line) == Math.abs(toColumn - column)) {
            return isPathClear(chessBoard, line, column, toLine, toColumn);
        }

        return false;
    }

    private boolean isPathClear(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int stepRow = Integer.compare(toLine, line);
        int stepCol = Integer.compare(toColumn, column);

        int currentRow = line + stepRow;
        int currentCol = column + stepCol;

        while (currentRow != toLine && currentCol != toColumn) {
            if (chessBoard.board[currentRow][currentCol] != null) {
                return false;
            }
            currentRow += stepRow;
            currentCol += stepCol;
        }

        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        return targetPiece == null || !targetPiece.getColor().equals(this.color);
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
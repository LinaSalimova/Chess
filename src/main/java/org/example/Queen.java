package org.example;

public class Queen extends ChessPiece {
    public Queen(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) return false;
        if (line == toLine && column == toColumn) return false;

        boolean isDiagonalMove = Math.abs(toLine - line) == Math.abs(toColumn - column);
        boolean isStraightMove = toLine == line || toColumn == column;

        if (isDiagonalMove || isStraightMove) {
            return isPathClear(chessBoard, line, column, toLine, toColumn);
        }

        return false;
    }

    private boolean isPathClear(ChessBoard chessBoard, int startLine, int startCol, int endLine, int endCol) {
        int lineStep = Integer.compare(endLine, startLine);
        int colStep = Integer.compare(endCol, startCol);

        int currentLine = startLine + lineStep;
        int currentCol = startCol + colStep;

        while (currentLine != endLine || currentCol != endCol) {
            if (chessBoard.board[currentLine][currentCol] != null) {
                return false;
            }
            currentLine += lineStep;
            currentCol += colStep;
        }

        ChessPiece targetPiece = chessBoard.board[endLine][endCol];
        return targetPiece == null || !targetPiece.getColor().equals(this.color);
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
package org.example;

public class Pawn extends ChessPiece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) return false;
        if (line == toLine && column == toColumn) return false;

        int direction = color.equals("White") ? 1 : -1;
        int startLine = color.equals("White") ? 1 : 6;

        if (column == toColumn) {
            if (toLine - line == direction && chessBoard.board[toLine][toColumn] == null) return true;
            if (isFirstMove && line == startLine && toLine - line == 2 * direction
                    && chessBoard.board[toLine][toColumn] == null
                    && chessBoard.board[line + direction][column] == null) return true;
        } else if (Math.abs(toColumn - column) == 1 && toLine - line == direction) {
            return chessBoard.board[toLine][toColumn] != null
                    && !chessBoard.board[toLine][toColumn].getColor().equals(this.color);
        }

        return false;
    }

    public boolean isValidPawnMove(ChessBoard chessBoard, int startLine, int startColumn, int endLine, int endColumn) {
        if (!chessBoard.checkPos(endLine) || !chessBoard.checkPos(endColumn)) return false;
        if (startLine == endLine && startColumn == endColumn) return false;

        int direction = color.equals("White") ? 1 : -1;
        int startingRow = color.equals("White") ? 1 : 6;

        // Обычный ход вперед
        if (startColumn == endColumn && endLine == startLine + direction && chessBoard.board[endLine][endColumn] == null) {
            return true;
        }

        // Ход на две клетки вперед при первом ходе
        if (isFirstMove && startColumn == endColumn && endLine == startLine + 2 * direction
                && chessBoard.board[endLine][endColumn] == null
                && chessBoard.board[startLine + direction][startColumn] == null
                && startLine == startingRow) {
            return true;
        }

        // Взятие по диагонали
        if (Math.abs(endColumn - startColumn) == 1 && endLine == startLine + direction) {
            return chessBoard.board[endLine][endColumn] != null
                    && !chessBoard.board[endLine][endColumn].getColor().equals(this.color);
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
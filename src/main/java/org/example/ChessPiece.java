package org.example;

public abstract class ChessPiece {
    protected String color;
    protected boolean isFirstMove = true;// Для отслеживания, двигалась ли фигура

    public ChessPiece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    public abstract String getSymbol();
}
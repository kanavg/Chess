package com.kanav.chess;

public class Square {
	public enum SquareColor {
		White, Black
	}
	
	public enum SquareState {
		Empty, Occupied
	}
	
	private SquareColor color;
	private SquareState state;
	private Piece piece;
	private int row;
	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	private int col;

	public Square(int i, int j) {
		row = i;
		col = j;
	}

	public SquareColor getColor() {
		return color;
	}

	public void setColor(SquareColor color) {
		this.color = color;
	}

	public SquareState getState() {
		return state;
	}

	public void setState(SquareState state) {
		this.state = state;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

}

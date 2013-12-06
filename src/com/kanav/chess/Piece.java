package com.kanav.chess;

import com.kanav.chess.Square.SquareState;

public abstract class Piece {
	public enum PieceType {
		Pawn, Rook, Knight, Bishop, Queen, King
	}
	public enum PieceColor {
		White, Black
	}
	
	public enum PieceState {
		Alive, Captured
	}
	
	public static String TAG = "Piece";
	
	private PieceType type;
	private PieceColor color;
	private Square square;
	
	public PieceType getType() {
		return type;
	}
	public void setType(PieceType type) {
		this.type = type;
	}
	public PieceColor getColor() {
		return color;
	}
	public void setColor(PieceColor color) {
		this.color = color;
	}

	public Square getSquare() {
		return square;
	}
	public void setSquare(Square square) {
		this.square = square;
	}
	
	public int move(ChessBoard boardInstance, int startPos, int startRow,
			int startCol, int finalPos, int finalRow, int finalCol) {
		Square[][] squares = boardInstance.getSquares();
		Piece origPiece = squares[startRow][startCol].getPiece();
		Piece capturedPiece = squares[finalRow][finalCol].getPiece();
		squares[startRow][startCol].setPiece(null);
		squares[startRow][startCol].setState(SquareState.Empty);
		MainActivity.pieces[finalPos] = MainActivity.pieces[startPos];
		MainActivity.pieces[startPos] = null;
		squares[finalRow][finalCol].setState(SquareState.Occupied);
		squares[finalRow][finalCol].setPiece(origPiece);
		origPiece.setSquare(squares[finalRow][finalCol]);
		if(capturedPiece != null) {
			if(capturedPiece.getColor() == PieceColor.Black) {
				boardInstance.blackPieces.remove(capturedPiece);
			}
			else {
				boardInstance.whitePieces.remove(capturedPiece);
			}
		}
		if((capturedPiece != null) && (capturedPiece.getType() == PieceType.King)) {
			return 1;
		}
		else
			return 0;
	}
	
	public abstract boolean isValid(ChessBoard boardInstance, int startPos, int startRow, int startCol, int finalPos, int finalRow, int finalCol);
	public abstract int randomMove(ChessBoard boardInstance, int startRow, int startCol);

}

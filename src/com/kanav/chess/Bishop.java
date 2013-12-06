package com.kanav.chess;

import android.util.Log;

import com.kanav.chess.Square.SquareColor;
import com.kanav.chess.Square.SquareState;

public class Bishop extends Piece {

	@Override
	public boolean isValid(ChessBoard boardInstance, int startPos,
			int startRow, int startCol, int finalPos, int finalRow, int finalCol) {
		if( Math.abs(finalRow - startRow) !=  Math.abs(finalCol - startCol)) {
			return false;
		}
		if(finalRow < 0 || finalRow > 7 || finalCol < 0 || finalCol > 7) {
			return false;
		}
		Square[][] squares = boardInstance.getSquares();
		if(squares[finalRow][finalCol].getState() == SquareState.Occupied) {
			PieceColor startColor = squares[startRow][startCol].getPiece().getColor();
			PieceColor finalColor = squares[finalRow][finalCol].getPiece().getColor();
			if(startColor.compareTo(finalColor) == 0) {
				return false;
			}
		}
		if(finalRow > startRow) {
			if(finalCol > startCol) {
				for(int i = startRow + 1, j = startCol + 1; i< finalRow && j < finalCol;i++, j++) {
					if(squares[i][j].getState() == SquareState.Occupied) {
						return false;
					}
				}
				return true;
			}
			else {
				for(int i = startRow + 1, j = startCol - 1; i< finalRow && j > finalCol;i++, j--) {
					if(squares[i][j].getState() == SquareState.Occupied) {
						return false;
					}
				}
				return true;
			}
		}
		else {
			if(finalCol > startCol) {
				for(int i = startRow - 1, j = startCol + 1; i> finalRow && j < finalCol;i--, j++) {
					if(squares[i][j].getState() == SquareState.Occupied) {
						return false;
					}
				}
				return true;
			}
			else {
				for(int i = startRow - 1, j = startCol - 1; i> finalRow && j > finalCol;i--, j--) {
					if(squares[i][j].getState() == SquareState.Occupied) {
						return false;
					}
				}
				return true;
			}
		}
	}

	@Override
	public int randomMove(ChessBoard boardInstance, int startRow, int startCol) {
		int finalRow = startRow, finalCol = startCol;
		//lets trying moving in each of the four diagonals one by one
		if(isValid(boardInstance, -1, startRow, startCol, -1, startRow -1, startCol - 1)) {
			finalRow = startRow -1;
			finalCol = startCol - 1;
			return move(boardInstance, (startRow * 8 + startCol), startRow, startCol, (finalRow * 8 + finalCol), finalRow, finalCol);
		}
		else if(isValid(boardInstance, -1, startRow, startCol, -1, startRow +1, startCol + 1)) {
			finalRow = startRow +1;
			finalCol = startCol + 1;
			return move(boardInstance, (startRow * 8 + startCol), startRow, startCol, (finalRow * 8 + finalCol), finalRow, finalCol);
		}
		else if(isValid(boardInstance, -1, startRow, startCol, -1, startRow -1 , startCol + 1)) {
			finalRow = startRow - 1;
			finalCol = startCol + 1;
			return move(boardInstance, (startRow * 8 + startCol), startRow, startCol, (finalRow * 8 + finalCol), finalRow, finalCol);
		}
		else if(isValid(boardInstance, -1, startRow, startCol, -1, startRow + 1, startCol - 1)) {
			finalRow = startRow +1;
			finalCol = startCol - 1;
			return move(boardInstance, (startRow * 8 + startCol), startRow, startCol, (finalRow * 8 + finalCol), finalRow, finalCol);
		}
		else {
			return -1;
		}
	}

}

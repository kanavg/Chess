package com.kanav.chess;

import com.kanav.chess.Square.SquareState;

public class Rook extends Piece {

	@Override
	public boolean isValid(ChessBoard boardInstance, int startPos,
			int startRow, int startCol, int finalPos, int finalRow, int finalCol) {
		if((finalRow != startRow) && (finalCol != startCol)) {
			return false;
		}
		if(finalRow < 0 || finalRow > 7 || finalCol < 0 || finalCol > 7) {
			return false;
		}
		Square[][] squares = boardInstance.getSquares();
		if(squares[finalRow][finalCol].getState() == SquareState.Occupied) {
			if(squares[startRow][startCol].getPiece().getColor().compareTo(squares[finalRow][finalCol].getPiece().getColor()) == 0) {
				return false;
			}
		}
		if(finalCol == startCol) { //moved vertically
			if(finalRow > startRow) {
				for(int i = startRow + 1; i < finalRow; i++) {
					if(squares[i][finalCol].getState() == SquareState.Occupied) {
						return false;
					}
				}
				return true;
			}
			else {
				for(int i =finalRow + 1; i < startRow; i++) {
					if(squares[i][finalCol].getState() == SquareState.Occupied) {
						return false;
					}
				}
				return true;
			}
		}
		else { //moved horizontally
			if(finalCol > startCol) {
				for(int i = startCol + 1; i < finalCol; i++) {
					if(squares[finalRow][i].getState() == SquareState.Occupied) {
						return false;
					}
				}
				return true;
			}
			else {
				for(int i =finalCol + 1; i < startCol; i++) {
					if(squares[finalRow][i].getState() == SquareState.Occupied) {
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
		//lets trying moving in each of the four directions one by one
		if(isValid(boardInstance, -1, startRow, startCol, -1, startRow -1, startCol)) {
			finalRow = startRow -1;
			return move(boardInstance, (startRow * 8 + startCol), startRow, startCol, (finalRow * 8 + finalCol), finalRow, finalCol);
		}
		else if(isValid(boardInstance, -1, startRow, startCol, -1, startRow +1, startCol)) {
			finalRow = startRow +1;
			return move(boardInstance, (startRow * 8 + startCol), startRow, startCol, (finalRow * 8 + finalCol), finalRow, finalCol);
		}
		else if(isValid(boardInstance, -1, startRow, startCol, -1, startRow, startCol - 1)) {
			finalCol = startCol - 1;
			return move(boardInstance, (startRow * 8 + startCol), startRow, startCol, (finalRow * 8 + finalCol), finalRow, finalCol);
		}
		else if(isValid(boardInstance, -1, startRow, startCol, -1, startRow, startCol + 1)) {
			finalCol = startCol + 1;
			return move(boardInstance, (startRow * 8 + startCol), startRow, startCol, (finalRow * 8 + finalCol), finalRow, finalCol);
		}
		else {
			return -1;
		}
	}

}

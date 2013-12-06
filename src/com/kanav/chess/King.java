package com.kanav.chess;

import com.kanav.chess.Square.SquareState;

public class King extends Piece {

	@Override
	public boolean isValid(ChessBoard boardInstance, int startPos,
			int startRow, int startCol, int finalPos, int finalRow, int finalCol) {
		if(finalRow < 0 || finalRow > 7 || finalCol < 0 || finalCol > 7) {
			return false;
		}
		Square[][] squares = boardInstance.getSquares();
		if(squares[finalRow][finalCol].getState() == SquareState.Occupied) {
			if(squares[startRow][startCol].getPiece().getColor().compareTo(squares[finalRow][finalCol].getPiece().getColor()) == 0) {
				return false;
			}
		}
		if((finalRow == startRow - 1)      && (finalCol == startCol -1) ||
				(finalRow == startRow)     && (finalCol == startCol - 1) ||
				(finalRow == startRow + 1) && (finalCol == startCol - 1) ||
				(finalRow == startRow + 1) && (finalCol == startCol) ||
				(finalRow == startRow + 1) && (finalCol == startCol + 1) ||
				(finalRow == startRow)     && (finalCol == startCol + 1) ||
				(finalRow == startRow - 1) && (finalCol == startCol + 1) ||
				(finalRow == startRow - 1) && (finalCol == startCol)) {
			return true;
		}
		return false;
	}

	@Override
	public int randomMove(ChessBoard boardInstance, int startRow, int startCol) {
		int finalRow = startRow, finalCol = startCol;
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
		else if(isValid(boardInstance, -1, startRow, startCol, -1, startRow -1, startCol)) {
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

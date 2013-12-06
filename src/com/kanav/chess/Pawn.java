package com.kanav.chess;

import com.kanav.chess.Square.SquareState;

import android.util.Log;

public class Pawn extends Piece{


	@Override
	public boolean isValid(ChessBoard boardInstance, int startPos,
			int startRow, int startCol, int finalPos, int finalRow, int finalCol) {
		Square[][] squares = boardInstance.getSquares();
		if(finalRow < 0 || finalRow > 7 || finalCol < 0 || finalCol > 7) {
			return false;
		}
		if(squares[finalRow][finalCol].getState() == SquareState.Occupied) {
			if(squares[startRow][startCol].getPiece().getColor().compareTo(squares[finalRow][finalCol].getPiece().getColor()) == 0) {
				return false;
			}
		}
		if(MainActivity.currentTurn == MainActivity.BLACK_TURN) { //Black moves up
			if((finalRow == (startRow - 1)) && (finalCol == startCol) && 
					(squares[finalRow][finalCol].getState() == SquareState.Empty)) {
				return true;
			}
			if((startRow == 6) && (finalRow == (startRow - 2)) && (finalCol == startCol) && 
					(squares[finalRow][finalCol].getState() == SquareState.Empty) && 
					(squares[startRow-1][finalCol].getState() == SquareState.Empty)) {
				return true;
			}
			if(finalRow == startRow -1) {
				if((finalCol == startCol + 1) || (finalCol == startCol -1)) {
					if(squares[finalRow][finalCol].getState() == SquareState.Occupied) {
						return true;
					}
				}
			}
			return false;
		}
		else {// White moves down
			if((finalRow == (startRow + 1)) && (finalCol == startCol) && 
					(squares[finalRow][finalCol].getState() == SquareState.Empty)) {
				return true;
			}
			if((startRow == 1) && (finalRow == (startRow + 2)) && (finalCol == startCol) && 
					(squares[finalRow][finalCol].getState() == SquareState.Empty) && 
					(squares[startRow+1][finalCol].getState() == SquareState.Empty)) {
				return true;
			}
			if(finalRow == startRow + 1) {
				if((finalCol == startCol + 1) || (finalCol == startCol -1)) {
					if(squares[finalRow][finalCol].getState() == SquareState.Occupied) {
						return true;
					}
				}
			}
			return false;
		}
	}

	@Override
	public int randomMove(ChessBoard boardInstance, int startRow,
			int startCol) {
		int finalRow = -1, finalCol = -1;
		if(MainActivity.currentTurn == MainActivity.BLACK_TURN) {
			if(isValid(boardInstance, -1, startRow, startCol, -1, startRow -1, startCol)) {
				finalRow = startRow -1;
				finalCol = startCol;
				return move(boardInstance, (startRow * 8 + startCol), startRow, startCol, (finalRow * 8 + finalCol), finalRow, finalCol);
			}
			else if(isValid(boardInstance, -1, startRow, startCol, -1, startRow -1, startCol + 1)) {
				finalRow = startRow -1;
				finalCol = startCol + 1;
				return move(boardInstance, (startRow * 8 + startCol), startRow, startCol, (finalRow * 8 + finalCol), finalRow, finalCol);
			}
			else if(isValid(boardInstance, -1, startRow, startCol, -1, startRow -1, startCol -1)) {
				finalRow = startRow -1;
				finalCol = startCol - 1;
				return move(boardInstance, (startRow * 8 + startCol), startRow, startCol, (finalRow * 8 + finalCol), finalRow, finalCol);
			}
			else {
				return -1;
			}
		}
		else {
			if(isValid(boardInstance, -1, startRow, startCol, -1, startRow +1, startCol)) {
				finalRow = startRow +1;
				finalCol = startCol;
				return move(boardInstance, (startRow * 8 + startCol), startRow, startCol, (finalRow * 8 + finalCol), finalRow, finalCol);
			}
			else if(isValid(boardInstance, -1, startRow, startCol, -1, startRow +1, startCol + 1)) {
				finalRow = startRow +1;
				finalCol = startCol + 1;
				return move(boardInstance, (startRow * 8 + startCol), startRow, startCol, (finalRow * 8 + finalCol), finalRow, finalCol);
			}
			else if(isValid(boardInstance, -1, startRow, startCol, -1, startRow +1, startCol -1)) {
				finalRow = startRow +1;
				finalCol = startCol - 1;
				return move(boardInstance, (startRow * 8 + startCol), startRow, startCol, (finalRow * 8 + finalCol), finalRow, finalCol);
			}
			else {
				return -1;
			}
		}
	}

}

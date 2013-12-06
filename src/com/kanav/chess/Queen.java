package com.kanav.chess;

public class Queen extends Piece {

	@Override
	public boolean isValid(ChessBoard boardInstance, int startPos,
			int startRow, int startCol, int finalPos, int finalRow, int finalCol) {
		Piece bishop = new Bishop();
		Piece rook = new Rook();
		return(bishop.isValid(boardInstance, startPos, startRow, startCol, finalPos, finalRow, finalCol) ||
				rook.isValid(boardInstance, startPos, startRow, startCol, finalPos, finalRow, finalCol));
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

package com.kanav.chess;

import java.util.ArrayList;
import java.util.Random;

import android.util.Log;

import com.kanav.chess.Piece.PieceColor;
import com.kanav.chess.Piece.PieceType;
import com.kanav.chess.Square.SquareColor;
import com.kanav.chess.Square.SquareState;

public class ChessBoard { //Singleton
	public static int NUM_PIECES = 32;
	public static int NUM_SQUARES = 64;
	
	public static String TAG = "Board";
	
	private Random randomNum;
	private static ChessBoard boardInstance;
	
	public ArrayList<Piece> blackPieces;
	public ArrayList<Piece> whitePieces;
	private Square[][] squares;
	public Square[][] getSquares() {
		return squares;
	}

	public void setSquares(Square[][] squares) {
		this.squares = squares;
	}
	
	public static ChessBoard getInstance() {
		if(boardInstance == null) {
			boardInstance = new ChessBoard();
		}
		return boardInstance;
	}
	
	private ChessBoard() {
		squares = new Square[NUM_SQUARES/8][NUM_SQUARES/8];
		blackPieces = new ArrayList<Piece>();
		whitePieces = new ArrayList<Piece>();
		randomNum = new Random();
		setup();
	}
	
	public void setup() {
		blackPieces.clear();
		whitePieces.clear();
		for(int i = 0; i< NUM_SQUARES/8; i++) {
			if(i%2 == 0) { //row starts with white square on left
				for(int j = 0; j< NUM_SQUARES/8; j++) {
					squares[i][j] = new Square(i,j);
					squares[i][j].setState(SquareState.Empty);
					if(j%2 ==0) {
						squares[i][j].setColor(SquareColor.White);
					}
					else {
						squares[i][j].setColor(SquareColor.Black);
					}
					if((i == 0) || (i == 6)) {
						squares[i][j].setState(SquareState.Occupied);
						setPiece(i, j, squares[i][j]);	
					}
				}
			}
			else { //row starts with a black square on left
				for(int j = 0; j< NUM_SQUARES/8; j++) {
					squares[i][j] = new Square(i,j);
					squares[i][j].setState(SquareState.Empty);
					if(j%2 ==0) {
						squares[i][j].setColor(SquareColor.Black); 
					}
					else {
						squares[i][j].setColor(SquareColor.White);
					}
					if((i == 1) || (i == 7)) {
						squares[i][j].setState(SquareState.Occupied);
						setPiece(i, j,squares[i][j]);
					}
				}
			}
		}
	}

	private void setPiece(int i, int j, Square square) {
		if((i == 0) || (i == 7)) {
			Piece piece;
			switch (j) {
			case 0:
			case 7:
				piece = new Rook();
				piece.setSquare(square);
				piece.setType(PieceType.Rook);
				if(i == 0) {
					piece.setColor(PieceColor.White);
					whitePieces.add(piece);
				}
				else {
					piece.setColor(PieceColor.Black);
					blackPieces.add(piece);
				}
				square.setPiece(piece);
				break;			
			case 1:
			case 6:
				piece = new Knight();
				piece.setSquare(square);
				piece.setType(PieceType.Knight);
				if(i == 0) {
					piece.setColor(PieceColor.White);
					whitePieces.add(piece);
				}
				else {
					piece.setColor(PieceColor.Black);
					blackPieces.add(piece);
				}
				square.setPiece(piece);
				break;
			
			case 2:
			case 5:
				piece = new Bishop();
				piece.setSquare(square);
				piece.setType(PieceType.Bishop);
				if(i == 0) {
					piece.setColor(PieceColor.White);
					whitePieces.add(piece);
				}
				else {
					piece.setColor(PieceColor.Black);
					blackPieces.add(piece);
				}
				square.setPiece(piece);
				break;

			case 3:
				piece = new Queen();
				piece.setSquare(square);
				piece.setType(PieceType.Queen);
				if(i == 0) {
					piece.setColor(PieceColor.White);
					whitePieces.add(piece);
				}
				else {
					piece.setColor(PieceColor.Black);
					blackPieces.add(piece);
				}
				square.setPiece(piece);
				break;
			
			case 4:
				piece = new King();
				piece.setSquare(square);
				piece.setType(PieceType.King);
				if(i == 0) {
					piece.setColor(PieceColor.White);
					whitePieces.add(piece);
				}
				else {
					piece.setColor(PieceColor.Black);
					blackPieces.add(piece);
				}
				square.setPiece(piece);
				break;

			default:
				break;
			}
		}
		
		else if((i == 1) || (i == 6)) {
			setPawn(i, square);
		}
	}
	
	private void setPawn(int i, Square square) {
		Piece piece = new Pawn();
		piece.setSquare(square);
		piece.setType(PieceType.Pawn);
		if(i == 1) {
			piece.setColor(PieceColor.White);
			whitePieces.add(piece);
		}
		else {
			piece.setColor(PieceColor.Black);
			blackPieces.add(piece);
		}
		square.setPiece(piece);
	}

	public boolean isAllowed(int startPos, int startRow, int startCol, int finalPos, int finalRow, int finalCol) {
		boolean retValue = false;
		if(squares[startRow][startCol].getState() == SquareState.Empty) {
			return retValue;
		}
		if(squares[finalRow][finalCol].getState() == SquareState.Occupied) {
			if(squares[startRow][startCol].getPiece().getColor().compareTo(squares[finalRow][finalCol].getPiece().getColor()) == 0) {
				return retValue;
			}
		}
		if((squares[startRow][startCol].getPiece().getColor() == PieceColor.Black) && 
				(MainActivity.currentTurn == MainActivity.WHITE_TURN)) {
			return retValue;
		}
		if((squares[startRow][startCol].getPiece().getColor() == PieceColor.White) && 
				(MainActivity.currentTurn == MainActivity.BLACK_TURN)) {
			return retValue;
		}
		boolean isValid = squares[startRow][startCol].getPiece().isValid(boardInstance, startPos, startRow, startCol, finalPos, finalRow, finalCol);
		return isValid;
		
	}

	public int move(int startPos, int startRow, int startCol, int finalPos, int finalRow, int finalCol) {
		return squares[startRow][startCol].getPiece().move(boardInstance, startPos, startRow, startCol, finalPos, finalRow, finalCol);
		
	}

	public int randomMove() {
		int ret = -1;
		Piece selectedPiece;
		while(ret == -1) {
			if(MainActivity.currentTurn == MainActivity.BLACK_TURN) {
				int randPiece = randomNum.nextInt(blackPieces.size());
				selectedPiece = blackPieces.get(randPiece);
			}
			else {
				int randPiece = randomNum.nextInt(whitePieces.size());
				selectedPiece = whitePieces.get(randPiece);
			}
			Square selectedSquare = selectedPiece.getSquare();
			ret = selectedPiece.randomMove(boardInstance, selectedSquare.getRow(), selectedSquare.getCol());
		}
		return ret;
		
	}

}

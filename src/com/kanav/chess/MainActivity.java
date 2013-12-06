package com.kanav.chess;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private GridView gridView;
	private TextView turn;
	private Button restart;
	private Button randomMove;
	private static ChessBoard myBoard;
	private int startPos = -1;
	private ImageView startImage;
	private SquareAdapter squareAdapter ;
	public static String TAG = "MainActivity";
	public static Integer[] pieces = new Integer[ChessBoard.NUM_SQUARES];
	
	public final static int WHITE_TURN = 0;
	public final static int BLACK_TURN = 1;
	
	public static int currentTurn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myBoard = ChessBoard.getInstance();
		currentTurn = BLACK_TURN;
		setPieces();
		turn = (TextView)findViewById(R.id.textView1);
		restart = (Button)findViewById(R.id.restart);
		randomMove = (Button)findViewById(R.id.random_move);
		gridView = (GridView)findViewById(R.id.chess_board);

		squareAdapter = new SquareAdapter(this, pieces);
		gridView.setAdapter(squareAdapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos,
					long id) {
				if(startPos == -1) {
					startPos = pos;
					startImage = (ImageView) v;
					startImage.setColorFilter(Color.YELLOW);
				}
				else {
					startImage.clearColorFilter();
					int numOfCol = gridView.getNumColumns();
					int startRow = (int) Math.ceil(startPos/numOfCol);
					int startCol = Math.abs((startRow * numOfCol) - startPos);
					int finalRow = (int) Math.ceil(pos/numOfCol);
					int finalCol = Math.abs((finalRow * numOfCol) - pos);
					
					if(myBoard.isAllowed(startPos, startRow, startCol, pos, finalRow, finalCol)) {
						int ret = myBoard.move(startPos, startRow, startCol, pos, finalRow, finalCol);
						squareAdapter.notifyDataSetChanged();
						onValidMove(ret);
					}
					else {
						Toast.makeText(MainActivity.this, "Invalid move", Toast.LENGTH_SHORT).show();
					}
					startPos = -1;
				}
			}
		});
		
		restart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				myBoard.setup();
				setPieces();
				gridView.setEnabled(true);
				randomMove.setEnabled(true);
				currentTurn = BLACK_TURN;
				turn.setText("Black's turn");
				squareAdapter.notifyDataSetChanged();
			}
		});
		
		randomMove.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int ret = myBoard.randomMove();
				if(startImage != null) {
					startImage.clearColorFilter();
				}
				startPos = -1;
				squareAdapter.notifyDataSetChanged();
				onValidMove(ret);
				
			}
		});
	}
	
	protected void onValidMove(int ret) {
		if(ret == 1) {
			if(currentTurn == BLACK_TURN) {
				Toast.makeText(MainActivity.this, "Congrats! Black won!", Toast.LENGTH_SHORT).show();
				turn.setText("Black Won!!");
			}
			else {
				Toast.makeText(MainActivity.this, "Congrats! White won!", Toast.LENGTH_SHORT).show();
				turn.setText("White Won!!");
			}
			gridView.setEnabled(false);
			randomMove.setEnabled(false);
		}
		else {
			switchTurns();
		}
		
	}

	private void setPieces() {
		pieces[0] = pieces[7] = R.drawable.white_rook;
		pieces[1] = pieces[6] = R.drawable.white_knight;
		pieces[2] = pieces[5] = R.drawable.white_bishop;
		pieces[3] = R.drawable.white_queen;
		pieces[4] = R.drawable.white_king;
		for(int i =8; i<=15;i++)
			pieces[i] = R.drawable.white_pawn;
		for(int i =16; i<=47;i++)
			pieces[i] = null;
		pieces[56] = pieces[63] = R.drawable.black_rook;
		pieces[57] = pieces[62] = R.drawable.black_knight;
		pieces[58] = pieces[61] = R.drawable.black_bishop;
		pieces[59] = R.drawable.black_queen;
		pieces[60] = R.drawable.black_king;
		for(int i =48; i<=55;i++)
			pieces[i] = R.drawable.black_pawn;
	}

	protected void switchTurns() {
		if(currentTurn == BLACK_TURN) {
			currentTurn = WHITE_TURN;
			turn.setText("White's turn");
		}
		else {
			currentTurn = BLACK_TURN;
			turn.setText("Black's turn");
		}
	}

}

package com.kanav.chess;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class SquareAdapter extends BaseAdapter{
	
	private Context context;
	private Integer[] pieces = new Integer[ChessBoard.NUM_SQUARES];
	
	public SquareAdapter(Context context, Integer[] pieces) {
		this.context = context;
		this.pieces = pieces;

	}

	@Override
	public int getCount() {
		return pieces.length;
	}

	@Override
	public Object getItem(int arg0) {
		return pieces[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView squareView;
        if (convertView == null) {  
        	squareView = new ImageView(context);
            int size = parent.getWidth()/8;
            squareView.setLayoutParams(new GridView.LayoutParams(size,size));

	        int col = (position/8) %2;
	        if (col == 0)
	        {
	            if (position%2 == 0) {
	            	squareView.setBackgroundColor(Color.WHITE);
	            }
	            else {
	            	squareView.setBackgroundColor(Color.BLACK);
	            }
	        }
	        else
	        {
	            if (position%2 == 0) {
	            	squareView.setBackgroundColor(Color.BLACK);
	            }
	            else {
	            	squareView.setBackgroundColor(Color.WHITE);
	            }
	        }
        }
        else {
        	squareView = (ImageView) convertView;
        }
        if(pieces[position] != null) {
        	squareView.setImageResource(pieces[position]);
        }
        else {
        	squareView.setImageDrawable(null);
        }
        return squareView;
    }	
}

package edu.bc.casinepe.HW2;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;

public class FifteenPuzzle extends Activity {

	private int[][] puzzle = new int[4][4];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		List<Integer> availableNumbers = new LinkedList<Integer>();
		for (int i = 0; i <= 15; i++) {
			availableNumbers.add(i);
		}
		
		Random r = new Random();
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle[0].length; j++) {
				int num = r.nextInt(availableNumbers.size());
				puzzle[i][j] = availableNumbers.get(num);
				availableNumbers.remove(num);
			}
		}
		
		Log.d(""+this.getClass(), "Finished filling array");
		/*
		Button btn00 = (Button)this.findViewById(R.id.button00);
		Button btn01 = (Button)this.findViewById(R.id.button01);
		Button btn02 = (Button)this.findViewById(R.id.button02);
		Button btn03 = (Button)this.findViewById(R.id.button03);
		Button btn10 = (Button)this.findViewById(R.id.button10);
		Button btn11 = (Button)this.findViewById(R.id.button11);
		Button btn12 = (Button)this.findViewById(R.id.button12);
		Button btn13 = (Button)this.findViewById(R.id.button13);
		Button btn20 = (Button)this.findViewById(R.id.button20);
		Button btn21 = (Button)this.findViewById(R.id.button21);
		Button btn22 = (Button)this.findViewById(R.id.button22);
		Button btn23 = (Button)this.findViewById(R.id.button23);
		Button btn30 = (Button)this.findViewById(R.id.button30);
		Button btn31 = (Button)this.findViewById(R.id.button31);
		Button btn32 = (Button)this.findViewById(R.id.button32);
		Button btn33 = (Button)this.findViewById(R.id.button33);*/



		
		setContentView(R.layout.activity_fifteen_puzzle);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fifteen_puzzle, menu);
		return true;
	}
	
	/*public char canMove(Button from) {
		return 'N';
	}*/

}

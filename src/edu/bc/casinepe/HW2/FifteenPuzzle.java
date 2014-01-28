package edu.bc.casinepe.HW2;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class FifteenPuzzle extends Activity {

	private int[][] puzzle = new int[4][4];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fifteen_puzzle);
		randomizeNumbers();
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
	
	public void buttonClicked(View view) {
		int buttonId = view.getId();
		Button btn = (Button)view.findViewById(buttonId);

		if (buttonId == R.id.randomizeButton) {
			Log.d(""+this.getClass(), "Random button is: " + buttonId);
			randomizeNumbers();
		} else {
			Log.d(""+this.getClass(), "Button is: " + buttonId);
		}
	}
	
	/*
	 * @param
	 * @return Randomly fills the view's buttons with numbers from 1-15 and one empty slot
	 */
	public void randomizeNumbers() {
		
		List<Integer> availableNumbers = new LinkedList<Integer>();
		for (int i = 0; i <= 15; i++) {
			availableNumbers.add(i);
		}
		
		Random r = new Random();
		int count = 0x0;
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle[0].length; j++) {
				//Choose a random number within the range of availableNumber's indices
				int num = r.nextInt(availableNumbers.size());
				//Set the puzzle array's value
				puzzle[i][j] = availableNumbers.get(num);
				//Remove value from availableNumbers so the same number cannot be chosen twice
				availableNumbers.remove(num);

				//Set button in view
				Button btn = (Button)findViewById(R.id.button0 + count);
				if (puzzle[i][j] == 0) {
					btn.setText(" ");
				} else {
					btn.setText(""+puzzle[i][j]);	
				}
				//Increment counter so the next button is chosen
				count += 0x1;
			}
		}
		
		Log.d(""+this.getClass(), "Finished randomizing array");
	}

}

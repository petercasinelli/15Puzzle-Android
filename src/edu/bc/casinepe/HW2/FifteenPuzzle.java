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

	/*
	 * @param row
	 * @param col
	 */
	public char canMove(int row, int col) {
		Log.d(""+this.getClass(), "Value is: " + puzzle[row][col]);
		printArray(puzzle);
		if (puzzle[row][col] == 0) {
			Log.d(""+this.getClass(), "You cannot move.");
			return 'N';
		} else if (col + 1 < puzzle[0].length && puzzle[row][col + 1] == 0) {
			Log.d(""+this.getClass(), "You can move to the right.");
			return 'R';
		} else if (col - 1 >= 0 && puzzle[row][col - 1] == 0) {
			Log.d(""+this.getClass(), "You can move to the left.");
			return 'L';
		} else if (row + 1 < puzzle.length && puzzle[row + 1][col] == 0) {
			Log.d(""+this.getClass(), "You can move down.");
			return 'D';
		} else if (row - 1 >= 0 && puzzle[row - 1][col] == 0) {
			Log.d(""+this.getClass(), "You can move up.");
			return 'U';
		} else {
			Log.d(""+this.getClass(), "You cannot move. Last else.");
			return 'N';
		}
	}

	/*
	 * @param fromRow
	 * @param fromCol
	 * @param direction
	 */
	public void moveNumberFrom(int fromRow, int fromCol, char direction) {
		printArray(puzzle);

		//Temp record of number from the current row and col
		int num = puzzle[fromRow][fromCol];

		switch(direction) {
		case 'L': puzzle[fromRow][fromCol] = puzzle[fromRow][fromCol - 1];
				  puzzle[fromRow][fromCol - 1] = num;
		break;
		case 'R': puzzle[fromRow][fromCol] = puzzle[fromRow][fromCol + 1];
				  puzzle[fromRow][fromCol + 1] = num;
		break;
		case 'U': puzzle[fromRow][fromCol] = puzzle[fromRow - 1][fromCol];
				  puzzle[fromRow - 1][fromCol] = num;
		break;
		case 'D': puzzle[fromRow][fromCol] = puzzle[fromRow + 1][fromCol];
				  puzzle[fromRow + 1][fromCol] = num;
		break;
		default: //do nothing
			break;
		}
		
		printArray(puzzle);
	}
	
	public void printArray(int[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println("");
		}
	}

	/*
	 * Respond to a user touching a button
	 */
	public void buttonClicked(View view) {
		int buttonId = view.getId();
		Button btn = (Button)view.findViewById(buttonId);

		if (buttonId == R.id.randomizeButton) {
			randomizeNumbers();
		} else {
			Log.d(""+this.getClass(), "Button tag is: " + btn.getTag());
			String[] rowColumn = btn.getTag().toString().split(",");
			int row = Integer.parseInt(rowColumn[0]);
			int col = Integer.parseInt(rowColumn[1]);
			char direction = canMove(row, col);
			if (direction != 'N') {
				moveNumberFrom(row, col, direction);
				updateButtons();
			}
		}
	}

	/*
	 * Randomly fills the view's buttons with numbers from 1-15 and one empty slot
	 */
	public void randomizeNumbers() {

		List<Integer> availableNumbers = new LinkedList<Integer>();
		for (int i = 0; i <= 15; i++) {
			availableNumbers.add(i);
		}

		Random r = new Random();
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle[0].length; j++) {
				//Choose a random number within the range of availableNumber's indices
				int num = r.nextInt(availableNumbers.size());
				//Set the puzzle array's value
				puzzle[i][j] = availableNumbers.get(num);
				//Remove value from availableNumbers so the same number cannot be chosen twice
				availableNumbers.remove(num);
			}
		}
		//Update button text
		updateButtons();
		Log.d(""+this.getClass(), "Finished randomizing array");
	}

	/*
	 * Updates buttons' numbers with respective puzzle array button
	 */
	public void updateButtons() {
		int count = 0x0;
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle[0].length; j++) {
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

	}

}

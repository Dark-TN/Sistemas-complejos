 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

/**
 *
 * @author jedua
 */
public class Cell {
    int x,
    y;

    public Cell(int x, int y) {
    	this.x = x;
    	this.y = y;
    }

		public String toString() {
			return x + " " + y;
		}

		public boolean equals(Object o) {
			if (this == o) return true;
			if (! (o instanceof Cell)) return false;
			if (((Cell) o).x == this.x && ((Cell) o).y == this.y) return true;
			return false;
		}
	}
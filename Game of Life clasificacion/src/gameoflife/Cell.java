/* @author jedua */
package gameoflife;
public class Cell {
    int x, y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() { return x + " " + y; }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.x;
        hash = 37 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;
        return ((Cell) o).x == this.x && ((Cell) o).y == this.y;
    }
}
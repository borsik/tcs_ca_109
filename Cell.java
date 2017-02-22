/**
 * Created by olzhas on 27.01.17.
 */
public class Cell {
    private int color;
    private int value;

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Cell(int value) {
        this.color = 0;
        this.value = value;
    }
}

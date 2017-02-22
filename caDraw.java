import javax.swing.*;
import java.awt.*;

/**
 * Created by olzhas on 26.01.17.
 */
public class caDraw extends JFrame {
    boolean flag = true;

    public Cell[] cells;

    public Cell[] generateFirst() {
        cells = new Cell[60];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new Cell(0);
        }
        int randomCell = (int)(Math.random() * (cells.length - 1));
        cells[randomCell].setValue(1);
        return cells;
    }

    public int rules(int a, int b, int c) {
        if (a == 1 && b == 1 && c == 1) return 0;
        else if (a == 1 && b == 1 && c == 0) return 1;
        else if (a == 1 && b == 0 && c == 1) return 1;
        else if (a == 1 && b == 0 && c == 0) return 0;
        else if (a == 0 && b == 1 && c == 1) return 1;
        else if (a == 0 && b == 1 && c == 0) return 1;
        else if (a == 0 && b == 0 && c == 1) return 0;
        else if (a == 0 && b == 0 && c == 0) return 1;
        return 0;
    }

    Cell[] generate(Cell[] cells) {
        Cell newCells[] = new Cell[cells.length];
        for (int j = 0; j < cells.length; j++) {
            int l = cells[((j - 1) + cells.length) % cells.length].getValue();
            int m = cells[j].getValue();
            int r = cells[(j + 1) % cells.length].getValue();
            newCells[j] = new Cell(rules(l, m, r));
        }
        for (int j = 0; j < cells.length; j++) {
            if (cells[j].getValue() == newCells[j].getValue()) {
                if(cells[j].getValue() == 1) {
                    newCells[j].setColor(cells[j].getColor() + 1);
                } else {
                    newCells[j].setColor(cells[j].getColor() - 1);
                }

            }
        }
        return newCells;
    }

    public caDraw() {
        super("Cellular Automata");

        getContentPane().setBackground(Color.white);
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    void drawCells(Graphics g) throws InterruptedException {
        Graphics2D g2d = (Graphics2D) g;
        int width = 10;
        int height = 10;

        String[] colors = {"FFFFFF", "CCCCFF", "9999FF", "6666FF", "3333FF", "0000FF",
                "0000CC", "000099", "000066", "000033", "000000"};

        cells = generateFirst();
        int p = 0;

        while(flag) {

            p = p % cells.length;
            Cell[] newCell = generate(cells);
            for (int j = 0; j < cells.length; j++) {
                int colorIndex = cells[j].getColor();
                if (colorIndex <= -5) {
                    colorIndex = 0;
                } else if(colorIndex >= 5) {
                    colorIndex = 10;
                } else {
                    colorIndex = colorIndex + 5;
                }
                g2d.setColor(Color.decode("#" + colors[colorIndex]));
                g2d.fillRect(j * width , 22 + (p * height), width, height);
            }
            cells = newCell;
            p++;
            Thread.sleep(50);
        }

    }

    public void paint(Graphics g) {
        super.paint(g);
        try {
            drawCells(g);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new caDraw().setVisible(true);
            }
        });
    }

}

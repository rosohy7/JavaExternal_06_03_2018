import java.nio.channels.Pipe;
import java.util.*;
class Board {
    private int width, height;
    private Boolean filled[][];

    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        this.filled = new Boolean[height][width];

        for (int i = 0; i < height; ++i) {
            for (int k = 0; k < width; ++k) {
                filled[i][k] = false;
            }
        }
    }

    public void printBoard() {
        for (int i = height - 1; i >= 0; --i) {
            for (int k = 0; k < width; ++k) {
                System.out.print(filled[i][k] ? "* " : "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public void drawLine2Points(double x1, double y1, double x2, double y2) {
        if (Math.abs(x1 - x2) < 0.0001 && Math.abs(y1 - y2) < 0.0001) {
            filled[(int) Math.round(y1)][(int) Math.round(x1)] = true;
            return;
        }
        int t1, t2;
        double xdif = Math.abs(x1 - x2);
        double ydif = Math.abs(y1 - y2);
        if (ydif > xdif) {
            if (y1 < y2) {
                t1 = (int) Math.round(y1);
                t2 = (int) Math.round(y2);
            } else {
                t1 = (int) Math.round(y2);
                t2 = (int) Math.round(y1);
            }
            while (t1 <= t2) {
                filled[t1][(int) Math.round((t1 - y1) / (y1 - y2) * (x1 - x2) + x1)] = true;
                ++t1;
            }
        } else {
            if (x1 < x2) {
                t1 = (int) Math.round(x1);
                t2 = (int) Math.round(x2);
            } else {
                t1 = (int) Math.round(x2);
                t2 = (int) Math.round(x1);
            }
            while (t1 <= t2) {
                filled[(int) Math.round((t1 - x1) / (x1 - x2) * (y1 - y2) + y1)][t1] = true;
                ++t1;
            }
        }
    }
}


class Main {
    public static double getPointOnLine(double x, double x1, double y1, double x2, double y2) {
        if (Math.abs(x1 - x2) < 0.001) return 0;
        return (x - x1) / (x1 - x2) * (y1 - y2) + y1;
    }


    public static void printOrthoTriBoard(int height, int width) {
        Board board = new Board(height, width);
        board.drawLine2Points(0, 0, 0, height - 1);
        board.drawLine2Points(0, 0, width - 1, 0);
        board.drawLine2Points(0, height - 1, width - 1, 0);
        board.printBoard();
    }

    public static void printRecBoard(int height, int width) {
        Board board = new Board(height, width);
        board.drawLine2Points(0, 0, 0, height - 1);
        board.drawLine2Points(0, 0, width - 1, 0);
        board.drawLine2Points(width - 1, height - 1, width - 1, 0);
        board.drawLine2Points(width - 1, height - 1, 0, height - 1);
        board.printBoard();
    }
    public static final double PIXEL_WIDTH_TO_HEIGHT=14d/17;
    public static void printEquilTriBoard(int height, int width) {
        Board board = new Board(height, width);
        if (((double) width - 1) *PIXEL_WIDTH_TO_HEIGHT* Math.sqrt(3) / 2.0 <= (double) (height - 1)) {
            board.drawLine2Points(0, 0, width - 1, 0);
            board.drawLine2Points((width - 1) / 2, (int) Math.round((double) (width - 1) *PIXEL_WIDTH_TO_HEIGHT* Math.sqrt(3) / 2), 0, 0);
            board.drawLine2Points((width - 1) / 2, (int) Math.round((double) (width - 1) *PIXEL_WIDTH_TO_HEIGHT* Math.sqrt(3) / 2), width - 1, 0);
        } else {
            double length = (height - 1) * 2.0 / Math.sqrt(3)/PIXEL_WIDTH_TO_HEIGHT;
            board.drawLine2Points((int) Math.round(((double) width - 1 - length) / 2), 0, (int) Math.round(((double) width - 1 + length) / 2), 0);
            board.drawLine2Points((int) Math.round(((double) width - 1 - length) / 2), 0, (int) Math.round(((double) width - 1) / 2), height - 1);
            board.drawLine2Points((int) Math.round(((double) width - 1) / 2), height - 1, (int) Math.round(((double) width - 1 + length) / 2), 0);
        }
        board.printBoard();
    }

    public static void printDiamondBoard(int height, int width) {
        Board board = new Board(height, width);
        board.drawLine2Points((width - 1) / 2, 0, 0, (height - 1) / 2);
        board.drawLine2Points((width - 1) / 2, height - 1, 0, (height - 1) / 2);
        board.drawLine2Points((width - 1) / 2, 0, width - 1, (height - 1) / 2);
        board.drawLine2Points((width - 1) / 2, height - 1, width - 1, (height - 1) / 2);
        board.printBoard();
    }

    public static void main(String[] args) {
        printRecBoard(10, 45);
        printOrthoTriBoard(10, 45);
        printEquilTriBoard(20, 20);
        printEquilTriBoard(14, 29);
        printDiamondBoard(15, 45);

    }
    // Старое решение до создания класса Board
    /*public static void printRec(int height, int width) {
        for (int i = 0; i < height; ++i) {
            for (int k = 0; k < width; ++k) {
                if (i == 0 || i == height - 1 || k == 0 || k == width - 1)
                    System.out.print("* ");
                else System.out.print("  ");
            }

            System.out.println();
        }
        System.out.println();
    }

    public static void printRecTri(int height, int width) {
        for (int i = 0; i < height; ++i) {
            for (int k = 0; k < width; ++k) {
                if (i == height - 1 || k == 0)
                    System.out.print("* ");
                else if (i == Math.round(getPointOnLine(k, 0, 0, width - 1, height - 1)))
                    System.out.print("* ");
                else System.out.print("  ");
            }

            System.out.println();
        }
        System.out.println();
    }*/
}
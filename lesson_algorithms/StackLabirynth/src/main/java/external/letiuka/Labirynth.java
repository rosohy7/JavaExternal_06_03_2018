package external.letiuka;

import java.io.*;
import java.util.Scanner;

public class Labirynth {
    public static final int[][] MOVES = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    Stack story;
    boolean[][] free;
    char[][] solution;
    Point start, fin;
    int n, m;

    public void readLabirynth(String file) throws IOException {
        Scanner scan = null;
        String str;
        char ch;
        BufferedReader stream = null;
        try {
            stream = new BufferedReader(new FileReader(file));
            str = stream.readLine();
            scan = new Scanner(str);
            n = scan.nextInt() + 2;
            m = scan.nextInt() + 2;
            free = new boolean[n][m];
            for (int i = 1; i < n - 1; ++i) {
                str = stream.readLine();
                for (int j = 1; j < m - 1; ++j) {
                    ch = str.charAt(j - 1);
                    switch (ch) {
                        case '.':
                            free[i][j] = true;
                            break;
                        case '#':
                            break;
                        case 'S':
                            start = new Point(i, j);
                            free[i][j] = true;
                        case 'F':
                            fin = new Point(i, j);
                            free[i][j] = true;
                        default:
                            break;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) stream.close();
        }
    }

    private boolean move(Point p, int dir) {
        if (free[p.y + MOVES[dir][0]][p.x + MOVES[dir][1]]) {
            free[p.y][p.x] = false;
            p.y += MOVES[dir][0];
            p.x += MOVES[dir][1];
            System.out.println("Current pos(move) is (" + p.x + "," + p.y + ")");
            return true;
        }
        return false;
    }

    private boolean moveBack(Point p, int dir) {

        free[p.y][p.x] = true;
        p.y -= MOVES[dir][0];
        p.x -= MOVES[dir][1];
        System.out.println("Current pos(moveback) is (" + p.x + "," + p.y + ")");
        return true;

    }

    private boolean canMove(Point p, int dir) {
        if (free[p.y + MOVES[dir][0]][p.x + MOVES[dir][1]]) {
            return true;
        }
        return false;
    }

    private int tryMove(Point cur, int dir) {
        while (dir < 4 && !move(cur, dir)) {
            dir++;
        }
        return dir;
    }

    public void fillWalls() {
        solution = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                solution[i][j] = free[i][j] ? '.' : '#';
            }
        }

        solution[start.y][start.x] = 'S';
        solution[fin.y][fin.x] = 'F';
    }

    public void fillSolution() {
        solution = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                solution[i][j] = free[i][j] ? '.' : '#';
            }
        }
        Point cur = new Point(fin.y, fin.x);
        Integer dir;
        while ((dir = story.pop()) != null) {
            cur.y -= MOVES[dir][0];
            cur.x -= MOVES[dir][1];
            solution[cur.y][cur.x] = '*';
        }
        solution[start.y][start.x] = 'S';
        solution[fin.y][fin.x] = 'F';
    }

    public void printSolution() {
        for (int i = 0; i < n; i++) {
            System.out.println(solution[i]);
        }
    }

    public void createStory() {
        story = new Stack();
        story.push(-1);
        int dir;
        Point cur = new Point(start.x, start.y);
        boolean found = false;
        while (!found) {

            free[cur.y][cur.x] = true;
            dir = story.pop();
            if (dir != -1) moveBack(cur, dir);

            while ((dir = tryMove(cur, dir + 1)) < 4) {
                story.push(dir);

                if (cur.x == fin.x && cur.y == fin.y) {
                    System.out.println("Found a path");
                    return;
                }

                dir = -1;
            }

        }
    }
}

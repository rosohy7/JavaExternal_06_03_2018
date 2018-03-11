class IndexVal {
    public int ind;
    public int val;

    public IndexVal(int index, int value) {
        ind = index;
        val = value;
    }

}

public class Main {


    public static void quickSort(IndexVal[] arr) {
        qsort(arr, 0, arr.length - 1);
    }

    public static void qsort(IndexVal[] arr, int left, int right) {
        int l = left;
        int r = right;
        int mid = arr[(l + r) / 2].val;
        while (l <= r) {
            while ((arr[l].val < mid)) ++l;
            while ((mid < arr[r].val)) --r;
            if (l <= r) {
                IndexVal t = arr[l];
                arr[l] = arr[r];
                arr[r] = t;
                --r;
                ++l;
            }
        }
        if (r > left) qsort(arr, left, r);
        if (l < right) qsort(arr, l, right);
    }

    public static void part1() {
        System.out.println("Part 1");
        int mat[][] = {{7, 5, 1, 0, 1, 9, 9},
                {4, 5, 7, 7, 1, 5, 3},
                {0, 7, 5, 4, 9, 8, 7},
                {4, 4, 2, 2, 2, 2, 4},
                {7, 5, 2, 3, 0, 4, 1},
                {9, 8, 9, 4, 5, 5, 7},
                {4, 8, 5, 3, 6, 1, 6},
                {1, 8, 8, 8, 5, 0, 1}};
        for (int i = 0; i < mat.length; ++i) {
            for (int k = 0; k < mat[0].length; ++k) {
                System.out.print(mat[i][k] + " ");
            }
            System.out.println();
        }
        System.out.println();
        IndexVal indexVal[] = new IndexVal[mat[0].length];
        for (int k = 0; k < mat[0].length; ++k) {
            int sum = 0;
            for (int i = 0; i < mat.length; ++i) {
                sum += mat[i][k];
            }
            indexVal[k] = new IndexVal(k, -sum);
        }
        quickSort(indexVal);
        int[][] resmat = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; ++i) {
            for (int k = 0; k < mat[0].length; ++k) {
                resmat[i][k] = mat[i][indexVal[k].ind];
            }
        }
        for (int i = 0; i < resmat.length; ++i) {
            for (int k = 0; k < resmat[0].length; ++k) {
                System.out.print(resmat[i][k] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void part2() {
        System.out.println("Part 2");
        int mat[][] = {{7, 5, 1, 0, 1, 9, 9},
                {4, 5, 7, 7, 1, 5, 3},
                {0, 7, 5, 4, 9, 8, 7},
                {4, 4, 2, 2, 2, 2, 4},
                {7, 5, 2, 3, 0, 4, 1},
                {9, 8, 9, 4, 5, 5, 7},
                {4, 8, 5, 3, 6, 1, 6},
                {1, 8, 8, 8, 5, 0, 1}};
        for (int i = 0; i < mat.length; ++i) {
            for (int k = 0; k < mat[0].length; ++k) {
                System.out.print(mat[i][k] + " ");
            }
            System.out.println();
        }
        System.out.println();
        IndexVal indexVal[] = new IndexVal[mat.length];
        for (int i = 0; i < mat.length; ++i) {
            int last = mat[i][0] + 1;
            int maxStreak = 1;
            int streak = 1;
            for (int k = 0; k < mat[0].length; ++k) {
                if (mat[i][k] == last) {
                    ++streak;
                    maxStreak = streak > maxStreak ? streak : maxStreak;
                } else {
                    streak = 1;
                }
                last = mat[i][k];
            }
            indexVal[i] = new IndexVal(i, maxStreak);
        }
        quickSort(indexVal);
        int[][] resmat = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; ++i) {
            for (int k = 0; k < mat[0].length; ++k) {
                resmat[i][k] = mat[indexVal[i].ind][k];
            }
        }
        for (int i = 0; i < resmat.length; ++i) {
            for (int k = 0; k < resmat[0].length; ++k) {
                System.out.print(resmat[i][k] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public static void part3() {
        final int[][] VEC = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        System.out.println("Part 3");
        int mat[][] = {{7, 5, 1, 0, 1, 9, 9},
                {4, 5, 7, 7, 1, 5, 3},
                {0, 7, 5, 4, 9, 8, 7},
                {4, 4, 2, 2, 2, 2, 4},
                {7, 5, 2, 3, 0, 4, 1},
                {9, 8, 9, 4, 5, 5, 7},
                {4, 8, 5, 3, 6, 1, 6}};
        for (int i = 0; i < mat.length; ++i) {
            for (int k = 0; k < mat[0].length; ++k) {
                System.out.print(mat[i][k] + " ");
            }
            System.out.println();
        }
        System.out.println();
        final int MATSIZE = mat.length * mat[0].length;
        Boolean[][] walls = new Boolean[mat.length + 1][mat[0].length + 1];
        for (int i = 0; i < walls.length; ++i) {
            for (int k = 0; k < walls[0].length; ++k) {
                if (i == walls.length - 1 || k == walls[0].length - 1) walls[i][k] = true;
                else walls[i][k] = false;
            }
        }
        int vecref = 0;
        int x1 = 0, y1 = 0;
        System.out.print(mat[0][0] + " ");
        for (int i = 0; i < MATSIZE - 1; ++i) {
            int x2, y2;
            walls[y1][x1] = true;
            vecref--;
            do {
                vecref = (vecref + 1) % 4;
                x2 = x1 + VEC[vecref][1];
                y2 = y1 + VEC[vecref][0];
            } while (x2 < 0 || y2 < 0 || walls[y2][x2]);
            x1 = x2;
            y1 = y2;
            System.out.print(mat[y1][x1] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        part1();
        System.out.println();
        part2();
        System.out.println();
        part3();
    }
}
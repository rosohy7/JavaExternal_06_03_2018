public class Main {
    public static Boolean comp(int a, int b) {
        if (a < 0 || b < 0) {
            return a < b;
        } else return a > b;
    }

    public static void quickSort(int[] arr) {
        qsort(arr, 0, arr.length - 1);
    }

    public static void qsort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int mid = arr[(l + r) / 2];
        while (l <= r) {
            while (comp(arr[l], mid)) ++l;
            while (comp(mid, arr[r])) --r;
            if (l <= r) {
                int t = arr[l];
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
        int[] arr = {10, -2, -9, 8, -1, 0, 12, -2, 3};
        for (int i = 0; i < arr.length; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        quickSort(arr);
        for (int i = 0; i < arr.length; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    public static void part2() {
        int[] arr = {10, -2, -9, 8, -1, 0, 12, -2, 3};
        for (int i = 0; i < arr.length; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        int l=0;
        int r=arr.length-1;
        while(l<r)
        {
            while(l<arr.length && arr[l]>=0) ++l;
            while(r>=0 && arr[r]<0)--r;
            if(l<r)
            {
                int t = arr[l];
                arr[l] = arr[r];
                arr[r] = t;
                --r;
                ++l;
            }
        }
        for (int i = 0; i < arr.length; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        part1();
        System.out.println();
        part2();

    }
}

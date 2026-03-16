package Search;

public class SearchManager {

    public static int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == key) return i;
        return -1;
    }

    public static int binarySearch(int[] arr, int key) {
        int l = 0, h = arr.length - 1;
        while (l <= h) {
            int m = (l + h) / 2;
            if (arr[m] == key) return m;
            else if (arr[m] < key) l = m + 1;
            else h = m - 1;
        }
        return -1;
    }
}

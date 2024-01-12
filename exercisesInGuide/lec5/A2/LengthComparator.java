package A2;

import java.sql.Array;

public class LengthComparator implements NullSafeStringComparator {

    @Override
    public int compare(String s1, String s2) {
        if(s1 == null && s2 == null) {
            return 0;
        }
        if(s1 == null) {
            return -1;
        }
        if(s2 == null) {
            return 1;
        }
        return Integer.compare(s1.length(), s2.length());
    }

    public static String max(String[] a, NullSafeStringComparator sc) {
        String maxStr = a[0];
        for(int i = 0; i < a.length; i++) {
            if(sc.compare(a[i], maxStr)> 0) {
                maxStr = a[i];
            }
        }
        return maxStr;
    }

    public static String[][] step(String[][] arr) {
        /* Recall: All String references in stepped are null by default,
          So the edges are correct on initialization.
         */
        String[][] stepped = new String[arr.length][arr[0].length];

        // We should EXCEPT the edges
        for(int i = 1; i < arr.length - 1; i++) {
            for(int j = 1; j < arr[0].length - 1; j++) {
                String[] temp = new String[9]; //There are 8 neighbors and itself
                LengthComparator sc = new LengthComparator();
                for (int k = -1; k <= 1; k += 1) {
                    for (int m = -1; m <= 1; m += 1) {
                        int count = (k + 1) * 3 + m + 1;
                        temp[count] = arr[i + k][j + m];
                    }
                }
                stepped[i][j] = max(temp, sc);
            }
        }
        return stepped;
    }
}

package B2;

public class DList {
    private int[][] elements;
    private int shortestLength;
    private int longestLength;

    public DList(int capacity) {
        elements = new int[capacity][0];
        shortestLength = 0;
        longestLength = 0;
    }

    public void addElementToRow(int rowCount, int x) {
        if(isShortestRow(rowCount)) {
            addLast(rowCount, x);
        } else {
            int next = nextRow(rowCount);
            addElementToRow(next, x);
        }

        updateLengthData();
    }

    private Boolean isShortestRow(int rowCount) {
        return elements[rowCount].length == shortestLength;
    }

    private void addLast(int rowCount, int x) {
        int[] temp = new int[shortestLength + 1];
        System.arraycopy(elements[rowCount], 0, temp, 0, shortestLength);
        temp[shortestLength] = x;
        elements[rowCount] = temp;
    }

    private int nextRow(int rowCount) {
        if(rowCount == elements.length - 1) {
            return 0;
        }
        return rowCount + 1;
    }

    private void updateLengthData() {
        shortestLength = elements[0].length;
        longestLength = elements[0].length;
        for (int[] element : elements) {
            if (element.length < shortestLength) {
                shortestLength = element.length;
            } else if (element.length > longestLength) {
                longestLength = element.length;
            }
        }
    }

    public static void main(String[] args) {
        DList d = new DList(5);

        for (int i = 0; i < 100; i++) {
            d.addElementToRow((int)(Math.random() * 5), i);
            System.out.println(d.longestLength - d.shortestLength);
        }
    }
}

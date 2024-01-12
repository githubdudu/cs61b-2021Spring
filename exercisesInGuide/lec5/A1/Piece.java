package A1;

public class Piece {
    private int longitude;
    private int latitude;
    public Piece(int x, int y) {
        longitude = x;
        latitude = y;
    }

    public Piece[][] groupByLat(Piece[] p){
        // Cause originally it is a NxN map, NxN == p.length
        int width = (int) Math.sqrt(p.length);
        Piece[][] latGroup = new Piece[width][width];

        for (Piece piece : p) { // Ex: 16
            for (int j = 0; j < width; j++) { // Ex: 4
                if (latGroup[j][0] == null) {
                    latGroup[j][0] = piece;
                    break;
                } else if (latGroup[j][0].latitude == piece.latitude) {
                    int counter;
                    /* On question pdf, it is upper one. On solution pdf, it is lower line */
//                    for(counter= 0; counter < p.length - 1; counter++){
                    for (counter = 0; counter < width; counter++) {
                        if (latGroup[j][counter] == null) {
                            break;
                        }
                    }
                    latGroup[j][counter] = piece;
                    break;

                }
            }
        }
        return latGroup ;
    }

    public Piece[][] solvePuzzle(Piece[] scattered) {
        Piece[][] puzzleGroupedByLat = groupByLat(scattered);
        puzzleGroupedByLat = sortByLat(puzzleGroupedByLat);
        for (Piece[] row: puzzleGroupedByLat){
            row = sortHalfLong(row);
            row = swapHalf(row);
        }
        return puzzleGroupedByLat;
    }
    public Piece[][] sortByLat(Piece[][] p) {
        return null;
    }
    public Piece[] sortHalfLong(Piece[] p) {
        return null;
    }
    /* It is really hard to know what the question giver wants and his meaning
    * Apparently, he wants us to use arraycopy() to deal with result of sortHalfLong().
    * I couldn't figure it out and wanted to skip this method and nearly wrote a sort method myself. */
    public Piece[] swapHalf(Piece[] row) {
        int upper = (int) Math.ceil((double) row.length / 2);
        int lower = (int) Math.floor((double) row.length / 2);
        Piece[] tmp = new Piece[row.length];

        System.arraycopy(row, 0, tmp, lower, upper);
        System.arraycopy(row, upper, tmp, 0, lower);
        return tmp;
    }

}

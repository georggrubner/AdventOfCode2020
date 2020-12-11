package calender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import main.Calculation;

/**
 *
 * @author georggrubner
 */
public class Dec11 implements Calculation {

    @Override
    public String calculateStar1(String inputFileName) throws Exception {
        char[][] seats = getSeatsArray(inputFileName);

        char[][] newSeats = duplicate(seats);

        boolean changed = false;

        do {
            changed = false;
            for (int i = 0; i < seats.length; i++) {
                for (int j = 0; j < seats[0].length; j++) {
                    if (newSeats[i][j] == 'L') {
                        if (checkIfEmptyAdjacent(seats, i - 1, j - 1)
                                && checkIfEmptyAdjacent(seats, i - 1, j)
                                && checkIfEmptyAdjacent(seats, i - 1, j + 1)
                                && checkIfEmptyAdjacent(seats, i, j - 1)
                                && checkIfEmptyAdjacent(seats, i, j + 1)
                                && checkIfEmptyAdjacent(seats, i + 1, j - 1)
                                && checkIfEmptyAdjacent(seats, i + 1, j)
                                && checkIfEmptyAdjacent(seats, i + 1, j + 1)) {
                            newSeats[i][j] = '#';
                            changed = true;
                        }
                    }
                    if (newSeats[i][j] == '#') {
                        int count = 0;

                        count += checkIfOccupiedAdjacent(seats, i - 1, j - 1) ? 1 : 0;
                        count += checkIfOccupiedAdjacent(seats, i - 1, j) ? 1 : 0;
                        count += checkIfOccupiedAdjacent(seats, i - 1, j + 1) ? 1 : 0;
                        count += checkIfOccupiedAdjacent(seats, i, j - 1) ? 1 : 0;
                        count += checkIfOccupiedAdjacent(seats, i, j + 1) ? 1 : 0;
                        count += checkIfOccupiedAdjacent(seats, i + 1, j - 1) ? 1 : 0;
                        count += checkIfOccupiedAdjacent(seats, i + 1, j) ? 1 : 0;
                        count += checkIfOccupiedAdjacent(seats, i + 1, j + 1) ? 1 : 0;

                        if (count >= 4) {
                            newSeats[i][j] = 'L';
                            changed = true;
                        }
                    }
                }
            }

            seats = duplicate(newSeats);
        } while (changed);

        int count = 0;

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                if (seats[i][j] == '#') {
                    count++;
                }
            }
        }

        return "" + count;
    }

    @Override
    public String calculateStar2(String inputFileName) throws Exception {
        char[][] seats = getSeatsArray(inputFileName);

        char[][] newSeats = duplicate(seats);

        boolean changed = false;

        do {
            changed = false;
            for (int i = 0; i < seats.length; i++) {
                for (int j = 0; j < seats[0].length; j++) {
                    if (newSeats[i][j] == 'L') {
                        if (checkIfEmptySee(seats, i, j, - 1, - 1)
                                && checkIfEmptySee(seats, i, j, - 1, 0)
                                && checkIfEmptySee(seats, i, j, - 1, 1)
                                && checkIfEmptySee(seats, i, j, 0, - 1)
                                && checkIfEmptySee(seats, i, j, 0, 1)
                                && checkIfEmptySee(seats, i, j, 1, - 1)
                                && checkIfEmptySee(seats, i, j, 1, 0)
                                && checkIfEmptySee(seats, i, j, 1, 1)) {
                            newSeats[i][j] = '#';
                            changed = true;
                        }
                    }
                    if (newSeats[i][j] == '#') {
                        int count = 0;

                        count += checkIfOccupiedSee(seats, i, j, - 1, - 1) ? 1 : 0;
                        count += checkIfOccupiedSee(seats, i, j, - 1, 0) ? 1 : 0;
                        count += checkIfOccupiedSee(seats, i, j, - 1, 1) ? 1 : 0;
                        count += checkIfOccupiedSee(seats, i, j, 0, - 1) ? 1 : 0;
                        count += checkIfOccupiedSee(seats, i, j, 0, 1) ? 1 : 0;
                        count += checkIfOccupiedSee(seats, i, j, 1, - 1) ? 1 : 0;
                        count += checkIfOccupiedSee(seats, i, j, 1, 0) ? 1 : 0;
                        count += checkIfOccupiedSee(seats, i, j, 1, 1) ? 1 : 0;

                        if (count >= 5) {
                            newSeats[i][j] = 'L';
                            changed = true;
                        }
                    }
                }
            }

            seats = duplicate(newSeats);
        } while (changed);

        int count = 0;

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                if (seats[i][j] == '#') {
                    count++;
                }
            }
        }

        return "" + count;
    }

    private char[][] getSeatsArray(String inputFileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        char[][] seats;
        String st;
        int rowCount = 0;
        int columnCount = 0;

        while ((st = br.readLine()) != null) {
            rowCount++;
            columnCount = st.length();
        }

        br = new BufferedReader(new FileReader(inputFileName));
        seats = new char[rowCount][columnCount];
        int i = 0;

        while ((st = br.readLine()) != null) {
            char[] chars = st.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                seats[i][j] = chars[j];
            }
            i++;
        }

        return seats;
    }

    private char[][] duplicate(char[][] seats) {
        char[][] newSeats = new char[seats.length][seats[0].length];
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                newSeats[i][j] = seats[i][j];
            }
        }
        return newSeats;
    }

    private boolean checkIfEmptyAdjacent(char[][] seats, int i, int j) {
        if (!isValidCoordinates(seats, i, j)) {
            return true;
        }
        return seats[i][j] == 'L' || seats[i][j] == '.';
    }

    private boolean isValidCoordinates(char[][] seats, int i, int j) {
        return i >= 0 && j >= 0 && i < seats.length && j < seats[0].length;
    }

    private boolean checkIfOccupiedAdjacent(char[][] seats, int i, int j) {
        return isValidCoordinates(seats, i, j) && seats[i][j] == '#';
    }

    private boolean checkIfEmptySee(char[][] seats, int i, int j, int deltaI, int deltaJ) {
        int posI = i + deltaI;
        int posJ = j + deltaJ;
        
        while (isValidCoordinates(seats, posI, posJ)) {
            switch (seats[posI][posJ]) {
                case '.':
                    posI += deltaI;
                    posJ += deltaJ;
                    break;
                case 'L':
                    return true;
                case '#':
                    return false;
                default:
                    break;
            }
        }
        return true;
    }

    private boolean checkIfOccupiedSee(char[][] seats, int i, int j, int deltaI, int deltaJ) {
        int posI = i + deltaI;
        int posJ = j + deltaJ;

        while (isValidCoordinates(seats, posI, posJ)) {
            switch (seats[posI][posJ]) {
                case '.':
                    posI += deltaI;
                    posJ += deltaJ;
                    break;
                case 'L':
                    return false;
                case '#':
                    return true;
                default:
                    break;
            }
        }
        return false;
    }
}

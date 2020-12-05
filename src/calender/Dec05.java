package calender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import main.Calculation;

/**
 *
 * @author georggrubner
 */
public class Dec05 implements Calculation {

    @Override
    public String calculateStar1(String inputFileName) throws Exception {
        Set<Integer> seats = getSeatsSet(inputFileName);
        int maxSeatId = seats.stream().mapToInt((value) -> value).max().getAsInt();

        return "" + maxSeatId;
    }

    @Override
    public String calculateStar2(String inputFileName) throws Exception {
        Set<Integer> seats = getSeatsSet(inputFileName);

        Iterator<Integer> it = seats.iterator();
        int previousSeatId = 0;

        while (it.hasNext()) {
            int value = it.next();

            if (previousSeatId != 0) {
                if (value == previousSeatId + 2) {
                    return "" + (value - 1);
                }
            }
            previousSeatId = value;
        }

        return "ERROR: Seat not found";
    }

    int fromBinaryToInt(String input) {
        return Integer.parseInt(input.replace('B', '1').replace('R', '1').replace('F', '0').replace('L', '0'), 2);
    }

    private Set<Integer> getSeatsSet(String inputFileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        String st;
        Set<Integer> seats = new TreeSet<>();

        while ((st = br.readLine()) != null) {
            seats.add(fromBinaryToInt(st));
        }

        return seats;
    }
}

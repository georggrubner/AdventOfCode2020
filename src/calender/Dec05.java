package calender;

import java.io.BufferedReader;
import java.io.FileReader;
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
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        String st;
        int maxSeatId = 0;

        while ((st = br.readLine()) != null) {
            int row = fromBinaryToInt(st.substring(0, 7));
            int column = fromBinaryToInt(st.substring(7));
            int seatId = row * 8 + column;
            maxSeatId = Math.max(maxSeatId, seatId);
        }

        return "" + maxSeatId;
    }

    @Override
    public String calculateStar2(String inputFileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        String st;
        Set<Integer> seats = new TreeSet<>();

        while ((st = br.readLine()) != null) {
            int row = fromBinaryToInt(st.substring(0, 7));
            int column = fromBinaryToInt(st.substring(7));
            int seatId = row * 8 + column;
            seats.add(seatId);
        }

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
        char[] chars = input.toCharArray();
        String output = "";
        for (int i = 0; i < chars.length; i++) {
            output = output.concat(chars[i] == 'B' || chars[i] == 'R' ? "1" : "0");
        }
        return Integer.parseInt(output, 2);
    }
}

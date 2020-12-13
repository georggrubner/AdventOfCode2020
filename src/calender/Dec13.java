package calender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import main.Calculation;

/**
 *
 * @author georggrubner
 */
public class Dec13 implements Calculation {

    @Override
    public String calculateStar1(String inputFileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        int timestamp = Integer.parseInt(br.readLine());
        int busId = 0;
        int bestTime = Integer.MAX_VALUE;

        String[] busses = br.readLine().split(",");

        for (String bus : busses) {
            if (!"x".equals(bus)) {
                int id = Integer.parseInt(bus);
                int diff = (Math.floorDiv(timestamp, id) + 1) * id - timestamp;
                if (diff < bestTime) {
                    busId = id;
                    bestTime = diff;
                }
            }
        }

        return "" + busId * bestTime;
    }

    @Override
    public String calculateStar2(String inputFileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        List<Bus> lst = new ArrayList<>();

        br.readLine(); // ignore first line
        String[] busses = br.readLine().split(",");
        for (int i = 0; i < busses.length; i++) {
            String bus = busses[i];
            if (!"x".equals(bus)) {
                lst.add(new Bus(Long.parseLong(bus), i));
            }
        }

        long product = lst.stream().mapToLong(bus -> bus.id).reduce((a, b) -> a * b).getAsLong();
        long sum = lst.stream().mapToLong(bus -> bus.delay * (product / bus.id) * diff(product / bus.id, bus.id)).sum();
        return "" + (product - sum % product);
    }

    long diff(long x, long y) {
        if (x == 0) {
            return 0;
        }
        long mod = y % x;
        return mod == 0 ? 1 : y - diff(mod, x) * y / x;
    }

    class Bus {

        long id;
        long delay;

        public Bus(long id, long delay) {
            this.id = id;
            this.delay = delay;
        }

        @Override
        public String toString() {
            return id + " " + delay;
        }

    }
}

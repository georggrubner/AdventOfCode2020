package calender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import main.Calculation;

/**
 *
 * @author georggrubner
 */
public class Dec09 implements Calculation {

    @Override
    public String calculateStar1(String inputFileName) throws Exception {
        List<Long> data = getXMASData(inputFileName);
        int preamble = 25;

        for (int i = preamble; i < data.size(); i++) {
            boolean valid = false;
            for (int j = i - preamble; j < i && !valid; j++) {
                for (int k = i - preamble + 1; k < i && !valid; k++) {
                    valid = data.get(j) + data.get(k) == data.get(i);
                }
            }
            if (!valid) {
                return "" + data.get(i);
            }
        }

        return "ERROR: No Invalid Data Found";
    }

    @Override
    public String calculateStar2(String inputFileName) throws Exception {
        List<Long> data = getXMASData(inputFileName);
        int preamble = 25;

        for (int i = preamble; i < data.size(); i++) {
            boolean valid = false;
            for (int j = i - preamble; j < i && !valid; j++) {
                for (int k = i - preamble + 1; k < i && !valid; k++) {
                    valid = data.get(j) + data.get(k) == data.get(i);
                }
            }
            if (!valid) {
                System.out.println("" + data.get(i));
                for (int j = 0; j < i; j++) {
                    for (int k = j + 1; k < i; k++) {
                        long value = data.subList(j, k).stream().mapToLong((v) -> v).sum();
                        if (value == data.get(i)) {
                            long result = data.subList(j, k).stream().mapToLong((v) -> v).min().getAsLong() + data.subList(j, k).stream().mapToLong((v) -> v).max().getAsLong();
                            return "" + result;
                        }
                    }
                }
            }
        }

        return "ERROR: No Invalid Data Found";
    }

    private List<Long> getXMASData(String inputFileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        String st;
        List<Long> data = new ArrayList<>();

        while ((st = br.readLine()) != null) {
            data.add(Long.parseLong(st));
        }

        return data;
    }
}

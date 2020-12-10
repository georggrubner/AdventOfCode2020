package calender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import main.Calculation;

/**
 *
 * @author georggrubner
 */
public class Dec10 implements Calculation {

    @Override
    public String calculateStar1(String inputFileName) throws Exception {
        Set<Integer> set = getAdaptersSet(inputFileName);
        Iterator<Integer> it = set.iterator();
        int prev = 0;
        int diff1 = 0;
        int diff3 = 1;

        while (it.hasNext()) {
            int i = it.next();
            if (i - prev == 1) {
                diff1++;
            }
            if (i - prev == 3) {
                diff3++;
            }
            prev = i;
        }

        return "" + (diff1 * diff3);
    }

    @Override
    public String calculateStar2(String inputFileName) throws Exception {
        List<Integer> lst = new ArrayList<>(getAdaptersSet(inputFileName));
        lst.add(0, 0);
        long[] arr = new long[lst.size()];

        arr[0] = 1;
        for (int i = 0; i < lst.size(); i++) {
            for (int j = i + 1; j < lst.size() && lst.get(j) - lst.get(i) <= 3; j++) {
                arr[j] += arr[i];
            }
        }
        return "" + arr[arr.length - 1];
    }

    private Set<Integer> getAdaptersSet(String inputFileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        String st;
        Set<Integer> set = new TreeSet<>();

        while ((st = br.readLine()) != null) {
            set.add(Integer.parseInt(st));
        }

        return set;
    }
}

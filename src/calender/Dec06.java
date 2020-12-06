package calender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import main.Calculation;

/**
 *
 * @author georggrubner
 */
public class Dec06 implements Calculation {

    @Override
    public String calculateStar1(String inputFileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        String st;
        Set<Character> set = new TreeSet<>();
        int count = 0;

        while ((st = br.readLine()) != null) {
            if (st.isEmpty()) {
                count += set.size();
                set.clear();
            } else {
                char[] c = st.toCharArray();
                for (char d : c) {
                    set.add(d);
                }
            }
        }
        count += set.size();

        return "" + count;
    }

    @Override
    public String calculateStar2(String inputFileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        String st;
        List<Character> lst = new ArrayList<>();
        boolean initialized = false;
        int count = 0;

        while ((st = br.readLine()) != null) {
            if (st.isEmpty()) {
                count += lst.size();
                lst.clear();
                initialized = false;
            } else {
                char[] c = st.toCharArray();
                if (lst.isEmpty() && !initialized) {
                    for (char d : c) {
                        lst.add(d);
                        initialized = true;
                    }
                } else {
                    List<Character> lst2 = new ArrayList<>();
                    for (char d : c) {
                        lst2.add(d);
                    }
                    lst.retainAll(lst2);
                }
            }
        }
        count += lst.size();

        return "" + count;
    }
}

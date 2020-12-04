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
public class Dec01 implements Calculation {

    @Override
    public String calculateStar1(String inputFileName) throws Exception {
        List<String> lst = getList(inputFileName);
        for (int i = 0; i < lst.size() - 1; i++) {
            for (int j = i + 1; j < lst.size(); j++) {
                int compare1 = Integer.parseInt(lst.get(i));
                int compare2 = Integer.parseInt(lst.get(j));
                if (compare1 + compare2 == 2020) {
                    return "" + (compare1 * compare2);
                }
            }
        }

        return "result not found";
    }

    @Override
    public String calculateStar2(String inputFileName) throws Exception {
        List<String> lst = getList(inputFileName);

        for (int i = 0; i < lst.size() - 2; i++) {
            for (int j = i + 1; j < lst.size(); j++) {
                for (int k = j + 1; k < lst.size(); k++) {
                    int compare1 = Integer.parseInt(lst.get(i));
                    int compare2 = Integer.parseInt(lst.get(j));
                    int compare3 = Integer.parseInt(lst.get(k));
                    if (compare1 + compare2 + compare3 == 2020) {
                        return "" + (compare1 * compare2 * compare3);
                    }
                }

            }
        }

        return "result not found";
    }

    static List<String> getList(String inputFileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        String st;
        List<String> lst = new ArrayList<>();

        while ((st = br.readLine()) != null) {
            lst.add(st);
        }
        return lst;
    }
}

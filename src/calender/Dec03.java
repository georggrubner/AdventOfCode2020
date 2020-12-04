package calender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import main.Calculation;

/**
 *
 * @author grubn
 */
public class Dec03 implements Calculation {

    @Override
    public String calculateStar1(String inputFileName) throws Exception {
        List<String> lst = getList(inputFileName);

        return "" + countTrees(lst, 3, 1);
    }

    @Override
    public String calculateStar2(String inputFileName) throws Exception {
        List<String> lst = getList(inputFileName);

        return "" + (countTrees(lst, 1, 1) * countTrees(lst, 3, 1) * countTrees(lst, 5, 1) * countTrees(lst, 7, 1) * countTrees(lst, 1, 2));
    }

    static long countTrees(List<String> lst, int deltaX, int deltaY) {
        int j = 0;
        long count = 0;
        for (int i = 0; i < lst.size(); i += deltaY) {
            if (lst.get(i).charAt(j) == '#') {
                count++;
            }
            j = (j + deltaX) % lst.get(0).length();
        }
        System.out.println(count);
        return count;
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

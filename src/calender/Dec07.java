package calender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.Calculation;

/**
 *
 * @author georggrubner
 */
public class Dec07 implements Calculation {

    private static Map<String, List<String>> map;

    @Override
    public String calculateStar1(String inputFileName) throws Exception {
        initializeBagMap(inputFileName);
        int count = 0;

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            List<String> value = entry.getValue();
            if (containsBag("shiny gold", value)) {
                count++;
            }
        }

        return "" + count;
    }

    @Override
    public String calculateStar2(String inputFileName) throws Exception {
        initializeBagMap(inputFileName);

        return "" + countBags(map.get("shiny gold"));
    }

    static boolean containsBag(String bag, List<String> lst) {
        if (lst.isEmpty()) {
            return false;
        }
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).contains(bag)) {
                return true;
            }
        }
        for (int i = 0; i < lst.size(); i++) {
            String bagToSearch = lst.get(i);
            int index = bagToSearch.indexOf(" ");
            bagToSearch = bagToSearch.substring(index + 1);
            boolean contains = containsBag(bag, map.get(bagToSearch));
            if (contains) {
                return true;
            }
        }

        return false;
    }

    static int countBags(List<String> lst) {
        int count = 0;
        for (int i = 0; i < lst.size(); i++) {
            String bagToSearch = lst.get(i);
            int index = bagToSearch.indexOf(" ");
            int amount = Integer.parseInt(bagToSearch.substring(0, index));
            bagToSearch = bagToSearch.substring(index + 1);
            count += amount * (1 + countBags(map.get(bagToSearch)));
        }
        return count;
    }
    
    static void initializeBagMap(String inputFileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        String st;
        map = new HashMap<>();

        while ((st = br.readLine()) != null) {
            String[] s = st.split("\\b bags contain \\b");
            String key = s[0];
            List<String> lst = new ArrayList<>();
            String[] values = s[1].split(",");
            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                if (value.endsWith(".")) {
                    value = value.substring(0, value.length() - 1);
                }
                if (value.endsWith("s")) {
                    value = value.substring(0, value.length() - 1);
                }
                value = value.substring(0, value.length() - 4).trim();
                if (!value.equals("no other")) {
                    lst.add(value);
                }
            }
            map.put(key, lst);
        }
    }
}

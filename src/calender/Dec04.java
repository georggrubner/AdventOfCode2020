package calender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;
import main.Calculation;

/**
 *
 * @author georggrubner
 */
public class Dec04 implements Calculation {

    @Override
    public String calculateStar1(String inputFileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        String st;
        Map<String, String> map = new TreeMap<>();
        int count = 0;

        while ((st = br.readLine()) != null) {
            String[] s = st.split(" ");
            if (st.isEmpty()) {
                System.out.println(map.size());
                if (map.size() == 7) {
                    count++;
                }
                map.clear();
            } else {
                for (String pairs : s) {
                    String[] pair = pairs.split(":");
                    String key = pair[0];
                    String value = pair[1];
                    if (!key.equals("cid")) {
                        map.put(key, value);
                    }
                }
            }
        }
        if (map.size() == 7) {
            count++;
        }
        map.clear();

        return "" + count;
    }

    @Override
    public String calculateStar2(String inputFileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        String st;
        Map<String, String> map = new TreeMap<>();
        int count = 0;

        while ((st = br.readLine()) != null) {
            String[] s = st.split(" ");
            if (st.isEmpty()) {
                System.out.println(map.size());
                if (map.size() == 7) {
                    count++;
                }
                map.clear();
            } else {
                for (int i = 0; i < s.length; i++) {
                    String[] pairs = s[i].split(":");
                    String key = pairs[0];
                    String value = pairs[1];
                    if (!key.equals("cid")) {
                        if (key.equals("byr")) {
                            int byr = Integer.parseInt(value);
                            if (byr >= 1920 && byr <= 2002) {
                                map.put(key, value);
                            }
                        }
                        if (key.equals("iyr")) {
                            int iyr = Integer.parseInt(value);
                            if (iyr >= 2010 && iyr <= 2020) {
                                map.put(key, value);
                            }
                        }
                        if (key.equals("eyr")) {
                            int eyr = Integer.parseInt(value);
                            if (eyr >= 2020 && eyr <= 2030) {
                                map.put(key, value);
                            }
                        }
                        if (key.equals("hgt")) {
                            if (value.length() >= 4) {
                                int hgt = Integer.parseInt(value.substring(0, value.length() - 2));
                                String unit = value.substring(value.length() - 2);
                                if (("cm".equals(unit) && hgt >= 150 && hgt <= 193) || ("in".equals(unit) && hgt >= 59 && hgt <= 76)) {
                                    map.put(key, value);
                                }
                            }
                        }
                        if (key.equals("hcl")) {
                            if (value.length() == 7 && value.charAt(0) == '#') {
                                String hcl = value.substring(1);
                                if (hcl.matches("[0-9A-Fa-f]+")) {
                                    map.put(key, value);
                                }
                            }
                        }
                        if (key.equals("ecl")) {
                            if (value.equals("amb") || value.equals("blu") || value.equals("brn") || value.equals("gry") || value.equals("grn") || value.equals("hzl") || value.equals("oth")) {
                                map.put(key, value);
                            }
                        }
                        if (key.equals("pid")) {
                            if (value.length() == 9) {
                                map.put(key, value);
                            }
                        }
                    }
                }
            }
        }
        if (map.size() == 7) {
            count++;
        }
        map.clear();

        return "" + count;
    }
}

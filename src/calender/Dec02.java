package calender;

import java.io.BufferedReader;
import java.io.FileReader;
import main.Calculation;

/**
 *
 * @author grubn
 */
public class Dec02 implements Calculation {

    @Override
    public String calculateStar1(String inputFileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        String st;
        int result = 0;

        while ((st = br.readLine()) != null) {
            String[] s = st.split("-");
            int min = Integer.parseInt(s[0]);
            s = s[1].split(" ");
            int max = Integer.parseInt(s[0]);
            char letter = s[1].substring(0, s[1].length() - 1).charAt(0);
            String password = s[2];

            long count = password.chars().filter((value) -> value == letter).count();

            if (count >= min && count <= max) {
                result++;
            }
        }

        return "" + result;
    }

    @Override
    public String calculateStar2(String inputFileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        String st;
        int result = 0;

        while ((st = br.readLine()) != null) {
            String[] s = st.split("-");
            int index1 = Integer.parseInt(s[0]) - 1;
            s = s[1].split(" ");
            int index2 = Integer.parseInt(s[0]) - 1;
            char letter = s[1].substring(0, s[1].length() - 1).charAt(0);
            String password = s[2];

            char[] chars = password.toCharArray();

            if ((chars[index1] == letter && chars[index2] != letter) || chars[index2] == letter && chars[index1] != letter) {
                result++;
            }
        }

        return "" + result;
    }
}

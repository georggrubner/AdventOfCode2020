package calender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import main.Calculation;

/**
 *
 * @author georggrubner
 */
public class Dec14 implements Calculation {

    @Override
    public String calculateStar1(String inputFileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        String st;
        char[] mask = new char[36];
        Map<String, Long> map = new HashMap<>();

        while ((st = br.readLine()) != null) {
            if (st.startsWith("mask")) {
                mask = st.substring(7).toCharArray();
            } else {
                String[] s;
                s = st.substring(4).split("]");

                String mem = s[0];
                char[] value = withLeadingZeros(Integer.toBinaryString(Integer.parseInt(s[1].substring(3))), 36).toCharArray();

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < mask.length; i++) {
                    sb.append('X' == mask[i] ? value[i] : mask[i]);
                }

                map.put(mem, (new BigInteger(sb.toString(), 2)).longValueExact());
            }
        }

        return "" + map.values().stream().mapToLong((i) -> i).sum();
    }

    @Override
    public String calculateStar2(String inputFileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        String st;
        String mask = "";
        Map<String, Integer> map = new HashMap<>();

        while ((st = br.readLine()) != null) {
            if (st.startsWith("mask")) {
                mask = st.substring(7);
            } else {
                String[] s;
                s = st.substring(4).split("]");
                char[] mem = withLeadingZeros(Integer.toBinaryString(Integer.parseInt(s[0])), 36).toCharArray();
                int value = Integer.parseInt(s[1].substring(3));

                int x = (int) mask.chars().filter((c) -> c == 'X').count();
                int possibilities = (int) Math.pow(2, x);

                StringBuilder sb = new StringBuilder();
                char[] maskArray = mask.toCharArray();
                for (int i = 0; i < mask.length(); i++) {
                    sb.append('0' == maskArray[i] ? mem[i] : maskArray[i]);
                }

                String maskedMem = sb.toString();

                for (int i = 0; i < possibilities; i++) {
                    maskArray = maskedMem.toCharArray();
                    char[] memPossibility = withLeadingZeros(Integer.toBinaryString(i), x).toCharArray();
                    for (int j = 0, k = 0; j < maskArray.length; j++) {
                        if (maskArray[j] == 'X') {
                            maskArray[j] = memPossibility[k];
                            k++;
                        }
                    }
                    map.put(String.valueOf(maskArray), value);
                }
            }
        }

        return "" + map.values().stream().mapToLong((i) -> i).sum();
    }

    static String withLeadingZeros(String s, int length) {
        String zeros = IntStream.range(0, length).mapToObj((i) -> "0").collect(Collectors.joining());

        return (zeros + s).substring(s.length());
    }
}

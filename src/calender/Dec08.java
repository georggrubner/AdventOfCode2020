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
public class Dec08 implements Calculation {

    @Override
    public String calculateStar1(String inputFileName) throws Exception {
        List<Instruction> lst = initializeInstructionsList(inputFileName);

        int accumulator = 0;
        int i = 0;
        while (!lst.get(i).used) {
            lst.get(i).used = true;
            if ("acc".equals(lst.get(i).operation)) {
                accumulator += lst.get(i).argument;
            }
            if ("jmp".equals(lst.get(i).operation)) {
                i += lst.get(i).argument;
                continue;
            }
            i++;
        }

        return "" + accumulator;
    }

    @Override
    public String calculateStar2(String inputFileName) throws Exception {
        List<Instruction> originalList = initializeInstructionsList(inputFileName);

        int accumulator = 0;
        boolean finished = false;
        int index = 0;
        while (!finished && index < originalList.size()) {
            accumulator = 0;
            List<Instruction> lst = initializeInstructionsList(inputFileName);
            if ("jmp".equals(lst.get(index).operation)) {
                lst.set(index, new Instruction("nop", lst.get(index).argument));
            } else if ("nop".equals(lst.get(index).operation)) {
                lst.set(index, new Instruction("jmp", lst.get(index).argument));
            }
            if ("acc".equals(lst.get(index).operation)) {
                index++;
                continue;
            }
            int i = 0;
            while (true) {
                if (i >= lst.size()) {
                    finished = true;
                    break;
                }
                if (lst.get(i).used) {
                    break;
                }
                lst.get(i).used = true;
                if ("acc".equals(lst.get(i).operation)) {
                    accumulator += lst.get(i).argument;
                }
                if ("jmp".equals(lst.get(i).operation)) {
                    i += lst.get(i).argument;
                    continue;
                }
                i++;
            }
            index++;
        }

        return "" + accumulator;
    }

    private List<Instruction> initializeInstructionsList(String inputFileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        List<Instruction> lst = new ArrayList<>();
        String st;

        while ((st = br.readLine()) != null) {
            String[] s = st.split(" ");
            lst.add(new Instruction(s[0], Integer.parseInt(s[1])));
        }

        return lst;
    }
}

class Instruction {

    String operation;
    int argument;
    boolean used;

    public Instruction(String operation, int argument) {
        this.operation = operation;
        this.argument = argument;
        this.used = false;
    }

    @Override
    public String toString() {
        return operation + " " + argument + " " + used;
    }

}

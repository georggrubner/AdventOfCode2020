package calender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import main.Calculation;

/**
 *
 * @author georggrubner
 */
public class Dec12 implements Calculation {

    @Override
    public String calculateStar1(String inputFileName) throws Exception {
        List<String> lst = getInstructions(inputFileName);
        int direction = 0;
        int east = 0; // negative is west
        int south = 0; // negative is north

        for (int i = 0; i < lst.size(); i++) {
            String instruction = lst.get(i);
            char d = instruction.charAt(0);
            int move = Integer.parseInt(instruction.substring(1));

            switch (d) {
                case 'N':
                    south -= move;
                    break;
                case 'S':
                    south += move;
                    break;
                case 'E':
                    east += move;
                    break;
                case 'W':
                    east -= move;
                    break;
                case 'F':
                    switch (direction) {
                        case 0:
                            east += move;
                            break;
                        case 90:
                            south += move;
                            break;
                        case 180:
                            east -= move;
                            break;
                        case 270:
                            south -= move;
                            break;
                        default:
                            break;
                    }
                    break;
                case 'R':
                    direction += move;
                    direction %= 360;
                    break;
                case 'L':
                    direction += 360 - move;
                    direction %= 360;
                    break;
                default:
                    break;
            }
        }

        return "" + (Math.abs(east) + Math.abs(south));
    }

    @Override
    public String calculateStar2(String inputFileName) throws Exception {
        List<String> lst = getInstructions(inputFileName);
        int waypointEast = 10; // negative is west
        int waypointSouth = -1; // negative is north
        int shipEast = 0; // negative is west
        int shipSouth = 0; // negative is north

        for (int i = 0; i < lst.size(); i++) {
            String instruction = lst.get(i);
            char d = instruction.charAt(0);
            int move = Integer.parseInt(instruction.substring(1));

            switch (d) {
                case 'N':
                    waypointSouth -= move;
                    break;
                case 'S':
                    waypointSouth += move;
                    break;
                case 'E':
                    waypointEast += move;
                    break;
                case 'W':
                    waypointEast -= move;
                    break;
                case 'F':
                    shipEast += waypointEast * move;
                    shipSouth += waypointSouth * move;
                    break;
                case 'R':
                    int[] coordsR = rotate(waypointEast, waypointSouth, move);
                    waypointEast = coordsR[0];
                    waypointSouth = coordsR[1];
                    break;
                case 'L':
                    int[] coordsL = rotate(waypointEast, waypointSouth, 360 - move);
                    waypointEast = coordsL[0];
                    waypointSouth = coordsL[1];
                    break;
                default:
                    break;
            }
        }

        return "" + (Math.abs(shipEast) + Math.abs(shipSouth));
    }

    private List<String> getInstructions(String inputFileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        List<String> lst = new ArrayList<>();
        String st;

        while ((st = br.readLine()) != null) {
            lst.add(st);
        }

        return lst;
    }

    private int[] rotate(int waypointEast, int waypointSouth, int move) {
        switch (move) {
            case 90:
                return new int[]{-waypointSouth, waypointEast};
            case 180:
                return new int[]{-waypointEast, -waypointSouth};
            case 270:
                return new int[]{waypointSouth, -waypointEast};
            default:
                return new int[]{waypointEast, waypointSouth};
        }
    }
}

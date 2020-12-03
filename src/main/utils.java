/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 *
 * @author grubn
 */
public class utils {

    public static String getDayName(int day) {
        return "Dec" + (day < 10 ? "0" : "") + day;
    }

    private static int getParameter(Integer[] instructions, int parameterMode, int adress) {
        int parameter = instructions[adress];
        return parameterMode == 0 && parameter >= 0 && parameter <= instructions.length ? instructions[parameter] : parameter;
    }

    public static int runIntcode(final Integer[] array, int[] input) {
        Integer[] instructions = array.clone();
        int inputPointer = 0;
        Integer diagnosticCode = null;
        boolean halt = false;

        for (int instructionPointer = 0; instructionPointer < instructions.length && !halt;) {
            try {
                int instruction = instructions[instructionPointer];
                int opcode = instruction % 100;
                int parameter1 = getParameter(instructions, (instruction / 100) % 2, instructionPointer + 1);
                int parameter2 = getParameter(instructions, (instruction / 1000) % 2, instructionPointer + 2);
                int addressTarget = instructionPointer + 3 < instructions.length ? instructions[instructionPointer + 3] : instructions.length;
                switch (opcode) {
                    case 1: {
                        instructions[addressTarget] = parameter1 + parameter2;
                        instructionPointer += 4;
                        break;
                    }
                    case 2: {
                        instructions[addressTarget] = parameter1 * parameter2;
                        instructionPointer += 4;
                        break;
                    }
                    case 3: {
                        instructions[instructions[instructionPointer + 1]] = input[inputPointer];
                        instructionPointer += 2;
                        inputPointer++;
                        break;
                    }
                    case 4: {
                        diagnosticCode = parameter1;
                        instructionPointer += 2;
                        break;
                    }
                    case 5: {
                        instructionPointer = parameter1 != 0 ? parameter2 : instructionPointer + 3;
                        break;
                    }
                    case 6: {
                        instructionPointer = parameter1 == 0 ? parameter2 : instructionPointer + 3;
                        break;
                    }
                    case 7: {
                        instructions[addressTarget] = parameter1 < parameter2 ? 1 : 0;
                        instructionPointer += 4;
                        break;
                    }
                    case 8: {
                        instructions[addressTarget] = parameter1 == parameter2 ? 1 : 0;
                        instructionPointer += 4;
                        break;
                    }
                    case 99: {
                        halt = true;
                        break;
                    }
                    default: {
                        System.out.println("Unknown opcode " + opcode + " on instructionPointer " + instructionPointer);
                        return -1;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                return diagnosticCode;
            }
        }
        if (diagnosticCode != null) {
            return diagnosticCode;
        }
        return instructions[0];
    }

    public static int runIntcode(final Integer[] array) {
        return runIntcode(array, new int[]{0});
    }

    public static int runIntcode(final Integer[] array, int noun, int verb) {
        Integer[] instructions = array.clone();

        instructions[1] = noun;
        instructions[2] = verb;

        return runIntcode(instructions);
    }

    public static List<String> getAllPossiblePermutations(String str, int start, int end, List<String> lst) {
        if (start == end - 1) {
            lst.add(str);
        } else {
            for (int i = start; i < end; i++) {
                str = swap(str, start, i);
                getAllPossiblePermutations(str, start + 1, end, lst);
                str = swap(str, start, i);
            }
        }
        return lst;
    }

    public static String swap(String a, int i, int j) {
        char[] charArray = a.toCharArray();
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
}

package ua.lviv.iot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.pow;

public class Main {

    private static double lengthBetween;
    private static List<Integer> listOfHeights = new ArrayList<>();

    private static double calculateOptimalLength(double x1, double x2) {
        return pow((pow(lengthBetween, 2) + pow((x1 - x2), 2)), 0.5);
    }

    public static void main(String[] args) {
        File file = new File("text.txt");
        readFromFile(file);
        double d = sort();
        System.out.println(d);
    }

    static double sort() {
        int minHeight = 1;
        double firstWay = 0.0;
        double secondWay = 0.0;

        for (int i = 0; i < listOfHeights.size() - 1; i++) {
            double firstPointer = firstWay;
            double secondPointer = secondWay;

            double downToDown = calculateOptimalLength(minHeight, minHeight);
            double downToTop = calculateOptimalLength(minHeight, listOfHeights.get(i + 1));
            double topToDown = calculateOptimalLength(listOfHeights.get(i), minHeight);
            double topToTop = calculateOptimalLength(listOfHeights.get(i), listOfHeights.get(i + 1));

            firstWay =  max((downToDown + firstPointer), (topToDown + secondPointer));
            secondWay = max((topToTop + secondPointer), (downToTop + firstPointer));

        }

        return max(firstWay, secondWay);
    }

    static void readFromFile(File file) {
        try {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            String st;
            int count = 0;
            while ((st = br.readLine()) != null) {
                for (String retval : st.split(" ")) {
                    if (count == 0) {
                        lengthBetween = Integer.parseInt(retval);
                        count++;
                    } else {
                        listOfHeights.add(Integer.parseInt(retval));
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}

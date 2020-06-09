/**
 * Name: Eric Truong
 * Date: September 18, 2019
 * Description: Given a file of students' grades, allow the user to display a sorted list, find the average, find the
 * maximum, minimum, and median grades
 */

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import java.text.DecimalFormat;

public class StatisticalAnalysis {
    public static void main (String[] args) {

        DecimalFormat df = new DecimalFormat("0.0");

        ArrayList <Integer> studentGrades = populateGrades();

        int choice = 0;

        //loop the program until the user wants to quit
        while (choice != 6) {
            displayMenu();
            System.out.print("Please make a selection: ");
            choice = CheckInput.getIntRange(1, 6);

            if (choice == 1) {
                displayGrades(studentGrades);
            }

            else if (choice == 2) {
                double average = sumGrades(studentGrades);
                average = average / studentGrades.size();
                System.out.println("The average is " + df.format(average));
            }

            else if (choice == 3) {
                studentGrades = sortList(studentGrades);
                System.out.println("The maximum is " + studentGrades.get(studentGrades.size()-1));
            }

            else if (choice == 4) {
                studentGrades = sortList(studentGrades);
                System.out.println("The minimum is " + studentGrades.get(0));
            }

            else if (choice == 5) {
                double median = findMedian(studentGrades);
                System.out.println("The median is " + df.format(median));
            }
            System.out.println("");

        }
        System.out.println("Goodbye!");
    }

    /**
     * Given a test file of grades, add them to an ArrayList
     * @return  return ArrayList full of grades
     */
    public static ArrayList <Integer> populateGrades() {
        ArrayList <Integer> studentGrades = new ArrayList <Integer> ();
        try {
            Scanner read = new Scanner(new File("grades.txt"));
            do {
                String line = read.nextLine();
                int grades = Integer.parseInt(line);
                studentGrades.add(grades);
            } while(read.hasNext());
            read.close();
        } catch (FileNotFoundException fnf) {
            System.out.println("File not found");
        }

        return studentGrades;
    }

    /**
     * Given an ArrayList, sort it and display each value
     * @param studentGrades user's given ArrayList
     */
    public static void displayGrades(ArrayList <Integer> studentGrades) {
        studentGrades = sortList(studentGrades);
        int counter = 0;
        for (int i : studentGrades) {
            System.out.print(i);
            if (counter != 9) {
                System.out.print(", ");
            }
            if (counter == 9) {
                System.out.println("");
            }
            counter++;
            if (counter > 9) {
                counter = 0;
            }
        }
    }

    /**
     * Given a user's ArrayList, sort each value from decreasing to increasing
     * @param studentGrades user's ArrayList
     * @return              return sorted ArrayList
     */
    public static ArrayList <Integer> sortList (ArrayList <Integer> studentGrades) {
        for (int i = 0; i < studentGrades.size(); i++) {
            int lowest = i;
            for (int j = i + 1; j < studentGrades.size(); j++) {
                if (studentGrades.get(j) < studentGrades.get(lowest)) {
                    lowest = j;
                }
            }
            int swap = studentGrades.get(i);
            studentGrades.set(i, studentGrades.get(lowest));
            studentGrades.set(lowest, swap);
        }
        return studentGrades;
    }

    /**
     * Given user's ArrayList, calculate the s um
     * @param studentGrades user's ArrayList
     * @return              sum of user's ArrayList
     */
    public static int sumGrades(ArrayList <Integer> studentGrades) {
        int sum = 0;
        for (int i : studentGrades) {
            sum += i;
        }
        return sum;
    }

    /**
     * Given user's ArrayList, find the median
     * @param studentGrades user's ArrayList
     * @return              median of ArrayList
     */
    public static double findMedian(ArrayList <Integer> studentGrades) {
        studentGrades = sortList(studentGrades);
        double median = 0;
        if (studentGrades.size() % 2 == 0) {
            median = (double) (studentGrades.get((studentGrades.size()/2)-1) + (studentGrades.get((studentGrades.size()/2))))/2;
        }
        else {
            median = (double) (studentGrades.get((studentGrades.size() + 1)/2));
        }
        return median;
    }

    /**
     * Displays options for the user to choose from
     */
    public static void displayMenu() {
        System.out.println("1. Display Sorted Grades \n" +
                "2. Display Average Grade \n" +
                "3. Display Maximum Grade \n" +
                "4. Display Minimum Grade \n" +
                "5. Display Median Grade \n" +
                "6. Quit");
    }
}

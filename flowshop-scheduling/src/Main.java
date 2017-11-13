import java.util.ArrayList;

/**
 * Class for testing the NEH-algorithm.
 * 
 * @author maxkratz
 * @version 0.1.8
 */
public class Main {

  public static void main(String[] args) {
    // create an NEH-object
    NEH myNEHObject = new NEH();

    // create an arraylist of ints
    ArrayList<int[]> jobList = new ArrayList<int[]>();

    // create int-arrays for jobs
    int[] job1 = {54, 79, 16, 66, 58};
    int[] job2 = {83, 3, 89, 58, 56};
    int[] job3 = {15, 11, 49, 31, 20};
    int[] job4 = {71, 99, 15, 68, 85};
    int[] job5 = {77, 56, 89, 78, 53};
    int[] job6 = {36, 70, 45, 91, 35};
    int[] job7 = {53, 99, 60, 13, 53};
    int[] job8 = {38, 60, 23, 59, 41};
    int[] job9 = {27, 5, 57, 49, 69};
    int[] job10 = {87, 56, 64, 85, 13};
    int[] job11 = {76, 3, 7, 85, 86};
    int[] job12 = {91, 61, 1, 9, 72};
    int[] job13 = {14, 73, 63, 39, 8};
    int[] job14 = {29, 75, 41, 41, 49};
    int[] job15 = {12, 47, 63, 56, 47};
    int[] job16 = {77, 14, 47, 40, 87};
    int[] job17 = {32, 21, 26, 54, 58};
    int[] job18 = {87, 86, 75, 77, 18};
    int[] job19 = {68, 5, 77, 51, 68};
    int[] job20 = {94, 77, 40, 31, 28};


    // add the job-arrays to the arraylist
    jobList.add(job1);
    jobList.add(job2);
    jobList.add(job3);
    jobList.add(job4);
    jobList.add(job5);
    jobList.add(job6);
    jobList.add(job7);
    jobList.add(job8);
    jobList.add(job9);
    jobList.add(job10);
    jobList.add(job11);
    jobList.add(job12);
    jobList.add(job13);
    jobList.add(job14);
    jobList.add(job15);
    jobList.add(job16);
    jobList.add(job17);
    jobList.add(job18);
    jobList.add(job19);
    jobList.add(job20);

    // end of creating the test-sequences

    // calculate the arraylist with the best order
    ArrayList<int[]> results = myNEHObject.calculateNEHOrder(jobList);

    // calculate the total makespan
    int totalMakespan = myNEHObject.calculateNTotalMakespan(results);

    System.out.println(myNEHObject.calculateOrder(results, jobList));
    System.out.println("Total makespan: " + totalMakespan);
  }
}

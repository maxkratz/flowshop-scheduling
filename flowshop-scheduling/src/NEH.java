import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Class for the NEG-algorithm.
 * 
 * @author maxkratz
 * @version 0.1.8
 */
public class NEH {
	/**
	 * Default constructor
	 */
	public NEH() {
		// empty ...
	}
	
	/**
	 * Method for step nr 1. Returns an ArrayList with the totaltime for each job.
	 * 
	 * @param jobs
	 * 			ArrayList of jobs (input)
	 * @return totaltimes
	 * 			ArrayList with the totaltime for each job
	 */
	public ArrayList<Integer> step_one(ArrayList<int[]> jobs) {
		// create an empy ArrayList of integers	
		ArrayList<Integer> totalTimes = new ArrayList<Integer>();
		
		for(int i = 0; i < jobs.size(); i++) {
			int sum = 0; // variable for summing
			
			// go through each job
			for(int j = 0; j < jobs.get(i).length; j++) {
				sum = sum + jobs.get(i)[j]; // sum up
			}
		
			totalTimes.add(sum);
		}
		
		return totalTimes;
	}
	
	/**
	 * Step 2: Sort the list (descending order)
	 * 
	 * @param jobs
	 * 			ArrayList of all jobs
	 * @return list of jobs
	 * 			descending order
	 */
	public ArrayList<int[]> step_two(ArrayList<Integer> jobs) {
		ArrayList<int[]> numberAndJobs = new ArrayList<int[]>();
		
		// add all jobs to an ArrayList
		for(int i = 0; i < jobs.size(); i++) {
			int[] tempElement = {i, jobs.get(i)}; // temporary Array with the job-number and the jobs
			numberAndJobs.add(tempElement); // add the temporaray Array to the ArrayList
		}
		
		// comparator for sorting the jobs
		Comparator<int[]> jobComparator = new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				
				if(o1[1] < o2[1]) {
					return 1;
				}
				
				if(o1[1] > o2[1]) {
					return -1;
				}
				
				return 0;
			}
	    }; // end of comparator
		
		// sort ArrayList descending
		Collections.sort(numberAndJobs, jobComparator);
		
		return numberAndJobs;
	}
	
	/**
	 * Searches for the biggest two elements and puts them in the best order.
	 * 
	 * @param sortierteListe
	 * 			descending sorted list with all jobs
	 * @return Liste
	 * 			list with the biggest two elements (sorted)
	 * 
	 */
	public ArrayList<int[]> sortFirstTwoElements(ArrayList<int[]> sortedList, int[] first, int[] second) {
		// search for the to biggest elemets:
		int one = 0;
		int oneValue = 0;
		int twoValue = 0;
		
		// find the biggest job:
		for(int i = 0; i < sortedList.size(); i++) {
			if(sortedList.get(i)[1] > oneValue) {
				oneValue = sortedList.get(i)[1]; 
				one = sortedList.get(i)[0];	
			}
		}
		
		// search for the second largest element:
		for(int i = 0; i < sortedList.size(); i++) {
			if(sortedList.get(i)[1] > twoValue && sortedList.get(i)[0] != one) {
				twoValue = sortedList.get(i)[1];
			}
		}
		
		/*
		 * Until now, the first and the second largest jobs are found.
		 * Todo: Test both sequences (1 first, 2 second or 2 first, 1 secon).
		 */
		
		ArrayList<int[]> resultList = new ArrayList<int[]>();
		
		int makespan1 = this.calculateTotalMakespan(first, second);
		int makespan2 = this.calculateTotalMakespan(second, first);
		
		if(makespan1 > makespan2) {
			resultList.add(sortedList.get(1));
			resultList.add(sortedList.get(0));
		}
		
		else {
			resultList.add(sortedList.get(0));
			resultList.add(sortedList.get(1));
		}
		
		return resultList; 
	}
	
	/**
	 * Returns the makespan of n jobs.
	 * 
	 * @param jobs
	 * 			ArrayList of jobs
	 * 
	 * @return makespan
	 * 			calculated makespan
	 */
	public int calculateNTotalMakespan(ArrayList<int[]> jobs) {
		int totalMakespan = 0; 
		
		ArrayList<int[]> myJobSumList = new ArrayList<int[]>();
		
		// create a job1-array with the summings
		myJobSumList.add(new int[jobs.get(0).length]);
		
		myJobSumList.get(0)[0] = jobs.get(0)[0];
		
		// summing
		for(int i = 1; i < jobs.get(0).length; i++) {
			myJobSumList.get(0)[i] = myJobSumList.get(0)[i-1] + jobs.get(0)[i];
		}
		
		// end of job1-array creating
		
		// add all other "empty" job-arrays to the list
		for(int i = 1; i < jobs.size(); i++) {
			myJobSumList.add(new int[jobs.get(i).length]);
		}
		
		// fill the first row (from left to right summing)
		for(int i = 1; i < myJobSumList.size(); i++) {
			myJobSumList.get(i)[0] = myJobSumList.get(i-1)[0] + jobs.get(i)[0];
		}
		
		// go through all jobs each
		for(int i = 1; i < jobs.size(); i++) {
			for(int j = 1; j < myJobSumList.get(i).length; j++) {
				// is the first time of the first job larger, chose it ...
				if(myJobSumList.get(i-1)[j] > myJobSumList.get(i)[j-1]) {
					myJobSumList.get(i)[j] = myJobSumList.get(i-1)[j] + jobs.get(i)[j];
				}
				
				// is the time of the second job larger, chose it ...
				else {
					myJobSumList.get(i)[j] = myJobSumList.get(i)[j-1] + jobs.get(i)[j];
				}
			}
		}
		
		totalMakespan = myJobSumList.get(myJobSumList.size()-1)[myJobSumList.get(myJobSumList.size()-1).length-1];
		
		return totalMakespan;
	}
	
	/**
	 * Return the makespan of two jobs.
	 * 
	 * @param job1
	 * 			first job
	 * @param job2
	 * 			second job
	 * @return makespan
	 * 			calculated makespan
	 */
	public int calculateTotalMakespan(int[] job1, int[] job2) {
		// create a job1-array with the summing
		int[] job1_d = new int[job1.length];
		
		job1_d[0] = job1[0]; // add first place
		
		// summing
		for(int i = 1; i < job1.length; i++) {
			job1_d[i] = job1_d[i-1] + job1[i]; 
		}

		// end of creation of the job1-array (with summing)
		
		// create a job2-array with summing (with case destinction)
		int[] job2_d = new int[job2.length];
		
		job2_d[0] = job1_d[0] + job2[0]; // add first place
		
		// add all other places
		
		/*
		 * Is the job of the first machine or the first job faster?
		 * Chose the higher result for calculatin.
		 */
		for(int i = 1; i < job2.length; i++) {
			// is the first time of the first job larger, chose it ...
			if(job1_d[i] > job2_d[i-1]) {
				job2_d[i] = job1_d[i] + job2[i];
			}
			
			// is the time of the second job larger, chose it ...
			else {
				job2_d[i] = job2_d[i-1] + job2[i];
			}
		}
		
		return job2_d[job2_d.length-1]; //zurueckgeben
	}
	
	/**
	 * Creates the sum of each job-time.
	 * 
	 * @param job
	 * 			array of a job
	 * 
	 * @return sum
	 * 			sum of each job-time
	 */
	public int calculateJobTotalTime(int[] job) {
		int value = 0;
		
		// summing
		for(int i = 0; i < job.length; i++) {
			value = value + job[i];
		}
		
		return value;
	}

	/**
	 * Calculates the best order for all jobs.
	 * 
	 * @param jobs
	 * 			ArrayList of all jobs (as int-array)
	 * 
	 * @return jobs in order
	 * 			arraylist of all jobs (as int-array) in order with the smallest makespan
	 */
	public ArrayList<int[]> calculateNEHOrder(ArrayList<int[]> jobs) {
		// list for the  first two jobs
		ArrayList<int[]> firstTwoElemetList = new ArrayList<int[]>();
		
		// list for the total times
		ArrayList<Integer> sumList = new ArrayList<Integer>();
		
		//sumList filling:
		for(int i = 0; i < jobs.size(); i++) {
			sumList.add(this.calculateJobTotalTime(jobs.get(i)));
		}
		
		// create sorted list
		ArrayList<int[]> sortierteListe = this.step_two(sumList);
		
		// add the first two elements
		firstTwoElemetList.add(jobs.get(sortierteListe.get(0)[0]));
		firstTwoElemetList.add(jobs.get(sortierteListe.get(1)[0]));
		
		// add the first two jobs in the final list
		ArrayList<int[]> myList_2 =  this.sortFirstTwoElements(firstTwoElemetList, jobs.get(sortierteListe.get(0)[0]), jobs.get(sortierteListe.get(1)[0]));
		
		// iterate through all jobs
		for(int j = 2; j < sortierteListe.size(); j++) {
			int tempMakeSpan = Integer.MAX_VALUE;
			int gutePosition = 0;
			
			// iterate through all positons
			for(int i = 0; i <= myList_2.size(); i++) {
				ArrayList<int[]> tempMyList = new ArrayList<int[]>();
				tempMyList.addAll(myList_2);
				
				tempMyList.add(i, jobs.get(sortierteListe.get(j)[0]));
				
				// if the actual position is better than the one before, go with it ...
				if(this.calculateNTotalMakespan(tempMyList) < tempMakeSpan) {
					tempMakeSpan = this.calculateNTotalMakespan(tempMyList);
					gutePosition = i;
				}
				
			}
			
			// If the second iteration is finished, the best place is found:
			myList_2.add(gutePosition, jobs.get(sortierteListe.get(j)[0]));
		}
		
		//DEBUGGING:
		//System.out.println(this.gibAktuelleReihenfolge(myList_2, jobs));
		
		return myList_2;
	}
	
	/**
	 * Returns the actual order as String (for debugging).
	 * 
	 * @param jobListeAct
	 * 			list of jobs
	 * @param jobs
	 * 			list of jobs (before sorting)
	 * 
	 * @return Order
	 * 			order of jobs as String
	 */
	public String calculateOrder(ArrayList<int[]> jobListeAct, ArrayList<int[]> jobs) { 
		// create an arraylist for the order of the jobs (number of jobs)
		ArrayList<Integer> orderNumber = new ArrayList<Integer>();
		
		System.out.println("Order of jobs: ");
		String orderString = "";
		
		// go through all jobs an find the representing numbers of each job
		for(int i = 0; i < jobListeAct.size(); i++) {
			for(int k = 0; k < jobs.size(); k++) {
				if(jobListeAct.get(i) == jobs.get(k)) {
					orderNumber.add(k+1); // add job in list
					
					// add the number of the job to the string (separated with commas)
					orderString = orderString + String.valueOf(k+1);
					orderString = orderString + ", ";
				}
			}
		}
		
		return orderString;
	}
}

# Flowshop-Scheduling

Java-project for solving a flowshop-scheduling problem (FSP) based on the algorithm of Nawaz, Enscore and Ham (NEH). Click here to learn more about this algorithm: [NEH algorithm](http://mams.rmit.edu.au/b5oatq61pmjl.pdf)

## Features
- Written in Java using eclipse as IDE
- Easy to use with integer values

## Installation
- Clone the repository to your local workspace and import the project with eclipse
- No other libs or packages required

## How to run
- See example in [Main.java](https://github.com/maxkratz/flowshop-scheduling/blob/master/flowshop-scheduling/src/Main.java)
	- Create an object of [NEH.java](https://github.com/maxkratz/flowshop-scheduling/blob/master/flowshop-scheduling/src/NEH.java)
	- Create an arraylist of int-arrays
	- Create an int-array for each job or read the job-times from a textdocument
	- Add the job-arrays to the arraylist
	- run the **calculateNEHOrder(joblist)** method to calculate the Order of the NEH algorithm
	- run the **calculateNTotalMakespan(results)** method to calculate the total makespan of a joblist
- There is a folder with the java doc :-)
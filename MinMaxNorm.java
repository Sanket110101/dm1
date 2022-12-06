import java.util.*;
import java.io.*;

class MinMaxNorm {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("data1.txt"));

		String line = br.readLine();

		String[] items = line.split(" ");

		int [] arr = new int[items.length];

		int i = 0;
		for(String item : items) {
			arr[i++] = Integer.parseInt(item); 
		}

		System.out.println("Data : ");
		System.out.println(Arrays.toString(arr));

		int minValue = arr[0], maxValue = arr[0];
		for(int item : arr) {
			if(minValue > item) {
				minValue = item;
			} else if(maxValue < item) {
				maxValue = item;
			}
		}

		System.out.println("\nMin Value : " + minValue + "\nMax Value : " + maxValue);

		int newMin = 0, newMax = 1;

		System.out.println("\nNormalizing Data with max = "+ newMax +" & min = " + newMin);

		double [] normArr = new double[arr.length];
		i = 0;
		for(int item : arr)
	    {
	    	double a = (item - minValue) / ((maxValue - minValue) * 1.0);
	    	double b = newMax - newMin;

	    	normArr[i++] = a * b + newMin;
	    }

	    for(i=0; i<arr.length; i++)
	    {
	         System.out.printf(arr[i] + "---Normalized value--> %.4f\n", normArr[i]);
	    }

	    double mean = 0, sum = 0;

	    for(double item : arr) {
	    	sum += item;
	    }

	    int n = arr.length;
	    mean = sum / n;

	    double stdDev = 0.0; 
	    double sqrsum = 0.0;

	    for(i = 0; i < n; i++)
	    {
	       sqrsum += (arr[i] - mean) * (arr[i] - mean);
	    }

	    sqrsum /= n;
	    stdDev = Math.sqrt(sqrsum);

	    System.out.printf("\nMean : %.4f \nStandard Deviation : %.4f", mean, stdDev);

	    double zscore[] = new double[n];
	    for(i = 0;i < n; i++)
	    {
	       zscore[i] = (arr[i] - mean) / stdDev;
	    }

	    System.out.println("\nData item -------------> zscore");
	    for(i = 0; i < n; i++)
	    {
	        System.out.printf("%d -------------> %.4f\n", arr[i], zscore[i]);
	    }
	}
}
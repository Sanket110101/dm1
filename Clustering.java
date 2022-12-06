import java.util.*;
import java.io.*;

class Clustering {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("data9.txt"));
		int dimensions = Integer.parseInt(br.readLine().trim().split(",")[0]);
		int totalPoints = Integer.parseInt(br.readLine().trim().split(",")[0]);

		System.out.println("Dimensions :" + dimensions + "\nTotal Points: " + totalPoints);
		double[][] points = new double[totalPoints][dimensions];

		//Reading Data
		for(int i = 0; i < totalPoints; i++) {
			try{
				String[] coordinates = br.readLine().trim().split(",");
				int j = 0;
				for(String str : coordinates) {
					points[i][j++] = Double.parseDouble(str);
				}
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
				break;
			}
		}
		br.close();
		System.out.println("Points");
		for(int i = 0; i < totalPoints; i++) {
			for(int j = 0; j < dimensions; j++) {
				System.out.print(points[i][j] + " ");
			}
			System.out.println();
		}

		//Calculating Center
		double [] center = new double[dimensions];
		for(int i = 0; i < totalPoints; i++) {
			for(int j = 0; j < dimensions; j++) {
				center[j] += points[i][j]; 
			}
		}

		for(int j = 0; j < dimensions; j++) {
				center[j] /= totalPoints; 
		}
		System.out.println("\nCenter");
		for(int i = 0; i < dimensions; i++) {
			System.out.printf("%.2f ", center[i]);
		}

		//Calculating distance
		System.out.println("\n\nDistance From Center");
		double [] distanceFromCenter = new double[totalPoints];
		for(int i = 0; i < totalPoints; i++) {
			double distance = 0;
			for(int j = 0; j < dimensions; j++) {
				distance += (center[j] - points[i][j])*(center[j] - points[i][j]);
			}
			distanceFromCenter[i] = Math.sqrt(distance);
			
			System.out.printf("Distance of point " + (i + 1) + ": %.2f \n", distanceFromCenter[i]);
		}

		//Calculating distance of all points
		double[][] distanceMatrix = new double[totalPoints][totalPoints]; 
		for(int i = 0; i < totalPoints; i++) {
			for(int j = 0; j < i+1; j++) {
				double distance = 0;
				for(int k = 0; k < dimensions; k++) {
					distance += (points[i][k] - points[j][k])*(points[i][k] - points[j][k]);
				}
				distanceMatrix[i][j] = Math.sqrt(distance);
				
			}
		}
		System.out.println("\nDistance Matrix:");

		for(int i = 0; i < totalPoints; i++) {
			for(int j =0; j < totalPoints; j++) {
				System.out.printf("  %.2f", distanceMatrix[i][j]);
			}
			System.out.println();
		}
	}
}
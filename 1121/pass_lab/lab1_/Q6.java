import java.util.Scanner;


public class Q6{
	public static void main(String[] args){

        //your code here

        Scanner sc = new Scanner(System.in);

        double [] students ;
        students = new double[(int) 10.0];


        for(int i =0 ; i < 10 ; i=i+1){
			System.out.print("Please enter the grade of student " + (i+1)+":");
			students[i] = sc.nextDouble();
        }
        System.out.println("The average is " + calculateAverage(students));
        System.out.println("The median is " + calculateMedian(students));
        System.out.println("The Number of student failed is "+calculateNumberFailed(students));
        System.out.println("The Number of student passed is "+calculateNumberPassed(students));
        

	}

	public static double calculateAverage(double[] notes){
        //your code here
        double result = 0 ;
		for (int i=0 ; i< notes.length ; i = i+1) {
			result = result + notes[i] ;
		}
		result = result / (notes.length) ;
        return result;
        //from my Q3 AverageDemo
	}

	public static double calculateMedian(double[] notes){
        //your code here
    
        int a=0 ;
        double b=0 ;
        double result = 0 ; 
        for (int i = 0; i<notes.length; i=i+1) {
            a = i;
            for (int j=i+1; j<notes.length; j=j+1) {
                if (notes[j]<notes[a]) {
                    a=j;
                }
            }
            b=notes[a];
            notes[a]=notes[i];
            notes[i]=b;
        }
        result = (notes[5]+notes[6])/2 ;
        return result ;
	}

	public static int calculateNumberFailed(double[] notes){
        //your code here
        int result ;

		result = 0;
		for(int i = 0; i < notes.length; i=i+1){
			if(notes[i] < 50) {
				result = result +1 ;
			}
		}

		return result;
	}

	public static int calculateNumberPassed(double[] notes){
        //your code here
        int result ;

		result = 0;
		for(int i = 0; i < notes.length; i=i+1){
			if(notes[i] > 50) {
				result = result +1 ;
			}
		}

		return result;
	}

}
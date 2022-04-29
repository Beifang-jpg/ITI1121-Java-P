public class Q3_ReverseSortDemo {
	public static void main(String[] args){
		char[] unorderedLetters;
		unorderedLetters = new char[]{'b', 'm', 'z', 'a', 'u'};
		reverseSort(unorderedLetters);
		for (int i = 0 ; i < unorderedLetters.length; i++ )
			System.out.print(unorderedLetters[i]);
	}

	//method that sorts a char array into its reverse alphabetical order
	public static void reverseSort(char[] values){

		//your code here
		char ordering ;
		int position = values.length ;

		for (int i = 0 ; i < values.length-1 ; i = i +1 ) {
			ordering = values[i] ;
			int store = 0 ;
			position= position -1 ;

			for ( int x=i+1; x < values.length; x = x +1  ){
				if (values[x] < ordering){
					ordering = values[x] ;					
					store = x ;

				}
			}	
			values [store] = values [i] ;
			values[i] = ordering ;						
		}
	}	
}	
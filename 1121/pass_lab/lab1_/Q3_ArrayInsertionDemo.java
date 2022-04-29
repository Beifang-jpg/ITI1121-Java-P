public class Q3_ArrayInsertionDemo{

	public static int[] insertIntoArray(int[] beforeArray, int indexToInsert, int valueToInsert){
		// Your code here
		int [] afterArray ;
		
		afterArray = new int [beforeArray.length +1 ] ;

		for (int i=0 ; i < indexToInsert ; i =i +1){
			afterArray[i] = beforeArray[i] ;
		}

		afterArray[indexToInsert] = valueToInsert ;

		for (int i=indexToInsert; i<beforeArray.length; i=i+1){
			afterArray[i+1] = beforeArray[i] ;
		}


		return afterArray ;
	}

	public static void main(String[] args){
		// Your code here
		int [] oldArray ;
		int [] newArray ;
		oldArray = new int[] {1,5,4,7,9,6}  ;

		System.out.println("Array before insertion:" );
		for(int i=0 ; i<oldArray.length; i =i+1){
			System.out.println(oldArray[i]) ;
		}
		newArray = insertIntoArray(oldArray, 3, 15) ;
		System.out.println("Array after insertion of 15 at position 3: ");
		for(int i=0 ; i<newArray.length; i =i+1) {
			System.out.println(newArray[i]) ;			
		}
	}
}
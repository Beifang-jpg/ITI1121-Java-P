/**
 * COPYRIGHTED MATERIAL -- DO NOT DISTRIBUTE
 *
 * @author Guy-Vincent Jourdan
 * @author Mehrdad Sabetzadeh 
 */


public class A implements DeepCopyable{ // complete the class declaration as required
	
	private int a , b, c;

	public A(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	// ADD YOUR CODE HERE
	public boolean equals(Object o) {
		if (o == null){
			return false ;
		}
		if (this == o){
			return true;
		}
		A other = (A)o; 
		if (other.a ==this.a && other.b == this.b&&other.c == this.c){
			return true ;
		}
		return false ;
	}
	public DeepCopyable deepCopy(){
		return new A(this.a , this.b , this.c) ;
	}

}


/// 30014060 hongru wang

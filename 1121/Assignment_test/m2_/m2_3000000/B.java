/**
 * COPYRIGHTED MATERIAL -- DO NOT DISTRIBUTE
 *
 * @author Guy-Vincent Jourdan
 * @author Mehrdad Sabetzadeh 
 */

public class B implements DeepCopyable{ // complete the class declaration as required

	private A a1, a2;

	public B( A a1, A a2){
		this.a1 = a1;
		this.a2 = a2;
	}

	public A getA1() {
		return a1;
	}

	public A getA2() {
		return a2;
	}

	// ADD YOUR CODE HERE
	public boolean equals(Object o){
		if (o == null){
			return false ;
		}
		boolean check1 = false ;
		boolean check2 = false ;

		B other = (B)o; 
		if (other.a1 != null && other.a2 != null && this.a1 != null && this.a2 != null){
			if (other.a1.equals(this.a1) && other.a2.equals(this.a2)){
				return true ;
			}
		} else {
			if (other.a1 == null){
				if (this.a1!=null){
					return false;
				}else {
					check1 = true ;
				}
			} 
					

			
			if (other.a2 == null){
				if (this.a2!=null){
					return false;
				}else {
					check2 = true ;
				}
			}

		if (check1 == true && check2 == true){
			return true ;
		}

		}
		
		return false ;
	}
	public DeepCopyable deepCopy(){
		return new B((A)this.a1.deepCopy(), (A)this.a2.deepCopy());
	}

}



// 300140660 hongru wang
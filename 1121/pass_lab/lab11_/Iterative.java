public class Iterative {

	public static BitList complement( BitList in ) {

        BitList c = new BitList();
        Iterator i = in.iterator();
        int temp;
        while (i.hasNext()){
            temp = i.next();
            if (temp == 1){
                temp = 0;
            }else {
                temp = 1;
            }
            c.addFirst(temp);
        }
        return c;
	}

	public static BitList or( BitList a, BitList b ) {

        BitList result;
        result = new BitList();

        Iterator i = a.iterator();
        Iterator j = b.iterator();

        Iterator k = result.iterator();

        if ( ! i.hasNext() ) {
             throw new IllegalArgumentException("a is empty");
        }
        if ( ! j.hasNext() ) {
             throw new IllegalArgumentException("b is empty");
        }
        while ( i.hasNext() ) {

            if ( ! j.hasNext() ) {
                throw new IllegalArgumentException("length not match");
            }
            int iBit = i.next();
            int jBit = j.next();
            
            if ( iBit == BitList.ONE || jBit == BitList.ONE ) {
                k.add( BitList.ONE );
            } else {
                k.add( BitList.ZERO );
            }
        }

        if ( j.hasNext() ) {
            throw new IllegalArgumentException("length not match" );
        }
        return result;
    }

	

	public static BitList and( BitList a, BitList b ) {

		BitList result;
        result = new BitList();

        Iterator i = a.iterator();
        Iterator j = b.iterator();

        Iterator k = result.iterator();

        if ( ! i.hasNext() ) {
             throw new IllegalArgumentException("a is empty");
        }
        if ( ! j.hasNext() ) {
             throw new IllegalArgumentException("b is empty");
        }
        while ( i.hasNext() ) {

            if ( ! j.hasNext() ) {
                throw new IllegalArgumentException("length not match");
            }
            int iBit = i.next();
            int jBit = j.next();
            
            if ( iBit == BitList.ONE && jBit == BitList.ONE ) {
                k.add( BitList.ONE );
            } else {
                k.add( BitList.ZERO );
            }
        }

        if ( j.hasNext() ) {
            throw new IllegalArgumentException("length not match");
        }
        return result;
	}

	public static BitList xor( BitList a, BitList b ) {

		BitList result;
        result = new BitList();

        Iterator i = a.iterator();
        Iterator j = b.iterator();

        Iterator k = result.iterator();

        if ( ! i.hasNext() ) {
             throw new IllegalArgumentException("a is empty");
        }
        if ( ! j.hasNext() ) {
             throw new IllegalArgumentException("b is empty");
        }
        while ( i.hasNext() ) {

            if ( ! j.hasNext() ) {
                throw new IllegalArgumentException("length not match");
            }
            int iBit = i.next();
            int jBit = j.next();
            
            if ( (iBit == BitList.ONE || jBit == BitList.ZERO) && (iBit == BitList.ZERO || jBit == BitList.ONE) ) {
                k.add( BitList.ONE );
            } else {
                k.add( BitList.ZERO );
            
			}

		}
		if ( j.hasNext() ) {
            throw new IllegalArgumentException("length not match");
        }
        return result;
	}
}	
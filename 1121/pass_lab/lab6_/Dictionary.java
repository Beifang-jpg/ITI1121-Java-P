
public class Dictionary implements Map<String, Integer> {

    private final static int INITIAL_CAPACITY = 10;
    private final static int INCREMENT = 5;
    private int count;

    private Pair[] elems;

    public int getCount() {
      return count;
    }

    public int getCapacity() {
      return elems.length;
    }

    public Dictionary() {
        /* Your code here */
        this.elems = new Pair[INITIAL_CAPACITY];
        this.count = 0;
    }

    @Override
    public void put(String key, Integer value) {
        /* Your code here */
        Pair p = new Pair(key, value);
        if(elems.length == count){
            increaseCapacity();
        }
        elems[count] = p;
        count++;
    }

    private void increaseCapacity() {
        /* Your code here.  Use this in put() where necessary. */
        Pair[] tmpArray;

        tmpArray = new Pair [elems.length+INCREMENT];
        for(int i = 0; i<elems.length;i++){
            tmpArray[i] = elems[i];
        }
        elems = tmpArray;
    }

    @Override
    public boolean contains(String key) {
        /* Your code here. */
        for(int i = 0; i < this.elems.length;i++){

            if(this.elems[i] != null){
              Pair o = this.elems[i];
    
            if(o.getKey().equals(key)){
              return true;
                }
            }
            
          }
    
          return false;
    }

    @Override
    public Integer get(String key) {
        /* Your code here. */
        Pair p = new Pair ("",0);

        for(int i = this.elems.length-1; i > -1; i--){
          if(this.elems[i] != null){
            if(this.elems[i].getKey().equals(key)){
            p = this.elems[i];
            break;
          }
  
          }
          
        }
  
        return p.getValue();
    }

    @Override
    public void replace(String key, Integer value) {
        /* Your code here. */
        for(int i = this.elems.length-1; i > -1; i--){
            if(this.elems[i] != null){
              if(this.elems[i].getKey().equals(key)){
              this.elems[i].setValue(value);
              break;
            }
            }
            
          }
    }

    @Override
    public Integer remove(String key) {
        /* Your code here. */
        int p = 0;

      
      
      for(int i = this.elems.length-1; i > -1; i--){
        if(this.elems[i] != null){
          if(this.elems[i].getKey().equals(key)){
            p = this.elems[i].getValue();
          count--;
          this.elems[i] =null;
          break;
        }
        }
        
      }

      return p;

    }

    @Override
    public String toString() {
      String res;
      res = "Dictionary: {elems = [";
      for (int i = count-1; i >= 0 ; i--) {
          res += elems[i];
          if(i > 0) {
              res += ", ";
          }
      }
      return res +"]}";
    }

}
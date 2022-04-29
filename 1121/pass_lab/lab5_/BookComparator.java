import java.util.Comparator;

public class BookComparator implements Comparator<Book> {

    
    public int compare(Book a, Book b){
    	

    	if(a.getAuthor().compareTo(b.getAuthor()) < 0){
    		return -1;
    	}else if(a.getAuthor().compareTo(b.getAuthor()) > 0 ){
    		return 1;
    	}

    	if(a.getAuthor().compareTo(b.getAuthor()) == 0){
    		if(a.getTitle().compareTo(b.getTitle()) < 0 ){
    			return -1;
    		}else if(a.getTitle().compareTo(b.getTitle()) > 0 ){
    			return 1;
    		}else{
    			if(a.getYear() < b.getYear()){
    				return -1;
    			}else if (a.getYear() > b.getYear()){
    				return 1;
    			}else{
    				return 0;
    			}
    		}
    	}

    	return 0;

    	
    }

}

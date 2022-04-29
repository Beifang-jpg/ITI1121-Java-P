public class Book {

    // Your variables declaration here
    String author;
    String title;
    int year;

    public Book (String author, String title, int year) {
        // Your code here
        this.author = author;
        this.title = title;
        this.year = year;
    }

    public String getAuthor() {
        // Your code here
        return author;
    }

    public String getTitle() {
        // Your code here
        return title;
    }

    public int getYear() {
        // Your code here
        return year ;
    }

    public boolean equals(Object other) {
        // Your code here
        if( other == null){
            return false;
        }
        if (getClass() != other.getClass()){
            return false;
        }
        Book o = (Book) other;
        if (title != null && o.title != null ){
            if (!title.equals(o.title)) {
                return false ;
            }    
        }else if (title == null){
            if (o.title != null){
                return false ;
            }
        }
        if (author != null && o.author != null){
            if (!author.equals(o.author)){
                return false;
            }
            
        }else if (author == null) {
            if (o.author!= null){
                return false;
            }
        }
        if (year != o.year){
            return false;
        }
        
        return true ;
    }

    

    public String toString() {
        // Your code here
        return(author+ ": " + title + " (" + year + ")");
    }
}
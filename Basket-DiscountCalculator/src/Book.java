/**
 * Created by William Flageol on 2020-04-30.
 */
public class Book {
    private int bookNb;

    public Book(int nb) {
        bookNb = nb;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this)
            return true;
        if(!(o instanceof Book))
            return false;

        Book b = (Book) o;

        return b.bookNb == this.bookNb;
    }

    @Override
    public int hashCode() {
        return bookNb;
    }
}
import java.util.ArrayList;

public class Library {
    private ArrayList<Book> map;
    private int size;

    public Library(int size) {
        int s = (int)Math.pow(2, size);
        this.size = s;
        map = new ArrayList<Book>(s);
        for(int i = 0; i < (int)Math.pow(2,size); i++) map.add(null);
    }

    public int hf1(String b){
        String isbn = b;
        int hc = 0;
        for(int i = 0; i < isbn.length(); i++){
            hc += (int)isbn.charAt(i);
        }
        return hc%size;
    }
    public int hf2(String b){
        String isbn = b;
        int r = hf1(b);
        return r % (size-1) + 1;
    }

    public void addToHash(Book b){
        int h1 = hf1(b.getISBN());
        int h2 = hf2(b.getISBN());
        for(int i = 0; i < size; i++){
            if(map.get(h1) == null)
            {
                map.add(h1, b);
                return;
            }
            h1 = (h1 + h2) % size;
        }
        map.add(b);
    }

    public void deleteFromHash(Book b){
        int h1 = hf1(b.getISBN());
        int h2 = hf2(b.getISBN());

        for(int i = 0; i < size; i++){
            if(map.get(h1) == b)
            {
                map.remove(h1);
                return;
            }
            h1 = (h1 + h2) % size;
        }
    }

    public Book findByISBN(String isbn){
        int h1 = hf1(isbn);
        int h2 = hf2(isbn);
        for(int i = 0; i < size; i++){
            if(map.get(h1).getISBN() == isbn) return map.get(h1);
            h1 = (h1 + h2) % size;
        }
        return null;
    }

    public void printLibrary(){
        System.out.println(map);
    }
}

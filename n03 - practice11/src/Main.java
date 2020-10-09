public class Main {
    public static void main(String[] args) {
        Library lb = new Library(3); //размер задается степенью двойки, поэтому размер lb - 8
        Book b1 = new Book("Malvin", "Alice", "436485902314");
        Book b2 = new Book("Kel", "Might in the Dark", "436321023141");
        Book b3 = new Book("Roan", "Ocean in the Dark", "000100110000"); //1+1+1=3%8=3
        Book b4 = new Book("L.N. Flower", "Ground and life", "853724365821");
        Book b5 = new Book("W.Y. Luck", "Two Sides of a Coin", "1000010000010"); //1+1+1=3%8=3 коллизия
        lb.addToHash(b1);
        lb.addToHash(b2);
        lb.addToHash(b3);
        lb.addToHash(b4);
        lb.addToHash(b5);
        lb.printLibrary();
    }
}

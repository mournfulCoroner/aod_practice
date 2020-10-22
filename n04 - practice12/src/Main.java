public class Main {
    public static void main(String[] args) {
        ByteFile f = new ByteFile("pr12.txt", "test.txt");
        f.createNewBinary();
        f.showFile();
        f.showStealing();
        f.checkIsStolen("a673ma");
        f.checkIsStolen("p123pp");
    }
}

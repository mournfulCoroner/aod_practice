public class Node {
    int num;
    char color = 'b';
    int height;
    Node parent;
    Node left;
    Node right;

    public Node(int num) {
        this.num = num;
        this.height = 1;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

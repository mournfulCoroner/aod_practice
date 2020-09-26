import java.util.ArrayList;

public class Tree {
    private Node root;
    private ArrayList<Integer> arr = new ArrayList<>();

    public Node find(int num) {
        Node cur = root;
        while(cur.num != num){
            if(num < cur.num) cur = cur.left;
            else cur = cur.right;
            if(cur == null) return null;
        }
        return cur;
    }

    public void insert(int num){
        Node nNode = new Node(num);
        if(root == null) root = nNode;
        else{
            Node cur = root;
            Node parent;
            while(true){
                parent = cur;
                if(num < cur.num){
                    cur = cur.left;
                    if(cur == null){
                        parent.left = nNode;
                        return;
                    }
                }
                else{
                    cur = cur.right;
                    if(cur == null) {
                        parent.right = nNode;
                        return;
                    }
                }
            }
        }
    }

    public boolean delete(int num){
        Node cur = root;
        Node parent = root;
        boolean isLeftChild = true;

        while(cur.num != num){
            parent = cur;
            if(num < cur.num){
                isLeftChild = true;
                cur = cur.left;
            }
            else{
                isLeftChild = false;
                cur = cur.right;
            }
            if(cur == null) return false;
        }
        if(cur.left == null && cur.right == null){
            if(cur == root) root = null;
            else if(isLeftChild) parent.left = null;
            else parent.right = null;
        }
        else if(cur.right == null){
            if(cur == root) root = cur.left;
            else if(isLeftChild) parent.left = cur.left;
            else parent.right = cur.left;
        }
        else if(cur.left == null){
            if(cur == root) root = cur.right;
            else if(isLeftChild)  parent.left = cur.right;
            else parent.right = cur.right;
        }
        else{
            Node des = getDescen(cur);
            if(cur == root) root = des;
            else if(isLeftChild) parent.left = des;
            else parent.right = des;
            des.left = cur.left;
        }
        return true;
    }

    private Node getDescen(Node delNode){
        Node desParent = delNode;
        Node des = delNode;
        Node cur = delNode.right;
        while(cur != null){
            desParent = des;
            des = cur;
            cur = cur.left;
        }

        if (des != delNode.right) {
            desParent.left = des.right;
            des.right = delNode.right;
        }
        return des;
    }

    private void createMas(Node localRoot){
        if(localRoot != null){
            createMas(localRoot.left);
            arr.add(localRoot.num);
            createMas(localRoot.right);
        }
    }

    private void printMas(){
        System.out.print(arr);
    }

    public static void main(String[] args) {
        Tree m = new Tree();
        m.insert(6);
        m.insert(11);
        m.insert(4);
        m.insert(23);
        m.createMas(m.root);
        m.printMas();
    }
}

import java.util.ArrayList;

public class Tree {
    private Node root;
    private ArrayList<Integer> arr = new ArrayList<>();

    public int bfactor(Node p){
        if(p.right != null && p.left != null) return p.right.getHeight()-p.left.getHeight();
        else return 0;
    }

    void fixheight(Node p)
    {
        int hl, hr;
       if(p.left != null) hl = p.left.getHeight();
       else hl = 0;
       if(p.right != null) hr = p.right.getHeight();
       else hr = 0;
        p.setHeight(Math.max(hl, hr)+1);
    }

    Node rotateRight(Node p) // правый поворот вокруг p
    {
        Node q = p.left;
        p.left = q.right;
        q.right = p;
        fixheight(p);
        fixheight(q);
        return q;
    }

    Node rotateLeft(Node q) // левый поворот вокруг q
    {
        Node p = q.right;
        q.right = p.left;
        p.left = q;
        fixheight(q);
        fixheight(p);
        return p;
    }

    Node balance(Node p) // балансировка узла p
    {
        fixheight(p);
        if( bfactor(p)==2 )
        {
            if( bfactor(p.right) < 0 )
                p.right = rotateRight(p.right);
            return rotateLeft(p);
        }
        if( bfactor(p)==-2 )
        {
            if( bfactor(p.left) > 0  )
                p.left = rotateLeft(p.left);
            return rotateRight(p);
        }
        return p; // балансировка не нужна
    }

    public Node find(int num) {
        Node cur = root;
        while(cur.num != num){
            if(num < cur.num) cur = cur.left;
            else cur = cur.right;
            if(cur == null) return null;
        }
        return cur;
    }

    public void add(int num){this.root = insert(this.root, num);}

    public Node insert(Node p, int num){
        if(p == null) {
            root = new Node(num);
            return root;
        }
        if(num < p.num) p.left = insert(p.left, num);
        else p.right = insert(p.right, num);
        return balance(p);
    }

    public Node findmin(Node p){
        if(p.left != null) return findmin(p.left);
        else return p;
    }

    public Node removemin(Node p){
        if(p.left.num == 0) return p.right;
        p.left = removemin(p.left);
        return balance(p);
    }

    public Node delete(Node p, int num){
        /*Node cur = root;
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
        return true;*/
        if(p == null) return null;
        if(num < p.num) p.left = delete(p.left, num);
        else if(num > p.num) p.right = delete(p.right, num);
        else {
            Node q = p.left;
            Node r = p.right;
            if (r == null) return q;
            Node min = findmin(r);
            min.right = removemin(r);
            min.left = q;
            return balance(min);
        }
        return balance(p);
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
            if(localRoot.left != null) System.out.println("\"" + localRoot.num + "\"" + " -> " + "\"" + localRoot.left.num + "\"" + ";");
            if(localRoot.right != null) System.out.println("\"" + localRoot.num + "\"" +  " -> " + "\"" + localRoot.right.num + "\"" +  ";");
            createMas(localRoot.left);
            arr.add(localRoot.num);
            createMas(localRoot.right);
        }
    }

    private void printMas(){
        System.out.print(arr);
    }

    public void printTree(Node l){
        System.out.println("digraph G {");
        createMas(l);
        System.out.println("}");
    }

    public static void main(String[] args) {
        Tree m = new Tree();
        m.add(6);
        m.add(22);
        m.add(11);
        m.add(4);
        m.add(25);
        m.add(26);
        m.add(27);
        m.add(28);
        m.add(29);
        m.printTree(m.root);
        m.printMas();
    }
}

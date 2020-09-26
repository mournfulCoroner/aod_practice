import java.util.ArrayList;

public class RBTree {
    private Node root;
    private ArrayList<Integer> arr = new ArrayList<>();

    public Node getParent(Node n){
        return n == null ? null : n.parent;
    }

    public Node getGrandParent(Node n){
        return getParent(getParent(n));
    }

    public Node getSibling(Node n){
        Node p = getParent(n);
        if(p == null) return null;
        if(n == p.left) return p.right;
        else return p.left;
    }

    public Node getUncle(Node n){
        Node p = getParent(n);
        return getSibling(p);
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

    public void rotateLeft(Node n){
        Node nnew = n.right;
        Node p = getParent(n);
        assert(nnew != null);

        n.right = nnew.left;
        nnew.left = n;
        n.parent = nnew;

        if(n.right != null) n.right.parent = n;
        if(p != null){
            if(n == p.left) p.left = nnew;
            else if(n == p.right) p.right = nnew;
        }
        nnew.parent = p;
    }

    public void rotateRight(Node n){
        Node nnew = n.left;
        Node p = getParent(n);
        assert(nnew != null);

        n.left = nnew.right;
        nnew.right = n;
        n.parent = nnew;

        if(n.left != null) n.left.parent = n;
        if(p != null){
            if(n == p.left) p.left = nnew;
            else if(n == p.right) p.right = nnew;
        }
        nnew.parent = p;
    }

    public void insert(int num){
        Node n = new Node(num);
        insertRecurse(root, n);

        insertRepairTree(n);

        root = n;
        while (getParent(root) != null) root = getParent(root);
    }

    private void insertRecurse(Node root, Node n){
        if(root != null){
            if(n.num < root.num) {
                if(root.left != null){
                    insertRecurse(root.left, n);
                    return;
                }
                else root.left = n;
            }
            else {
                if(root.right != null){
                    insertRecurse(root.right, n);
                    return;
                }
                else root.right = n;
            }
        }

        n.parent = root;
        n.left = null;
        n.right = null;
        n.color = 'r';
    }

    private void insertCase1(Node n){
        n.color = 'b';
    }
    private void insertCase3(Node n){
        getParent(n).color = 'b';
        getUncle(n).color = 'b';
        getGrandParent(n).color = 'r';
        insertRepairTree(getGrandParent(n));
    }
    private void insertCase4(Node n){
        Node p = getParent(n);
        Node g = getGrandParent(n);

        if(n == p.right && p == g.right){
            rotateLeft(p);
            n = n.left;
        }
        else if(n == p.left && p == g.right){
            rotateRight(p);
            n = n.right;
        }
        insertCase4Step2(n);
    }
    private void insertCase4Step2(Node n){
        Node p = getParent(n);
        Node g = getGrandParent(n);
        if(n == p.left) rotateRight(g);
        else rotateLeft(g);
        p.color = 'b';
        g.color = 'r';
    }
    private void insertRepairTree(Node n){
        if (getParent(n) == null) insertCase1(n);
        else if(getParent(n).color == 'b') return;
        else if(getUncle(n) != null && getUncle(n).color == 'r') insertCase3(n);
        else insertCase4(n);
    }

    private void replaceNode(Node n, Node child){
        child.parent = n.parent;
        if(n == n.parent.left) n.parent.left = child;
        else n.parent.right = child;
    }

    public void deleteChild(int num){
        Node n = find(num);
        Node child = (n.right == null) ? n.left : n.right;
        assert(child!=null);
        replaceNode(n, child);
        if(n.color == 'b'){
            if(child.color == 'r') child.color = 'b';
            else deleteCase1(child);
        }
    }
    private void deleteCase1(Node n){
        if(n.parent != null) deleteCase2(n);
    }
    private void deleteCase2(Node n){
        Node s = getSibling(n);
        if(s.color == 'r'){
            n.parent.color = 'r';
            s.color = 'b';
            if(n == n.parent.left) rotateLeft(n.parent);
            else rotateRight(n.parent);
        }
        deleteCase3(n);
    }
    private void deleteCase3(Node n){
        Node s = getSibling(n);

        if((n.parent.color == 'b') && (s.color == 'b') && (s.left.color == 'b') && (s.right.color == 'b')){
            s.color = 'r';
            deleteCase1(n.parent);
        }
        else deleteCase4(n);
    }
    private void deleteCase4(Node n){
        Node s = getSibling(n);
        if((n.parent.color == 'r') && (s.color == 'b') &&
                (s.left.color == 'b') && (s.right.color == 'b')){
            s.color = 'r';
            n.parent.color = 'b';
        }
        else deleteCase5(n);
    }
    private void deleteCase5(Node n){
        Node s = getSibling(n);

        if (s.color == 'b') {
            if ((n == n.parent.left) && (s.right.color == 'b') &&
                    (s.left.color == 'r')) {
                s.color = 'r';
                s.left.color = 'b';
                rotateRight(s);
            } else if ((n == n.parent.right) && (s.left.color == 'b') &&
                    (s.right.color == 'r')) {
                s.color = 'r';
                s.right.color = 'b';
                rotateLeft(s);
            }
        }
        deleteCase6(n);
    }
    private void deleteCase6(Node n){
        Node s = getSibling(n);

        s.color = n.parent.color;
        n.parent.color = 'b';

        if(n == n.parent.left){
            s.right.color = 'b';
            rotateLeft(n.parent);
        }
        else{
            s.left.color = 'b';
            rotateRight(n.parent);
        }
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
        RBTree m = new RBTree();
        m.insert(6);
        m.insert(11);
        m.insert(4);
        m.insert(23);
        m.createMas(m.root);
        m.printMas();
    }
}

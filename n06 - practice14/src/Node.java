public class Node {
    private int frequence;//частота
    private char letter;//буква
    private Node leftChild;//левый потомок
    private Node rightChild;//правый потомок

    public Node(char letter, int frequence) {
        this.letter = letter;
        this.frequence = frequence;
    }

    public Node() {}

    public void addChild(Node newNode) {//добавить потомка
        if (leftChild == null)//если левый пустой=> правый тоже=> добавляем в левый
            leftChild = newNode;
        else {
            if (leftChild.getFrequence() <= newNode.getFrequence()) //левым потомком станет тот, чья частота меньше
                rightChild = newNode;
            else {
                rightChild = leftChild;
                leftChild = newNode;
            }
        }
        frequence += newNode.getFrequence();//итоговая частота
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public int getFrequence() {
        return frequence;
    }

    public char getLetter() {
        return letter;
    }

    public boolean isLeaf() {
        return leftChild != null && rightChild != null;
    }
}
import java.util.*;
import java.util.stream.Collectors;


public class Huffman {
    private String line;
    private HashMap<Character, String> encodingArray;

    public Huffman(String line){
        this.line = line;
        encodingArray = new HashMap<>(line.length());
    }

    //получение отсортированной по частоте таблицы
    public LinkedHashMap<Character, Integer> getTable(String line){
        HashMap<Character, Integer> map = new HashMap<>(line.length()/2);
        for(int i = 0; i < line.length(); i++){
            char a = line.charAt(i);
            if(map.containsKey(a)) map.replace(a, map.get(a)+1);
            else map.put(a, 1);
        }
        return map.entrySet().stream()
                .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    //заполнение кодировочной таблицы
    void fillEncodingArray(Node node, String codeBefore, String direction) {
        if (!node.isLeaf()) {
            encodingArray.put(node.getLetter(), codeBefore + direction);
        } else {
            fillEncodingArray(node.getLeftChild(), codeBefore + direction, "0");
            fillEncodingArray(node.getRightChild(), codeBefore + direction, "1");
        }
    }

    private BinaryTree getHuffmanTree() {
        PriorityQueue<BinaryTree> pq = new PriorityQueue<>();
        LinkedHashMap<Character, Integer> table = getTable(line);
        //создание деревьев для каждого узла
        for (char m: table.keySet()) {
                Node newNode = new Node(m, table.get(m));
                BinaryTree newTree = new BinaryTree(newNode);
                pq.add(newTree);
        }
        //слияние деревьев в одно
        while (true) {
            BinaryTree tree1 = pq.remove();//извлечь из очереди первое дерево.
            try {
                BinaryTree tree2 = pq.remove();//извлечь из очереди второе дерево

                Node newNode = new Node();//создать новый Node
                newNode.addChild(tree1.getRoot());//сделать его потомками два извлеченных дерева
                newNode.addChild(tree2.getRoot());

                pq.add(new BinaryTree(newNode));
            } catch (NoSuchElementException e) {//осталось одно дерево в очереди
                return tree1;
            }
        }
    }

    private void memoryCompare(StringBuilder result){
        double size = result.toString().replaceAll("\\s","").length();
        int i = 1;
        for(;; i++) if(Math.pow(2, i) >= encodingArray.size()) break;
        System.out.println();
        System.out.println("Вес строки в кодировке ascii: " + line.length()*8 + " бит");
        System.out.println("Вес равномерного кода: " + i * line.length() + " бит");
        System.out.println("Вес строки после шифрования: " + (int)size + " бит");
        System.out.println("Коэффициент сжатия для кодировки: " + line.length()*8/size +
                "\nКоэффициент сжатия для равномерного кода: " + i*line.length()/size);
    }

    public void getCode(){
        StringBuilder result = new StringBuilder();
        BinaryTree huffmanTree = getHuffmanTree();
        fillEncodingArray(huffmanTree.getRoot(), "", "");
        System.out.println(line);
        for(int i = 0; i < line.length(); i++){
            result.append(encodingArray.get(line.charAt(i)) + " ");
            System.out.print(encodingArray.get(line.charAt(i)) + " ");
        }

        memoryCompare(result);
    }

    public static void main(String[] args) {
        String line = "Леутина Таисия Александровна";
        Huffman h = new Huffman(line);
        h.getCode();
    }
}


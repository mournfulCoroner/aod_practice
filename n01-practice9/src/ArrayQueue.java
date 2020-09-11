import java.util.ArrayList;

public class ArrayQueue {
    private int size;
    private int timeA = 0;
    private int timeB = 0;
    private int timeC = 0;
    ArrayList<Employee> array = new ArrayList<>();

    public ArrayQueue(int size, Employee[] mas) {
        this.size = size;
        ArrayList<Employee> A = new ArrayList<>();
        ArrayList<Employee> B = new ArrayList<>();
        ArrayList<Employee> C = new ArrayList<>();
        for(int i = 0; i < mas.length; i++)
        {
            if(mas[i].getType() == 'A') A.add(mas[i]);
            else if(mas[i].getType() == 'B') B.add(mas[i]);
            else C.add(mas[i]);
        }
        array.addAll(A);
        array.addAll(B);
        array.addAll(C);
    }

    public void delete()
    {
        if(!isEmpty()) array.remove(0);
    }

    public Employee getElement()
    {
        if(!isEmpty()) return array.get(0);
        else return null;
    }

    public void add(Employee emp)
    {
        if(!isFull()) array.add(emp);
    }

    public boolean isFull()
    {
        if(array.size() == size) return true;
        else return false;
    }

    public boolean isEmpty()
    {
        return array.isEmpty();
    }

    public void print()
    {
        while(!isEmpty())
        {
            Employee s = getElement();
            System.out.println(s);
            if(s.getType() == 'A') timeA+=s.getTimeRequest();
            else if(s.getType() == 'B') timeB+=s.getTimeRequest();
            else timeC+=s.getTimeRequest();
            delete();
        }
        System.out.println("Time of type 'A': " + timeA + "; time of type 'B': " + timeB +
                "; time of type 'C': " + timeC);
    }
}

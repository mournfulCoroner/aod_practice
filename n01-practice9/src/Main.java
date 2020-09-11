import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args)
    {
        int qsize;
        String[] subemp;
        String emp;
        Scanner sc = new Scanner(System.in);
        qsize = parseInt(sc.nextLine());

        Employee[] mas = new Employee[qsize];
        for(int i = 0; i < qsize; i++)
        {
            emp = sc.nextLine();
            subemp = emp.split(" ");
            mas[i] = new Employee(subemp[0].charAt(0), parseInt(subemp[1]), parseInt(subemp[2]));
        }

        ArrayQueue aq = new ArrayQueue(qsize, mas);
        aq.print();

    }
}


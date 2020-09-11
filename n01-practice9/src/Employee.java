public class Employee {
    char type;
    int number;
    int timeRequest;

    public Employee(char type, int number, int timeRequest) {
        this.type = type;
        this.number = number;
        this.timeRequest = timeRequest;
    }

    public char getType() {
        return type;
    }

    public int getTimeRequest() {
        return timeRequest;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "type=" + type +
                ", number=" + number +
                ", timeRequest=" + timeRequest +
                '}';
    }
}

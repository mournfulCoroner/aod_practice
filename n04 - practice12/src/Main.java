public class Main {
    /* Вариант 4 - Владельцы автомобилей
    Доп. операции: 1. Список автомобилей, числящихся в угоне
    2. Установить факт угона автомобиля с заданным номером
     */
    public static void main(String[] args) {
        ByteFile f = new ByteFile("pr12.txt", "test.txt");
        f.createNewBinary();
        f.showFile();
        f.showStealing();
        f.checkIsStolen("a673ma");
        f.checkIsStolen("p123pp");
    }
}

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String c;
        Scanner in = new Scanner(System.in);
        First y = new First();
        System.out.println("Введите значение x или команду");
        while(true) {
            c = in.nextLine();
            try {
                double x = Double.parseDouble(c);
                y.result();
            }
            catch (Exception ex) {
                if (c.equalsIgnoreCase("save")) {
                    try (ObjectOutputStream op = new ObjectOutputStream(new FileOutputStream("output"))) {
                        op.writeObject(y);
                        System.out.println("Значения сохранены");
                    }
                    catch (IOException IOex) {
                        IOex.getMessage();
                    }
                }
                else
                    if (c.equalsIgnoreCase("upload")){
                    try (ObjectInputStream ip = new ObjectInputStream(new FileInputStream("output"))) {
                        y = (First) ip.readObject();
                        System.out.println("Значения восстановлены");
                    }
                    catch (Exception IOex) {
                        IOex.getMessage();
                    }
                }
                else
                    if(c.equalsIgnoreCase("first")){
                    System.out.println("Данные объекта:");
                    System.out.println("x: " + y.x + " y: "+ y.y);
                }
                else{
                    System.out.println("Не верно введена команда");
                }
            }
        }
    }
}
class First implements Serializable{
    double y;
    double x;
    First(){
        y=0;
        x=0;
    }
    void result(){
        this.y = x - Math.sin(x);
        System.out.println(y);
    }
}
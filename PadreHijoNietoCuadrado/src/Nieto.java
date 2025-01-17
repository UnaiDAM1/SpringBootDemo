import java.util.Scanner;

public class Nieto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String n = sc.nextLine();
            if (n.equals("fin")) {
                break;
            }

            try {
                int x = Integer.parseInt(n);
                System.out.println(x * x);
                System.out.flush();
            } catch (NumberFormatException e) {
                System.out.println("Error");
                System.out.flush();
            }
        }
        sc.close();
    }
}

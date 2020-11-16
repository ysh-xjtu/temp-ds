import java.util.Scanner;

public class Power {

    public static int getPower(int num, int power){
        if(power == 0) return 1;
        if(power == 1) return num;
        int result = 0;
        if(power%2 == 0){
            result = getPower(num, power/2);
            result = result * result;
        }
        else{
            result = getPower(num, (power-1)/2);
            result = num* result * result;
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int power = scanner.nextInt();
        System.out.println(getPower(num, power));
        scanner.close();
    }
    
}

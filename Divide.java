import java.util.Scanner;

public class Divide {
    public static int divide(int numbersOfSet, int subSets){
        if(subSets == 1 || subSets == numbersOfSet) return 1;
        return divide(numbersOfSet - 1, subSets - 1) + subSets * divide(numbersOfSet - 1, subSets);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numbersOfSet = scanner.nextInt();
        int subSets = scanner.nextInt();
        System.out.println(divide(numbersOfSet, subSets));
        scanner.close();
    }
}

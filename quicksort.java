public class quicksort {
    static void method(){
        for(int i = 0; i < 10000; i++);
    }
    static void qsort(int[] array, int i, int j) {
        if (i < j) {
            int k = quick(array, i, j);
            qsort(array, i, k - 1);
            qsort(array, k + 1, j);
        }
    }

    static int quick(int[] array, int i, int j) {
        int t = array[i];
        while (i < j) {
            while (i < j && array[j] > t) {
                j--;
            }
            array[i] = array[j];
            while (i < j && array[i] < t) {
                i++;
            }
            array[j] = array[i];
        }
        array[i] = t;
        return i;
    }

    public static void main(String[] args) {
        for (int j = 100; j <= 10000; j += 100) {
            System.out.println(j);
            int array[] = new int[j];
            for (int i = 0; i < j; i++) {
                array[i] = (int) (Math.random() * 10000);
                System.out.print(array[i] + "  ");
            }
            System.out.println();
            double startTime = System.nanoTime();
            qsort(array, 0, j - 1);
            //method();
            double endTime = System.nanoTime();
            double Time = endTime - startTime;
            System.out.println(+Time);
            int array2[] = new int[j];
            for (int i = 0, t = j - 1; i < j; i++, t--) {
                array2[i] = array[t];
                System.out.print(array2[i] + "  ");
            }
            System.out.println();
            double startTime2 = System.nanoTime();
            qsort(array2, 0, j - 1);
            //method();
            double endTime2 = System.nanoTime();
            double Time2 = endTime2 - startTime2;
            System.out.println(+Time2);
        }
    }
}
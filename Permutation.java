public class Permutation{
    public static void main(String[] args) {
        String s = "caa";
        permutation(s.toCharArray(), 0, s.length());
               
    }
    static void permutation(char[] strs, int start ,int end){
        if(start == end){
            System.out.println(strs);
            return;
        }
        for(int i = start; i<end; i++){
            swap(strs, start, i);
            if((start == i) || (strs[start] != strs[i])) permutation(strs, start+1, end);
            swap(strs, start, i);
        } 
    }
    static void swap(char[] strs, int i, int j){
        char temp = strs[i];
        strs[i] = strs[j];
        strs[j] = temp;
    }
}
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class WordLadderGame {
    public static void main(String[] args) {
        WordLadder wordLadder = new WordLadder("d:\\temp-ds\\words5.txt");
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        SET<String> nonWordsLink = wordLadder.getNonLadderLink();
        String from = null;
        String to = null;
        LinkedList<String> path = null;
        while(path == null){
            int start = rand.nextInt(wordLadder.size());
            if(nonWordsLink.contains(from = wordLadder.getPosWord(start))) continue;
            int end = rand.nextInt(wordLadder.size());
            if(nonWordsLink.contains(to = wordLadder.getPosWord(end))) continue;
            path = wordLadder.getPath(from, to);
        }
        for(int i = 0; i < path.size(); i++)
        {
            if(i == path.size() - 1) System.out.println(path.get(i));
            else
                System.out.print(path.get(i) + " -> ");
        }
        System.out.println("Please give your solutions of WordLadder from '" + from + "' to '" + to + "'");
        System.out.println("The game over when you reach to " + to + " or when you input 'end'.");
        String pre = from;
        String curr = sc.next();
        while(curr.compareTo(to) != 0 && curr.compareTo("end") != 0)
        {
            if(!wordLadder.isNeighbour(pre, curr))
            {
                System.out.println("The word '" + curr + "' is not correct. The Game is over.");
                sc.close();
                return;
            }
            pre = curr;
            curr = sc.next();
        } 
        if(curr.compareTo("end") == 0)
        {
            System.out.println("You give up the game.");
        }
        else
        {
            System.out.println("Congratulations!");
        }
        sc.close();
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class WordLadder {
    private String[] words = new String[2500];
    private int size = 0;
    private Graph<String> wordsGraph = new Graph<String>();

    public static void main(String[] args) {
        WordLadder ladder = new WordLadder();
        ladder.readWords(new File("d:/temp/temp-ds/words5.txt"));
        ladder.createGraph();
        //System.out.println(ladder.size());
        // try (PrintWriter pw = new PrintWriter("d:/temp/words5_graph.txt")) {
        //     pw.println(ladder.getGraph().toString());
        // } catch (Exception e) {
        //     System.out.println(e.toString());
        // }
        // try(PrintWriter pw = new PrintWriter("d:/temp/words5_nonLadder.txt")){
        //     for(String s : ladder.getNonLadderLink())
        //         pw.println(s);
        // } catch(Exception e){
        //     System.out.println(e.getMessage());
        // }
        LinkedList<String> path = new PathFinder<String>(ladder.getGraph()).getPath("green", "brown");
        if(path == null){
            System.out.println("no connected.");
            return;
        }
        for(String s : path)
            System.out.println(s);
    }

    public void readWords(File fileName) {
        Scanner sc = null;
        int i = 0;
        try {
            sc = new Scanner(fileName);
            while (sc.hasNextLine()) {
                words[i++] = sc.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file: " + fileName + " is not found.");
        } catch (Exception e) {
            System.out.println("some other io operations error occured." + e.toString());
        } finally {
            try {
                size = i;
                sc.close();
            } catch (Exception e) {
                System.out.println("Can't close this file.");
            }
        }
    }
    public void createGraph(){
        for (int i = 0; i < size(); i++){
            wordsGraph.addVertex(words[i]);
            for (int j = i + 1; j < size(); j++) {
                if (isNeighbour(i, j))
                    wordsGraph.addEdge(words[i], words[j]);
            }
        }
    }
    public Graph<String> getGraph(){
        return wordsGraph;
    }
    public SET<String> getNonLadderLink(){
        SET<String> nonLadderLinkWords = new SET<String>();
        for(String vertex : wordsGraph.vertices())
            if(wordsGraph.degree(vertex) == 0)
                nonLadderLinkWords.add(vertex);
        return nonLadderLinkWords;
    }
    private boolean _isNeighbour(String word1, String word2) {
        int different = 0;
        if (word1.length() != word2.length())
            return false;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i))
                different++;
            if (different > 1)
                return false;
        }
        if (different == 1)
            return true;
        return false;
    }

    public boolean isNeighbour(int i, int j) {
        return _isNeighbour(words[i], words[j]);
    }

    public String getWords(int index) {
        return words[index];
    }

    public int size() {
        return size;
    }

}

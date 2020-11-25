import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class WordLadderData {
    public static void main(String[] args) throws IOException {
        WordLadder wordLadder = new WordLadder("d:\\temp-ds\\words5.txt");
        //PrintWriter pw = new PrintWriter(new File("d:\\temp-ds\\words5_allWordsLadder.txt"));
        PrintWriter pw = null;
        int fileNameCounter = 0;
        SET<String> nonWordsLink = wordLadder.getNonLadderLink();
        pw = new PrintWriter(new File("d:\\temp-ds\\words5_allWordsLadder_" + fileNameCounter + ".txt"));
        for (int i = 0; i < wordLadder.size(); i++) {
            String currentFrom = wordLadder.getPosWord(i);
            if((currentFrom.charAt(0) - 'a') != fileNameCounter)
            {
                pw.close();
                fileNameCounter++;
                pw = new PrintWriter(new File("d:\\temp-ds\\words5_allWordsLadder_" + fileNameCounter + ".txt"));
            }
            if (nonWordsLink.contains(wordLadder.getPosWord(i))) {
                continue;
            }
            System.out.println("i = " + i);
            for (int j = i + 1; j < wordLadder.size(); j++) {
                String from = wordLadder.getPosWord(i);
                String to = wordLadder.getPosWord(j);
                LinkedList<LinkedList<String>> paths = wordLadder.getAllPath(from, to);
                if (paths == null) {
                    continue;
                }
                printAllPaths(pw, from, to, paths, wordLadder.getShortestLength(from, to));
                // LinkedList<String> path = wordLadder.getPath(from, to);
                // if (path == null)
                // {
                //     continue;
                // }
                // printOnePath(pw, from, to, path, wordLadder.getShortestLength(from, to));
                System.out.printf("j = %d ", j);
            }
            System.out.println();
        }
        pw.close();
    }

    public static void printOnePath(PrintWriter pw, String from, String to, LinkedList<String> path, int shortestLength) {
        pw.println(from + " -----> " + to + "(SL:" + shortestLength + ")");
        for (int i = 0; i < path.size(); i++) {
            if (i == path.size() - 1) {
                pw.println(path.get(i));
            } else {
                pw.print(path.get(i) + " -> ");
            }
        }
    }

    public static void printAllPaths(PrintWriter pw, String from, String to, LinkedList<LinkedList<String>> paths,
            int shortestLength) {
        pw.println(from + " -----> " + to + "(SL:" + shortestLength + ")");
        for (LinkedList<String> path : paths) {
            for (int i = 0; i < path.size(); i++) {
                if (i == path.size() - 1) {
                    pw.println(path.get(i));
                } else {
                    pw.print(path.get(i) + " -> ");
                }
            }
        }
    }
}

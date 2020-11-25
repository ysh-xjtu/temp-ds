import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class WordLadder{
    private String[] words = new String[2500];
    private int size = 0; //记录图中顶点的个数，也就是单词的数量
    private Graph<String> wordsGraph = new Graph<String>();
    private PathFinder<String> pathFinder = null;
    public WordLadder(String fileName){
        readWords(new File(fileName));
        createGraph();
    }
    public LinkedList<String> getPath(String from, String to)
    {
        if(pathFinder == null) pathFinder = new PathFinder<String>(this.wordsGraph);
        return pathFinder.getPath(from, to);
    }
    public LinkedList<LinkedList<String>> getAllPath(String from, String to)
    {
        if(pathFinder == null) pathFinder = new PathFinder<String>(this.wordsGraph);
        return pathFinder.getAllPath(from, to);
    }
    public int getShortestLength(String from, String to)
    {
        return pathFinder.getShortestLength(from, to);
    }
    private void readWords(File fileName) {
        Scanner sc = null;
        int i = 0;
        try 
        {
            sc = new Scanner(fileName);
            while (sc.hasNextLine()) {
                words[i++] = sc.nextLine();
            }
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("The file: " + fileName + " is not found.");
        } 
        catch (Exception e) 
        {
            System.out.println("some other io operations error occured." + e.toString());
        } 
        finally 
        {
            try 
            {
                size = i;
                sc.close();
            } 
            catch (Exception e) 
            {
                System.out.println("Can't close this file.");
            }
        }
    }
    private void createGraph(){
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
    //获得图中那些出度数为0的顶点，这些顶点对应的单词也是和单词列表中其他单词无法构成字梯的单词
    public SET<String> getNonLadderLink(){
        SET<String> nonLadderLinkWords = new SET<String>();
        for(String vertex : wordsGraph.vertices())
            if(wordsGraph.degree(vertex) == 0)
                nonLadderLinkWords.add(vertex);
        return nonLadderLinkWords;
    }
    //判断两个单词是否只有一个字母的差异
    public boolean isNeighbour(String word1, String word2) {
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
        return isNeighbour(words[i], words[j]);
    }

    public String getWords(int index) {
        return words[index];
    }

    public int size() {
        return size;
    }
    public String getPosWord(int i){
        if( i<0 && i>=size) return null;
        return words[i];
    }

}

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
class Graph
{
    public HashMap<String,ArrayList<String>> adjList;
    public HashMap<String,String> path;
    Graph()
    {
        adjList=new HashMap<String,ArrayList<String>>();
        path=new HashMap<String,String>();
    }

    public void makeGraph(String inputWord) throws Exception
    {
        //ClassLoader classLoader=new Graph().getClass().getClassLoader();
        //File file=new File(classLoader.getResource("sowpods.txt").getFile());
        File file=new File("sowpods.txt");
        BufferedReader rdr=new BufferedReader(new FileReader(file));
        String line="";
        while((line=rdr.readLine())!=null)
        {
            line=line.trim();
            if(line.length()!=inputWord.length())
                continue;
            if(!adjList.containsKey(line))
                adjList.put(line,new ArrayList<String>());
            for(String word:adjList.keySet())
            {
                if(diffOne(word.toCharArray(),line.toCharArray()))
                {
                    adjList.get(line).add(word);
                    adjList.get(word).add(line);

                }
            }
        }
        rdr.close();
    }

    boolean diffOne(char[] word1,char[] word2)
    {
        int diff=0;
        for(int i=0;i<word1.length && diff<2;i++)
        {
            if(word1[i]!=word2[i])
                diff++;
        }
        if(diff==1)
            return true;
        return false;
    }

    public boolean BFS(String startWord, String endWord)
    {
        String currentWord = startWord;
        Set<String> visited = new HashSet<String>();
        LinkedList<String> queue = new LinkedList<String>();
        visited.add(currentWord);
        //HashMap<String, Integer> distance = new HashMap<String, Integer>();
        queue.add(currentWord);
        //distance.put(currentWord, 0);

        while (queue.size() != 0)
        {
            currentWord = queue.poll();
            ArrayList<String> adjacentWords = adjList.get(currentWord);
            for (String s : adjacentWords) 
            {
                if (!visited.contains(s))
                {
                    visited.add(s);
                    queue.add(s);
                    path.put(s,currentWord);
                    //distance.put(s, distance.get(currentWord)+1) ;
                }
                if(s.equals(endWord))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void printPath(String startWord,String endWord)
    {
        String currentWord=endWord;
        while(!currentWord.equals(startWord))
        {
            System.out.println(currentWord);
            currentWord=path.get(currentWord);
        }
        System.out.println(currentWord);
    }
}

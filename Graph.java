import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
class Graph
{
    public HashMap<String,ArrayList<String>> adjList;

    Graph()
    {
        adjList=new HashMap<String,ArrayList<String>>();
    }

    public void perform(String inputWord) throws Exception
    {

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
        System.out.println(adjList.get(inputWord));
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
    
    
}
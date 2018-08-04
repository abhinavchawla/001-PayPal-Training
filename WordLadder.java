import java.util.Scanner;
public class WordLadder
{
	public static void main(String[] a) throws Exception
	{
		Scanner s=new Scanner(System.in);
        System.out.println("Input words sorce followed by destination...");
        String inputWord=s.nextLine();
        String dest=s.nextLine();
        Graph g=new Graph();
        g.makeGraph(inputWord);
        if(g.BFS(inputWord,dest))
        	g.printPath(inputWord,dest);
        else
        	System.out.println("No path found..!");
	}
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class main {

    public static void main(String[] args) throws FileNotFoundException {
    // define edges of the graph 
    File file = new File("graph.txt"); 
    Scanner sc = new Scanner(file); 
    Graph graph = new Graph();
    
    while (sc.hasNextLine()){ 
      String[] element = sc.nextLine().split("\t");
      graph.addEgde(element[0], element[1], Integer.parseInt(element[2]));
    }
    sc.close();
    String u = "A";
    String v = "C";
    /*if (graph.isReachable(u, v)) 
            System.out.println("There is a path from " + u +" to " + v); 
        else
            System.out.println("There is no path from " + u +" to " + v);*/
    System.out.println();
    graph.printAllPaths(u, v);
    graph.secondQuestion();
        
    }
    

    

    /*
    public void maxFlow(Graph graph,String source, String destination){
        int u, v;
        Graph exgraph = graph;
        int size = graph.size();
        int maxFlow = 0;
        int p[] = new int[graph.verticesSize()];
        while(bfs(graph,source,destination,size)){
            int path_flow = Integer.MAX_VALUE;
            for(int i = size; i!=source ;i=graph.verticesSize()){
                u = p[i];
                path_flow = Math.min(path_flow, graph);
            }
        }
    }
    */
}
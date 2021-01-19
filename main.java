import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class main {

    public static void main(String[] args) throws FileNotFoundException {
    // define edges of the graph 
    File file = new File("original.txt"); 
    Scanner sc = new Scanner(file); 
    Graph graph = new Graph();
    
    while (sc.hasNextLine()){ 
      String[] element = sc.nextLine().split("\t");
      graph.addEgde(element[0], element[1], Integer.parseInt(element[2]));
    }
    sc.close();
    String u = "HG";
    String v = "KA";
    System.out.println("basladı");
    System.out.println("The maximum possible flow is " + graph.fordFulkerson(u, v));
    System.out.println("bitti");
    while(!graph.reelbottlenecks.isEmpty()){
        Edge x = graph.reelbottlenecks.poll();
        System.out.println(x.getSource().getName() + " - " + x.getDestination().getName());
    }
    }
}
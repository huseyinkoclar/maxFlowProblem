import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class main {

    public static void main(String[] args) throws FileNotFoundException {
    // define edges of the graph 
    File file = new File("grp1.txt"); 
    Scanner sc = new Scanner(file); 
    Graph graph = new Graph();
    while (sc.hasNextLine()){ 
      String[] element = sc.nextLine().split("\t");
      graph.addEgde(element[0], element[1], Integer.parseInt(element[2]));
    }
    sc.close();
    Scanner scan = new Scanner(System.in);
    System.out.print("Please enter the source node:  ");
    String u = scan.nextLine();
    System.out.print("Please enter destination node:  ");
    String v = scan.nextLine();
    System.out.println();
    System.out.println("-------------------------------------------");
    System.out.println("Question 1:  ");
    System.out.println("The maximum possible flow is " + graph.fordFulkerson(u, v));
    System.out.println();
    System.out.println("-------------------------------------------");
    System.out.println("Question 2:  ");
    while(!graph.reelbottlenecks.isEmpty()){
        Edge x = graph.reelbottlenecks.poll();
        graph.increasebottlenecks.add(x);
        System.out.println(x.getSource().getName() + " - " + x.getDestination().getName());
    }

    System.out.println();
    System.out.println("------------------------------------------");
    System.out.println("Question 3:   ");
    while(!graph.increasebottlenecks.isEmpty()){
      Edge x = graph.increasebottlenecks.poll();
      System.out.println(x.getSource().getName() + " - " + x.getDestination().getName() + " - " + x.increase);
  }
  
    }
}
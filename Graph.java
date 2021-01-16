import java.util.*;

public class Graph {
	private HashMap<String,Vertex> vertices;
	private HashMap<String,Edge> edges;
	int maxFlow = 0;
	LinkedList<Edge> bottleneck = new LinkedList<Edge>();
	LinkedList<List<Vertex>> allpath = new LinkedList<>();
	Graph() {
		this.vertices = new HashMap<>();
		this.edges = new HashMap<>();
	}

	public void addEgde(String source, String destination, int weight) {

		if(edges.get(source + "-" + destination) == null && edges.get(destination + "-" + source) == null)
		{
			Vertex source_v, destination_v;

			if(vertices.get(source) == null) 
				{
					source_v  = new Vertex(source);
					vertices.put(source, source_v);
				}
			else source_v = vertices.get(source);

			if(vertices.get(destination) == null) 
				{
				destination_v  = new Vertex(destination);
				vertices.put(destination, destination_v );
				}
			else destination_v = vertices.get(destination);

			vertices.get(destination).setParent(vertices.get(source));

			Edge edge = new Edge(source_v, destination_v, weight);
			source_v.addEdge(edge);
			destination_v.addEdge(edge);
			edges.put(source + "-" + destination, edge);
		}
		else
		{
			System.out.println("This edge has already added!");
		}
	}

	public void print(){

		System.out.println("Source\tDestination\tWeight");
		for (Edge e : edges.values()) {
			System.out.println("" + e.getSource().getName() + "\t" + e.getDestination().getName() + "\t\t" + e.getWeight()+ " ");
		}
	}

	public  Iterable<Vertex> vertices()
	{
		return vertices.values();
	}

	public  Iterable<Edge> edges()
	{
		return edges.values();
	}

	public int verticesSize(){
		return vertices.size();
	}
	
	public int size()
	{
		return vertices.size();
	}
	
	
		

	public void printAllPaths(String s, String d) 
    { 
        ArrayList<Vertex> pathList = new ArrayList<>(); 
  
        // add source to path[] 
        pathList.add(vertices.get(s)); 
  
		// Call recursive utility 
        printAllPathsUtil(s, d, pathList); 
	}
	
	 void printAllPathsUtil(String u, String d, List<Vertex> localPathList) 
    { 
		
        if (u.equals(d)) {
			int min = Integer.MAX_VALUE;
			for(int k = 0; k<localPathList.size()-1;k++){

			System.out.print(localPathList.get(k).getName() + " ");

				for(Edge z : vertices.get(localPathList.get(k).getName()).getEdges()){
					if(z.getDestination().getName().equals(localPathList.get(k+1).getName()) && z.getWeight()<min)
						min = z.getWeight();
				}
				
			}
			for(int k = 0; k<localPathList.size()-1;k++){
			for(Edge z : vertices.get(localPathList.get(k).getName()).getEdges()){
				if(z.getDestination().getName().equals(localPathList.get(k+1).getName())){
					z.setWeight(z.getWeight()-min);
					if(z.getWeight()==0){
					boolean flag = false;
					for(Edge x : bottleneck){
						if(x.equals(z))
							flag = true;
					}
					if(!flag) bottleneck.add(z);
				}
				
				}
			}
		}
			maxFlow += min; 
			System.out.print(localPathList.get(localPathList.size()-1).getName() + " ");
			List<Vertex> ex = new ArrayList<Vertex>();
			ex.addAll(localPathList);
			allpath.add(ex);

			System.out.println(min);
			System.out.println(maxFlow);
			System.out.println();
			
			
			
            return; 
        } 
  
        // Mark the current node 
        vertices.get(u).visited = true; 
  
        // Recur for all the vertices 
        // adjacent to current vertex 
        for (Edge i : vertices.get(u).getEdges()) { 
            if (!vertices.get(i.getDestination().getName()).visited) { 
                // store current node 
                // in path[] 
                localPathList.add(i.getDestination()); 
                printAllPathsUtil(i.getDestination().getName(), d, localPathList); 
  
                // remove current node 
                // in path[] 
                localPathList.remove(i.getDestination()); 
            } 
        } 
  
        // Mark the current node 
        vertices.get(u).visited = false; 
	} 

	
	
	public void secondQuestion(){

		print();
		System.out.println("-----------------------------------------------------");
		System.out.println("Question 2");
		for(List<Vertex> i : allpath){
			int counter = 0;
			int mark = 0;
			for(int k = 0; k<i.size()-1;k++){
				for(int z = 0; z<i.get(k).getEdges().size(); z++){
				if(i.get(k).getEdges().get(z).getDestination().getName().equals(i.get(k+1).getName()) && i.get(k).getEdges().get(z).getWeight()==0){
					counter++;
					mark = z;
				}
				}
			}
			if(counter == 1){
				System.out.println(i.get(mark).getName() + " - " + i.get(mark+1).getName());
			}

		}
		/*while(!allpathList.isEmpty()){
			List<Vertex> path = allpathList.poll();
			while(!path.isEmpty()){
				if(path.get()
			}
		}*/
		System.out.println();
	}
}


	
	

	
    


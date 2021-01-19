import java.util.*;

public class Graph {
	private HashMap<String,Vertex> vertices;
	private HashMap<String,Edge> edges;
	public Queue<Vertex> visitedVertexs = new LinkedList<>();
	public Queue<Edge> reelbottlenecks = new LinkedList<>();
	boolean secondquestionflag = false;
	String source,destination;
	int firstQuestionAnswer;
	int bottleneck = 0;
	Graph() {
		this.vertices = new HashMap<>();
		this.edges = new HashMap<>();
	}

	public void addEgde(String source, String destination, int weight) {

		if(edges.get(source + "-" + destination) == null)
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

			Edge edge = new Edge(source_v, destination_v, weight, weight);
			source_v.addEdge(edge);
			destination_v.addEdge(edge);
			edges.put(source + "-" + destination, edge);
		}
		else
		{
			edges.get(source + "-" + destination).setWeight(edges.get(source + "-" + destination).getWeight() + weight);
		}
	}

	public void fillGraph(){
		for(Vertex v : vertices()){
			for(Vertex x : vertices()){
				if(edges.get(v + "-" + x) == null) addEgde(v.getName(), x.getName(), 0);
				if(edges.get(x + "-" + v) == null) addEgde(x.getName(), v.getName(), 0);
			}
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
	
	public boolean bfs(String s, String d){
		for(Vertex v : vertices()){
			v.visited = false;
		}
		
		/*while(!visitedVertexs.isEmpty()){
			Vertex a = visitedVertexs.poll();
			a.visited = false;
		}*/

		LinkedList<Vertex> queue = new LinkedList<Vertex>(); 
		queue.add(vertices.get(s));
		vertices.get(s).visited= true;

		while (!queue.isEmpty()) 
        { 
            Vertex u = queue.poll();
  
            for (Vertex v : vertices()) 
            {  
				
						   
				if (!v.visited && edges.get(u.getName() + "-" + v.getName()) != null && edges.get(u.getName() + "-" + v.getName()).getWeight()>0) {
							 
					queue.add(v); 
                    v.setParent(u);
					v.visited = true; 
					
				} 
			}
        } 


		return vertices.get(d).visited;
	}

	int fordFulkerson(String s, String d) 
    { 
		Vertex u;
  
		int max_flow = 0;
		
        while (bfs(s, d)) 
        {   	
			LinkedList<Edge> bottlenecks = new LinkedList<Edge>(); 
			int counter = 0;

			int path_flow = Integer.MAX_VALUE;
            for (Vertex v = vertices.get(d); v != vertices.get(s); v = v.getParent()) 
            { 
                u = v.getParent(); 
                path_flow = Math.min(path_flow, edges.get(u.getName() + "-" + v.getName()).getWeight()); 
            } 
  
            for (Vertex v = vertices.get(d); v != vertices.get(s); v = v.getParent()) 
            { 
                u = v.getParent(); 
				edges.get(u.getName() + "-" + v.getName()).setWeight(edges.get(u.getName() + "-" + v.getName()).getWeight()-path_flow);
				if(edges.get(u.getName() + "-" + v.getName()).getWeight() == 0){
					counter++;
					bottlenecks.add(edges.get(u.getName() + "-" + v.getName()));
				}
			} 
			if(counter == 1){
				reelbottlenecks.add(bottlenecks.poll());
			}
			else if(counter > 0){
				while(!bottlenecks.isEmpty())
					bottlenecks.poll();
			}
			
            max_flow += path_flow; 
        } 
  
        return max_flow; 
    }
}
	



	
	

	
    


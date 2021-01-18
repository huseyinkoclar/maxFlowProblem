import java.util.*;

public class Graph {
	private HashMap<String,Vertex> vertices;
	private HashMap<String,Edge> edges;
	public Queue<Edge> bottlenecks = new LinkedList<>();
	boolean secondquestionflag = false;
	String source,destination;
	int firstQuestionAnswer;
	int bottleneck = 0;
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

	public void checkbfs(String s, String d, boolean flag){
		bottleneck = 0;
		if(flag){
			source = s;
			destination = d;
		}
		int flow = 0;
		secondquestionflag = false;
		do{
			for(Edge x : edges.values()){
				x.getSource().visited = false;
				x.getDestination().visited = false;
			}
			if(flow==bfs(s, d)) break;
			else {flow = bfs(s, d);}
		}while(true);
		if(flag){
			firstQuestionAnswer = flow;
			System.out.println("Source " + s + " to " + destination + " max flow is " + flow);
		}
		else{
			vertices.get(destination).value = firstQuestionAnswer;
			flow -= firstQuestionAnswer;
		}
		if(flow != 0) bottleneck = flow;
		

	}
	
	public int bfs(String s, String d){
		int sourceValue = 0;
		for(Edge edge : vertices.get(source).getEdges()){
			sourceValue += edge.getWeight();
		}
		vertices.get(source).value = sourceValue;
		Vertex source = vertices.get(s);
		Vertex destination = vertices.get(d);
		Queue<Vertex> q = new LinkedList<>();

		source.visited = true;
		q.add(source);
		
		while(!q.isEmpty()){
			Vertex x = q.poll();
			for(Edge exEdge : x.getEdges()){
				if(!exEdge.getDestination().visited && (exEdge.getWeight()>0 || exEdge.getDestination().value!=0) && exEdge.getSource().getName().equals(x.getName())){
					q.add(exEdge.getDestination());
					exEdge.getDestination().visited = true;

					if(exEdge.getSource().value >= exEdge.getWeight()){
						exEdge.getDestination().value += exEdge.getWeight();
						exEdge.getSource().value -= exEdge.getWeight();
						exEdge.setWeight(0);
						if(!exEdge.getSource().getName().equals(s))
						bottlenecks.add(exEdge);
					}
					else{
						exEdge.getDestination().value += exEdge.getSource().value;
						exEdge.getSource().value = 0;
						exEdge.setWeight(exEdge.getWeight()-exEdge.getSource().value);
					}
					
				}
				
			}
			
		}

		return destination.value;
		//System.out.println(vertices.get("G").value);
		}

		
	public void secondQuestion(){
		Queue<Edge> nonduplicatebottlenecks = new LinkedList<>();
		Queue<Edge> reelbottleneck = new LinkedList<>();
		Queue<Edge> thirdquestion = new LinkedList<>();
		for(Edge element : bottlenecks){
			if(!nonduplicatebottlenecks.contains(element))
				nonduplicatebottlenecks.add(element);
		}
		for(Edge element : nonduplicatebottlenecks){
			element.setWeight(Integer.MAX_VALUE);
			checkbfs(element.getSource().getName(), destination, false);
			if(bottleneck!= 0) {reelbottleneck.add(element); element.bottleneck = bottleneck;}
			element.setWeight(0);
		}
		System.out.println("------------------------------------------------");
		System.out.println("Second Question");
		System.out.println();
		while(!reelbottleneck.isEmpty()){
			Edge edge = reelbottleneck.poll();
			thirdquestion.add(edge);
		System.out.println(edge.getSource().getName() + " to " + edge.getDestination().getName());
		}
		System.out.println();
		System.out.println("------------------------------------------------");
		System.out.println("Third Question");
		System.out.println();
		while(!thirdquestion.isEmpty()){
			Edge edge = thirdquestion.poll();
			System.out.println(edge.getSource().getName() + " to " + edge.getDestination().getName() + "  " + edge.bottleneck);
		}
	}
	
}


	
	

	
    


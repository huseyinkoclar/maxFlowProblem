public class Edge {
    private Vertex source;
	private Vertex destination;
	private int weight;
	private final int finalweight;
	public int bottleneck = 0;
	public int increase = 0;
	
	public Edge(Vertex source, Vertex destination, int weight, int finalweight) {
		super();
		this.source = source;
		this.destination = destination;
		this.weight = weight;
		this.finalweight = finalweight;
	}

	public Vertex getSource() {
		return source;
	}

	public void setSource(Vertex source) {
		this.source = source;
	}

	public Vertex getDestination() {
		return destination;
	}

	public void setDestination(Vertex destination) {
		this.destination = destination;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getFinalweight() {
		return finalweight;
	}
}

package stats;

public class Stat {
	private String name;
	private double weight;
	private double value;
	
	public Stat(String name, int weight, double value) {
		this.name = name;
		this.weight = weight;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public double getValue() {
		return value;
	}

	public double getWeight() {
		return weight;
	}
	
	public double getScore(){
		return weight * value;
	}
	
}

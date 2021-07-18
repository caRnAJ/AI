package neuron.connection;

import neuron.Neuron;

public class Connection {

	public double weight;
	public Neuron parent;
	public Neuron child;

	public Connection(Neuron parent, Neuron child) {
		weight = Math.random();
		this.parent = parent;
		this.child = child;
	}

	public double getPoundedParent() {
		return parent.value * weight;
	}

}

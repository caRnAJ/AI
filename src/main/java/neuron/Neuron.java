package neuron;

import java.util.ArrayList;
import java.util.List;

import neuron.connection.Connection;

public class Neuron {

	public List<Connection> parents;
	public List<Connection> children;
	public double value;
	public int lifecycles;
	public double acceptationTreshold;
	public int maxWrongCycles;

	public Neuron() {
		parents = new ArrayList<>();
		children = new ArrayList<>();
		value = 0;
	}

	public void connectChild(Neuron childNeuron) {
		Connection newConnection = new Connection(this, childNeuron);
		children.add(newConnection);
		childNeuron.parents.add(newConnection);
	}

	public void computeValue() {
		value = 0;
		for (Connection connection : parents) {
			value = value + connection.getPoundedParent();
		}
	}

	public void correctValue(double detectedDiff) {
		boolean isExpectedValueSuperior = Math.abs(detectedDiff) > acceptationTreshold;
		for (Connection connection : parents) {
//			connection.weight = connection.weight
//					* ((expectedValue / value) * (isExpectedValueSuperior ? 1.00000000000001 : 0.99999999999999));
//			connection.parent.correctValue(value
//					* ((expectedValue / value) * (isExpectedValueSuperior ? 1.00000000000001 : 0.99999999999999)));
//
//			connection.weight = connection.weight * (isExpectedValueSuperior ? 1.0001 : 0.9999);
//			connection.parent.correctValue(value * (isExpectedValueSuperior ? 1.0001 : 0.9999));
		}
	}
}

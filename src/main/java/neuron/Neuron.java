package neuron;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Neuron {

	public static final Logger LOGGER = Logger.getGlobal();

	public static int MAX_LIFE_CYCLES_ABOVE_MIN = 5000000;
	public static int MIN_LIFE_CYCLES = 1000000;
	public static double DEFAULT_ACCEPTATION_TRESHOLD = 0.00000001;
	public static int MAX_WRONG_CYCLES = 20;

	public List<Neuron> parents;
	public Map<Neuron, Double> children;
	public double value;
	public int lifecycles;
	public double acceptationTreshold;
	public int maxWrongCycles;
	public int currentWrongCycles;
	public int currentLifeCycles;

	public Neuron() {
		init();
	}

	public Neuron(double value) {
		init();
		this.value = value;
	}

	private void init() {
		parents = new ArrayList<>();
		children = new HashMap<>();
		value = 0;
		lifecycles = (int) (MAX_LIFE_CYCLES_ABOVE_MIN * Math.random()) + MIN_LIFE_CYCLES;
		acceptationTreshold = DEFAULT_ACCEPTATION_TRESHOLD;
		currentWrongCycles = 0;
		currentLifeCycles = 0;
		maxWrongCycles = MAX_WRONG_CYCLES;
	}

	public void connectChild(Neuron childNeuron) {
		children.put(childNeuron, Math.random());
		childNeuron.parents.add(this);
	}

	public void connectParent(Neuron parentNeuron) {
		parentNeuron.connectChild(this);
	}

	public void disconnectChild(Neuron childNeuron) {
		children.remove(childNeuron);
	}

	public void disconnectParent(Neuron parentNeuron) {
		parents.remove(parentNeuron);
	}

	public void computeValue() {
		if (parents.size() > 0) {
			value = 0;
			for (Neuron parent : parents) {
				parent.computeValue();
				value = value + parent.getPoundedValueToChild(this);
			}
			currentLifeCycles++;
		}
	}

	private double getPoundedValueToChild(Neuron child) {
		return value * children.get(child);
	}

	public void correctValue(double detectedDiff) {
		boolean isExpectedValueSuperior = Math.abs(detectedDiff) > acceptationTreshold;

		for (Neuron parent : parents) {
			if (isExpectedValueSuperior) {
				parent.setCorrectChildPound(this, detectedDiff);
			}
			parent.correctValue(detectedDiff);
		}

		currentWrongCycles = isExpectedValueSuperior ? currentWrongCycles + 1 : 0;
		if ((children.size() > 0) && (currentWrongCycles > maxWrongCycles)) {
			evolve(this);
		}
	}

	private void setCorrectChildPound(Neuron neuron, double detectedDiff) {
		children.put(neuron, children.get(neuron) * (1 + detectedDiff));
	}

	public static void evolve(Neuron neuron) {
		if (Math.random() > 0.5) {
			LOGGER.info("SPLIT");
			// horizontal evolving
			Neuron n1 = new Neuron();
			Neuron n2 = new Neuron();

			n1.connectChild(n2);

			for (Neuron parent : neuron.parents) {
				n1.connectParent(neuron);
				parent.disconnectChild(neuron);
			}

			for (Neuron child : neuron.children.keySet()) {
				n2.connectChild(child);
				child.disconnectParent(neuron);
			}
		} else {
			LOGGER.info("GENERATE");
			// vertical evolving
			Neuron n1 = new Neuron();
			Neuron n2 = new Neuron();

			for (Neuron parent : neuron.parents) {
				n1.connectParent(neuron);
				n2.connectParent(neuron);
				parent.disconnectChild(neuron);
			}
			for (Neuron child : neuron.children.keySet()) {
				n1.connectChild(child);
				n2.connectChild(child);
				child.disconnectParent(neuron);
			}

		}
	}
}

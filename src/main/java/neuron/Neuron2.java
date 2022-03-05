package neuron;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.apache.maven.surefire.shared.lang3.tuple.Pair;

public class Neuron2 {

	public static final Logger LOGGER = Logger.getGlobal();

	protected List<Double> inputs;
	protected Double output;

	protected List<Neuron2> internals;
	protected Map<Pair<Neuron2, Neuron2>, Double> relations;
	protected Pair<Neuron2, Neuron2> selfPair;

	public Neuron2(int inputsCount) {
		init();
		for (int i = 0; i < inputsCount; i++)
			addInput(0.0);
	}

	public Neuron2() {
		init();
	}

	private void init() {
		inputs = new LinkedList<>();
		output = 0.0;
		internals = new ArrayList<>();
		relations = new HashMap<>();
		selfPair = Pair.of(this, this);
		relations.put(selfPair, new Double(Math.random()));
	}

	public void computeValue() {
		Double result = 0.0;
		if (internals.size() > 0) {
			for (Entry<Pair<Neuron2, Neuron2>, Double> relation : relations.entrySet()) {
				Neuron2 n1 = relation.getKey().getLeft();
				Neuron2 n2 = relation.getKey().getRight();
				Double value = relation.getValue();
				if (n2.equals(this) && (!n1.equals(this))) {
					result = result + (n1.getValue() * value);
				}
			}
		} else {
			for (Double input : inputs) {
				result = result + (input * relations.get(selfPair));
			}
		}
		output = result;
	}

	public void correctValue(Double detectedDiff) {
		if (internals.size() > 0) {
			for (Entry<Pair<Neuron2, Neuron2>, Double> relation : relations.entrySet()) {
				if (relation.getKey().getRight().equals(this) && (!relation.getKey().getLeft().equals(this))) {
					relation.getKey().getLeft().correctValue(detectedDiff);
				}
			}
		} else {
			relations.put(selfPair, detectedDiff * relations.get(selfPair));
		}
	}

	public Double getOutput() {
		return output;
	}

	public Double getValue() {
		computeValue();
		return getOutput();
	}

	protected void addInput(Double initialValue) {
		inputs.add(initialValue);
	}

	public void deleteInput() {
		inputs.remove(inputs.size() - 1);
	}

	public void setInput(int inputIndex, Double value) {
		inputs.set(inputIndex, value);
	}

	public boolean train(Map<List<Double>, Double> okValues, Double accuracy) {
		LOGGER.info(String.format(" ok VALUE = %s", okValues));
		List<Double> results = new ArrayList<>();
		Double globalErrorRatio = accuracy + 2;
		int j = 0;
		int currentWrongCycles = 0;
		int maxWrongCycles = 10000;
		while ((Math.abs(1 - globalErrorRatio) > accuracy) && (currentWrongCycles < maxWrongCycles)) {
			// on demande de calculer tous les résultats, dont on stocke le rapport de
			// différence avec le résultat attendu
			results.clear();
			for (Entry<List<Double>, Double> okValue : okValues.entrySet()) {
				for (int i = 0; i < okValue.getKey().size(); i++) {
					setInput(i, okValue.getKey().get(i));
				}
				computeValue();
				LOGGER.info(String.format("EXPECTED/ACTUAL %s %s", okValue.getValue(), getOutput()));
				results.add(okValue.getValue() / getOutput());
			}

			// pour chaque différence, on rajoute à la moyenne
			globalErrorRatio = 0.0;
			for (Double result : results) {
				globalErrorRatio = globalErrorRatio + (result / results.size());
			}

			if (Math.abs(1 - globalErrorRatio) > accuracy) {
				LOGGER.info(String.format("global diff= %s, current = %s", globalErrorRatio, relations.get(selfPair)));
				correctValue(globalErrorRatio);
			}

			LOGGER.info(String.format("SELFPAIR %s", relations.get(selfPair)));
			LOGGER.info(String.format("GLOBALDIFF/ACCURACY %s %s", globalErrorRatio, accuracy));
			currentWrongCycles++;
		}
		return currentWrongCycles < maxWrongCycles;
	}

	public boolean train() {
		return false;
	}

}

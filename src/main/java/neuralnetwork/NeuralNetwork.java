package neuralnetwork;

import neuron.Neuron;

public class NeuralNetwork extends Neuron {

	public Neuron resultNeuron;

	public NeuralNetwork(int entryCount) {
		resultNeuron = new Neuron();
		for (int i = 0; i < entryCount; i++) {
			Neuron parentNeuron = new Neuron();
			parents.add(parentNeuron);
			parentNeuron.connectChild(resultNeuron);
		}

	}

	public void setEntryValue(int entryIndex, double value) {
		parents.get(entryIndex).value = value;
	}

	public void computeResult() {
		resultNeuron.computeValue();
	}

	public double getResult() {
		return resultNeuron.value;
	}

	public void train() {
		// needs to be overridden by specialized networks
	}

}

package neuralnetwork;

import java.util.ArrayList;
import java.util.List;

import neuron.Neuron;

public class NeuralNetwork {

	public List<Neuron> entryNeurons;
	protected List<Neuron> hiddenNeurons;
	protected List<Neuron> hiddenNeuron2;
	public Neuron resultNeuron;

	public NeuralNetwork(int entryNeuronsCount) {
		entryNeurons = new ArrayList<>();
		hiddenNeurons = new ArrayList<>();

		resultNeuron = new Neuron();

		Neuron newHidden = new Neuron();
		newHidden.connectChild(resultNeuron);

		hiddenNeurons.add(newHidden);

		for (int i = 0; i < entryNeuronsCount; i++) {
			Neuron newEntry = new Neuron();
			newEntry.connectChild(newHidden);
			entryNeurons.add(newEntry);
		}

	}

	public void setEntryValue(int entryIndex, double value) {
		entryNeurons.get(entryIndex).value = value;
	}

	public void computeResult() {
		for (Neuron hidden : hiddenNeurons) {
			hidden.computeValue();
		}
		resultNeuron.computeValue();
	}

	public double getResult() {
		return resultNeuron.value;
	}

	protected void addNewHidden() {
		Neuron newHidden = new Neuron();
		hiddenNeurons.add(newHidden);
		newHidden.connectChild(resultNeuron);
		for (Neuron entry : entryNeurons) {
			entry.connectChild(newHidden);
		}
	}

}

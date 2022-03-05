package ai;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import neuralnetwork.AddOperationNetwork;
import neuralnetwork.NeuralNetwork;

public class AI {

	private List<NeuralNetwork> neuralNetworks;

	public AI() {
		neuralNetworks = new ArrayList<>();
	}

	public void createAddOperationNetwork() {
		AddOperationNetwork addOperationNetwork = new AddOperationNetwork();
		addOperationNetwork.train();
		testPair(addOperationNetwork, 5, 3);
		testPair(addOperationNetwork, 10, 15);
		testPair(addOperationNetwork, 1000, 300);
		testPair(addOperationNetwork, 80, 19);
		testPair(addOperationNetwork, 8, 7);
		testPair(addOperationNetwork, 1532, 6);
	}

	private void testPair(AddOperationNetwork addOperationNetwork, int a, int b) {
		addOperationNetwork.setEntryValue(0, a);
		addOperationNetwork.setEntryValue(1, b);
		addOperationNetwork.computeResult();
		Logger.getGlobal().info(String.format("a=%d, b=%d, r=%d", (int) addOperationNetwork.parents.get(0).value,
				(int) addOperationNetwork.parents.get(1).value, (int) addOperationNetwork.resultNeuron.value));
	}

}

package neuralnetwork;

import java.util.logging.Logger;

public class AddOperationNetwork extends NeuralNetwork {

	public AddOperationNetwork() {
		super(2);
	}

	public void train() {
		int maxDepth = 2000;
		for (int i = 0; i < maxDepth; i++) {
			addNewHidden();
		}

//		while ((hiddenNeurons.size() < maxDepth )) {
		for (int i = 0; i < 10000; i++) {
			// Logger.getGlobal().info(String.format("depth = %d, i=%d",
			// hiddenNeurons.size(), i));
			iterate();
		}
		Logger.getGlobal().info(String.format("depth = %d", hiddenNeurons.size()));
		if (!iterate()) {
		}
	}

//	}

	private boolean iterate() {
		int a = (int) (Math.random() * 10000) + 1;
		int b = (int) (Math.random() * 10000) + 2;
		entryNeurons.get(0).value = a;
		entryNeurons.get(1).value = b;
		int factor = 1;
//		int result = (int) (resultNeuron.value * factor);
		double result = resultNeuron.value;
		int j = 0;
		while ((Math.abs((result - (a + b))) > 0.000000001)) {
//		for (int i = 0; i < 10000; i++) {
			computeResult();
			resultNeuron.correctValue((a + b));
//			result = (int) (resultNeuron.value * factor);
			result = resultNeuron.value;
			j++;
			if (j > 50000) {
				int diff = (int) (100 - (100 * resultNeuron.value / (a + b)));
//				System.out.println(String.format("a=%d, b=%d, result=%d, diff=%d", a, b, result, diff));
				System.out.println(String.format("a=%d, b=%d, result=%f, diff=%d", a, b, result, diff));
			}
		}
//		System.out.println(String.format("diff=%f", (100 / (a + b) * (resultNeuron.value - (a + b)))));
//		System.out.println(String.format("a=%d, b=%d, result=%f, diff=%f", a, b, resultNeuron.value,
//				(resultNeuron.value - (a + b))));
		return true;
	}

}

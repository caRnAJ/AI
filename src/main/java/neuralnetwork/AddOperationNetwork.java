package neuralnetwork;

public class AddOperationNetwork extends NeuralNetwork {

	private static final int TRAIN_CYCLES = 1000;
	private static final int OPERATIONS_PER_CYCLE = 1000;

	public AddOperationNetwork() {
		super(2);
		LOGGER.info("CREATED ADD OPERATION NETWORK");
	}

	public void train() {
		LOGGER.info("STARTING TO TRAIN OPERATION NETWORK");
		double avgTotal = 0;
		double avgResult = 0;
		for (int i = 0; i < TRAIN_CYCLES; i++) {
			for (int j = 0; j < OPERATIONS_PER_CYCLE; j++) {

				int a = (int) (Math.random() * 1000);
				int b = (int) (Math.random() * 1000);
				avgTotal = (avgTotal + ((a + b) / OPERATIONS_PER_CYCLE));
				parents.get(0).value = a;
				parents.get(1).value = a;
				computeResult();
				avgResult = (avgResult + (resultNeuron.value / OPERATIONS_PER_CYCLE));
//				LOGGER.info(String.format("i=[%d]/j=[%d] : a=[%d] ; b=[%d], computed result = [%f]", i, j, a, b,
//						resultNeuron.value));
			}
			// LOGGER.info(String.format("avgTotal=[%f], avgResult=[%f]", avgTotal,
			// avgResult));
			double diff = (avgTotal - avgResult) / OPERATIONS_PER_CYCLE;
			LOGGER.info(String.format("%f", diff));
			resultNeuron.correctValue(diff);
		}
		LOGGER.info("END OF OPERATION NETWORK");
	}

}

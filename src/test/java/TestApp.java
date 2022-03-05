import static org.junit.Assert.assertFalse;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;

import neuron.AddOperationNeuron;
import neuron.IdenticalValueNeuron;
import neuron.MultiplyOperationNeuron;

public class TestApp {

	public static final Logger LOGGER = Logger.getGlobal();

	public TestApp() {

	}

	public static void main(String[] args) {

	}

	@Test
	public void test() {

		// testIdentical();
		testAdd();

		// testMultiply();

		assertFalse(false);
	}

	private void testMultiply() {
		MultiplyOperationNeuron n2 = new MultiplyOperationNeuron();
		LOGGER.info(Boolean.toString(n2.train()));

		for (int i = 0; i < 10; i++) {
			List<Double> l = new LinkedList<>();
			int a = (int) (Math.random() * 10);
			int b = (int) (Math.random() * 10);
//			Double a = Math.random() * 1000000;
//			Double b = Math.random() * 1000000;
			n2.setInput(0, a * 1.0);
			n2.setInput(1, b * 1.0);
			LOGGER.info(String.format("value for %s * %s = %s <-> %s", a, b, n2.getValue(), a * b));
		}
	}

	private void testAdd() {
		AddOperationNeuron n2 = new AddOperationNeuron();
		n2.train();
		n2.setInput(0, 42.0);
		n2.setInput(1, 58.0);
		LOGGER.info(String.format("value for 42+58 = %f", n2.getValue()));

		for (int i = 0; i < 10; i++) {
			List<Double> l = new LinkedList<>();
//			int a = (int) (Math.random() * 1000000);
//			int b = (int) (Math.random() * 1000000);
			Double a = Math.random() * 1000000;
			Double b = Math.random() * 1000000;
			n2.setInput(0, Double.valueOf(a.intValue()));
			n2.setInput(1, Double.valueOf(b.intValue()));
			LOGGER.info(String.format("value for %s+%s = %s <-> %s", a, b, n2.getValue(), a + b));
		}
	}

	private void testIdentical() {
		IdenticalValueNeuron n = new IdenticalValueNeuron();
		n.train();
		n.setInput(0, 20.0);
		LOGGER.info(String.format("value for 20 = %f", n.getValue()));
	}

}

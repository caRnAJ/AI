package neuron;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MultiplyOperationNeuron extends Neuron2 {

	public MultiplyOperationNeuron() {
		super();
		addInput(0.0);
		addInput(0.0);
//		relations.put(selfPair, 0.5);
	}

	@Override
	public boolean train() {
		Map<List<Double>, Double> okValues = new HashMap<>();
		Double accuracy = 0.001;
		for (int i = 0; i < 1; i++) {
			List<Double> l = new LinkedList<>();
			int a = (int) (Math.random() * 10);
			int b = (int) (Math.random() * 10);
			l.add(a * 1.0);
			l.add(b * 1.0);
//			l.add(Math.random() * 1000000);
//			l.add(Math.random() * 1000000);
			okValues.put(l, l.get(0) * l.get(1));
		}
		return super.train(okValues, accuracy);
	}

}

package neuron;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IdenticalValueNeuron extends Neuron2 {

	public IdenticalValueNeuron() {
		super();
		addInput(0.0);
//		relations.put(selfPair, 0.5);
	}

	@Override
	public boolean train() {
		Map<List<Double>, Double> okValues = new HashMap<>();
		Double accuracy = 0.0000001;
		for (int i = 0; i < 1000; i++) {
			List<Double> l = new LinkedList<>();
			int a = (int) (Math.random() * 1000000);
			l.add(a * 1.0);
			okValues.put(l, l.get(0));
		}
		return super.train(okValues, accuracy);
	}

}

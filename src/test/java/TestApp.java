import static org.junit.Assert.assertFalse;

import org.junit.Test;

import ai.AI;

public class TestApp {

	public TestApp() {

	}

	public static void main(String[] args) {

	}

	@Test
	public void test() {
		AI ai = new AI();
		ai.createAddOperationNetwork();
		assertFalse(false);
	}

}

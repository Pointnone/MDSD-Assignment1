package main;

import main.metamodel.Machine;
import main.metamodel.State;

public class MachineInterpreter {
	
	protected Machine _machine = null;

	public void run(Machine m) {
		this._machine = m;
		this._machine.setCurrentStateToInitialState();
	}

	public State getCurrentState() {
		return this._machine.getCurrentState();
	}

	public void processEvent(String string) {
		this._machine.processEvent(string);
	}

	public int getInteger(String string) {
		return this._machine.getInteger(string);
	}
}

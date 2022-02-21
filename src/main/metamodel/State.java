package main.metamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class KeyVal<T> {
	public String key = null;
	public T val = null;
	
	public KeyVal(String key, T val) {
		this.key = key;
		this.val = val;
	}
}

public class State {
	protected String _name = null;
	protected List<Transition> _transitions = new ArrayList<Transition>();

	public State(String name) {
		this._name = name;
	}
	
	public void addTransition(Transition trans) {
		this._transitions.add(trans);
		//this._transitions.put(trans.getEvent(), trans);
	}
	
	public String getName() {
		return this._name;
	}

	public List<Transition> getTransitions() {
		return this._transitions;
	}

	public List<Transition> getTransitionByEvent(String string) {
		return this._transitions.stream().filter(trans -> trans.getEvent() == string).collect(Collectors.toList());
	}

}

package main;

import java.util.HashMap;
import java.util.Map;

import main.metamodel.Condition;
import main.metamodel.ConditionType;
import main.metamodel.Machine;
import main.metamodel.Operation;
import main.metamodel.OperationType;
import main.metamodel.State;
import main.metamodel.Transition;

public class StateMachine extends Machine {
		
	private State _lastStateInBuilder = null;
	private Transition _lastTransitionInBuilder = null;

	public Machine build() {
		return this;
	}

	public StateMachine state(String stateName) {
		State newState = new State(stateName);
		
		super._states.put(stateName, newState);
		this._lastStateInBuilder = newState;
		
		return this;
	}

	public StateMachine initial() {
		super._initialState = this._lastStateInBuilder;
		return this;
	}

	public StateMachine when(String event) {
		Transition newTrans = new Transition(event);
		
		this._lastStateInBuilder.addTransition(newTrans);
		this._lastTransitionInBuilder = newTrans;
		
		return this;
	}

	public StateMachine to(String string) {
		this._lastTransitionInBuilder.setTarget(string);
		
		return this;
	}

	public StateMachine integer(String string) {
		if(!_integers.containsKey(string))
			_integers.put(string, 0);
		return this;
	}

	public StateMachine set(String string, int i) {
		this._lastTransitionInBuilder.addOperation(new Operation(OperationType.SET, string, i));
		return this;
	}

	public StateMachine increment(String string) {
		this._lastTransitionInBuilder.addOperation(new Operation(OperationType.INC, string, null));
		return this;
	}

	public StateMachine decrement(String string) {
		this._lastTransitionInBuilder.addOperation(new Operation(OperationType.DEC, string, null));
		return this;
	}

	public StateMachine ifEquals(String string, int i) {
		this._lastTransitionInBuilder.addCondition(new Condition(string, ConditionType.EQUAL, i));
		return this;
	}

	public StateMachine ifGreaterThan(String string, int i) {
		this._lastTransitionInBuilder.addCondition(new Condition(string, ConditionType.GREATERTHAN, i));
		return this;
	}

	public StateMachine ifLessThan(String string, int i) {
		this._lastTransitionInBuilder.addCondition(new Condition(string, ConditionType.LESSTHAN, i));
		return this;
	}

}

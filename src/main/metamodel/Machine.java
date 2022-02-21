package main.metamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Machine {
	protected Map<String, Integer> _integers = new HashMap<String, Integer>();
	protected Map<String, State> _states = new HashMap<String, State>();
	
	protected State _initialState = null;
	
	protected State _currentState = null;
	
	public void setCurrentStateToInitialState() {
		this._currentState = this._initialState;
	}
	
	public State getCurrentState() {
		return this._currentState;
	}

	public List<State> getStates() {
		return new ArrayList<State>(this._states.values());
	}

	public State getInitialState() {
		return this._initialState;
	}

	public State getState(String string) {
		return this._states.get(string);
	}

	public int numberOfIntegers() {
		return this._integers.values().size();
	}

	public boolean hasInteger(String string) {
		return this._integers.containsKey(string);
	}
	
	public Integer getInteger(String string) {
		return this._integers.get(string);
	}
	
	public void processEvent(String event) {
		List<Transition> transitions = this._currentState.getTransitionByEvent(event);
		if(transitions.size() == 0) //Guard function
			return;
		
		for(int i = 0; i < transitions.size(); i++) {
			Transition trans = transitions.get(i);
			boolean conditionsSatisfied = true; // Assume true for now
			
			if(trans.isConditional()) {
				List<Condition> conds = trans.getConditions();
				
				for(int j = 0; j < conds.size(); j++) { // Reduce
					Condition cond = conds.get(j);
					
					boolean equal = cond.getType() == ConditionType.EQUAL && this._integers.get(cond.getVar()) == cond.getVal();
					boolean greaterthan = cond.getType() == ConditionType.GREATERTHAN && this._integers.get(cond.getVar()) > cond.getVal();
					boolean lessthan = cond.getType() == ConditionType.LESSTHAN && this._integers.get(cond.getVar()) < cond.getVal();
					conditionsSatisfied = conditionsSatisfied && (equal || greaterthan || lessthan);
				}
				
				//if(!conditionsSatisfied) { // Guard function
				//	return; 
				//}
			}
			
			if(conditionsSatisfied) { // Do operations of transition
				if(trans.hasOperation()) {
					List<Operation> ops = trans.getOperations();
					
					for(int j = 0; j < ops.size(); j++) { // Perform
						Operation op = ops.get(j);
						
						if(op.getOpType() == OperationType.SET)
							this._integers.replace(op.getKey(), op.getValue());
						else if(op.getOpType() == OperationType.INC)
							this._integers.replace(op.getKey(), this._integers.get(op.getKey())+1);
						else if(op.getOpType() == OperationType.DEC)
							this._integers.replace(op.getKey(), this._integers.get(op.getKey())-1);
					}
				}
				this._currentState = this.getState(trans._target);
				return;
			}
		}
	}
}

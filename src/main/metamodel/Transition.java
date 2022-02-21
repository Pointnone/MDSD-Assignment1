package main.metamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Transition {
	protected String _event = null;
	protected String _target = null;
	
	protected List<Operation> _operations = new ArrayList<Operation>();
	protected List<Condition> _conditions = new ArrayList<Condition>();

	public Transition(String event) {
		this._event = event;
	}
	
	public void setTarget(String string) {
		this._target = string;
	}
	
	public void addOperation(Operation op) {
		this._operations.add(op);
	}
	
	public void addCondition(Condition cond) {
		this._conditions.add(cond);
	}
	
	public String getEvent() {
		return this._event;
	}

	public String getTarget() {
		return this._target;
	}
	
	private boolean hasXOperation(OperationType opType) {
		return !this._operations.stream().filter(op -> op._type == opType).collect(Collectors.toList()).isEmpty();
	}

	public boolean hasSetOperation() {
		return this.hasXOperation(OperationType.SET);
	}

	public boolean hasIncrementOperation() {
		return this.hasXOperation(OperationType.INC);
	}

	public boolean hasDecrementOperation() {
		return this.hasXOperation(OperationType.DEC);
	}

	public List<String> getOperationVariableNames() {
		return this._operations.stream().map(op -> op._opKey).collect(Collectors.toList());
	}

	public boolean isConditional() {
		return !this._conditions.isEmpty();
	}
	
	public List<Condition> getConditions() {
		return this._conditions;
	}

	public List<String> getConditionVariableNames() {
		return this._conditions.stream().map(cond -> cond._varName).collect(Collectors.toList());
	}

	public List<Object> getConditionComparedValues() {
		return this._conditions.stream().map(cond -> cond._condVal).collect(Collectors.toList());
	}
	
	private boolean isConditionX(ConditionType condType) {
		return !this._conditions.stream().filter(cond -> cond._type == condType).collect(Collectors.toList()).isEmpty();
	}

	public boolean isConditionEqual() {
		return this.isConditionX(ConditionType.EQUAL);
	}

	public boolean isConditionGreaterThan() {
		return this.isConditionX(ConditionType.GREATERTHAN);
	}

	public boolean isConditionLessThan() {
		return this.isConditionX(ConditionType.LESSTHAN);
	}

	public boolean hasOperation() {
		return !this._operations.isEmpty();
	}

	public List<Operation> getOperations() {
		return this._operations;
	}
}

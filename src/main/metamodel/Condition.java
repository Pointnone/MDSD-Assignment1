package main.metamodel;

public class Condition {
	protected String _varName = null;
	protected ConditionType _type = null;
	protected Integer _condVal = null;
	
	public Condition(String var, ConditionType type, Integer val) {
		this._varName = var;
		this._type = type;
		this._condVal = val;
	}
	
	public String getVar() {
		return this._varName;
	}
	
	public ConditionType getType() {
		return this._type;
	}
	
	public Integer getVal() {
		return this._condVal;
	}
}

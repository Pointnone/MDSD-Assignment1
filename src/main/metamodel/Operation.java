package main.metamodel;

public class Operation {
	protected OperationType _type = null;
	protected String _opKey = null;
	protected Integer _opVal = null;
	
	public OperationType getOpType() {
		return this._type;
	}
	
	public String getKey() {
		return this._opKey;
	}
	
	public Integer getValue() {
		return this._opVal;
	}

	public Operation(OperationType type, String key, Integer value) {
		this._type = type;
		this._opKey = key;
		this._opVal = value;
	}
}

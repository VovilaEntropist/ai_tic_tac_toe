package i.dont.care.utils.wrappers;

public class DoubleWrapper {
	
	public double value;
	
	public DoubleWrapper(double value) {
		this.value = value;
	}
	
	public DoubleWrapper() {
		this(0);
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
}

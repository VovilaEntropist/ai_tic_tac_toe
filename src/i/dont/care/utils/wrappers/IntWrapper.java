package i.dont.care.utils.wrappers;

public class IntWrapper {
	
	public int value;
	
	public IntWrapper(int value) {
		this.value = value;
	}
	
	public IntWrapper() {
		this(0);
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public IntWrapper inc() {
		++value;
		return this;
	}
	
	public IntWrapper dec() {
		--value;
		return this;
	}
}

package interp;

public class NumV implements VObject {

    public NumV (final String id, final Integer value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public Object evaluate() {
        return this.value;
    }

	public String getId() {
		return id;
	}

	public Integer getValue() {
		return value;
	}
 
	private String  id;
    private Integer value;
 }

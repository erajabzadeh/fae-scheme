package interp;

public class NumV implements VObject {

    public NumV (final String id, final Integer value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public VObject evaluate(final Environment e) {
    	// for int nodes
    	if (this.value != null)
    		return this;
    	
    	// for symbol nodes
    	VObject vo = e.lookUp(this.id);
    	return vo;
    }

    public String getId() {
        return id;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
    	return "[type=NumV, id=" + this.getId() + 
    			", value=" + getValue() + "]";
    }
    
    private String  id;
    private Integer value;
}

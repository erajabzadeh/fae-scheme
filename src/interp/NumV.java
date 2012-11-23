package interp;

public class NumV implements VObject {

    public NumV (final String id, final Integer value) {
        this.id = id;
        this.value = value;
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

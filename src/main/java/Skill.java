import java.io.Serializable;

// class that contains a single skill
// still under development
public class Skill implements Serializable {
	private static final long serialVersionUID = 1L;
	String name, desc;
	int valuePerPoint;

	Skill(String name, int valuePerPoint, String desc) {
		this.name = name;
		this.desc = desc;
		this.valuePerPoint = valuePerPoint;
	}
}

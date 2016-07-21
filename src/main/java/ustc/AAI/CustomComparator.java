package ustc.AAI;

import java.util.Comparator;

public class CustomComparator implements Comparator<Entity> {

	@Override
	public int compare(Entity o1, Entity o2) {
		if(o1.fvalue>o2.fvalue)
			return 1;
		else if(o1.fvalue<o2.fvalue)
			return -1;
		else
			return 0;
	}

}
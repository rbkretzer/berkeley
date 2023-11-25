package berkeley.utils;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Random;

/**
 * 
 * @author Gabriel A. Rodrigues, Leonardo S. Nunes e Rafael B. Kretzer
 *
 */
public class Time implements Serializable {

	private static final long serialVersionUID = 1L;
	private LocalTime value;

	public Time() {
		Random random = new Random();
		value = LocalTime.of(random.nextInt(23), random.nextInt(59), random.nextInt(59));
	}

	public long toSeconds() {
        return value.toSecondOfDay();
	}

	public LocalTime getValue() {
		return value;
	}

	public void setValue(LocalTime ofInstant) {
		this.value = ofInstant;
	}

	@Override
	public String toString() {
		return value.toString();
	}

}
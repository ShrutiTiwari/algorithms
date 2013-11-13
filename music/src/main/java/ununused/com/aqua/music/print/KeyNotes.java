package ununused.com.aqua.music.print;

import java.util.LinkedHashSet;
import java.util.Set;

public class KeyNotes {
	private final String vadi;
	private final String samvadi;
	private final Set<String> aarohiNotes = new LinkedHashSet<String>();
	private final Set<String> avarohiNotes = new LinkedHashSet<String>();

	private KeyNotes(String vadi, String samvadi) {
		this.vadi = vadi;
		this.samvadi = samvadi;
	}

	public static KeyNotes with(String vadi, String samvadi) {
		return new KeyNotes(vadi, samvadi);
	}

	public String vadi() {
		return vadi;
	}

	public String samvadi() {
		return samvadi;
	}
}
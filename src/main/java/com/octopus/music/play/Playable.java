package com.octopus.music.play;

public interface Playable {
	public String code();

	enum BaseNotes implements Playable {
		SA("Sa"),
		RE_("Re_"),
		RE("Re"),
		GA_("Ga_"),
		GA("Ga"),
		MA("Ma"),
		MA_("Ma_"),
		PA("Pa"),
		DHA_("Dha_"),
		DHA("Dha"),
		NI_("Ni_"),
		NI("Ni"),
		HIGH_SA("Sa-High");

		private final String code;

		private BaseNotes(String code) {
			this.code = code;
		}

		public String code() {
			return code;
		}
	}
}
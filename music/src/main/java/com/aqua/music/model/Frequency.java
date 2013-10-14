package com.aqua.music.model;

public interface Frequency {
	public String fileCode();

	enum ClassicalNote implements Frequency {
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

		private final String fileCode;

		private ClassicalNote(String fileCode) {
			this.fileCode = fileCode;
		}

		public String fileCode() {
			return fileCode;
		}
	}
}
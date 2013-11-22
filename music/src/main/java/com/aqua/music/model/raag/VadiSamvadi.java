package com.aqua.music.model.raag;

class VadiSamvadi {
	private final String vadi;
	private final String samvadi;

	private VadiSamvadi(String vadi, String samvadi) {
		this.vadi = vadi;
		this.samvadi = samvadi;
	}

	public static VadiSamvadi with(String vadi, String samvadi) {
		return new VadiSamvadi(vadi, samvadi);
	}

	public String vadi() {
		return vadi;
	}

	public String samvadi() {
		return samvadi;
	}
}
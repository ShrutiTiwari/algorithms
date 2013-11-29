package com.aqua.music.model.raag;

import java.util.ArrayList;
import java.util.Collection;

import com.aqua.music.model.core.DynamicFrequency;

/**
 * @author "Shruti Tiwari"
 *
 */
public abstract class AbstractRaag {
	private final Collection<DynamicFrequency> aalap = new ArrayList<DynamicFrequency>();
	private final Collection<DynamicFrequency> antara = new ArrayList<DynamicFrequency>();
	private final Collection<DynamicFrequency> antaraTaan = new ArrayList<DynamicFrequency>();
	private final Collection<DynamicFrequency> arohiAvarohi = new ArrayList<DynamicFrequency>();
	private final Collection<DynamicFrequency> chalan = new ArrayList<DynamicFrequency>();
	private final Collection<DynamicFrequency> sthayi = new ArrayList<DynamicFrequency>();
	private final Collection<DynamicFrequency> sthayiLongTaan = new ArrayList<DynamicFrequency>();
	private final Collection<DynamicFrequency> sthayiTaan = new ArrayList<DynamicFrequency>();
	private final Collection<DynamicFrequency> vistar = new ArrayList<DynamicFrequency>();

	
	AbstractRaag(){
		initialize();
	}
	
	
	abstract void initialize();
	
	public Collection<DynamicFrequency> aalap() {
		return aalap;
	}

	public Collection<DynamicFrequency> aarohiAvrohi() {
		return arohiAvarohi;
	}

	public Collection<DynamicFrequency> antara() {
		return antara;
	}

	public Collection<DynamicFrequency> antaraTaan() {
		return antaraTaan;
	}

	public Collection<DynamicFrequency> arohiAvarohi() {
		return arohiAvarohi;
	}

	public Collection<DynamicFrequency> chalan() {
		return chalan;
	}

	public Collection<DynamicFrequency> sthayi() {
		return sthayi;
	}

	public Collection<DynamicFrequency> sthayiLongTaan() {
		return sthayiLongTaan;
	}

	public Collection<DynamicFrequency> sthayiTaan() {
		return sthayiTaan;
	}

	public Collection<DynamicFrequency> vistar() {
		return vistar;
	}

	protected AbstractRaag addAlap(MusicalPhrase... musicalPhrases) {
		addToCollection(aalap, musicalPhrases);
		return this;
	}

	protected AbstractRaag addArohiAvrohi(MusicalPhrase... musicalPhrases) {
		addToCollection(arohiAvarohi, musicalPhrases);
		return this;
	}

	protected AbstractRaag addChalan(MusicalPhrase... musicalPhrases) {
		addToCollection(chalan, musicalPhrases);
		return this;
	}

	protected AbstractRaag addSthayi(MusicalPhrase... musicalPhrases) {
		addToCollection(sthayi, musicalPhrases);
		return this;
	}

	protected AbstractRaag addSthayiTaan(MusicalPhrase... musicalPhrases) {
		addToCollection(sthayiTaan, musicalPhrases);
		return this;
	}

	protected AbstractRaag addSthayiLongTaan(MusicalPhrase... musicalPhrases) {
		addToCollection(sthayiLongTaan, musicalPhrases);
		return this;
	}

	protected AbstractRaag addAntara(MusicalPhrase... musicalPhrases) {
		addToCollection(antara, musicalPhrases);
		return this;
	}

	protected AbstractRaag addAntaraTaan(MusicalPhrase... musicalPhrases) {
		addToCollection(antaraTaan, musicalPhrases);
		return this;
	}

	protected AbstractRaag addVistar(MusicalPhrase... musicalPhrases) {
		addToCollection(vistar, musicalPhrases);
		return this;
	}

	private void addToCollection(Collection<DynamicFrequency> collection, MusicalPhrase... musicalPhrases) {
		for (MusicalPhrase each : musicalPhrases) {
			collection.addAll(each.frequencies());
		}
	}
}

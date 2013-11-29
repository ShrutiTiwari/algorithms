package com.aqua.music.model.raag.song;

import static com.aqua.music.model.core.ClassicalNote.D_;
import static com.aqua.music.model.core.ClassicalNote.G3_;
import static com.aqua.music.model.core.ClassicalNote.G_;
import static com.aqua.music.model.core.ClassicalNote.M_;
import static com.aqua.music.model.core.ClassicalNote.N;
import static com.aqua.music.model.core.ClassicalNote.N1;
import static com.aqua.music.model.core.ClassicalNote.P;
import static com.aqua.music.model.core.ClassicalNote.R3_;
import static com.aqua.music.model.core.ClassicalNote.R_;
import static com.aqua.music.model.core.ClassicalNote.S;
import static com.aqua.music.model.core.ClassicalNote.S3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.aqua.music.model.raag.MusicalPhrase;
import com.aqua.music.model.raag.Taal;

/**
 * @author "Shruti Tiwari"
 *
 */
class SongMultani extends AbstractSong {
	SongMultani() {
		super(Taal.EKTAAL);
		addAll(sthayiTaans());
	}

	private Collection<Taan> sthayiTaans() {
		List<Taan> sthatyiTaans = new ArrayList<Taan>();
		return sthatyiTaans;
	}
	
	@Override
	protected Collection<MusicalPhrase> antaraExtraLines(){
		ArrayList<MusicalPhrase> result = new ArrayList<MusicalPhrase>();
		result.add(antaraThirdLine());
		result.add(antaraFourthLine());
		return result;
	}
	
	@Override
	protected MusicalPhrase antaraFirstLine() {
		return new MusicalPhrase(beatsPerDivision).n(M_).e(P, 2).e(G_, 2).n(M_,P).e(N, 2).e(S3, 2).n(S3);
	}

	@Override
	protected MusicalPhrase antaraSecondLine() {
		return new MusicalPhrase(beatsPerDivision,2).n(N,S3,G3_,R3_).e(S3, 2).n(N,S3,N).couple(S3,N).n(D_,P);
	}

	protected MusicalPhrase antaraThirdLine() {
		return new MusicalPhrase(beatsPerDivision,2).n(M_).e(P, 2).e(G_, 2).n(M_,P,N).couple(S3,N).n(D_).e(P,2);
	}
	
	protected MusicalPhrase antaraFourthLine() {
		return sthayiSecondLine();
	}
	
	@Override
	protected MusicalPhrase sthayiFirstLine() {
		return new MusicalPhrase(beatsPerDivision).n(M_, G_,M_,P).couple(M_,P,D_,P).n(M_,G_,R_,S,N1,S);
	}

	@Override
	protected MusicalPhrase sthayiSecondLine() {
		return new MusicalPhrase(beatsPerDivision,2).n(S,N1,S).couple(S,M_).n(G_,M_).e(P, 2).couple(M_,P,D_,P).n(M_,G_);
	}
}
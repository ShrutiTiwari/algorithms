package com.aqua.music.model.raag.song;

import static com.aqua.music.model.core.ClassicalNote.D_;
import static com.aqua.music.model.core.ClassicalNote.G3_;
import static com.aqua.music.model.core.ClassicalNote.G_;
import static com.aqua.music.model.core.ClassicalNote.M_;
import static com.aqua.music.model.core.ClassicalNote.N;
import static com.aqua.music.model.core.ClassicalNote.R3_;
import static com.aqua.music.model.core.ClassicalNote.R_;
import static com.aqua.music.model.core.ClassicalNote.S;
import static com.aqua.music.model.core.ClassicalNote.S3;

import java.util.ArrayList;
import java.util.Collection;

import com.aqua.music.model.raag.MusicalPhrase;

public class SongGujariTodi extends AbstractSong{

	@Override
	protected Collection<MusicalPhrase> antaraExtraLines(){
		ArrayList<MusicalPhrase> result = new ArrayList<MusicalPhrase>();
		result.add(antaraThirdLine());
		return result;
	}
	@Override
	protected MusicalPhrase antaraFirstLine() {
		return new MusicalPhrase(beatsPerDivision).n(M_,M_,G_).couple(G_,D_).n(M_,D_,M_,D_).couple(D_,N).n(S3,S3,S3).couple(S3,N).n(R3_).e(S3, 2);
	}

	@Override
	protected MusicalPhrase antaraSecondLine() {
		return new MusicalPhrase(beatsPerDivision,2).n(D_,D_,N,N).e(S3, 2).n(S3,R3_).couple(S3,R3_).n(G3_,R3_,S3).couple(S3,N,R3_,S3).n(N,D_);
	}

	protected MusicalPhrase antaraThirdLine() {
		return new MusicalPhrase(beatsPerDivision).n(D_,G3_,R3_,S3).couple(S3,N,R3_,S3).n(N,D_).couple(S,R_,G_,M_,D_,N,S3,R3_,S3,N,D_,M_,G_,R_).n(S);
	}
	
	@Override
	protected MusicalPhrase sthayiFirstLine() {
		return new MusicalPhrase(beatsPerDivision).n(S,S,S,R_,G_).e(R_, 2).n(S,D_,M_,D_,N,D_,M_,G_,R_);
	}

	@Override
	protected MusicalPhrase sthayiSecondLine() {
		return new MusicalPhrase(beatsPerDivision).n(S,R_,G_,M_).couple(D_,N,D_,M_,D_,M_).n(D_).couple(D_,N).n(S3,N,D_,M_,G_,R_,S);
	}

}

package com.aqua.music.model.song;

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

class SongMultani extends AbstractSong {
	SongMultani(int beatDivison) {
		super(beatDivison);
		addAll(sthayiTaans());
	}

	private Collection<Taan> sthayiTaans() {
		List<Taan> sthatyiTaans = new ArrayList<Taan>();
		return sthatyiTaans;
	}
	
	@Override
	protected Collection<SongLine> antaraExtraLines(){
		ArrayList<SongLine> result = new ArrayList<SongLine>();
		result.add(antaraThirdLine());
		result.add(antaraFourthLine());
		return result;
	}
	
	@Override
	protected SongLine antaraFirstLine() {
		return new SongLine(beatDivison).normal(M_).extended(P, 2).extended(G_, 2).normal(M_,P).extended(N, 2).extended(S3, 2).normal(S3);
	}

	@Override
	protected SongLine antaraSecondLine() {
		return new SongLine(beatDivison,2).normal(N,S3,G3_,R3_).extended(S3, 2).normal(N,S3,N).couple(S3,N).normal(D_,P);
	}

	protected SongLine antaraThirdLine() {
		return new SongLine(beatDivison,2).normal(M_).extended(P, 2).extended(G_, 2).normal(M_,P,N).couple(S3,N).normal(D_).extended(P,2);
	}
	
	protected SongLine antaraFourthLine() {
		return sthayiSecondLine();
	}
	
	@Override
	protected SongLine sthayiFirstLine() {
		return new SongLine(beatDivison).normal(M_, G_,M_,P).couple(M_,P,D_,P).normal(M_,G_,R_,S,N1,S);
	}

	@Override
	protected SongLine sthayiSecondLine() {
		return new SongLine(beatDivison,2).normal(S,N1,S).couple(S,M_).normal(G_,M_).extended(P, 2).couple(M_,P,D_,P).normal(M_,G_);
	}
}
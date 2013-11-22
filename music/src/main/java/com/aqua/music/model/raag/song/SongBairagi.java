package com.aqua.music.model.raag.song;

import static com.aqua.music.model.core.ClassicalNote.M;
import static com.aqua.music.model.core.ClassicalNote.N_;
import static com.aqua.music.model.core.ClassicalNote.N1_;
import static com.aqua.music.model.core.ClassicalNote.P;
import static com.aqua.music.model.core.ClassicalNote.P1;
import static com.aqua.music.model.core.ClassicalNote.R3_;
import static com.aqua.music.model.core.ClassicalNote.R_;
import static com.aqua.music.model.core.ClassicalNote.S;
import static com.aqua.music.model.core.ClassicalNote.S3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class SongBairagi extends AbstractSong {
	SongBairagi(int beatDivison) {
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
		return result;
	}
	
	@Override
	protected SongLine antaraFirstLine() {
		return new SongLine(beatDivison).extended(M, 2).normal(M).couple(M,N_).normal(P, N_, N_,S3,S3,S3,S3,S3);
	}

	@Override
	protected SongLine antaraSecondLine() {
		return new SongLine(beatDivison,2).normal(S3,S3).couple(S3,N_,N_,S3).normal(R3_,N_, P).couple(P,N_,S3,R3_).extended(S3,3);
	}

	protected SongLine antaraThirdLine() {
		return new SongLine(beatDivison,2).normal(S3).couple(S3,N_,N_,S3).normal(R3_,R3_,N_,N_, P,M).couple(R_,M).normal(R_,S);
	}
	
	@Override
	protected SongLine sthayiFirstLine() {
		return new SongLine(beatDivison).normal(M, M, R_, R_, S, S, P1, N1_, R_).extended(S, 2).normal(R_);
	}

	protected SongLine sthayiFirstLineVariation() {
		return new SongLine(beatDivison).normal(M, M, R_, R_, S, S, P1, N1_, R_).extended(S, 2).normal(S);
	}

	@Override
	protected SongLine sthayiSecondLine() {
		return new SongLine(beatDivison).normal(S,R_,M, P).couple(M,P).normal(N_,N_,P,M).couple(R_,M).normal(R_, S);
	}
	@Override
	protected SongLine sthayiSecondLineVariation() {
		return new SongLine(beatDivison).normal(S,R_,M, P).couple(P,N_,S3,R3_).normal(S3,N_,P,M,R_,S);
	}
}
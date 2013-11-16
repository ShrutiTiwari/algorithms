package com.aqua.music.model.song;

import static com.aqua.music.model.core.ClassicalNote.P1;
import static com.aqua.music.model.core.ClassicalNote.D;
import static com.aqua.music.model.core.ClassicalNote.D1;
import static com.aqua.music.model.core.ClassicalNote.M;
import static com.aqua.music.model.core.ClassicalNote.M3_;
import static com.aqua.music.model.core.ClassicalNote.M_;
import static com.aqua.music.model.core.ClassicalNote.N;
import static com.aqua.music.model.core.ClassicalNote.N1;
import static com.aqua.music.model.core.ClassicalNote.P;
import static com.aqua.music.model.core.ClassicalNote.R;
import static com.aqua.music.model.core.ClassicalNote.R3;
import static com.aqua.music.model.core.ClassicalNote.S;
import static com.aqua.music.model.core.ClassicalNote.S3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class SongShudhSarang extends AbstractSong {
	SongShudhSarang(int beatDivison) {
		super(beatDivison);
		addAll(sthayiTaans());
	}

	private Collection<Taan> sthayiTaans() {
		List<Taan> sthatyiTaans = new ArrayList<Taan>();
		return sthatyiTaans;
	}

	@Override
	protected SongLine antaraFirstLine() {
		return new SongLine(beatDivison).normal(P, P).couple(P,N).normal(D,N).extended(S3, 2).normal(S3, S3, N, D).couple(N, R3).normal(S3,N, D, M_);
	}
	@Override
	protected SongLine antaraFirstLineVariation() {
		return new SongLine(beatDivison).normal(P, P).couple(P,N).normal(D,N).extended(S3, 2).normal(S3, S3).couple(N,S3,R3,M3_).normal(R3,S3).extended(N, 2).normal(D,P,M_);
	}

	@Override
	protected SongLine antaraSecondLine() {
		return new SongLine(beatDivison).normal(M_, M_, M_, P,M,R).couple(S,N1).normal(R).extended(S, 2).normal(N1,S,R,M_,P);
	}

	@Override
	protected SongLine sthayiFirstLine() {
		return new SongLine(beatDivison).normal(M, R, R).couple(S, N1, R, S).extended(N1, 2).normal(P1, N1).normal(D1, N1).couple(S, N1)
				.normal(R).extended(S, 3);
	}
	@Override
	protected SongLine sthayiFirstLineVariation() {
		return new SongLine(beatDivison).normal(M, R, R).couple(S, N1, R, S).extended(N1, 2).normal(P1, N1).normal(D1, N1).couple(S, N1)
				.normal(R).extended(S, 2);
	}

	@Override
	protected SongLine sthayiSecondLine() {
		return new SongLine(beatDivison).normal(N1, S, M, R,M_).extended(P, 2).normal(D,M_,P).extended(M, 2).normal(R,S,N1,R,S);
	}

}
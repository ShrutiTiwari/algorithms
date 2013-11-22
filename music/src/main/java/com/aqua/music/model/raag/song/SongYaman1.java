package com.aqua.music.model.raag.song;

import static com.aqua.music.model.core.ClassicalNote.D;
import static com.aqua.music.model.core.ClassicalNote.G;
import static com.aqua.music.model.core.ClassicalNote.G3;
import static com.aqua.music.model.core.ClassicalNote.M_;
import static com.aqua.music.model.core.ClassicalNote.N;
import static com.aqua.music.model.core.ClassicalNote.P;
import static com.aqua.music.model.core.ClassicalNote.R;
import static com.aqua.music.model.core.ClassicalNote.R3;
import static com.aqua.music.model.core.ClassicalNote.S;
import static com.aqua.music.model.core.ClassicalNote.S3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class SongYaman1 extends AbstractSong {
	SongYaman1(int beatDivison) {
		super(beatDivison);
		addAll(sthayiTaans());
	}

	private Collection<Taan> sthayiTaans() {
		List<Taan> sthatyiTaans = new ArrayList<Taan>();
		return sthatyiTaans;
	}

	@Override
	protected SongLine antaraFirstLine() {
		return new SongLine(beatDivison).normal( P, P, S3, S3).extended(S3, 2).normal(S3,S3,N,D).couple(N,R3).normal(S3).couple(N,D).normal(N,P,P);
	}

	@Override
	protected SongLine antaraSecondLine() {
		return new SongLine(beatDivison).normal(P,G3,R3,S3).couple(N,D).normal(N,P,P).couple(M_, N).couple(D, N).extended(P, 2).normal(R, R, S, S);
	}

	@Override
	protected SongLine sthayiFirstLine() {
		return new SongLine(beatDivison).couple(M_, N).couple(D, N).extended(P, 2).normal(R, R, S, S, G, R, G).extended(G, 3).normal(G, M_);
	}

	@Override
	protected SongLine sthayiSecondLine() {
		return new SongLine(beatDivison,2).normal(G, M_, G, P, M_, D, M_, P, S3, N, P, P, R, R, S, S);
	}

}
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

import com.aqua.music.model.raag.MusicalPhrase;

class SongYaman1 extends AbstractSong {
	SongYaman1() {
		super();
		addAll(sthayiTaans());
	}

	private Collection<Taan> sthayiTaans() {
		List<Taan> sthatyiTaans = new ArrayList<Taan>();
		return sthatyiTaans;
	}

	@Override
	protected MusicalPhrase antaraFirstLine() {
		return new MusicalPhrase(beatsPerDivision).n( P, P, S3, S3).e(S3, 2).n(S3,S3,N,D).couple(N,R3).n(S3).couple(N,D).n(N,P,P);
	}

	@Override
	protected MusicalPhrase antaraSecondLine() {
		return new MusicalPhrase(beatsPerDivision).n(P,G3,R3,S3).couple(N,D).n(N,P,P).couple(M_, N).couple(D, N).e(P, 2).n(R, R, S, S);
	}

	@Override
	protected MusicalPhrase sthayiFirstLine() {
		return new MusicalPhrase(beatsPerDivision).couple(M_, N).couple(D, N).e(P, 2).n(R, R, S, S, G, R, G).e(G, 3).n(G, M_);
	}

	@Override
	protected MusicalPhrase sthayiSecondLine() {
		return new MusicalPhrase(beatsPerDivision,2).n(G, M_, G, P, M_, D, M_, P, S3, N, P, P, R, R, S, S);
	}

}
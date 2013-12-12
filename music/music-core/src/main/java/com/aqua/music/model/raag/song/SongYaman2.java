package com.aqua.music.model.raag.song;

import static com.aqua.music.model.core.ClassicalNote.D;
import static com.aqua.music.model.core.ClassicalNote.G;
import static com.aqua.music.model.core.ClassicalNote.G3;
import static com.aqua.music.model.core.ClassicalNote.M;
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

import com.aqua.music.model.raag.MusicalPhrase;

/**
 * @author "Shruti Tiwari"
 * 
 */
class SongYaman2 extends AbstractSong {
	SongYaman2() {
		super();
		addAll(sthayiTaans());
	}

	@Override
	protected MusicalPhrase antaraFirstLine() {
		return new MusicalPhrase(beatsPerDivision).n(P, P, S3, S3).e(S3, 2).n(S3, S3, N, D).couple(N, R3).n(S3).couple(N, D).n(N, P, P);
	}

	@Override
	protected MusicalPhrase antaraSecondLine() {
		return new MusicalPhrase(beatsPerDivision).n(P, G3, R3, S3).couple(N, D).n(N, P, P).couple(M_, N).couple(D, N).e(P, 2)
				.n(R, R, S, S);
	}

	@Override
	protected MusicalPhrase sthayiFirstLine() {
		return new MusicalPhrase(beatsPerDivision).couple (N,R,G, M_,P).couple(D,P).n(R, R).e(S, 2).n(G, R, G, G);
	}

	@Override
	protected MusicalPhrase sthayiSecondLine() {
		return new MusicalPhrase(beatsPerDivision).couple (N,R,G, M_,P).e(R,2).n( R).e(S, 2).n(G, R, G, G);
	}

	private Collection<Taan> sthayiTaans() {
		List<Taan> sthatyiTaans = new ArrayList<Taan>();
		sthatyiTaans.add(new Taan().couple(M, D, P, M_, G, R, S));
		sthatyiTaans.add(new Taan().couple(N1, R, G, M_).stress().couple(M_, D, P, M_, G, R, S));
		sthatyiTaans.add(new Taan().couple(N, N, D, P).stress().couple(M_, D, P, M_, G, R, S));
		sthatyiTaans.add(new Taan().couple(N1, R, G, M_, D, N, R3, G3, R3, S3).stress().couple(N, D, P, M_, G, R));
		sthatyiTaans.add(new Taan().couple(N1, R, G, R, G, M_, G, M_, P, M_).stress().couple(N, D, P, M_, G, R));
		sthatyiTaans.add(new Taan().couple(N1, R, G, M_, P, M_, G, R).stress().couple(G, M_, D, N, S3, N, D, P).stress()
				.couple(M_, D, N, R3, G3, R3, S3, N).stress().couple(D, P, M_, G, R, S, N1, S));
		sthatyiTaans.add(new Taan().couple(G, R, G, M_, P, M_, G, R, S).stress().couple(P, M_, G, R, S).stress()
				.couple(N1, R, G, M_, P, M_).stress().couple(S3, N, D, P, M_, G, R, S).stress()
				.couple(S3, N, R3, S3, N, D, P, M_, G, R, S, N1, R, S));
		return sthatyiTaans;
	}
}
package com.aqua.music.model.raag.song;

import static com.aqua.music.model.core.ClassicalNote.D;
import static com.aqua.music.model.core.ClassicalNote.D1;
import static com.aqua.music.model.core.ClassicalNote.G3_;
import static com.aqua.music.model.core.ClassicalNote.G_;
import static com.aqua.music.model.core.ClassicalNote.M;
import static com.aqua.music.model.core.ClassicalNote.N1_;
import static com.aqua.music.model.core.ClassicalNote.N_;
import static com.aqua.music.model.core.ClassicalNote.P;
import static com.aqua.music.model.core.ClassicalNote.P1;
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
class SongBhimpalasi extends AbstractSong {
	SongBhimpalasi() {
		super();
		addAll(sthayiTaans());
	}

	private Collection<Taan> sthayiTaans() {
		List<Taan> sthatyiTaans = new ArrayList<Taan>();
		sthatyiTaans.add(new Taan().couple(S, G_, M, P).stress().couple(N_, D, P).stress().couple(S3, N_, D, P, M, G_, R, N1_, S));
		sthatyiTaans.add(new Taan().couple(N1_, S, G_, M, P, N_).stress().couple(S3, N_, D, P, M, G_, R, S, N1_, S));
		sthatyiTaans.add(new Taan().couple(G_, M, P).stress().couple(M, P, N_).stress().couple(P, N_, S3).stress().couple(S, G_, M)
				.stress().couple(N1_, S, G_).stress().couple(P1, N1_, S));
		sthatyiTaans.add(new Taan().couple(S, G_, M, N1_, S, G_, R, S).stress().couple(R, S, N1_, D1, P1).stress().couple(P1, N1_, S)
				.stress().couple(P1, N1_, S).stress().couple(P1, N1_, S).stress());
		sthatyiTaans.add(new Taan().couple(M, P).stress().couple(G_, M, P).stress().couple(S, G_, M, P).stress().couple(N1_, S, G_, M, P)
				.stress().couple(P1, N1_, S, G_, M, P).stress());

		sthatyiTaans.add(new Taan().couple(S, G_, M, P, M, G_, R, S).stress().couple(N_, D, P, M, G_, R, S).stress()
				.couple(P, N_, S3, P, S3, N_, D, P).stress().couple(M, G_, R, S, N1_, N1_, S));
		sthatyiTaans.add(new Taan().couple(S3, N_, D, P, M, G_, R, S).stress().couple(N1_, S).stress().couple(P1, N1_, S, G_, M, P)
				.stress().couple(N_, D, P).stress().couple(S3, N_, D, P).stress().couple(R3, S3, N_, D, P).couple(M, G_, R, S));

		// ANTARA TAANS
		sthatyiTaans.add(new Taan().couple(S3, N_, D, P).stress().couple(M, P, G_, M).stress().couple(P, N_, S3));
		sthatyiTaans.add(new Taan().couple(G3_, R3, S3, N_, D, P).stress().couple(M, P, G_, M).stress().couple(P, N_, S3));

		sthatyiTaans.add(new Taan().couple(P, N_, S3, G3_, R3, S3, N_, D, P, M, P).stress().couple(M, G_, R, S).stress()
				.couple(N1_, S, G_, M, P, N_, S3).stress().couple(N1_, S, G_, M, P, N_, S3).stress().couple(N1_, S, G_, M, P, N_, S3));
		return sthatyiTaans;
	}

	@Override
	protected MusicalPhrase antaraFirstLine() {
		return new MusicalPhrase(beatsPerDivision).n(G_, M, P, N_, P, N_, S3, S3).e(S3, 2).couple(P, S3, N_, R3).n(S3, G3_, R3, S3);
	}

	@Override
	protected MusicalPhrase antaraSecondLine() {
		return new MusicalPhrase(beatsPerDivision).couple(R3, S3).n(N_).e(D, 2).e(P, 2).n(P, P, M, P).couple(M, P).n(D, P, G_)
				.couple(S, R, N1_, S);
	}

	@Override
	protected MusicalPhrase sthayiFirstLine() {
		return new MusicalPhrase(beatsPerDivision).couple(S, G_, M, P).n(G_, G_, R, R, S, S).couple(R, S).n(N1_, S, M, M, M).couple(P, M)
				.n(G_);
	}

	@Override
	protected MusicalPhrase sthayiSecondLine() {
		return new MusicalPhrase(beatsPerDivision).n(G_, M, P, S3, N_).e(D, 2).n(P, M, P).couple(M, P).couple(D, P).n(P, G_).couple(S, R)
				.couple(N1_, S);
	}

}
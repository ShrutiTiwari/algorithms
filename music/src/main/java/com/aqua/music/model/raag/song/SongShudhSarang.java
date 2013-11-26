package com.aqua.music.model.raag.song;

import static com.aqua.music.model.core.ClassicalNote.D;
import static com.aqua.music.model.core.ClassicalNote.D1;
import static com.aqua.music.model.core.ClassicalNote.M;
import static com.aqua.music.model.core.ClassicalNote.M3_;
import static com.aqua.music.model.core.ClassicalNote.M_;
import static com.aqua.music.model.core.ClassicalNote.N;
import static com.aqua.music.model.core.ClassicalNote.N1;
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
class SongShudhSarang extends AbstractSong {
	SongShudhSarang() {
		super();
		addAll(sthayiTaans());
	}

	private Collection<Taan> sthayiTaans() {
		List<Taan> sthatyiTaans = new ArrayList<Taan>();
		return sthatyiTaans;
	}

	@Override
	protected MusicalPhrase antaraFirstLine() {
		return new MusicalPhrase(beatsPerDivision).n(P, P).couple(P,N).n(D,N).e(S3, 2).n(S3, S3, N, D).couple(N, R3).n(S3,N, D, M_);
	}
	@Override
	protected MusicalPhrase antaraFirstLineVariation() {
		return new MusicalPhrase(beatsPerDivision).n(P, P).couple(P,N).n(D,N).e(S3, 2).n(S3, S3).couple(N,S3,R3,M3_).n(R3,S3).e(N, 2).n(D,P,M_);
	}

	@Override
	protected MusicalPhrase antaraSecondLine() {
		return new MusicalPhrase(beatsPerDivision).n(M_, M_, M_, P,M,R).couple(S,N1).n(R).e(S, 2).n(N1,S,R,M_,P);
	}

	@Override
	protected MusicalPhrase sthayiFirstLine() {
		return new MusicalPhrase(beatsPerDivision).n(M, R, R).couple(S, N1, R, S).e(N1, 2).n(P1, N1).n(D1, N1).couple(S, N1)
				.n(R).e(S, 3);
	}
	@Override
	protected MusicalPhrase sthayiFirstLineVariation() {
		return new MusicalPhrase(beatsPerDivision).n(M, R, R).couple(S, N1, R, S).e(N1, 2).n(P1, N1).n(D1, N1).couple(S, N1)
				.n(R).e(S, 2);
	}

	@Override
	protected MusicalPhrase sthayiSecondLine() {
		return new MusicalPhrase(beatsPerDivision).n(N1, S, M, R,M_).e(P, 2).n(D,M_,P).e(M, 2).n(R,S,N1,R,S);
	}

}
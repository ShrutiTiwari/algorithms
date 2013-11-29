package com.aqua.music.model.raag.song;

import static com.aqua.music.model.core.ClassicalNote.D;
import static com.aqua.music.model.core.ClassicalNote.G;
import static com.aqua.music.model.core.ClassicalNote.M;
import static com.aqua.music.model.core.ClassicalNote.N;
import static com.aqua.music.model.core.ClassicalNote.N1;
import static com.aqua.music.model.core.ClassicalNote.N_;
import static com.aqua.music.model.core.ClassicalNote.P;
import static com.aqua.music.model.core.ClassicalNote.R;
import static com.aqua.music.model.core.ClassicalNote.R3;
import static com.aqua.music.model.core.ClassicalNote.S;
import static com.aqua.music.model.core.ClassicalNote.S3;

import java.util.ArrayList;
import java.util.Collection;

import com.aqua.music.model.raag.MusicalPhrase;

/**
 * @author "Shruti Tiwari"
 *
 */
public class SongKhamaj extends AbstractSong {
	@Override
	protected Collection<MusicalPhrase> antaraExtraLines() {
		Collection<MusicalPhrase> extraLines= new ArrayList<MusicalPhrase>();
		extraLines.add(antaraThirdLine());
		return extraLines;
	}
	
	@Override
	protected MusicalPhrase antaraFirstLine() {
		return new MusicalPhrase(beatsPerDivision).n(G, M, D, N, S3, N, S3, S3, P, N, S3, R3).couple(S3, N, R3, S3).n(N_).couple(D, P);
	}

	@Override
	protected MusicalPhrase antaraSecondLine() {
		return new MusicalPhrase(beatsPerDivision).n(P, N, S3, R3).couple(S3, N, R3, S3).n(N_).couple(D, P).n(G, G, G, M, R, R)
				.e(S, 2);
	}

	protected MusicalPhrase antaraThirdLine() {
		return new MusicalPhrase(beatsPerDivision).n(N1, S, G, M, P).couple(N_, D).n(M, P, N).couple(S3, N).n(R3, S3)
				.couple(S3, N, R3, S3).n(N_, D, P).couple(M, G).n(G, M);
	}

	@Override
	protected MusicalPhrase sthayiFirstLine() {
		return new MusicalPhrase(beatsPerDivision).n(P, D).couple(S3, N, R3, S3).n(N_, D, P).couple(M, G, P, M).n(G, R, G, M, G)
				.e(P, 2);
	}

	@Override
	protected MusicalPhrase sthayiFirstLineVariation() {
		return new MusicalPhrase(beatsPerDivision).n(P, D).couple(S3, N, R3, S3).n(N_, D, P).couple(M, G, P, M).n(G, R, G, M, G, P);
	}

	@Override
	protected MusicalPhrase sthayiSecondLine() {
		return new MusicalPhrase(beatsPerDivision).couple(P, D).n(G, M, P, D, S3, N_, D, P).couple(M, G, P, M).e(G, 2)
				.n(M, G, R, S, N1, S, G).couple(M, G).e(P, 2).e(P, 2).n(N1, S, G, M, P).couple(N_, D)
				.n(M, P, N, N).couple(S3, N).n(R3).couple(S3, N, R3, S3).n(N_, D).couple(S3, N, R3, S3).n(N_, D, P)
				.couple(M, G).n(G, M);
	}
}

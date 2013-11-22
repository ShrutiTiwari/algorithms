package com.aqua.music.model.raag;

import static com.aqua.music.model.core.ClassicalNote.D;
import static com.aqua.music.model.core.ClassicalNote.D1;
import static com.aqua.music.model.core.ClassicalNote.G_;
import static com.aqua.music.model.core.ClassicalNote.M;
import static com.aqua.music.model.core.ClassicalNote.N_;
import static com.aqua.music.model.core.ClassicalNote.N1_;
import static com.aqua.music.model.core.ClassicalNote.P;
import static com.aqua.music.model.core.ClassicalNote.P1;
import static com.aqua.music.model.core.ClassicalNote.R;
import static com.aqua.music.model.core.ClassicalNote.S;
import static com.aqua.music.model.core.ClassicalNote.S3;

public class RaagBhimpalasi extends AbstractRaag {

	@Override
	void initialize() {
		addArohiAvrohi(new MusicalPhrase().n(S, G_, M, P).e(N_, 2).e(S3, 2).n(S3, N_).e(D, 2).e(P, 2).e(M, 2).n(P).e(G_, 2).n(M, G_)
				.e(R, 2).n(S));

		addAlap(new MusicalPhrase().e(S, 4).n(S).e(N1_, 2).n(D1).e(P1, 3).n(P1).e(N1_, 2).e(S, 3));
		addAlap(new MusicalPhrase().n(P1, N1_, S).e(G_, 3).e(R, 2).n(N1_).e(S, 3));
		addAlap(new MusicalPhrase().n(N1_, S).e(M, 3).e(M, 2).e(G_, 3).n(M, G_).e(R, 2).e(N1_, 3).n(P1, N1_, N1_).e(S, 4));
	}
}

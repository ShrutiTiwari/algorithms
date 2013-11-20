package com.aqua.music.model.song;

import static com.aqua.music.model.core.ClassicalNote.G3;
import static com.aqua.music.model.core.ClassicalNote.G;
import static com.aqua.music.model.core.ClassicalNote.M;
import static com.aqua.music.model.core.ClassicalNote.N_;
import static com.aqua.music.model.core.ClassicalNote.N1_;
import static com.aqua.music.model.core.ClassicalNote.P;
import static com.aqua.music.model.core.ClassicalNote.R3_;
import static com.aqua.music.model.core.ClassicalNote.R_;
import static com.aqua.music.model.core.ClassicalNote.D;
import static com.aqua.music.model.core.ClassicalNote.S;
import static com.aqua.music.model.core.ClassicalNote.S3;

import java.util.ArrayList;
import java.util.Collection;

class SongAhirBhairav extends AbstractSong {
	SongAhirBhairav(int beatDivison) {
		super(beatDivison);
	}

	@Override
	protected Collection<SongLine> antaraExtraLines() {
		Collection<SongLine> result = new ArrayList<SongLine>();
		result.add(antaraFiFthLine());
		return result;
	}

	@Override
	protected SongLine antaraFirstLine() {
		return new SongLine(beatDivison).extended(M, 2).normal(M).couple(M,D).normal(P,D,N_).extended(S3, 2).normal(S3,S3,R3_).couple(S3,R3_).normal(G3,R3_,S3);
	}

	@Override
	protected SongLine antaraSecondLine() {
		return new SongLine(beatDivison, 2).normal(N_, D, M).couple(M,D).normal(P,D,N_).extended(S3, 2).normal(S3,S3,R3_).couple(S3,R3_).normal(G3,R3_,S3);
	}

	@Override
	protected SongLine sthayiFirstLine() {
		return commonFirstLine().couple(D, P);
	}

	private SongLine commonFirstLine() {
		return new SongLine(beatDivison).couple(D, S3, N_, D, P, M, G, R_, S, N1_).normal(R_, R_).extended(S, 2).normal(S, R_)
				.extended(G, 2).normal(M, M);
	}
	
	@Override
	protected SongLine sthayiFirstLineVariation() {
		return commonFirstLine().couple(M,G,P, M).extended(R_, 2).extended(S,2);
	}

	@Override
	protected SongLine sthayiSecondLine() {
		return new SongLine(beatDivison).normal(M, M).couple(D,P).normal(D,N_,S3,N_,R3_).couple(S3,N_,D,P).normal(M);
	}

	@Override
	protected SongLine sthayiSecondLineVariation() {
		return sthayiFirstLine();
	}

	private SongLine antaraFiFthLine() {
		return sthayiFirstLine();
	}
}
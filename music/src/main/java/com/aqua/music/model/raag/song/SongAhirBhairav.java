package com.aqua.music.model.raag.song;

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

	
	private static final SongLine commonPhrase = new SongLine(4).couple(D, P);
	
	private static final SongLine commonAntaraPhrase1 = new SongLine(4).couple(M, D).normal(P, D, N_).extended(S3, 2).normal(S3, S3, R3_)
			.couple(S3, R3_).normal(G3, R3_, S3);

	private static final SongLine commonSthayiPharase1 = new SongLine(4).couple(D, S3, N_, D, P, M);

	private static final SongLine commonSthayiPharase2 = new SongLine(4).add(commonSthayiPharase1).couple(G, R_, S, N1_)
			.normal(R_, R_);

	SongAhirBhairav(int beatDivison) {
		super(beatDivison);
	}

	@Override
	protected Collection<SongLine> antaraExtraLines() {
		Collection<SongLine> result = new ArrayList<SongLine>();
		result.add(antaraThirdLine());
		result.add(antaraFiFthLine());
		return result;
	}

	@Override
	protected SongLine antaraFirstLine() {
		return new SongLine(beatDivison).extended(M, 2).normal(M).add(commonAntaraPhrase1);
	}

	@Override
	protected SongLine antaraSecondLine() {
		return new SongLine(beatDivison).normal(N_, D, M).add(commonAntaraPhrase1);
	}

	protected SongLine antaraThirdLine() {
		return new SongLine(beatDivison).normal(S3, S3).extended(N_, 2).extended(D, 2).normal(M, M).couple(M, G, R_, G, M, P)
				.add(commonSthayiPharase1).couple(G, P, M, G, R_, S, N1_, R_).normal(S);
	}

	@Override
	protected SongLine connectorLine() {
		return new SongLine(beatDivison).add(commonSthayiPharase2).extended(S, 6);
	}

	@Override
	protected SongLine sthayiFirstLine() {
		return commonFirstLine().add(commonPhrase);
	}

	@Override
	protected SongLine sthayiFirstLineVariation() {
		return commonFirstLine().couple(M, G, P, M).extended(R_, 2).extended(S, 2);
	}

	@Override
	protected SongLine sthayiSecondLine() {
		return new SongLine(beatDivison).normal(M, M).add(commonPhrase).normal(D, N_, S3, N_, R3_).couple(S3, N_).add(commonPhrase).normal(M);
	}

	@Override
	protected SongLine sthayiSecondLineVariation() {
		return sthayiFirstLine();
	}

	private SongLine antaraFiFthLine() {
		return sthayiFirstLine();
	}

	private SongLine commonFirstLine() {
		return new SongLine(beatDivison).add(commonSthayiPharase2).extended(S, 2).normal(S, R_).extended(G, 2).normal(M, M);
	}
}
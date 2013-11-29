package com.aqua.music.model.raag.song;

import static com.aqua.music.model.core.ClassicalNote.D;
import static com.aqua.music.model.core.ClassicalNote.G;
import static com.aqua.music.model.core.ClassicalNote.G3;
import static com.aqua.music.model.core.ClassicalNote.M;
import static com.aqua.music.model.core.ClassicalNote.N1_;
import static com.aqua.music.model.core.ClassicalNote.N_;
import static com.aqua.music.model.core.ClassicalNote.P;
import static com.aqua.music.model.core.ClassicalNote.R3_;
import static com.aqua.music.model.core.ClassicalNote.R_;
import static com.aqua.music.model.core.ClassicalNote.S;
import static com.aqua.music.model.core.ClassicalNote.S3;

import java.util.ArrayList;
import java.util.Collection;

import com.aqua.music.model.raag.MusicalPhrase;

/**
 * @author "Shruti Tiwari"
 *
 */
class SongAhirBhairav extends AbstractSong {
	private static final MusicalPhrase commonAntaraPhrase1 = createNewMusicalPhrase().couple(M, D).n(P, D, N_).e(S3, 2)
			.n(S3, S3, R3_).couple(S3, R3_).n(G3, R3_, S3);

	private static final MusicalPhrase commonPhrase = createNewMusicalPhrase().couple(D, P);

	private static final MusicalPhrase commonSthayiPhrase1 = createNewMusicalPhrase().couple(D, S3, N_, D, P, M);

	private static final MusicalPhrase commonSthayiPharase2 = createNewMusicalPhrase().add(commonSthayiPhrase1).couple(G, R_, S, N1_)
			.n(R_, R_);

	@Override
	protected Collection<MusicalPhrase> antaraExtraLines() {
		Collection<MusicalPhrase> result = new ArrayList<MusicalPhrase>();
		result.add(antaraThirdLine());
		result.add(antaraFiFthLine());
		return result;
	}

	@Override
	protected MusicalPhrase antaraFirstLine() {
		return new MusicalPhrase(beatsPerDivision).e(M, 2).n(M).add(commonAntaraPhrase1);
	}

	@Override
	protected MusicalPhrase antaraSecondLine() {
		return new MusicalPhrase(beatsPerDivision).n(N_, D, M).add(commonAntaraPhrase1);
	}

	protected MusicalPhrase antaraThirdLine() {
		return new MusicalPhrase(beatsPerDivision).n(S3, S3).e(N_, 2).e(D, 2).n(M, M).couple(M, G, R_, G, M, P)
				.add(commonSthayiPhrase1).couple(G, P, M, G, R_, S, N1_, R_).n(S);
	}

	@Override
	protected MusicalPhrase connectorLine() {
		return new MusicalPhrase(beatsPerDivision).add(commonSthayiPharase2).e(S, 6);
	}

	@Override
	protected MusicalPhrase sthayiFirstLine() {
		return commonFirstLine().add(commonPhrase);
	}

	@Override
	protected MusicalPhrase sthayiFirstLineVariation() {
		return commonFirstLine().couple(M, G, P, M).e(R_, 2).e(S, 2);
	}

	@Override
	protected MusicalPhrase sthayiSecondLine() {
		return new MusicalPhrase(beatsPerDivision).n(M, M).add(commonPhrase).n(D, N_, S3, N_, R3_).couple(S3, N_).add(commonPhrase)
				.n(M);
	}

	@Override
	protected MusicalPhrase sthayiSecondLineVariation() {
		return sthayiFirstLine();
	}

	private MusicalPhrase antaraFiFthLine() {
		return sthayiFirstLine();
	}

	private MusicalPhrase commonFirstLine() {
		return new MusicalPhrase(beatsPerDivision).add(commonSthayiPharase2).e(S, 2).n(S, R_).e(G, 2).n(M, M);
	}

	static MusicalPhrase createNewMusicalPhrase() {
		return new MusicalPhrase(4);
	}
}
package com.aqua.music.model.raag.song;

import static com.aqua.music.model.core.ClassicalNote.D;
import static com.aqua.music.model.core.ClassicalNote.D1;
import static com.aqua.music.model.core.ClassicalNote.G;
import static com.aqua.music.model.core.ClassicalNote.G3;
import static com.aqua.music.model.core.ClassicalNote.M_;
import static com.aqua.music.model.core.ClassicalNote.N;
import static com.aqua.music.model.core.ClassicalNote.N1;
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
public class SongPuryaKalyan extends AbstractSong {
	private final static MusicalPhrase GMRG = new MusicalPhrase(4).n(G, M_, R_, G);
	private final static MusicalPhrase MDPMG = new MusicalPhrase(4).couple(M_, D).n(P, M_, G);
	private final static MusicalPhrase MDNDP_M = new MusicalPhrase(4).n(M_, D, N, D).e(P, 3).n(M_);
	private final static MusicalPhrase MDMG_MRGG_NRND_NRGRS = new MusicalPhrase(4).n(M_, D, M_, G, M_, R_, G, G, N1, R_, N1, D1).couple(N1, R_)
			.n(G, R_, S);
	private final static MusicalPhrase PMDPMG = new MusicalPhrase(4).couple(P, M_, D, P).n(M_, G);
	private final static MusicalPhrase MGRSS = new MusicalPhrase(4).n(M_, G, R_, S).e(S, 4);

	@Override
	protected Collection<MusicalPhrase> antaraExtraLines() {
		Collection<MusicalPhrase> result= new ArrayList<MusicalPhrase>();
		result.add(antaraThirdLine());
		result.add(antaraFourthLine());
		return result;
	}

	@Override
	protected MusicalPhrase antaraFirstLine() {
		return new MusicalPhrase(beatsPerDivision).n(M_, D, M_, D).couple(D, N).n(S3, S3, S3).e(S3, 2).n(S3, S3, N, R3_)
				.e(S3, 2);
	}

	protected MusicalPhrase antaraFourthLine() {
		return new MusicalPhrase(4).add(sthayiSecondLine());
	}

	@Override
	protected MusicalPhrase antaraSecondLine() {
		return new MusicalPhrase(beatsPerDivision,2).n(N, N, R3_).couple(G3, R3_).n(G3, R3_).e(S3, 3).n(N, R3_, N).couple(D, N)
				.n(D).couple(R3_, N).n(D, P);
	}

	protected MusicalPhrase antaraThirdLine() {
		return new MusicalPhrase(beatsPerDivision,2).n(R_, G, R_, G).couple(G, M_).e(P, 2).e(M_, 2).n(D, N).couple(D, N)
				.n(D).couple(R3_, N).n(D, P);
	}

	@Override
	protected MusicalPhrase sthayiFirstLine() {
		return new MusicalPhrase(beatsPerDivision).add(GMRG).add(MDPMG).add(MDNDP_M);
	}

	@Override
	protected MusicalPhrase sthayiFirstLineVariation() {
		return new MusicalPhrase(beatsPerDivision).add(GMRG).add(MDPMG).add(MDNDP_M).add(GMRG);
	}

	protected MusicalPhrase sthayiFourthLine() {
		return new MusicalPhrase(4).add(PMDPMG).add(MGRSS);
	}

	@Override
	protected MusicalPhrase sthayiSecondLine() {
		return new MusicalPhrase(4).add(MDMG_MRGG_NRND_NRGRS).add(PMDPMG).add(MDNDP_M).add(GMRG).add(PMDPMG).add(MGRSS);
	}

	@Override
	protected MusicalPhrase connectorLine() {
		return new MusicalPhrase(beatsPerDivision).add(sthayiFirstLine()).add(GMRG).add(MDPMG).add(MGRSS).e(S, 4);
	}
}

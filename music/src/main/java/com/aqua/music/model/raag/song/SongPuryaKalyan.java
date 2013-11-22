package com.aqua.music.model.raag.song;

import static com.aqua.music.model.core.ClassicalNote.D;
import static com.aqua.music.model.core.ClassicalNote.D1;
import static com.aqua.music.model.core.ClassicalNote.G;
import static com.aqua.music.model.core.ClassicalNote.G3;
import static com.aqua.music.model.core.ClassicalNote.M_;
import static com.aqua.music.model.core.ClassicalNote.N;
import static com.aqua.music.model.core.ClassicalNote.N1;
import static com.aqua.music.model.core.ClassicalNote.P;
import static com.aqua.music.model.core.ClassicalNote.R_;
import static com.aqua.music.model.core.ClassicalNote.R3_;
import static com.aqua.music.model.core.ClassicalNote.S;
import static com.aqua.music.model.core.ClassicalNote.S3;

import java.util.ArrayList;
import java.util.Collection;

public class SongPuryaKalyan extends AbstractSong {
	private final static SongLine GMRG = new SongLine(4).normal(G, M_, R_, G);
	private final static SongLine MDPMG = new SongLine(4).couple(M_, D).normal(P, M_, G);
	private final static SongLine MDNDP_M = new SongLine(4).normal(M_, D, N, D).extended(P, 3).normal(M_);
	private final static SongLine MDMG_MRGG_NRND_NRGRS = new SongLine(4).normal(M_, D, M_, G, M_, R_, G, G, N1, R_, N1, D1).couple(N1, R_)
			.normal(G, R_, S);
	private final static SongLine PMDPMG = new SongLine(4).couple(P, M_, D, P).normal(M_, G);
	private final static SongLine MGRSS = new SongLine(4).normal(M_, G, R_, S).extended(S, 4);

	public SongPuryaKalyan(int beatDivison) {
		super(beatDivison);
	}

	@Override
	protected Collection<SongLine> antaraExtraLines() {
		Collection<SongLine> result= new ArrayList<SongLine>();
		result.add(antaraThirdLine());
		result.add(antaraFourthLine());
		return result;
	}

	@Override
	protected SongLine antaraFirstLine() {
		return new SongLine(beatDivison).normal(M_, D, M_, D).couple(D, N).normal(S3, S3, S3).extended(S3, 2).normal(S3, S3, N, R3_)
				.extended(S3, 2);
	}

	protected SongLine antaraFourthLine() {
		return new SongLine(4).add(sthayiSecondLine());
	}

	@Override
	protected SongLine antaraSecondLine() {
		return new SongLine(beatDivison,2).normal(N, N, R3_).couple(G3, R3_).normal(G3, R3_).extended(S3, 3).normal(N, R3_, N).couple(D, N)
				.normal(D).couple(R3_, N).normal(D, P);
	}

	protected SongLine antaraThirdLine() {
		return new SongLine(beatDivison,2).normal(R_, G, R_, G).couple(G, M_).extended(P, 2).extended(M_, 2).normal(D, N).couple(D, N)
				.normal(D).couple(R3_, N).normal(D, P);
	}

	@Override
	protected SongLine sthayiFirstLine() {
		return new SongLine(beatDivison).add(GMRG).add(MDPMG).add(MDNDP_M);
	}

	@Override
	protected SongLine sthayiFirstLineVariation() {
		return new SongLine(beatDivison).add(GMRG).add(MDPMG).add(MDNDP_M).add(GMRG);
	}

	protected SongLine sthayiFourthLine() {
		return new SongLine(4).add(PMDPMG).add(MGRSS);
	}

	@Override
	protected SongLine sthayiSecondLine() {
		return new SongLine(4).add(MDMG_MRGG_NRND_NRGRS).add(PMDPMG).add(MDNDP_M).add(GMRG).add(PMDPMG).add(MGRSS);
	}

	@Override
	protected SongLine connectorLine() {
		return new SongLine(beatDivison).add(sthayiFirstLine()).add(GMRG).add(MDPMG).add(MGRSS).extended(S, 4);
	}
}

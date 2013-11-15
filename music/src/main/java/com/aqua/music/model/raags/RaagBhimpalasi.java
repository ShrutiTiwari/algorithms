package com.aqua.music.model.raags;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static com.aqua.music.model.core.ClassicalNote.*;
class RaagBhimpalasi extends AbstractRaag {
	RaagBhimpalasi(int beatDivison) {
		super(beatDivison);
		addAll(sthayiTaans());
	}

	private Collection<Taan> sthayiTaans() {
		List<Taan> sthatyiTaans = new ArrayList<Taan>();
		return sthatyiTaans;
	}

	@Override
	protected SongLine antaraFirstLine() {
		return new SongLine(beatDivison).normal(G_, M, P, N_, P, N_, S3, S3).extended(S3, 2).couple(P, S3, N_, R3)
				.normal(S3, G3_, R3, S3);
	}

	@Override
	protected SongLine antaraSecondLine() {
		return new SongLine(beatDivison).couple(R3, S3).normal(N_).extended(D, 2).extended(P, 2).normal(P, P, M, P)
				.couple(M, P).normal(D, P, G_).couple(S, R, N1_, S);
	}

	@Override
	protected SongLine sthayiFirstLine() {
		return new SongLine(beatDivison).couple(S, G_, M, P).normal(G_, G_, R, R, S, S).couple(R, S)
				.normal(N1_, S, M, M, M).couple(P, M).normal(G_);
	}

	@Override
	protected SongLine sthayiSecondLine() {
		return new SongLine(beatDivison).normal(G_, M, P, S3, N_).extended(D, 2).normal(P, M, P).couple(M, P).couple(D, P)
				.normal(P, G_).couple(S, R).couple(N1_, S);
	}

}
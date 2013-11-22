package com.aqua.music.model.raag.song;

import static com.aqua.music.model.core.ClassicalNote.D_;
import static com.aqua.music.model.core.ClassicalNote.D1_;
import static com.aqua.music.model.core.ClassicalNote.G;
import static com.aqua.music.model.core.ClassicalNote.G3;
import static com.aqua.music.model.core.ClassicalNote.M;
import static com.aqua.music.model.core.ClassicalNote.N;
import static com.aqua.music.model.core.ClassicalNote.N1;
import static com.aqua.music.model.core.ClassicalNote.P;
import static com.aqua.music.model.core.ClassicalNote.R3_;
import static com.aqua.music.model.core.ClassicalNote.R_;
import static com.aqua.music.model.core.ClassicalNote.S;
import static com.aqua.music.model.core.ClassicalNote.S3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class SongBhairav extends AbstractSong {
	SongBhairav(int beatDivison) {
		super(beatDivison);
		addAll(sthayiTaans());
	}

	@Override
	protected Collection<SongLine> antaraExtraLines() {
		Collection<SongLine> result = new ArrayList<SongLine>();
		result.add(antaraThirdLine());
		result.add(antaraFourthLine());
		result.add(antaraFiFthLine());
		return result;
	}

	@Override
	protected SongLine antaraFirstLine() {
		return new SongLine(beatDivison).extended(M, 2).extended(P, 2).extended(D_, 2).extended(N, 2).extended(S3, 2).normal(S3)
				.extended(S3, 2).normal(S3, S3, S3);
	}

	@Override
	protected SongLine antaraSecondLine() {
		return new SongLine(beatDivison, 2).normal(D_, D_, N, N, S3, S3).extended(S3, 2).normal(R3_).extended(S3, 2).normal(N, S3)
				.extended(D_, 2).normal(P);
	}

	@Override
	protected SongLine sthayiFirstLine() {
		return new SongLine(beatDivison).normal(P, M).extended(R_, 2).extended(S, 3).normal(R_, N1, S).extended(M, 4).normal(G, M);
	}

	@Override
	protected SongLine sthayiFirstLineVariation() {
		return new SongLine(beatDivison).normal(P, M).extended(R_, 2).extended(S, 3).normal(R_, N1, S).extended(M, 4).normal(G)
				.extended(M, 3);
	}

	@Override
	protected SongLine sthayiSecondLine() {
		return sthayiSecondLineCommonPart().extended(M, 2);
	}

	private SongLine sthayiSecondLineCommonPart() {
		return new SongLine(beatDivison).extended(M, 2).normal(M).couple(M, G).extended(P, 2).normal(P, D_, S3, N, D_, P, M, M);
	}

	@Override
	protected SongLine sthayiSecondLineVariation() {
		return sthayiSecondLineCommonPart();
	}

	private SongLine antaraFiFthLine() {
		return sthayiFirstLine();
	}

	private SongLine antaraFourthLine() {
		return commonAnataraThirdLinePart().extended(R_, 2);
	}

	private SongLine antaraThirdLine() {
		return commonAnataraThirdLinePart().extended(R_, 4);
	}

	private SongLine commonAnataraThirdLinePart() {
		return new SongLine(beatDivison).extended(S3, 2).normal(N, S3).extended(D_, 2).couple(P, M).normal(P, M, M, G, M);
	}

	private Collection<Taan> sthayiTaans() {
		List<Taan> sthatyiTaans = new ArrayList<Taan>();
		sthatyiTaans.add(new Taan().couple(S, R_, G, M, P, D_).stress().couple(P, M, G, R_, S));
		sthatyiTaans.add(new Taan().couple(G, M).stress().couple(P, G, M, R_).stress().couple(G, R_, S, N1, S, S));
		sthatyiTaans.add(new Taan().couple(S, R_, G, M).stress().couple(R_, G, M, P).stress().couple(G, R_, S));
		sthatyiTaans.add(new Taan().couple(D_, P).stress().couple(M, P, D_, N).stress().couple(D_, P, M, G, R_, S));
		sthatyiTaans.add(new Taan().couple(S, R_, G, M, P, D_, N, S3).stress().couple(N, D_, P, M, G, R_, S));
		sthatyiTaans.add(new Taan().couple(S, R_,G).couple( R_, G, M).stress().couple(G, M, P, M).stress().couple(G, R_, G, R_, S));
		sthatyiTaans.add(new Taan().couple(D_, P, M, P).stress().couple(D_, G, M, P).stress().couple(R_, G, M, G).stress()
				.couple(R_, G, R_, S));
		sthatyiTaans.add(new Taan().couple(S, R_, G, M, P, D_, N, S3).stress().couple(R3_, G3, R3_, S3).stress()
				.couple(N, D_, P, M, G, R_, S));
		sthatyiTaans.add(new Taan().couple(S, R_, G, M, P, D_, N, S3).stress().couple(R3_, G3, R3_, S3).stress().couple(N, D_, P, M)
				.couple(G, R_, S, G, R_, S, G, R_, S));
		sthatyiTaans.add(new Taan().couple(S, R_, G, M, P, M, G, M).stress().couple(P, D_, N, D_, P, D_, N, S3).stress()
				.couple(R3_, G3, R3_, S3).stress().couple(N, D_, P, M, G, R_, S, N1, D1_, N1, S, R_, G, R_, S));

		sthatyiTaans.add(new Taan().couple(D_, P, M, P).stress().couple(D_, N, S3, N).stress().couple(D_, P, M, G, R_, S).stress()
				.couple(S3, N, R3_, S3, N, D_, P, M, G, R_, S, R_, S));

		return sthatyiTaans;
	}
}
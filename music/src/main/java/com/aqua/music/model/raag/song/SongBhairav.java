package com.aqua.music.model.raag.song;

import static com.aqua.music.model.core.ClassicalNote.D1_;
import static com.aqua.music.model.core.ClassicalNote.D_;
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

import com.aqua.music.model.raag.MusicalPhrase;

/**
 * @author "Shruti Tiwari"
 *
 */
class SongBhairav extends AbstractSong {
	SongBhairav() {
		super();
		addAll(sthayiTaans());
	}

	@Override
	protected Collection<MusicalPhrase> antaraExtraLines() {
		Collection<MusicalPhrase> result = new ArrayList<MusicalPhrase>();
		result.add(antaraThirdLine());
		result.add(antaraFourthLine());
		result.add(antaraFiFthLine());
		return result;
	}

	@Override
	protected MusicalPhrase antaraFirstLine() {
		return new MusicalPhrase(beatsPerDivision).e(M, 2).e(P, 2).e(D_, 2).e(N, 2).e(S3, 2).n(S3)
				.e(S3, 2).n(S3, S3, S3);
	}

	@Override
	protected MusicalPhrase antaraSecondLine() {
		return new MusicalPhrase(beatsPerDivision, 2).n(D_, D_, N, N, S3, S3).e(S3, 2).n(R3_).e(S3, 2).n(N, S3)
				.e(D_, 2).n(P);
	}

	@Override
	protected MusicalPhrase sthayiFirstLine() {
		return new MusicalPhrase(beatsPerDivision).n(P, M).e(R_, 2).e(S, 3).n(R_, N1, S).e(M, 4).n(G, M);
	}

	@Override
	protected MusicalPhrase sthayiFirstLineVariation() {
		return new MusicalPhrase(beatsPerDivision).n(P, M).e(R_, 2).e(S, 3).n(R_, N1, S).e(M, 4).n(G)
				.e(M, 3);
	}

	@Override
	protected MusicalPhrase sthayiSecondLine() {
		return sthayiSecondLineCommonPart().e(M, 2);
	}

	private MusicalPhrase sthayiSecondLineCommonPart() {
		return new MusicalPhrase(beatsPerDivision).e(M, 2).n(M).couple(M, G).e(P, 2).n(P, D_, S3, N, D_, P, M, M);
	}

	@Override
	protected MusicalPhrase sthayiSecondLineVariation() {
		return sthayiSecondLineCommonPart();
	}

	private MusicalPhrase antaraFiFthLine() {
		return sthayiFirstLine();
	}

	private MusicalPhrase antaraFourthLine() {
		return commonAnataraThirdLinePart().e(R_, 2);
	}

	private MusicalPhrase antaraThirdLine() {
		return commonAnataraThirdLinePart().e(R_, 4);
	}

	private MusicalPhrase commonAnataraThirdLinePart() {
		return new MusicalPhrase(beatsPerDivision).e(S3, 2).n(N, S3).e(D_, 2).couple(P, M).n(P, M, M, G, M);
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
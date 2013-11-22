package com.aqua.music.model.raag.song;

import static com.aqua.music.model.core.ClassicalNote.M;
import static com.aqua.music.model.core.ClassicalNote.N1_;
import static com.aqua.music.model.core.ClassicalNote.N_;
import static com.aqua.music.model.core.ClassicalNote.P;
import static com.aqua.music.model.core.ClassicalNote.P1;
import static com.aqua.music.model.core.ClassicalNote.R3_;
import static com.aqua.music.model.core.ClassicalNote.R_;
import static com.aqua.music.model.core.ClassicalNote.S;
import static com.aqua.music.model.core.ClassicalNote.S3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.aqua.music.model.raag.MusicalPhrase;
import com.aqua.music.model.raag.Taal;

class SongBairagi extends AbstractSong {

	private static final MusicalPhrase commonPhrase1 = new MusicalPhrase(4).couple(S3, N_, N_, S3);
	private static final MusicalPhrase commonPhrase2 = new MusicalPhrase(4).n(M, M, R_, R_, S, S, P1, N1_, R_);
	private static final MusicalPhrase commonPhrase3 = new MusicalPhrase(4).n(S, R_, M, P);

	SongBairagi() {
		super(Taal.EKTAAL);
		addAll(sthayiTaans());
	}

	private Collection<Taan> sthayiTaans() {
		List<Taan> sthatyiTaans = new ArrayList<Taan>();
		return sthatyiTaans;
	}

	@Override
	protected Collection<MusicalPhrase> antaraExtraLines() {
		ArrayList<MusicalPhrase> result = new ArrayList<MusicalPhrase>();
		result.add(antaraThirdLine());
		return result;
	}

	@Override
	protected MusicalPhrase antaraFirstLine() {
		return new MusicalPhrase(beatsPerDivision).e(M, 2).n(M).couple(M, N_).n(P, N_, N_, S3, S3, S3, S3, S3);
	}

	@Override
	protected MusicalPhrase antaraSecondLine() {
		return new MusicalPhrase(beatsPerDivision, 2).n(S3, S3).add(commonPhrase1).n(R3_, N_, P).couple(P, N_, S3, R3_)
				.e(S3, 3);
	}

	protected MusicalPhrase antaraThirdLine() {
		return new MusicalPhrase(beatsPerDivision, 2).n(S3).add(commonPhrase1).n(R3_, R3_, N_, N_, P, M).couple(R_, M).n(R_, S);
	}

	@Override
	protected MusicalPhrase sthayiFirstLine() {
		return new MusicalPhrase(beatsPerDivision).add(commonPhrase2).e(S, 2).n(R_);
	}

	protected MusicalPhrase sthayiFirstLineVariation() {
		return new MusicalPhrase(beatsPerDivision).add(commonPhrase2).e(S, 2).n(S);
	}

	@Override
	protected MusicalPhrase sthayiSecondLine() {
		return new MusicalPhrase(beatsPerDivision).add(commonPhrase3).couple(M, P).n(N_, N_, P, M).couple(R_, M).n(R_, S);
	}

	@Override
	protected MusicalPhrase sthayiSecondLineVariation() {
		return new MusicalPhrase(beatsPerDivision).add(commonPhrase3).couple(P, N_, S3, R3_).n(S3, N_, P, M, R_, S);
	}

	@Override
	protected MusicalPhrase connectorLine() {
		return sthayiFirstLine();
	}
}
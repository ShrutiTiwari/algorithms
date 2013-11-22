package com.aqua.music.model.raag.song;

import static com.aqua.music.model.core.ClassicalNote.D;
import static com.aqua.music.model.core.ClassicalNote.D1;
import static com.aqua.music.model.core.ClassicalNote.G;
import static com.aqua.music.model.core.ClassicalNote.G3;
import static com.aqua.music.model.core.ClassicalNote.P;
import static com.aqua.music.model.core.ClassicalNote.R;
import static com.aqua.music.model.core.ClassicalNote.R3;
import static com.aqua.music.model.core.ClassicalNote.S;
import static com.aqua.music.model.core.ClassicalNote.S3;

import java.util.ArrayList;
import java.util.Collection;

import com.aqua.music.model.raag.MusicalPhrase;

public class SongBhopali extends AbstractSong {
	@Override
	protected Collection<MusicalPhrase> antaraExtraLines() {
		Collection<MusicalPhrase> extraLines= new ArrayList<MusicalPhrase>();
		extraLines.add(antaraThirdLine());
		return extraLines;
	}
	
	@Override
	protected MusicalPhrase antaraFirstLine() {
		return new MusicalPhrase(beatsPerDivision).n(G, P, D, S3, D).e(S3, 2).e(S3, 2).e(S3, 2).n(D, S3, D, P, G);
	}

	@Override
	protected MusicalPhrase antaraSecondLine() {
		return new MusicalPhrase(beatsPerDivision).n(G, R, S, R, P, G, P, P, D, P, G, R, G, P, D).e(S3,2);
	}
	
	protected MusicalPhrase antaraThirdLine() {
		return new MusicalPhrase(beatsPerDivision).couple(P,D).n(S3, R3, G3,R3, S3, D, S3,D,P,G,R).couple(G,P,D,S3,D,P,G,R,S,D1);
	}

	@Override
	protected MusicalPhrase sthayiFirstLine() {
		return new MusicalPhrase(beatsPerDivision).n(S, R).e(G, 4).n(G, R).couple(G, D).n(P).e(R, 4).n(S, D1);
	}

	@Override
	protected MusicalPhrase sthayiFirstLineVariation() {
		return new MusicalPhrase(beatsPerDivision).n(S, R).e(G, 4).n(G, R).couple(G, D).n(P).e(G, 4).n(G, R);
	}

	@Override
	protected MusicalPhrase sthayiSecondLine() {
		return new MusicalPhrase(beatsPerDivision).n(G, P, D).e(S3, 2).n(S3, D, P, G, R).couple(G, P, D, S3, D, P, G, R)
				.n(S, D1);
	}
}

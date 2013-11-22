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

public class SongBhopali extends AbstractSong {
	public SongBhopali(int beatDivison) {
		super(beatDivison);
	}

	@Override
	protected Collection<SongLine> antaraExtraLines() {
		Collection<SongLine> extraLines= new ArrayList<SongLine>();
		extraLines.add(antaraThirdLine());
		return extraLines;
	}
	
	@Override
	protected SongLine antaraFirstLine() {
		return new SongLine(beatDivison).normal(G, P, D, S3, D).extended(S3, 2).extended(S3, 2).extended(S3, 2).normal(D, S3, D, P, G);
	}

	@Override
	protected SongLine antaraSecondLine() {
		return new SongLine(beatDivison).normal(G, R, S, R, P, G, P, P, D, P, G, R, G, P, D).extended(S3,2);
	}
	
	protected SongLine antaraThirdLine() {
		return new SongLine(beatDivison).couple(P,D).normal(S3, R3, G3,R3, S3, D, S3,D,P,G,R).couple(G,P,D,S3,D,P,G,R,S,D1);
	}

	@Override
	protected SongLine sthayiFirstLine() {
		return new SongLine(beatDivison).normal(S, R).extended(G, 4).normal(G, R).couple(G, D).normal(P).extended(R, 4).normal(S, D1);
	}

	@Override
	protected SongLine sthayiFirstLineVariation() {
		return new SongLine(beatDivison).normal(S, R).extended(G, 4).normal(G, R).couple(G, D).normal(P).extended(G, 4).normal(G, R);
	}

	@Override
	protected SongLine sthayiSecondLine() {
		return new SongLine(beatDivison).normal(G, P, D).extended(S3, 2).normal(S3, D, P, G, R).couple(G, P, D, S3, D, P, G, R)
				.normal(S, D1);
	}
}

package com.aqua.music.model.raag.song;

import static com.aqua.music.model.core.ClassicalNote.D;
import static com.aqua.music.model.core.ClassicalNote.G;
import static com.aqua.music.model.core.ClassicalNote.M;
import static com.aqua.music.model.core.ClassicalNote.N;
import static com.aqua.music.model.core.ClassicalNote.N1;
import static com.aqua.music.model.core.ClassicalNote.N_;
import static com.aqua.music.model.core.ClassicalNote.P;
import static com.aqua.music.model.core.ClassicalNote.R;
import static com.aqua.music.model.core.ClassicalNote.R3;
import static com.aqua.music.model.core.ClassicalNote.S;
import static com.aqua.music.model.core.ClassicalNote.S3;

import java.util.ArrayList;
import java.util.Collection;

public class SongKhamaj extends AbstractSong {
	public SongKhamaj(int beatDivison) {
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
		return new SongLine(beatDivison).normal(G, M, D, N, S3, N, S3, S3, P, N, S3, R3).couple(S3, N, R3, S3).normal(N_).couple(D, P);
	}

	@Override
	protected SongLine antaraSecondLine() {
		return new SongLine(beatDivison).normal(P, N, S3, R3).couple(S3, N, R3, S3).normal(N_).couple(D, P).normal(G, G, G, M, R, R)
				.extended(S, 2);
	}

	protected SongLine antaraThirdLine() {
		return new SongLine(beatDivison).normal(N1, S, G, M, P).couple(N_, D).normal(M, P, N).couple(S3, N).normal(R3, S3)
				.couple(S3, N, R3, S3).normal(N_, D, P).couple(M, G).normal(G, M);
	}

	@Override
	protected SongLine sthayiFirstLine() {
		return new SongLine(beatDivison).normal(P, D).couple(S3, N, R3, S3).normal(N_, D, P).couple(M, G, P, M).normal(G, R, G, M, G)
				.extended(P, 2);
	}

	@Override
	protected SongLine sthayiFirstLineVariation() {
		return new SongLine(beatDivison).normal(P, D).couple(S3, N, R3, S3).normal(N_, D, P).couple(M, G, P, M).normal(G, R, G, M, G, P);
	}

	@Override
	protected SongLine sthayiSecondLine() {
		return new SongLine(beatDivison).couple(P, D).normal(G, M, P, D, S3, N_, D, P).couple(M, G, P, M).extended(G, 2)
				.normal(M, G, R, S, N1, S, G).couple(M, G).extended(P, 2).extended(P, 2).normal(N1, S, G, M, P).couple(N_, D)
				.normal(M, P, N, N).couple(S3, N).normal(R3).couple(S3, N, R3, S3).normal(N_, D).couple(S3, N, R3, S3).normal(N_, D, P)
				.couple(M, G).normal(G, M);
	}
}

package com.aqua.music.controller.songs;

import static com.aqua.music.model.Frequency.ClassicalNote.D_;
import static com.aqua.music.model.Frequency.ClassicalNote.G3_;
import static com.aqua.music.model.Frequency.ClassicalNote.G_;
import static com.aqua.music.model.Frequency.ClassicalNote.M;
import static com.aqua.music.model.Frequency.ClassicalNote.N1_;
import static com.aqua.music.model.Frequency.ClassicalNote.N_;
import static com.aqua.music.model.Frequency.ClassicalNote.P;
import static com.aqua.music.model.Frequency.ClassicalNote.R;
import static com.aqua.music.model.Frequency.ClassicalNote.R3;
import static com.aqua.music.model.Frequency.ClassicalNote.S;
import static com.aqua.music.model.Frequency.ClassicalNote.S3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RaagJaunpuri extends AbstractSong {
	RaagJaunpuri(int beatDivison) {
		super(beatDivison);
		addAll(sthayiTaans());
	}

	@Override
	protected SongLine antaraFirstLine() {
		return new SongLine(beatDivison);
	}

	@Override
	protected SongLine antaraSecondLine() {
		return new SongLine(beatDivison);
	}

	@Override
	protected SongLine sthayiFirstLine() {
		return new SongLine(beatDivison).normal(P,M,P,S3,D_,D_).couple(P, M).normal(P,G_,G_,R,S,R,M).extended(P,2);
	}

	@Override
	protected SongLine sthayiSecondLine() {
		return new SongLine(beatDivison).normal(M,M,P,P,D_,D_,S3,S3).couple(S3,R3).normal(G3_,R3,S3,N_,S3,D_,M);
	}
	
	private Collection<Taan> sthayiTaans() {
		List<Taan> sthatyiTaans = new ArrayList<Taan>();
		sthatyiTaans.add(new Taan().couple(S, R, M, P, D_, P).stress().couple(D_, P, M, G_, R, S, N1_, N1_, S, S));
		sthatyiTaans.add(new Taan().couple(M, P, D_).stress().couple(R, M, P).stress().couple(S, R, M, P).stress()
				.couple(D_, P, M, G_, R, S));
		sthatyiTaans.add(new Taan().couple(S, R, M, P, D_, N_).stress().couple(S3, N_, D_, P, M, G_, R, S, N1_, S));
		sthatyiTaans.add(new Taan().couple(S, R, M).stress().couple(R, M, P).stress().couple(M, P, D_, P).stress()
				.couple(N_, D_, P, M, G_, R, S));
		sthatyiTaans.add(new Taan().couple(M, G_, R, S).stress().couple(D_, P, N_, D_).stress()
				.couple(S3, N_, D_, P, M, G_, R, S));
		sthatyiTaans.add(new Taan().couple(M, G_, R, S).stress().couple(D_, P, M).stress().couple(N_, D_).stress()
				.couple(S3, N_, D_, P, M, G_, R, S));
		sthatyiTaans.add(new Taan().couple(S, R, M, P, D_, N_, S3, R3).stress().couple(G3_, R3, S3, N_, D_, P, M, P));

		return sthatyiTaans;
	}

}

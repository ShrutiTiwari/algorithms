package com.aqua.music.model.raag.song;

import static com.aqua.music.model.core.ClassicalNote.D_;
import static com.aqua.music.model.core.ClassicalNote.G3_;
import static com.aqua.music.model.core.ClassicalNote.G_;
import static com.aqua.music.model.core.ClassicalNote.M;
import static com.aqua.music.model.core.ClassicalNote.N1_;
import static com.aqua.music.model.core.ClassicalNote.N_;
import static com.aqua.music.model.core.ClassicalNote.P;
import static com.aqua.music.model.core.ClassicalNote.R;
import static com.aqua.music.model.core.ClassicalNote.R3;
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
public class SongJaunpuri extends AbstractSong {
	SongJaunpuri() {
		super();
		addAll(sthayiTaans());
	}

	@Override
	protected MusicalPhrase antaraFirstLine() {
		return new MusicalPhrase(beatsPerDivision);
	}

	@Override
	protected MusicalPhrase antaraSecondLine() {
		return new MusicalPhrase(beatsPerDivision);
	}

	@Override
	protected MusicalPhrase sthayiFirstLine() {
		return new MusicalPhrase(beatsPerDivision).n(P,M,P,S3,D_,D_).couple(P, M).n(P,G_,G_,R,S,R,M).e(P,2);
	}

	@Override
	protected MusicalPhrase sthayiSecondLine() {
		return new MusicalPhrase(beatsPerDivision,2).n(M,M,P,P,D_,D_,S3,S3).couple(S3,R3).n(G3_,R3,S3,N_,S3,D_,M);
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

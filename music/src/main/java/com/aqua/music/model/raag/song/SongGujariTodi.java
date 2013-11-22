package com.aqua.music.model.raag.song;

import static com.aqua.music.model.core.ClassicalNote.D_;
import static com.aqua.music.model.core.ClassicalNote.G3_;
import static com.aqua.music.model.core.ClassicalNote.G_;
import static com.aqua.music.model.core.ClassicalNote.M_;
import static com.aqua.music.model.core.ClassicalNote.N;
import static com.aqua.music.model.core.ClassicalNote.R3_;
import static com.aqua.music.model.core.ClassicalNote.R_;
import static com.aqua.music.model.core.ClassicalNote.S;
import static com.aqua.music.model.core.ClassicalNote.S3;

import java.util.ArrayList;
import java.util.Collection;

public class SongGujariTodi extends AbstractSong{

	public SongGujariTodi(int beatDivison) {
		super(beatDivison);
	}

	@Override
	protected Collection<SongLine> antaraExtraLines(){
		ArrayList<SongLine> result = new ArrayList<SongLine>();
		result.add(antaraThirdLine());
		return result;
	}
	@Override
	protected SongLine antaraFirstLine() {
		return new SongLine(beatDivison).normal(M_,M_,G_).couple(G_,D_).normal(M_,D_,M_,D_).couple(D_,N).normal(S3,S3,S3).couple(S3,N).normal(R3_).extended(S3, 2);
	}

	@Override
	protected SongLine antaraSecondLine() {
		return new SongLine(beatDivison,2).normal(D_,D_,N,N).extended(S3, 2).normal(S3,R3_).couple(S3,R3_).normal(G3_,R3_,S3).couple(S3,N,R3_,S3).normal(N,D_);
	}

	protected SongLine antaraThirdLine() {
		return new SongLine(beatDivison).normal(D_,G3_,R3_,S3).couple(S3,N,R3_,S3).normal(N,D_).couple(S,R_,G_,M_,D_,N,S3,R3_,S3,N,D_,M_,G_,R_).normal(S);
	}
	
	@Override
	protected SongLine sthayiFirstLine() {
		return new SongLine(beatDivison).normal(S,S,S,R_,G_).extended(R_, 2).normal(S,D_,M_,D_,N,D_,M_,G_,R_);
	}

	@Override
	protected SongLine sthayiSecondLine() {
		return new SongLine(beatDivison).normal(S,R_,G_,M_).couple(D_,N,D_,M_,D_,M_).normal(D_).couple(D_,N).normal(S3,N,D_,M_,G_,R_,S);
	}

}

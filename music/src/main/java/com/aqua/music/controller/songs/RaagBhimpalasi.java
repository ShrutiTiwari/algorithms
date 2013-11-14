package com.aqua.music.controller.songs;

import static com.aqua.music.model.Frequency.ClassicalNote.DHA;
import static com.aqua.music.model.Frequency.ClassicalNote.GA3_;
import static com.aqua.music.model.Frequency.ClassicalNote.GA_;
import static com.aqua.music.model.Frequency.ClassicalNote.MA;
import static com.aqua.music.model.Frequency.ClassicalNote.NI1_;
import static com.aqua.music.model.Frequency.ClassicalNote.NI_;
import static com.aqua.music.model.Frequency.ClassicalNote.PA;
import static com.aqua.music.model.Frequency.ClassicalNote.RE;
import static com.aqua.music.model.Frequency.ClassicalNote.RE3;
import static com.aqua.music.model.Frequency.ClassicalNote.SA;
import static com.aqua.music.model.Frequency.ClassicalNote.SA3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class RaagBhimpalasi extends AbstractSong {
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
		return new SongLine(beatDivison).normal(GA_, MA, PA, NI_, PA, NI_, SA3, SA3).extended(SA3, 2).couple(PA, SA3, NI_, RE3)
				.normal(SA3, GA3_, RE3, SA3);
	}

	@Override
	protected SongLine antaraSecondLine() {
		return new SongLine(beatDivison).couple(RE3, SA3).normal(NI_).extended(DHA, 2).extended(PA, 2).normal(PA, PA, MA, PA)
				.couple(MA, PA).normal(DHA, PA, GA_).couple(SA, RE, NI1_, SA);
	}

	@Override
	protected SongLine sthayiFirstLine() {
		return new SongLine(beatDivison).couple(SA, GA_, MA, PA).normal(GA_, GA_, RE, RE, SA, SA).couple(RE, SA)
				.normal(NI1_, SA, MA, MA, MA).couple(PA, MA).normal(GA_);
	}

	@Override
	protected SongLine sthayiSecondLine() {
		return new SongLine(beatDivison).normal(GA_, MA, PA, SA3, NI_).extended(DHA, 2).normal(PA, MA, PA).couple(MA, PA).couple(DHA, PA)
				.normal(PA, GA_).couple(SA, RE).couple(NI1_, SA);
	}

}
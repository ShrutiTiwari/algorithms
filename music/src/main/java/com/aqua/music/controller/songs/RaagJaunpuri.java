package com.aqua.music.controller.songs;

import static com.aqua.music.model.Frequency.ClassicalNote.DHA_;
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
		return new SongLine(beatDivison).normal(PA,MA,PA,SA3,DHA_,DHA_).couple(PA, MA).normal(PA,GA_,GA_,RE,SA,RE,MA).extended(PA,2);
	}

	@Override
	protected SongLine sthayiSecondLine() {
		return new SongLine(beatDivison).normal(MA,MA,PA,PA,DHA_,DHA_,SA3,SA3).couple(SA3,RE3).normal(GA3_,RE3,SA3,NI_,SA3,DHA_,MA);
	}
	
	private Collection<Taan> sthayiTaans() {
		List<Taan> sthatyiTaans = new ArrayList<Taan>();
		sthatyiTaans.add(new Taan().couple(SA, RE, MA, PA, DHA_, PA).stress().couple(DHA_, PA, MA, GA_, RE, SA, NI1_, NI1_, SA, SA));
		sthatyiTaans.add(new Taan().couple(MA, PA, DHA_).stress().couple(RE, MA, PA).stress().couple(SA, RE, MA, PA).stress()
				.couple(DHA_, PA, MA, GA_, RE, SA));
		sthatyiTaans.add(new Taan().couple(SA, RE, MA, PA, DHA_, NI_).stress().couple(SA3, NI_, DHA_, PA, MA, GA_, RE, SA, NI1_, SA));
		sthatyiTaans.add(new Taan().couple(SA, RE, MA).stress().couple(RE, MA, PA).stress().couple(MA, PA, DHA_, PA).stress()
				.couple(NI_, DHA_, PA, MA, GA_, RE, SA));
		sthatyiTaans.add(new Taan().couple(MA, GA_, RE, SA).stress().couple(DHA_, PA, NI_, DHA_).stress()
				.couple(SA3, NI_, DHA_, PA, MA, GA_, RE, SA));
		sthatyiTaans.add(new Taan().couple(MA, GA_, RE, SA).stress().couple(DHA_, PA, MA).stress().couple(NI_, DHA_).stress()
				.couple(SA3, NI_, DHA_, PA, MA, GA_, RE, SA));
		sthatyiTaans.add(new Taan().couple(SA, RE, MA, PA, DHA_, NI_, SA3, RE3).stress().couple(GA3_, RE3, SA3, NI_, DHA_, PA, MA, PA));

		return sthatyiTaans;
	}

}

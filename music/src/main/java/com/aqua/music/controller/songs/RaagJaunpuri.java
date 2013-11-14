package com.aqua.music.controller.songs;

import static com.aqua.music.model.Frequency.ClassicalNote.RE3;
import static com.aqua.music.model.Frequency.ClassicalNote.*;

public class RaagJaunpuri extends AbstractSong {
	RaagJaunpuri(int beatDivison) {
		super(beatDivison);
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
}

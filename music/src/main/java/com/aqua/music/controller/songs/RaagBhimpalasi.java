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

class RaagBhimpalasi extends AbstractSong {
	RaagBhimpalasi() {
		super();
	}

	protected SongLine antaraFirstLine() {
		SongLine songLines = new SongLine();
		songLines.addMoreFrquencies(GA_, MA, PA, NI_, PA, NI_, SA3, SA3);
		songLines.addExtendedNotes(SA3, 2);
		songLines.addCoupleNotes(PA, SA3);
		songLines.addCoupleNotes(NI_, RE3);
		songLines.addMoreFrquencies(SA3, GA3_, RE3, SA3);
		return songLines;
	}

	protected SongLine antaraSecondLine() {
		SongLine songLines = new SongLine();
		songLines.addCoupleNotes(RE3, SA3);
		songLines.addMoreFrquencies(NI_);
		songLines.addExtendedNotes(DHA, 2);
		songLines.addExtendedNotes(PA, 2);
		songLines.addMoreFrquencies(PA, PA, MA, PA);
		songLines.addCoupleNotes(MA, PA);
		songLines.addMoreFrquencies(DHA, PA, GA_);
		songLines.addCoupleNotes(SA, RE);
		songLines.addCoupleNotes(NI1_, SA);
		return songLines;
	}

	protected SongLine sthayiFirstLine() {
		SongLine songLines = new SongLine();
		songLines.addCoupleNotes(SA, GA_);
		songLines.addCoupleNotes(MA, PA);
		songLines.addMoreFrquencies(GA_, GA_, RE, RE, SA, SA);
		songLines.addCoupleNotes(RE, SA);
		songLines.addMoreFrquencies(NI1_, SA, MA, MA, MA);
		songLines.addCoupleNotes(PA, MA);
		songLines.addMoreFrquencies(GA_);
		return songLines;
	}

	protected SongLine sthayiSecondLine() {
		SongLine songLines = new SongLine();
		songLines.addMoreFrquencies(GA_, MA, PA, SA3, NI_);
		songLines.addExtendedNotes(DHA, 2);
		songLines.addMoreFrquencies(PA, MA, PA);
		songLines.addCoupleNotes(MA, PA);
		songLines.addCoupleNotes(DHA, PA);
		songLines.addMoreFrquencies(PA, GA_);
		songLines.addCoupleNotes(SA, RE);
		songLines.addCoupleNotes(NI1_, SA);
		return songLines;
	}
}
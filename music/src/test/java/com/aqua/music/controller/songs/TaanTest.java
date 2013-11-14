package com.aqua.music.controller.songs;

import static com.aqua.music.model.Frequency.ClassicalNote.DHA_;
import static com.aqua.music.model.Frequency.ClassicalNote.GA_;
import static com.aqua.music.model.Frequency.ClassicalNote.MA;
import static com.aqua.music.model.Frequency.ClassicalNote.NI1_;
import static com.aqua.music.model.Frequency.ClassicalNote.NI_;
import static com.aqua.music.model.Frequency.ClassicalNote.PA;
import static com.aqua.music.model.Frequency.ClassicalNote.RE;
import static com.aqua.music.model.Frequency.ClassicalNote.RE3;
import static com.aqua.music.model.Frequency.ClassicalNote.SA;
import static com.aqua.music.model.Frequency.ClassicalNote.SA3;
import static com.aqua.music.model.Frequency.ClassicalNote.GA3_;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.aqua.music.bo.audio.manager.AudioLifeCycleManager;
import com.aqua.music.bo.audio.manager.AudioPlayConfig;

public class TaanTest {

	@Test
	public void testJaunpuriTaans() {
		List<Taan> taans = new ArrayList<Taan>();
		taans.add(new Taan().couple(SA, RE, MA, PA, DHA_, PA).stress().couple(DHA_, PA, MA, GA_, RE, SA, NI1_, NI1_, SA, SA));
		taans.add(new Taan().couple(MA, PA, DHA_).stress().couple(RE, MA, PA).stress().couple(SA, RE, MA, PA).stress()
				.couple(DHA_, PA, MA, GA_, RE, SA));
		taans.add(new Taan().couple(SA, RE, MA, PA, DHA_, NI_).stress().couple(SA3, NI_, DHA_, PA, MA, GA_, RE, SA, NI1_, SA));
		taans.add(new Taan().couple(SA, RE, MA).stress().couple(RE, MA, PA).stress().couple(MA, PA, DHA_, PA).stress()
				.couple(NI_, DHA_, PA, MA, GA_, RE, SA));
		taans.add(new Taan().couple(MA, GA_, RE, SA).stress().couple(DHA_, PA, NI_, DHA_).stress()
				.couple(SA3, NI_, DHA_, PA, MA, GA_, RE, SA));
		taans.add(new Taan().couple(MA, GA_, RE, SA).stress().couple(DHA_, PA, MA).stress().couple(NI_, DHA_).stress()
				.couple(SA3, NI_, DHA_, PA, MA, GA_, RE, SA));
		taans.add(new Taan().couple(SA, RE, MA, PA, DHA_, NI_, SA3, RE3).stress().couple(GA3_, RE3, SA3, NI_, DHA_, PA, MA, PA));

		for (Taan each : taans) {
			// Taan playtaan = taans.get(taans.size() - 1);
			Taan playtaan = each;
			print(playtaan.printText());
			for (int i = 0; i < 2; i++) {
				AudioLifeCycleManager.instance.play(playtaan.frequencies(), AudioPlayConfig.SYNCHRONOUS_DYNAMIC_PLAYER);
			}
		}
	}

	private void print(String printText) {
		System.out.println(printText);
	}
}

package com.shruti.music;

import static com.shruti.music.Playable.BaseNotes.*;

public interface ArohiAvrohiPlayer {
	public String name();

	public void playAarohi();

	public void playAarohiAvrohi();

	public void playAvrohi();

	public void playSequence(String sequence);

	public String type();

	public enum AllThaat implements ArohiAvrohiPlayer {
		BHAIRAV(RE_, GA, MA, PA, DHA_, NI),
		PURVI(RE_, GA, MA_, PA, DHA_, NI),
		MARWA(RE_, GA, MA_, PA, DHA, NI),
		KALYAN(RE, GA, MA_, PA, DHA, NI),
		BILAWAL(RE, GA, MA, PA, DHA, NI),
		KHAMAJ(RE, GA, MA, PA, DHA, NI_),
		KAFI(RE, GA_, MA, PA, DHA, NI_),
		ASAVARI(RE, GA_, MA, PA, DHA_, NI_),
		BHAIRAVI(RE_, GA_, MA, PA, DHA_, NI_),
		TODI(RE_, GA_, MA_, PA, DHA_, NI);

		private final Playable[] aarohiNotes;
		private final Playable[] avrohiNotes;

		private AllThaat(Playable... aarohiNotes) {
			this.aarohiNotes = aarohiNotes;
			this.avrohiNotes = Util.reverse(aarohiNotes);
		}

		public void playAarohi() {
			Util.play(SA, aarohiNotes, HIGH_SA);
		}

		public void playAarohiAvrohi() {
			Util.play(SA, aarohiNotes, HIGH_SA, avrohiNotes);
		}

		public void playAvrohi() {
			Util.play(HIGH_SA, avrohiNotes, SA);
		}

		public void playSequence(String sequence) {
			new Sequence(sequence).playSequence();
		}

		public String type() {
			return "THAAT";
		}

		public class Sequence {
			private final String delim="-";

			private Playable[] ascSequ;
			private Playable[] descSequ;

			private Sequence(String expression) {
				createPattern(expression);
			}

			void playSequence(){
				NotePlayer.playArray(ascSequ, descSequ);
			}

			private void createPattern(String expression) {
				String[] splitExp=expression.split(delim);
				int numOfNotesInPattern= splitExp.length;
				System.out.println(numOfNotesInPattern);
				ascSequ=createSequence(SA, HIGH_SA, aarohiNotes, numOfNotesInPattern);
				descSequ=createSequence( HIGH_SA, SA, avrohiNotes, numOfNotesInPattern);
			}

			private Playable[] createSequence(BaseNotes firstNote, BaseNotes lastNote, Playable[] seqNotes, int numOfNotesInPattern) {
				int numOfDistinctNotes = aarohiNotes.length+2;
				int totalNotesInSeq= (numOfDistinctNotes-numOfNotesInPattern)*numOfNotesInPattern+2;
				System.out.println(totalNotesInSeq);

				Playable[] resultSequence=new Playable[totalNotesInSeq];

				/*resultSequence[0]=firstNote;
				resultSequence[1]=seqNotes[0];
				 */

				int k=0;
				for (int i=0; i<numOfDistinctNotes-1;i++){
					for(int j=0; j<numOfNotesInPattern;j++){
						if(i==0){
							if(j==0){
								resultSequence[k++]=firstNote;
							}else{
								resultSequence[k++]=seqNotes[j-1];
							}
						}else{
							try{
								resultSequence[k++]=seqNotes[i+j];
							}catch (Exception e) {

								resultSequence[k-1]=lastNote;
							}
						}
						System.out.println((k-1)+"."+resultSequence[k-1]);
					}
				}
				return resultSequence;
			}
		}
	}

	public enum Pattern implements ArohiAvrohiPlayer {
		Khamaj(  NI_),
		Bilawal(  NI),
		Dha(DHA);
		private final Playable[] aarohiNotes;
		private final Playable[] avrohiNotes;

		private Pattern(Playable... aarohiNotes) {
			this.aarohiNotes = aarohiNotes;
			this.avrohiNotes = Util.reverse(aarohiNotes);
		}

		
		public void playAarohi() {
			Util.play(PA, aarohiNotes, HIGH_SA);
		}

		
		public void playAarohiAvrohi() {
			playAarohi();
			playAvrohi();
		}
		
		public void playAvrohi() {
			Util.play(HIGH_SA, avrohiNotes, PA);
		}

		
		public void playSequence(String sequence) {
			// TODO Auto-generated method stub

		}

		
		public String type() {
			return "PATTERN";
		}
	}

	public enum SecondYearRaag implements ArohiAvrohiPlayer {
		GUJARI_TODI(RE_, GA_, MA_, DHA_, NI),
		BAIRAGI(RE_, MA, PA, NI_),
		SHUDH_SARANG(sequence(RE, MA_, PA, NI), sequence(NI, DHA, PA, MA_, PA, MA, RE)),
		YAMAN(sequence(RE, GA, MA_, DHA, NI), sequence(NI, DHA, PA, MA_, GA, RE)),
		PURYA_KALYAN(sequence(RE_, GA, MA_, PA, MA_, DHA, NI), sequence(NI, DHA, PA, DHA, MA_, PA, GA, MA_, RE_, GA, RE_)),
		MULTANI(sequence(GA_, MA_, PA, NI), sequence(NI, DHA_, PA, MA_, GA_, RE_));

		private final Playable[] aarohiNotes;
		private final Playable[] avrohiNotes;

		private SecondYearRaag(Playable... aarohiNotes) {
			this.aarohiNotes = aarohiNotes;
			this.avrohiNotes = Util.reverse(aarohiNotes);
		}

		private SecondYearRaag(Playable[] aarohiNotes, Playable[] avrohiNotes) {
			this.aarohiNotes = aarohiNotes;
			this.avrohiNotes = avrohiNotes;
		}

		private static Playable[] sequence(Playable... notes) {
			return notes;
		}

		
		public void playAarohi() {
			Util.play(SA, aarohiNotes, HIGH_SA);
		}

		
		public void playAarohiAvrohi() {
			playAarohi();
			playAvrohi();
		}
		
		public void playAvrohi() {
			Util.play(HIGH_SA, avrohiNotes, SA);
		}

		
		public void playSequence(String sequence) {
			// TODO Auto-generated method stub

		}

		
		public String type() {
			return "RAAG";
		}
	}

	static abstract class Util {
		static void play(Playable start, Playable[] middleNotes, Playable end) {
			NotePlayer.play(start,middleNotes,end);
		}

		static void play(Playable start, Playable[] arohiNotes, Playable end, Playable[] avrohiNotes) {
			NotePlayer.play(start,arohiNotes,end, avrohiNotes);
		}

		static Playable[] reverse(Playable... aarohiNotes) {
			int count = aarohiNotes.length;
			Playable[] avrohi = new Playable[count];
			for (int i = 0; i < count; i++) {
				avrohi[i] = aarohiNotes[count - i - 1];
			}
			return avrohi;
		}

		static Playable[] sequence(Playable... notes) {
			return notes;
		}
	}
}
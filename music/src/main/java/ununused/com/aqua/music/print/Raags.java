package ununused.com.aqua.music.print;

import static ununused.com.aqua.music.print.RaagProperties.Time.Afternoon;
import static ununused.com.aqua.music.print.RaagProperties.Time.Evening;
import static ununused.com.aqua.music.print.RaagProperties.Time.Morning;
import static ununused.com.aqua.music.print.RaagProperties.WriteableThaat.Bhairav;
import static ununused.com.aqua.music.print.RaagProperties.WriteableThaat.Kafi;
import static ununused.com.aqua.music.print.RaagProperties.WriteableThaat.Kalyan;
import static ununused.com.aqua.music.print.RaagProperties.WriteableThaat.Khamaj;
import static ununused.com.aqua.music.print.RaagProperties.WriteableThaat.Marva;
import static ununused.com.aqua.music.print.RaagProperties.WriteableThaat.Todi;

import java.util.Collection;
import java.util.LinkedHashSet;

import ununused.com.aqua.music.print.RaagProperties.Time;
import ununused.com.aqua.music.print.RaagProperties.WriteableThaat;

public class Raags {
	private static Collection<Raag> raags = new LinkedHashSet<Raag>();

	static void populate() {
		// 1-yr
		raags.add(new Raag("Bhairav", KeyNotes.with("Re_", "Dha_"), Morning, Bhairav, "sampurn"));
		raags.add(new Raag("Yaman", KeyNotes.with("Ga", "Ni"), Evening, Kalyan, "Shadav-sampurn"));
		raags.add(new Raag("Bhopali", KeyNotes.with("Ga", "Dha"), Evening, Kalyan, "Shadav-sampurn"));
		raags.add(new Raag("Ahir-Bhairav", KeyNotes.with("Ma", "Sa"), Morning, Bhairav, "Shadav-sampurn"));
		raags.add(new Raag("Khamaj", KeyNotes.with("Ga", "Ni"), Evening, Khamaj, "Shadav-sampurn"));

		// 2-yr
		raags.add(new Raag("Multani", KeyNotes.with("Ma", "Sa"), Afternoon, Todi, "Audav-sampurn"));
		raags.add(new Raag("Gujari-Todi", KeyNotes.with("Dha_", "Re_"), Morning, Todi, "Audav-audav"));
		raags.add(new Raag("Purya-Kalyan", KeyNotes.with("Ga", "Ni"), Evening, Marva, "Audav-audav"));
		raags.add(new Raag("Bairagi", KeyNotes.with("Re", ""), Morning, Bhairav, "Audav-audav"));
		raags.add(new Raag("Shudh-Sarang", KeyNotes.with("Sa", "Ma"), Afternoon, Kalyan, "audav-shadav"));

		// 3-yr
		raags.add(new Raag("Bhimpalasi", KeyNotes.with("Ga_", "Ni_"), Afternoon, Kafi, "sampurn"));
	}

	public static void main(String[] args) {
		Raags.populate();
		RaagsPrinterWithSortAbility.printSortByCriterio(raags, RaagProperties.Time.class);
		RaagsPrinterWithSortAbility.printSortByCriterio(raags, RaagProperties.WriteableThaat.class);
	}

	static class Raag {
		final RaagProperties raagProperties;

		Raag(String raagName, KeyNotes keyNotes, Time time, WriteableThaat thaat, String jaati) {
			this.raagProperties = new RaagProperties(raagName, keyNotes, time, thaat, jaati);
		}

		String printShortSummary() {
			return raagProperties.properties();
		}

		@Override
		public String toString() {
			return raagProperties.name();
		}
	}
}

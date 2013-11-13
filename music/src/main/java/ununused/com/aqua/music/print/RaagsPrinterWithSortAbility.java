package ununused.com.aqua.music.print;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ununused.com.aqua.music.print.RaagProperties.RaagProp;
import ununused.com.aqua.music.print.Raags.Raag;

public class RaagsPrinterWithSortAbility {
	private static final Logger logger = LoggerFactory.getLogger(RaagsPrinterWithSortAbility.class);
	
	static void printAll(Collection<Raag> raags) {
		logger.info(RaagProperties.header());
		int i = 0;
		for (Raag each : raags) {
			String prefix = String.format("%-6s", "|" + ++i);
			logger.info(prefix + each.printShortSummary());
		}
	}

	static void printSortByCriterio(Collection<Raag> raags, Class<? extends RaagProp> criterio) {
		logger.info(RaagProperties.header());
		int i = 0;
		Collection<Raag> raags2 = sortByCriterio(raags, criterio);
		/*
		 * for( Raag each : raags2 ) { String prefix = String.format( "%-6s",
		 * "|" + ++i ); logger.info( prefix + each.printShortSummary() );
		 * }
		 */
	}

	private static RaagProp forInterface(Raag each, Class<? extends RaagProp> class1) {
		if (class1 == RaagProperties.Time.class) {
			return each.raagProperties.time();
		} else if (class1 == RaagProperties.WriteableThaat.class) {
			return each.raagProperties.thaat();
		}
		return null;
	}

	private static Collection<Raag> sortByCriterio(Collection<Raag> raags, Class<? extends RaagProp> criterio) {
		Map<RaagProp, ArrayList<Raag>> criterioSet = new TreeMap<RaagProp, ArrayList<Raag>>();
		for (Raag each : raags) {
			RaagProp criterioValue = forInterface(each, criterio);
			if (criterioSet.containsKey(criterioValue)) {
				criterioSet.get(criterioValue).add(each);
			} else {
				ArrayList<Raag> arrayList = new ArrayList<Raag>();
				arrayList.add(each);
				criterioSet.put(criterioValue, arrayList);
			}
		}

		logger.info(criterioSet.toString());
		return raags;
	}

}

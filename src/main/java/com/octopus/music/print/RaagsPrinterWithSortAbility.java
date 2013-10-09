package com.octopus.music.print;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeMap;

import com.octopus.music.print.RaagProperties.RaagProp;
import com.octopus.music.print.Raags.Raag;

public class RaagsPrinterWithSortAbility
{
    static void printAll(Collection<Raag> raags) {
        System.out.println( RaagProperties.header() );
        int i = 0;
        for( Raag each : raags ) {
            String prefix = String.format( "%-6s", "|" + ++i );
            System.out.println( prefix + each.printShortSummary() );
        }
    }

    static void printSortByCriterio(Collection<Raag> raags, Class<? extends RaagProp> criterio) {
        System.out.println( RaagProperties.header() );
        int i = 0;
        Collection<Raag> raags2 = sortByCriterio( raags, criterio );
        /*for( Raag each : raags2 ) {
            String prefix = String.format( "%-6s", "|" + ++i );
            System.out.println( prefix + each.printShortSummary() );
        }*/
    }
    
    
    private static RaagProp forInterface( Raag each, Class<? extends RaagProp> class1 ) {
        if( class1 == RaagProperties.Time.class ) {
            return each.raagProperties.time();
        }else if( class1 == RaagProperties.Thaat.class ) {
            return each.raagProperties.thaat();
        }
        return null;
    }

    private static Collection<Raag> sortByCriterio( Collection<Raag> raags, Class<? extends RaagProp> criterio ) {
        Map<RaagProp, ArrayList<Raag>> criterioSet = new TreeMap<RaagProp, ArrayList<Raag>>();
        for( Raag each : raags ) {
            RaagProp criterioValue = forInterface( each, criterio );
            if( criterioSet.containsKey( criterioValue ) ) {
                criterioSet.get( criterioValue ).add( each );
            } else {
                ArrayList<Raag> arrayList = new ArrayList<Raag>();
                arrayList.add( each );
                criterioSet.put( criterioValue, arrayList );
            }
        }

        System.out.println( criterioSet );
        
        
        Collection<Raag> sortedOutput= new LinkedHashSet<Raag>();
        return raags;
    }

}

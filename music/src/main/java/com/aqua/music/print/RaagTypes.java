package com.aqua.music.print;

import com.aqua.music.print.RaagProperties.WriteableThaat;
import com.aqua.music.print.RaagProperties.Time;

abstract class RaagTypes
{
    private final WriteableThaat thaat;
    private final KeyNotes keyNotes;
    private final Time time;

    protected String[] aarohi;
    protected String[] avrohi;

    protected RaagTypes( WriteableThaat thaat, KeyNotes keyNotes, Time time ) {
        this.thaat = thaat;
        this.keyNotes = keyNotes;
        this.time = time;
    }

    void setAvrohiAsReverseOfArohi() {
        int size = aarohi.length;
        for( int i = 0; i < size; i++ ) {
            avrohi[size - i - 1] = aarohi[i];
        }
    }

    public class Bhairav extends RaagTypes
    {
        public Bhairav() {
            super( WriteableThaat.Bhairav, KeyNotes.with( "Re_", "Dha_" ), Time.Morning );
            aarohi = new String[] { "Sa", "Re_", "Ga", "Ma", "Pa", "Dha_", "Ni" };
            setAvrohiAsReverseOfArohi();
        }
    }
    
    public static class AhirBhairav extends RaagTypes
    {
        public AhirBhairav() {
            super( WriteableThaat.Bhairav, KeyNotes.with( "Re_", "Dha_" ), Time.Morning );
            aarohi = new String[] { "Sa", "Re_", "Ga", "Ma", "Pa", "Dha", "Ni_" };
            setAvrohiAsReverseOfArohi();
        }
    }

    
    public static class Bairagi extends RaagTypes
    {
        public Bairagi() {
            super( WriteableThaat.Bhairav, KeyNotes.with( "Re_", "Dha_" ), Time.Morning );
            aarohi = new String[] { "Sa", "Re_", "Ma", "Pa", "Ni" };
            setAvrohiAsReverseOfArohi();
        }
    }
    
    public static class GujariTodi extends RaagTypes
    {
        public GujariTodi() {
            super( WriteableThaat.Todi, KeyNotes.with( "Ga_", "Dha_" ), Time.Morning );
            aarohi = new String[] { "Sa", "Re_", "Ga_", "Ma_", "Dha_", "Ni" };
            setAvrohiAsReverseOfArohi();
        }
    }
    
    public static class Bhopali extends RaagTypes
    {
        public Bhopali() {
            super( WriteableThaat.Kalyan, KeyNotes.with( "Ga", "Dha" ), Time.Evening );
            aarohi = new String[] { "Sa", "Re", "Ga", "Pa", "Dha" };
            setAvrohiAsReverseOfArohi();
        }
    }

    public static class Yaman extends RaagTypes
    {
        public Yaman() {
            super( WriteableThaat.Kalyan, KeyNotes.with( "Ga", "Ni" ), Time.Evening );
            aarohi = new String[] { "Re", "Ga", "Ma_", "Dha", "Ni" };
            avrohi = new String[] { ".Ni", "Ni", "Dha", "Pa", "Ma_", "Ga", "Re" };
        }
    }
    
    public static class ShudhSarang extends RaagTypes
    {
        public ShudhSarang() {
            super( WriteableThaat.Kalyan, KeyNotes.with( "Sa", "Ma" ), Time.Evening );
            aarohi = new String[] { ".Ni", "Sa", "Re", "Ma", "Pa", "Ni" };
            avrohi = new String[] { "Ni", "Dha", "Pa", "Ma_", "Pa", "Ma", "Re" };
        }
    }
}
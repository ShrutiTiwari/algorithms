package com.aqua.music.symmetrical.set.ipi;

import com.aqua.music.model.PredefinedFrequencySet.SymmetricalSet;

public class PlaySelectedSymmetricalSet
{
    private String[] multipleSets;

    PlaySelectedSymmetricalSet( String[] multipleSets ) {
        this.multipleSets = multipleSets;
    }

    void execute() {
        for( String eachSetName : multipleSets ) {
            SymmetricalSet.valueOf( eachSetName ).playAscendAndDescend();
        }
    }

    Object result() {
        return true;
    }

}
package com.aqua.music.symmetrical.set.ipi;

import com.aqua.music.items.PlayableItem.SymmetricalPlayableItem;
import com.aqua.music.model.FrequencySet.SymmetricalSet;

public class PlaySelectedSymmetricalSet
{
    private String[] multipleSets;

    PlaySelectedSymmetricalSet( String[] multipleSets ) {
        this.multipleSets = multipleSets;
    }

    void execute() {
        for( String eachSetName : multipleSets ) {
            SymmetricalPlayableItem.forSet( SymmetricalSet.valueOf( eachSetName )).play();
        }
    }

    Object result() {
        return true;
    }

}
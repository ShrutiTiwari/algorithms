package com.aqua.music.symmetrical.set.ipi;

import com.aqua.music.model.PredefinedFrequencySet.SymmetricalSet;

public class PlaySelectedThaats
{
    private String[] thaatsName;

    PlaySelectedThaats( String[] thaatsName ) {
        this.thaatsName = thaatsName;
    }

    void execute() {
        for( String each : thaatsName ) {
            SymmetricalSet eachThaat = SymmetricalSet.valueOf( each );
            eachThaat.playAscendAndDescend();
        }
    }

    Object result() {
        return true;
    }

}
package com.aqua.music.thaat.ipi;

import com.aqua.music.items.SequencePlayer.Thaat;

public class PlaySelectedThaats
{
    private String[] thaatsName;

    PlaySelectedThaats( String[] thaatsName ) {
        this.thaatsName = thaatsName;
    }

    void execute() {
        for( String each : thaatsName ) {
            Thaat eachThaat = Thaat.valueOf( each );
            eachThaat.playAscendAndDescend();
        }
    }

    Object result() {
        return true;
    }

}
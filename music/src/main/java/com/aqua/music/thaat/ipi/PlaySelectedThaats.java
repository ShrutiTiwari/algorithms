package com.aqua.music.thaat.ipi;

import com.aqua.music.play.SequencePlayer.AllThaat;

public class PlaySelectedThaats
{
    private String[] thaatsName;

    PlaySelectedThaats( String[] thaatsName ) {
        this.thaatsName = thaatsName;
    }

    void execute() {
        for( String each : thaatsName ) {
            AllThaat eachThaat = AllThaat.valueOf( each );
            eachThaat.playAscendAndDescend();
        }
    }

    Object result() {
        return true;
    }

}
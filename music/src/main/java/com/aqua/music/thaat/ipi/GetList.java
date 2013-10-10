package com.aqua.music.thaat.ipi;

import com.aqua.music.print.RaagProperties.Thaat;

public class GetList
{
    private String[] result;

    void execute() {
        int i = 0;
        Thaat[] allThaats = Thaat.values();
        result = new String[allThaats.length];
        for( Thaat each : allThaats ) {
            result[i++] = each.name();
        }
    }

    Object result() {
        return result;
    }

}
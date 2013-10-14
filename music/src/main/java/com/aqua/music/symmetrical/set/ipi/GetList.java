package com.aqua.music.symmetrical.set.ipi;

import com.aqua.music.print.RaagProperties.WriteableThaat;

public class GetList
{
    private String[] result;

    void execute() {
        int i = 0;
        WriteableThaat[] allThaats = WriteableThaat.values();
        result = new String[allThaats.length];
        for( WriteableThaat each : allThaats ) {
            result[i++] = each.name();
        }
    }

    Object result() {
        return result;
    }

}
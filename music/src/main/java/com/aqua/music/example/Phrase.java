package com.aqua.music.example;

import java.util.Vector;

public class Phrase {

    Vector< Chord> chords = new Vector<Chord>();

    public Phrase() {
    }

    public Phrase chord(Chord c) {
        chords.add(c);
        return this;
    }
}
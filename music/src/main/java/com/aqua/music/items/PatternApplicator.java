package com.aqua.music.items;

import java.util.Collections;
import java.util.List;

public interface PatternApplicator<T>
{
    static final String SEP = " |||| ";

    List<T> ascendSequence();

    List<T> descendSequence();

    List<T> allNotes();

    String prettyPrintTextForAscDesc();

    void generateAscendAndDescendSequences( T[] commonAscDescInput );

    PatternApplicator NONE = new PatternApplicator() {
        @Override
        public List ascendSequence() {
            return Collections.EMPTY_LIST;
        }

        @Override
        public List descendSequence() {
            return Collections.EMPTY_LIST;
        }

        @Override
        public List allNotes() {
            return Collections.EMPTY_LIST;
        }

        @Override
        public String prettyPrintTextForAscDesc() {
            return "";
        }

        @Override
        public void generateAscendAndDescendSequences( Object[] commonAscDescInput ) {}
    };
}

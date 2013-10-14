package com.aqua.music.items;

import java.util.Collections;
import java.util.List;

public interface PatternApplicator<T>
{
    static final String SEP = " |||| ";

    List<T> allNotes();

    String prettyPrintTextForAscDesc();

    void initializeWith( T[] commonAscDescInput );

    PatternApplicator NONE = new PatternApplicator() {
        @Override
        public List allNotes() {
            return Collections.EMPTY_LIST;
        }

        @Override
        public String prettyPrintTextForAscDesc() {
            return "";
        }

        @Override
        public void initializeWith( Object[] commonAscDescInput ) {}
    };
}

package com.aqua.artifacto;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;

import com.aqua.artifacto.pom.dependency.Aggregator;

class ClassEntriesAggregator implements Aggregator
{
    private final Map<String, String> allClassesWithJarAsValue = new LinkedHashMap<String, String>();
    private final Map<String, Set<String>> duplicateClassesWithJarsAsValue = new LinkedHashMap<String, Set<String>>();
    private Set<String> keywordSet = new HashSet<String>();

    @Override
    public void storeInformation( JarEntry jarEntry, File jarIOFile ) {
        String jarEntryName = jarEntry.getName();
        if( !isValidEntry( jarEntryName ) ) {
            return;
        }
        String containerJarName = jarIOFile.getName();
        String qualifiedClassname = fullyQualifiedName( jarEntryName );

        if( keywordSet.isEmpty() ) {
            storeAllClassesInfo( containerJarName, qualifiedClassname );
        } else {
            if( keywordSet.contains( qualifiedClassname ) ) {
                storeAllClassesInfo( containerJarName, qualifiedClassname );
            }
        }
    }

    private void storeAllClassesInfo( String containerJarName, String qualifiedClassname ) {
        String originalJarContainingClass = allClassesWithJarAsValue.get( qualifiedClassname );
        if( originalJarContainingClass != null && !originalJarContainingClass.equals( containerJarName ) ) {
            Set<String> jarsContainingClass = duplicateClassesWithJarsAsValue.get( qualifiedClassname );
            if( jarsContainingClass == null ) {
                jarsContainingClass = new LinkedHashSet<String>();
                jarsContainingClass.add( originalJarContainingClass );
            }
            jarsContainingClass.add( containerJarName );
            duplicateClassesWithJarsAsValue.put( qualifiedClassname, jarsContainingClass );
        } else {
            allClassesWithJarAsValue.put( qualifiedClassname, containerJarName );
        }
    }

    private String fullyQualifiedName( String jarEntryName ) {
        String truncatedClass = jarEntryName.replaceAll( CLASS_ENTRY, "" ).trim();
        String fullyQualifiedNameWithPackageConvention = truncatedClass.replaceAll( "/", "\\." );
        return fullyQualifiedNameWithPackageConvention;
    }

    private boolean isValidEntry( String jarEntryName ) {
        return (jarEntryName.startsWith( "" )) && (jarEntryName.endsWith( CLASS_ENTRY ));
    }

    public final Aggregator forKeywords( String... keywords ) {
        for( String each : keywords ) {
            keywordSet.add( each );
        }
        return this;
    }

    @Override
    public void print() {
        // TODO Auto-generated method stub

    }
}
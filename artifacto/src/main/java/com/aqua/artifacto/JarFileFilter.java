package com.aqua.artifacto;

import java.io.File;
import java.io.FilenameFilter;

public class JarFileFilter implements FilenameFilter {

	final String allJARFiles = "";

	private MatichgCriterion matchingCriterion;
	private String[] jarPrefix;

	
	JarFileFilter(){
	    this(MatichgCriterion.ALL_JARS);
	}
	
	JarFileFilter(MatichgCriterion matchingCriterion, String... jarPrefix) {
		this.matchingCriterion = matchingCriterion;
		this.jarPrefix = jarPrefix;
		if (jarPrefix == null) {
			this.jarPrefix = new String[] {};
		}
	}

	public boolean accept(File dir, String name) {
		switch (matchingCriterion) {
		case ALL_JARS:
			if (isArtifactJar( name )) {
				return true;
			}
			break;
		case ALL_JARS_EXCEPT:
			if (isArtifactJar( name ) && notExcluded(name)) {
				return true;
			}
			break;
		default:
			String prefix = jarPrefix[0];
			if (isArtifactJar( name ) && name.startsWith(prefix)) {
				return isArtifactWithoutVersionExactMatchForPrefix( name, prefix );
			}
		}
		return false;
	}

    private boolean isArtifactWithoutVersionExactMatchForPrefix( String name, String prefix ) {
        String abridgedName = name.replace(prefix, "");
        int MEAJOR_VERSION = 0;
        boolean result = Character.isDigit(abridgedName
        		.charAt(MEAJOR_VERSION));
        return result;
    }

    private boolean isArtifactJar( String name ) {
        return name.endsWith(".jar");
    }

	private boolean notExcluded(String name) {
		for (String each : jarPrefix) {
			if (name.startsWith(each)) {
				return false;
			}
		}
		return true;
	}
	enum MatichgCriterion {
		ALL_JARS, ALL_JARS_EXCEPT, JAR_WITH_NAME;
	}
}

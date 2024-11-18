package com.sbm.sonarqube.chaiscript.language;

// Extending AbstractLanguage to define a custom language for SonarQube
import org.sonar.api.resources.AbstractLanguage;

public class ChaiScriptLanguage extends AbstractLanguage {
    // Unique key to identify the ChaiScript language in SonarQube
    public static final String LANGUAGE_KEY = "chai";
    // Display name for the ChaiScript language in SonarQube
    public static final String LANGUAGE_NAME = "ChaiScript";

    // Constructor to register the language with its key and name
    public ChaiScriptLanguage() {
        super(LANGUAGE_KEY, LANGUAGE_NAME);
    }

    // Defining the file extensions associated with ChaiScript
    @Override
    public String[] getFileSuffixes() {
        return new String[] {".chai"};
    }
}

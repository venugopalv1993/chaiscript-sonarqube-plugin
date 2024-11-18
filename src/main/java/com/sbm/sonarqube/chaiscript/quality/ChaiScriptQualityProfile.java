package com.sbm.sonarqube.chaiscript.quality;

// Importing the necessary SonarQube API classes and custom ChaiScript language definitions
import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition;
import com.sbm.sonarqube.chaiscript.language.ChaiScriptLanguage;

import javax.annotation.Nonnull;

// Class to define the default quality profile for ChaiScript
public class ChaiScriptQualityProfile implements BuiltInQualityProfilesDefinition {

    @Override
    public void define(@Nonnull Context context) {
        // Create the default quality profile for ChaiScript
        NewBuiltInQualityProfile profile = context.createBuiltInQualityProfile(
            "ChaiScript Default Profile", // Profile name
            ChaiScriptLanguage.LANGUAGE_KEY // Associate the profile with ChaiScript
        );
        profile.setDefault(true); // Mark this profile as the default for ChaiScript

        // Activate specific rules for the profile
        profile.activateRule(ChaiScriptRuleKeys.REPOSITORY_KEY, ChaiScriptRuleKeys.SYNTAX_ERROR.rule()); // Activate syntax error rule
        profile.activateRule(ChaiScriptRuleKeys.REPOSITORY_KEY, ChaiScriptRuleKeys.NO_PASSWORD.rule()); // Activate no password rule
        profile.activateRule(ChaiScriptRuleKeys.REPOSITORY_KEY, ChaiScriptRuleKeys.DUPLICATE_VARIABLE_NAME.rule()); // Activate duplicate variable name rule
        profile.activateRule(ChaiScriptRuleKeys.REPOSITORY_KEY, ChaiScriptRuleKeys.DUPLICATE_FUNCTION_NAME.rule()); // Activate duplicate function name rule

        profile.done(); // Finalize the quality profile
    }
}

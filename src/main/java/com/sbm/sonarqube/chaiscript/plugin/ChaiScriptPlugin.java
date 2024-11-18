package com.sbm.sonarqube.chaiscript.plugin;

import org.sonar.api.Plugin;

import com.sbm.sonarqube.chaiscript.language.ChaiScriptLanguage;
import com.sbm.sonarqube.chaiscript.metrics.ChaiScriptMetrics;
import com.sbm.sonarqube.chaiscript.quality.ChaiScriptQualityProfile;
import com.sbm.sonarqube.chaiscript.quality.ChaiScriptRulesDefinition;
import com.sbm.sonarqube.chaiscript.sensors.ChaiScriptSensor;
import javax.annotation.Nonnull; // Importing Nonnull annotation

public class ChaiScriptPlugin implements Plugin  {

    @Override
    public void define(@Nonnull Context context) { // Annotate the parameter as @Nonnull
        context.addExtensions(
            ChaiScriptLanguage.class,           // Register ChaiScript language for analysis in SonarQube.
            ChaiScriptSensor.class,             // Register the sensor that analyzes ChaiScript files for quality metrics.
            ChaiScriptMetrics.class,            // Register any custom metrics defined in ChaiScriptMetrics for quality assessment.
            ChaiScriptRulesDefinition.class,    // Register the rules definition, specifying coding standards and guidelines for ChaiScript.
            ChaiScriptQualityProfile.class      // Register a quality profile, which groups rules and metrics for assessing code quality.
        );
    }
}

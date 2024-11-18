package com.sbm.sonarqube.chaiscript.validation;

import org.sonar.api.rule.RuleKey;

import com.sbm.sonarqube.chaiscript.quality.ChaiScriptRuleKeys;
import com.sbm.sonarqube.chaiscript.quality.ChaiScriptRulesDefinition;

public class PasswordValidator implements ChaiScriptValidator {

    @Override
    public void validate(String content) {
        if (content.toLowerCase().contains("password")) {
            throw new RuntimeException("Sensitive information found: 'password' should not be present.");
        }
    }

    @Override
    public RuleKey getRuleKey() {
        return RuleKey.of(ChaiScriptRulesDefinition.REPOSITORY_KEY, ChaiScriptRuleKeys.NO_PASSWORD.rule());  // Specific RuleKey for passord errors
    }
}

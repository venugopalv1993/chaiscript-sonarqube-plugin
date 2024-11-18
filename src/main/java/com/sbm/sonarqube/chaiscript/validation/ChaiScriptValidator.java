package com.sbm.sonarqube.chaiscript.validation;

import org.sonar.api.rule.RuleKey;

public interface ChaiScriptValidator {
    void validate(String content) throws Exception;
    RuleKey getRuleKey();  // New method to retrieve rule key for the validator
}

package com.sbm.sonarqube.chaiscript.validation;

import org.sonar.api.rule.RuleKey;

import com.sbm.sonarqube.chaiscript.quality.ChaiScriptRuleKeys;
import com.sbm.sonarqube.chaiscript.quality.ChaiScriptRulesDefinition;

public class SyntaxValidator implements ChaiScriptValidator {

    @Override
    public void validate(String content) throws Exception {
        int bracketCount = 0;
        boolean inFunction = false;

        String[] lines = content.split("\n");
        for (String line : lines) {
            if (line.contains("def ")) {
                inFunction = true;
            }
            if (line.contains("{")) {
                bracketCount++;
            }
            if (line.contains("}")) {
                bracketCount--;
            }
            if (inFunction && bracketCount == 0) {
                inFunction = false;
            }
        }

        if (bracketCount != 0) {
            throw new Exception("Unmatched brackets found.");
        }
    }
    
    @Override
    public RuleKey getRuleKey() {
        return RuleKey.of(ChaiScriptRulesDefinition.REPOSITORY_KEY, ChaiScriptRuleKeys.SYNTAX_ERROR.rule());  // Specific RuleKey for syntax errors
    }
    
}

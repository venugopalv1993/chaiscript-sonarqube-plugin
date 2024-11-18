package com.sbm.sonarqube.chaiscript.validation;

import org.sonar.api.rule.RuleKey;
import com.sbm.sonarqube.chaiscript.quality.ChaiScriptRuleKeys;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DuplicateFunctionNameValidator implements ChaiScriptValidator {

    // Regex pattern to match function declarations (e.g., def functionName() {...})
    private static final Pattern FUNCTION_DECLARATION_PATTERN = Pattern.compile("\\bdef\\s+(\\w+)\\s*\\(");

    @Override
    public void validate(String content) throws Exception {
        HashSet<String> declaredFunctions = new HashSet<>();
        Matcher matcher = FUNCTION_DECLARATION_PATTERN.matcher(content);
        
        while (matcher.find()) {
            String functionName = matcher.group(1); // Get the function name from the first capturing group
            if (!declaredFunctions.add(functionName)) {
                throw new Exception("Duplicate declaration of function: " + functionName);
            }
        }
    }

    @Override
    public RuleKey getRuleKey() {
        return RuleKey.of(ChaiScriptRuleKeys.REPOSITORY_KEY, ChaiScriptRuleKeys.DUPLICATE_FUNCTION_NAME.rule());
    }
}

package com.github.chaosfirebolt.generator.token;

import com.github.chaosfirebolt.generator.token.part.NumericTokenPart;
import com.github.chaosfirebolt.generator.token.part.SpecialCharacterTokenPart;
import com.github.chaosfirebolt.generator.token.part.UpperAlphabeticTokenPart;
import com.github.chaosfirebolt.generator.token.rule.BaseGeneratorRule;
import com.github.chaosfirebolt.generator.token.rule.GeneratorRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by ChaosFire on 22-Dec-21
 */
public class StringTokenGeneratorMethodTests extends TokenGeneratorMethodTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringTokenGeneratorMethodTests.class);

    public StringTokenGeneratorMethodTests() {
        super(LOGGER, 49, 233, 233);
    }

    @Override
    protected BaseTokenGenerator<String> getGenerator() {
        GeneratorRule generatorRule = new BaseGeneratorRule(Arrays.asList(new SpecialCharacterTokenPart(11), new UpperAlphabeticTokenPart(111), new NumericTokenPart(111)));
        return new StringTokenGenerator(generatorRule);
    }
}
package com.github.chaosfirebolt.generator.token;

import com.github.chaosfirebolt.generator.token.impl.UpperAlphabeticTokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ChaosFire on 22-Dec-21
 */
public class UpperAlphabeticTokenGeneratorTests extends TokenGeneratorTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpperAlphabeticTokenGeneratorTests.class);

    public UpperAlphabeticTokenGeneratorTests() {
        super(LOGGER, 50, 111, 111);
    }

    @Override
    protected BaseTokenGenerator<String> getGenerator() {
        return new UpperAlphabeticTokenGenerator(111);
    }
}
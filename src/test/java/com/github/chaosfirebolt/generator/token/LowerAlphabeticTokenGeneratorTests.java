package com.github.chaosfirebolt.generator.token;

import com.github.chaosfirebolt.generator.token.impl.LowerAlphabeticTokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ChaosFire on 22-Dec-21
 */
public class LowerAlphabeticTokenGeneratorTests extends TokenGeneratorTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(LowerAlphabeticTokenGeneratorTests.class);

    public LowerAlphabeticTokenGeneratorTests() {
        super(LOGGER, 11, 20, 20);
    }

    @Override
    protected BaseTokenGenerator<String> getGenerator() {
        return new LowerAlphabeticTokenGenerator(20);
    }
}
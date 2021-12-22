package com.github.chaosfirebolt.generator.token;

import com.github.chaosfirebolt.generator.token.impl.AlphabeticTokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ChaosFire on 22-Dec-21
 */
public class AlphabeticTokenGeneratorTests extends TokenGeneratorTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlphabeticTokenGeneratorTests.class);

    public AlphabeticTokenGeneratorTests() {
        super(LOGGER, 10, 30, 30);
    }

    @Override
    protected BaseTokenGenerator<String> getGenerator() {
        return new AlphabeticTokenGenerator(15, 15);
    }
}
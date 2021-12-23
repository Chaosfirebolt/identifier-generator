package com.github.chaosfirebolt.generator.token;

import com.github.chaosfirebolt.generator.token.impl.NumericTokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ChaosFire on 22-Dec-21
 */
public class NumericTokenGeneratorMethodTests extends TokenGeneratorMethodTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(NumericTokenGeneratorMethodTests.class);

    public NumericTokenGeneratorMethodTests() {
        super(LOGGER, 100, 29, 29);
    }

    @Override
    protected BaseTokenGenerator<String> getGenerator() {
        return new NumericTokenGenerator(29);
    }
}
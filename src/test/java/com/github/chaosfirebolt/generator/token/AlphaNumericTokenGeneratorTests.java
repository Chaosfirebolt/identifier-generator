package com.github.chaosfirebolt.generator.token;

import com.github.chaosfirebolt.generator.token.impl.AlphaNumericTokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ChaosFire on 22-Dec-21
 */
public class AlphaNumericTokenGeneratorTests extends TokenGeneratorTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlphaNumericTokenGeneratorTests.class);

    public AlphaNumericTokenGeneratorTests() {
        super(LOGGER, 5, 41, 41);
    }

    @Override
    protected BaseTokenGenerator<String> getGenerator() {
        return new AlphaNumericTokenGenerator(15, 15, 11);
    }
}
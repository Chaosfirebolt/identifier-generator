package com.github.chaosfirebolt.generator.token;

import com.github.chaosfirebolt.generator.token.impl.AnyCharacterTokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ChaosFire on 22-Dec-21
 */
public class AnyCharacterTokenGeneratorTests extends TokenGeneratorTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnyCharacterTokenGeneratorTests.class);

    public AnyCharacterTokenGeneratorTests() {
        super(LOGGER, 9, 33, 33);
    }

    @Override
    protected BaseTokenGenerator<String> getGenerator() {
        return new AnyCharacterTokenGenerator(9, 10, 11, 3);
    }
}
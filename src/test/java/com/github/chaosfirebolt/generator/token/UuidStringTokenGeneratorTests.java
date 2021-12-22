package com.github.chaosfirebolt.generator.token;

import com.github.chaosfirebolt.generator.token.impl.UuidStringTokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ChaosFire on 22-Dec-21
 */
public class UuidStringTokenGeneratorTests extends TokenGeneratorTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(UuidStringTokenGeneratorTests.class);

    public UuidStringTokenGeneratorTests() {
        super(LOGGER, 20, 36, 36);
    }

    @Override
    protected BaseTokenGenerator<String> getGenerator() {
        return new UuidStringTokenGenerator();
    }

    @Override
    public void generateTokenWithLength_LengthIsMoreThanMinimum_TokenShouldHaveCorrectLength() {
        //test not applicable for uuid token generator
    }

    @Override
    public void generateUniqueTokenWithLength_LengthIsMoreThanMinimum_TokenShouldHaveCorrectLength() {
        //test not applicable for uuid token generator
    }
}
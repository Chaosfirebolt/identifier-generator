package com.github.chaosfirebolt.generator.token.constructor;

import com.github.chaosfirebolt.generator.token.TokenGenerator;
import com.github.chaosfirebolt.generator.token.impl.UpperAlphabeticTokenGenerator;
import org.junit.jupiter.api.function.Executable;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by ChaosFire on 23-Dec-21
 */
public class UpperAlphabeticTokenGeneratorConstructorTests extends TokenGeneratorConstructorTests {

    @Override
    protected List<InvalidConstructorInvocationWrapper> getLengthParamNegative() {
        int length = -6;
        return Collections.singletonList(buildWrapperForIllegalArgument(() -> new UpperAlphabeticTokenGenerator(length), length));
    }

    @Override
    protected List<InvalidConstructorInvocationWrapper> getLengthParamZero() {
        int length = 0;
        return Collections.singletonList(buildWrapperForIllegalArgument(() -> new UpperAlphabeticTokenGenerator(length), length));
    }

    @Override
    protected List<Callable<? extends TokenGenerator<?>>> getValidLengthParams() {
        return Collections.singletonList(() -> new UpperAlphabeticTokenGenerator(9));
    }

    @Override
    protected List<InvalidConstructorInvocationWrapper> getParamsDoNotConformRules() {
        Executable executable = () -> new UpperAlphabeticTokenGenerator(9, Collections.singletonList(MIN_TOKEN_LENGTH_VALIDATOR));
        return Collections.singletonList(buildWrapperForInvalidGeneratorRule(executable));
    }

    @Override
    protected List<Callable<? extends TokenGenerator<?>>> getParamsConformRules() {
        return Collections.singletonList(() -> new UpperAlphabeticTokenGenerator(30, Collections.singletonList(MIN_TOKEN_LENGTH_VALIDATOR)));
    }

    @Override
    protected List<InvalidConstructorInvocationWrapper> getParamsWithRandomDoNotConformRules() {
        Executable executable = () -> new UpperAlphabeticTokenGenerator(new Random(), 9, Collections.singletonList(MIN_TOKEN_LENGTH_VALIDATOR));
        return Collections.singletonList(buildWrapperForInvalidGeneratorRule(executable));
    }

    @Override
    protected List<Callable<? extends TokenGenerator<?>>> getParamsWithRandomConformRules() {
        return Collections.singletonList(() -> new UpperAlphabeticTokenGenerator(new Random(), 40, Collections.singletonList(MIN_TOKEN_LENGTH_VALIDATOR)));
    }
}
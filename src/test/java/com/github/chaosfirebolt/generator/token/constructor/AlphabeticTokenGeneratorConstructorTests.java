package com.github.chaosfirebolt.generator.token.constructor;

import com.github.chaosfirebolt.generator.token.TokenGenerator;
import com.github.chaosfirebolt.generator.token.impl.AlphabeticTokenGenerator;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by ChaosFire on 23-Dec-21
 */
public class AlphabeticTokenGeneratorConstructorTests extends TokenGeneratorConstructorTests {

    @Override
    protected List<InvalidConstructorInvocationWrapper> getLengthParamNegative() {
        List<InvalidConstructorInvocationWrapper> list = new ArrayList<>();
        list.add(buildWrapperForIllegalArgument(() -> new AlphabeticTokenGenerator(-1, 9), -1));
        list.add(buildWrapperForIllegalArgument(() -> new AlphabeticTokenGenerator(9, -3), -3));
        return list;
    }

    @Override
    protected List<InvalidConstructorInvocationWrapper> getLengthParamZero() {
        List<InvalidConstructorInvocationWrapper> list = new ArrayList<>();
        list.add(buildWrapperForIllegalArgument(() -> new AlphabeticTokenGenerator(0, 9), 0));
        list.add(buildWrapperForIllegalArgument(() -> new AlphabeticTokenGenerator(9, 0), 0));
        return list;
    }

    @Override
    protected List<Callable<? extends TokenGenerator<?>>> getValidLengthParams() {
        return Collections.singletonList(() -> new AlphabeticTokenGenerator(5, 5));
    }

    @Override
    protected List<InvalidConstructorInvocationWrapper> getParamsDoNotConformRules() {
        Executable executable = () -> new AlphabeticTokenGenerator(9, 9, Collections.singletonList(MIN_TOKEN_LENGTH_VALIDATOR));
        return Collections.singletonList(buildWrapperForInvalidGeneratorRule(executable));
    }

    @Override
    protected List<Callable<? extends TokenGenerator<?>>> getParamsConformRules() {
        List<Callable<? extends TokenGenerator<?>>> list = new ArrayList<>();
        list.add(() -> new AlphabeticTokenGenerator(15, 15, Collections.singletonList(MIN_TOKEN_LENGTH_VALIDATOR)));
        list.add(() -> new AlphabeticTokenGenerator(20, 11, Collections.singletonList(MIN_TOKEN_LENGTH_VALIDATOR)));
        return list;
    }

    @Override
    protected List<InvalidConstructorInvocationWrapper> getParamsWithRandomDoNotConformRules() {
        Executable executable = () -> new AlphabeticTokenGenerator(new Random(), 9, 9, Collections.singletonList(MIN_TOKEN_LENGTH_VALIDATOR));
        return Collections.singletonList(buildWrapperForInvalidGeneratorRule(executable));
    }

    @Override
    protected List<Callable<? extends TokenGenerator<?>>> getParamsWithRandomConformRules() {
        List<Callable<? extends TokenGenerator<?>>> list = new ArrayList<>();
        list.add(() -> new AlphabeticTokenGenerator(new Random(), 15, 15, Collections.singletonList(MIN_TOKEN_LENGTH_VALIDATOR)));
        list.add(() -> new AlphabeticTokenGenerator(new Random(), 20, 11, Collections.singletonList(MIN_TOKEN_LENGTH_VALIDATOR)));
        return list;
    }
}
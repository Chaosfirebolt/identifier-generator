package com.github.chaosfirebolt.generator.token.constructor;

import com.github.chaosfirebolt.generator.token.TokenGenerator;
import com.github.chaosfirebolt.generator.token.impl.AnyCharacterTokenGenerator;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by ChaosFire on 23-Dec-21
 */
public class AnyCharacterTokenGeneratorConstructorTests extends TokenGeneratorConstructorTests {

    @Override
    protected List<InvalidConstructorInvocationWrapper> getLengthParamNegative() {
        List<InvalidConstructorInvocationWrapper> list = new ArrayList<>();
        list.add(buildWrapperForIllegalArgument(() -> new AnyCharacterTokenGenerator(-1, 9, 7, 8), -1));
        list.add(buildWrapperForIllegalArgument(() -> new AnyCharacterTokenGenerator(9, -3, 1, 3), -3));
        list.add(buildWrapperForIllegalArgument(() -> new AnyCharacterTokenGenerator(9, 3, -1, 3), -1));
        list.add(buildWrapperForIllegalArgument(() -> new AnyCharacterTokenGenerator(9, 3, 1, -3), -3));
        return list;
    }

    @Override
    protected List<InvalidConstructorInvocationWrapper> getLengthParamZero() {
        List<InvalidConstructorInvocationWrapper> list = new ArrayList<>();
        list.add(buildWrapperForIllegalArgument(() -> new AnyCharacterTokenGenerator(0, 9, 7, 8), 0));
        list.add(buildWrapperForIllegalArgument(() -> new AnyCharacterTokenGenerator(9, 0, 1, 3), 0));
        list.add(buildWrapperForIllegalArgument(() -> new AnyCharacterTokenGenerator(9, 3, 0, 3), 0));
        list.add(buildWrapperForIllegalArgument(() -> new AnyCharacterTokenGenerator(9, 3, 1, 0), 0));
        return list;
    }

    @Override
    protected List<Callable<? extends TokenGenerator<?>>> getValidLengthParams() {
        return Collections.singletonList(() -> new AnyCharacterTokenGenerator(3, 4, 5, 6));
    }

    @Override
    protected List<InvalidConstructorInvocationWrapper> getParamsDoNotConformRules() {
        int length = 7;
        Executable executable = () -> new AnyCharacterTokenGenerator(length, length, length, length, Collections.singletonList(MIN_TOKEN_LENGTH_VALIDATOR));
        return Collections.singletonList(buildWrapperForInvalidGeneratorRule(executable));
    }

    @Override
    protected List<Callable<? extends TokenGenerator<?>>> getParamsConformRules() {
        int ten = 10;
        int five = 5;
        return Collections.singletonList(() -> new AnyCharacterTokenGenerator(ten, ten, five, five, Collections.singletonList(MIN_TOKEN_LENGTH_VALIDATOR)));
    }

    @Override
    protected List<InvalidConstructorInvocationWrapper> getParamsWithRandomDoNotConformRules() {
        int length = 7;
        Executable executable = () -> new AnyCharacterTokenGenerator(new Random(), length, length, length, length, Collections.singletonList(MIN_TOKEN_LENGTH_VALIDATOR));
        return Collections.singletonList(buildWrapperForInvalidGeneratorRule(executable));
    }

    @Override
    protected List<Callable<? extends TokenGenerator<?>>> getParamsWithRandomConformRules() {
        int ten = 10;
        int five = 5;
        return Collections.singletonList(() -> new AnyCharacterTokenGenerator(new Random(), ten, ten, five, five, Collections.singletonList(MIN_TOKEN_LENGTH_VALIDATOR)));
    }
}

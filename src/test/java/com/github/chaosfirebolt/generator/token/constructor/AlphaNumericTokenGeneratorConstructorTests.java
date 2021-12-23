package com.github.chaosfirebolt.generator.token.constructor;

import com.github.chaosfirebolt.generator.token.TokenGenerator;
import com.github.chaosfirebolt.generator.token.impl.AlphaNumericTokenGenerator;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by ChaosFire on 23-Dec-21
 */
public class AlphaNumericTokenGeneratorConstructorTests extends TokenGeneratorConstructorTests {

    @Override
    protected List<InvalidConstructorInvocationWrapper> getLengthParamNegative() {
        List<InvalidConstructorInvocationWrapper> list = new ArrayList<>();
        list.add(buildWrapperForIllegalArgument(() -> new AlphaNumericTokenGenerator(-1, 9, 1), -1));
        list.add(buildWrapperForIllegalArgument(() -> new AlphaNumericTokenGenerator(9, -3, 1), -3));
        list.add(buildWrapperForIllegalArgument(() -> new AlphaNumericTokenGenerator(9, 3, -2), -2));
        return list;
    }

    @Override
    protected List<InvalidConstructorInvocationWrapper> getLengthParamZero() {
        List<InvalidConstructorInvocationWrapper> list = new ArrayList<>();
        list.add(buildWrapperForIllegalArgument(() -> new AlphaNumericTokenGenerator(0, 9, 3), 0));
        list.add(buildWrapperForIllegalArgument(() -> new AlphaNumericTokenGenerator(9, 0, 3), 0));
        list.add(buildWrapperForIllegalArgument(() -> new AlphaNumericTokenGenerator(9, 3, 0), 0));
        return list;
    }

    @Override
    protected List<Callable<? extends TokenGenerator<?>>> getValidLengthParams() {
        return Collections.singletonList(() -> new AlphaNumericTokenGenerator(5, 5, 1));
    }

    @Override
    protected List<InvalidConstructorInvocationWrapper> getParamsDoNotConformRules() {
        Executable executable = () -> new AlphaNumericTokenGenerator(9, 9, 9, Collections.singletonList(MIN_TOKEN_LENGTH_VALIDATOR));
        return Collections.singletonList(buildWrapperForInvalidGeneratorRule(executable));
    }

    @Override
    protected List<Callable<? extends TokenGenerator<?>>> getParamsConformRules() {
        List<Callable<? extends TokenGenerator<?>>> list = new ArrayList<>();
        list.add(() -> new AlphaNumericTokenGenerator(10, 10, 10, Collections.singletonList(MIN_TOKEN_LENGTH_VALIDATOR)));
        list.add(() -> new AlphaNumericTokenGenerator(20, 11, 5, Collections.singletonList(MIN_TOKEN_LENGTH_VALIDATOR)));
        return list;
    }

    @Override
    protected List<InvalidConstructorInvocationWrapper> getParamsWithRandomDoNotConformRules() {
        Executable executable = () -> new AlphaNumericTokenGenerator(new Random(), 9, 9, 9, Collections.singletonList(MIN_TOKEN_LENGTH_VALIDATOR));
        return Collections.singletonList(buildWrapperForInvalidGeneratorRule(executable));
    }

    @Override
    protected List<Callable<? extends TokenGenerator<?>>> getParamsWithRandomConformRules() {
        List<Callable<? extends TokenGenerator<?>>> list = new ArrayList<>();
        list.add(() -> new AlphaNumericTokenGenerator(new Random(), 10, 10, 10, Collections.singletonList(MIN_TOKEN_LENGTH_VALIDATOR)));
        list.add(() -> new AlphaNumericTokenGenerator(new Random(), 20, 11, 3, Collections.singletonList(MIN_TOKEN_LENGTH_VALIDATOR)));
        return list;
    }
}
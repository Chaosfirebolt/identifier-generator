/*
 * Copyright 2021 Boyan Georgiev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.chaosfirebolt.generator.token.constructor;

import com.github.chaosfirebolt.generator.token.IdentifierGenerator;
import com.github.chaosfirebolt.generator.token.impl.AlphaNumericIdentifierGenerator;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by ChaosFire on 23-Dec-21
 */
public class AlphaNumericIdentifierGeneratorConstructorTests extends IdentifierGeneratorConstructorTests {

    @Override
    protected List<InvalidConstructorInvocationWrapper> getLengthParamNegative() {
        List<InvalidConstructorInvocationWrapper> list = new ArrayList<>();
        list.add(buildWrapperForIllegalArgument(() -> new AlphaNumericIdentifierGenerator(-1, 9, 1), -1));
        list.add(buildWrapperForIllegalArgument(() -> new AlphaNumericIdentifierGenerator(9, -3, 1), -3));
        list.add(buildWrapperForIllegalArgument(() -> new AlphaNumericIdentifierGenerator(9, 3, -2), -2));
        return list;
    }

    @Override
    protected List<InvalidConstructorInvocationWrapper> getLengthParamZero() {
        List<InvalidConstructorInvocationWrapper> list = new ArrayList<>();
        list.add(buildWrapperForIllegalArgument(() -> new AlphaNumericIdentifierGenerator(0, 9, 3), 0));
        list.add(buildWrapperForIllegalArgument(() -> new AlphaNumericIdentifierGenerator(9, 0, 3), 0));
        list.add(buildWrapperForIllegalArgument(() -> new AlphaNumericIdentifierGenerator(9, 3, 0), 0));
        return list;
    }

    @Override
    protected List<Callable<? extends IdentifierGenerator<?>>> getValidLengthParams() {
        return Collections.singletonList(() -> new AlphaNumericIdentifierGenerator(5, 5, 1));
    }

    @Override
    protected List<InvalidConstructorInvocationWrapper> getParamsDoNotConformRules() {
        Executable executable = () -> new AlphaNumericIdentifierGenerator(9, 9, 9, Collections.singletonList(MIN_TOKEN_LENGTH_VALIDATOR));
        return Collections.singletonList(buildWrapperForInvalidGeneratorRule(executable));
    }

    @Override
    protected List<Callable<? extends IdentifierGenerator<?>>> getParamsConformRules() {
        List<Callable<? extends IdentifierGenerator<?>>> list = new ArrayList<>();
        list.add(() -> new AlphaNumericIdentifierGenerator(10, 10, 10, Collections.singletonList(MIN_TOKEN_LENGTH_VALIDATOR)));
        list.add(() -> new AlphaNumericIdentifierGenerator(20, 11, 5, Collections.singletonList(MIN_TOKEN_LENGTH_VALIDATOR)));
        return list;
    }

    @Override
    protected List<InvalidConstructorInvocationWrapper> getParamsWithRandomDoNotConformRules() {
        Executable executable = () -> new AlphaNumericIdentifierGenerator(new Random(), 9, 9, 9, Collections.singletonList(MIN_TOKEN_LENGTH_VALIDATOR));
        return Collections.singletonList(buildWrapperForInvalidGeneratorRule(executable));
    }

    @Override
    protected List<Callable<? extends IdentifierGenerator<?>>> getParamsWithRandomConformRules() {
        List<Callable<? extends IdentifierGenerator<?>>> list = new ArrayList<>();
        list.add(() -> new AlphaNumericIdentifierGenerator(new Random(), 10, 10, 10, Collections.singletonList(MIN_TOKEN_LENGTH_VALIDATOR)));
        list.add(() -> new AlphaNumericIdentifierGenerator(new Random(), 20, 11, 3, Collections.singletonList(MIN_TOKEN_LENGTH_VALIDATOR)));
        return list;
    }
}
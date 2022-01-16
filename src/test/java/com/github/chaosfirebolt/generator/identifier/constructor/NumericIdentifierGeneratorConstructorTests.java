/*
 * Copyright 2021-2022 Boyan Georgiev
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

package com.github.chaosfirebolt.generator.identifier.constructor;

import com.github.chaosfirebolt.generator.identifier.IdentifierGenerator;
import com.github.chaosfirebolt.generator.identifier.impl.NumericIdentifierGenerator;
import org.junit.jupiter.api.function.Executable;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by ChaosFire on 23-Dec-21
 */
public class NumericIdentifierGeneratorConstructorTests extends IdentifierGeneratorConstructorTests {

    @Override
    protected List<InvalidConstructorInvocationWrapper> getLengthParamNegative() {
        int length = -6;
        return Collections.singletonList(buildWrapperForIllegalArgument(() -> new NumericIdentifierGenerator(length), length));
    }

    @Override
    protected List<InvalidConstructorInvocationWrapper> getLengthParamZero() {
        int length = 0;
        return Collections.singletonList(buildWrapperForIllegalArgument(() -> new NumericIdentifierGenerator(length), length));
    }

    @Override
    protected List<Callable<? extends IdentifierGenerator<?>>> getValidLengthParams() {
        return Collections.singletonList(() -> new NumericIdentifierGenerator(9));
    }

    @Override
    protected List<InvalidConstructorInvocationWrapper> getParamsDoNotConformRules() {
        Executable executable = () -> new NumericIdentifierGenerator(9, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR));
        return Collections.singletonList(buildWrapperForInvalidGeneratorRule(executable));
    }

    @Override
    protected List<Callable<? extends IdentifierGenerator<?>>> getParamsConformRules() {
        return Collections.singletonList(() -> new NumericIdentifierGenerator(30, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR)));
    }

    @Override
    protected List<InvalidConstructorInvocationWrapper> getParamsWithRandomDoNotConformRules() {
        Executable executable = () -> new NumericIdentifierGenerator(new Random(), 9, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR));
        return Collections.singletonList(buildWrapperForInvalidGeneratorRule(executable));
    }

    @Override
    protected List<Callable<? extends IdentifierGenerator<?>>> getParamsWithRandomConformRules() {
        return Collections.singletonList(() -> new NumericIdentifierGenerator(new Random(), 40, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR)));
    }
}
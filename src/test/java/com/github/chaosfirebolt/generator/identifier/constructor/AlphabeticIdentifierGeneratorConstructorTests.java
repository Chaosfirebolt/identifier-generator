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
import com.github.chaosfirebolt.generator.identifier.impl.AlphabeticIdentifierGenerator;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by ChaosFire on 23-Dec-21
 */
public class AlphabeticIdentifierGeneratorConstructorTests extends IdentifierGeneratorConstructorTests {

    @Override
    protected List<InvalidConstructorInvocationWrapper> getLengthParamNegative() {
        List<InvalidConstructorInvocationWrapper> list = new ArrayList<>();
        list.add(buildWrapperForIllegalArgument(() -> new AlphabeticIdentifierGenerator(-1, 9), -1));
        list.add(buildWrapperForIllegalArgument(() -> new AlphabeticIdentifierGenerator(9, -3), -3));
        return list;
    }

    @Override
    protected List<InvalidConstructorInvocationWrapper> getLengthParamZero() {
        List<InvalidConstructorInvocationWrapper> list = new ArrayList<>();
        list.add(buildWrapperForIllegalArgument(() -> new AlphabeticIdentifierGenerator(0, 9), 0));
        list.add(buildWrapperForIllegalArgument(() -> new AlphabeticIdentifierGenerator(9, 0), 0));
        return list;
    }

    @Override
    protected List<Callable<? extends IdentifierGenerator<?>>> getValidLengthParams() {
        return Collections.singletonList(() -> new AlphabeticIdentifierGenerator(5, 5));
    }

    @Override
    protected List<InvalidConstructorInvocationWrapper> getParamsDoNotConformRules() {
        Executable executable = () -> new AlphabeticIdentifierGenerator(9, 9, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR));
        return Collections.singletonList(buildWrapperForInvalidGeneratorRule(executable));
    }

    @Override
    protected List<Callable<? extends IdentifierGenerator<?>>> getParamsConformRules() {
        List<Callable<? extends IdentifierGenerator<?>>> list = new ArrayList<>();
        list.add(() -> new AlphabeticIdentifierGenerator(15, 15, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR)));
        list.add(() -> new AlphabeticIdentifierGenerator(20, 11, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR)));
        return list;
    }

    @Override
    protected List<InvalidConstructorInvocationWrapper> getParamsWithRandomDoNotConformRules() {
        Executable executable = () -> new AlphabeticIdentifierGenerator(new Random(), 9, 9, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR));
        return Collections.singletonList(buildWrapperForInvalidGeneratorRule(executable));
    }

    @Override
    protected List<Callable<? extends IdentifierGenerator<?>>> getParamsWithRandomConformRules() {
        List<Callable<? extends IdentifierGenerator<?>>> list = new ArrayList<>();
        list.add(() -> new AlphabeticIdentifierGenerator(new Random(), 15, 15, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR)));
        list.add(() -> new AlphabeticIdentifierGenerator(new Random(), 20, 11, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR)));
        return list;
    }
}
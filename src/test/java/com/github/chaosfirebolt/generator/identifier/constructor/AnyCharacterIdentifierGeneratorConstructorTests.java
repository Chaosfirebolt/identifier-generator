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
import com.github.chaosfirebolt.generator.identifier.impl.AnyCharacterIdentifierGenerator;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by ChaosFire on 23-Dec-21
 */
public class AnyCharacterIdentifierGeneratorConstructorTests extends IdentifierGeneratorConstructorTests {

    @Override
    protected List<InvalidConstructorInvocationWrapper> getLengthParamNegative() {
        List<InvalidConstructorInvocationWrapper> list = new ArrayList<>();
        list.add(buildWrapperForIllegalArgument(() -> new AnyCharacterIdentifierGenerator(-1, 9, 7, 8), -1));
        list.add(buildWrapperForIllegalArgument(() -> new AnyCharacterIdentifierGenerator(9, -3, 1, 3), -3));
        list.add(buildWrapperForIllegalArgument(() -> new AnyCharacterIdentifierGenerator(9, 3, -1, 3), -1));
        list.add(buildWrapperForIllegalArgument(() -> new AnyCharacterIdentifierGenerator(9, 3, 1, -3), -3));
        return list;
    }

    @Override
    protected List<InvalidConstructorInvocationWrapper> getLengthParamZero() {
        List<InvalidConstructorInvocationWrapper> list = new ArrayList<>();
        list.add(buildWrapperForIllegalArgument(() -> new AnyCharacterIdentifierGenerator(0, 9, 7, 8), 0));
        list.add(buildWrapperForIllegalArgument(() -> new AnyCharacterIdentifierGenerator(9, 0, 1, 3), 0));
        list.add(buildWrapperForIllegalArgument(() -> new AnyCharacterIdentifierGenerator(9, 3, 0, 3), 0));
        list.add(buildWrapperForIllegalArgument(() -> new AnyCharacterIdentifierGenerator(9, 3, 1, 0), 0));
        return list;
    }

    @Override
    protected List<Callable<? extends IdentifierGenerator<?>>> getValidLengthParams() {
        return Collections.singletonList(() -> new AnyCharacterIdentifierGenerator(3, 4, 5, 6));
    }

    @Override
    protected List<InvalidConstructorInvocationWrapper> getParamsDoNotConformRules() {
        int length = 7;
        Executable executable = () -> new AnyCharacterIdentifierGenerator(length, length, length, length, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR));
        return Collections.singletonList(buildWrapperForInvalidGeneratorRule(executable));
    }

    @Override
    protected List<Callable<? extends IdentifierGenerator<?>>> getParamsConformRules() {
        int ten = 10;
        int five = 5;
        return Collections.singletonList(() -> new AnyCharacterIdentifierGenerator(ten, ten, five, five, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR)));
    }

    @Override
    protected List<InvalidConstructorInvocationWrapper> getParamsWithRandomDoNotConformRules() {
        int length = 7;
        Executable executable = () -> new AnyCharacterIdentifierGenerator(new Random(), length, length, length, length, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR));
        return Collections.singletonList(buildWrapperForInvalidGeneratorRule(executable));
    }

    @Override
    protected List<Callable<? extends IdentifierGenerator<?>>> getParamsWithRandomConformRules() {
        int ten = 10;
        int five = 5;
        return Collections.singletonList(() -> new AnyCharacterIdentifierGenerator(new Random(), ten, ten, five, five, Collections.singletonList(MIN_IDENTIFIER_LENGTH_VALIDATOR)));
    }
}

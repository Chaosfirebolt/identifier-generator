/*
 * Copyright 2021-2023 Boyan Georgiev
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

package com.github.chaosfirebolt.generator.identifier.api.string.builders;

import com.github.chaosfirebolt.generator.identifier.api.string.NumericGeneratorBuilder;
import com.github.chaosfirebolt.generator.identifier.api.string.part.Part;
import com.github.chaosfirebolt.generator.identifier.internal.builders.TypeSpecificStringIdentifierBuilder;
import com.github.chaosfirebolt.generator.identifier.internal.util.OptionalUtility;
import org.apiguardian.api.API;

import java.util.List;
import java.util.OptionalInt;

/**
 * Specification of {@link StringIdentifierGeneratorBuilder} to allow easier setup for numeric identifier generators.
 */
@API(status = API.Status.STABLE)
public final class NumericIdentifierGeneratorBuilder extends TypeSpecificStringIdentifierBuilder<NumericIdentifierGeneratorBuilder> implements NumericGeneratorBuilder<NumericIdentifierGeneratorBuilder> {

    private int numericLength;
    private int minNumericLength;

    NumericIdentifierGeneratorBuilder() {
    }

    @Override
    public NumericIdentifierGeneratorBuilder setNumericLength(int numericLength) {
        this.numericLength = numericLength;
        return this;
    }

    @Override
    public NumericIdentifierGeneratorBuilder setMinNumericLength(int minNumericLength) {
        this.minNumericLength = minNumericLength;
        return this;
    }

    @Override
    public int getNumericLength() {
        return this.numericLength;
    }

    @Override
    public OptionalInt getMinNumericLength() {
        return OptionalUtility.fromInt(this.minNumericLength);
    }

    @Override
    protected List<Part> getParts() {
        return List.of(createNumericPart());
    }
}

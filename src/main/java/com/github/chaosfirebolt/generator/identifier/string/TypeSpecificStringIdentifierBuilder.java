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

package com.github.chaosfirebolt.generator.identifier.string;

import com.github.chaosfirebolt.generator.identifier.string.part.Part;
import com.github.chaosfirebolt.generator.identifier.string.rule.BaseGeneratorRule;
import com.github.chaosfirebolt.generator.identifier.string.rule.GeneratorRule;

import java.util.List;

/**
 * Builder for subclasses of {@link StringIdentifierGenerator}.
 * @param <T> concrete type of the builder
 */
abstract class TypeSpecificStringIdentifierBuilder<T extends TypeSpecificStringIdentifierBuilder<T>> extends BaseStringIdentifierGeneratorBuilder<T> {

    TypeSpecificStringIdentifierBuilder() {
    }

    @Override
    public final T setGeneratorRule(GeneratorRule generatorRule) {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " does not support directly setting generator rule");
    }

    @Override
    final GeneratorRule getGeneratorRule() {
        return new BaseGeneratorRule(getParts());
    }

    abstract List<Part> getParts();
}

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

package com.github.chaosfirebolt.generator.identifier.string.rule;

import com.github.chaosfirebolt.generator.identifier.string.part.NumericPart;

import java.util.Collections;

/**
 * {@link GeneratorRule} rule for numeric identifiers.
 * <br>
 * Created by ChaosFire on 12/6/2021
 */
public class NumericGeneratorRule extends BaseGeneratorRule {

    /**
     * Constructs new NumericGeneratorRule with specified length for numeric part.
     * @param length desired length of numeric part
     */
    public NumericGeneratorRule(int length) {
        super(Collections.singletonList(new NumericPart(length)));
    }
}
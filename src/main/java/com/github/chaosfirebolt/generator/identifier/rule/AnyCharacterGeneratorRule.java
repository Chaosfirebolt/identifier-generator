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

package com.github.chaosfirebolt.generator.identifier.rule;

import com.github.chaosfirebolt.generator.identifier.part.*;

import java.util.Arrays;

/**
 * {@link GeneratorRule} rule for identifiers consisting of alphanumeric and special characters.
 * <br>
 * Created by ChaosFire on 12/6/2021
 */
public class AnyCharacterGeneratorRule extends BaseGeneratorRule {
    /**
     * Constructs new AnyCharacterGeneratorRule with specified lengths for alphanumeric and special character parts.
     * @param lowerCaseLength desired length of lower case part
     * @param upperCaseLength desired length of upper case part
     * @param numericLength desired length of numeric part
     * @param specialCharLength desired length of special characters part
     */
    public AnyCharacterGeneratorRule(int lowerCaseLength, int upperCaseLength, int numericLength, int specialCharLength) {
        super(Arrays.asList(new LowerAlphabeticPart(lowerCaseLength), new UpperAlphabeticPart(upperCaseLength),
                new NumericPart(numericLength), new SpecialCharacterPart(specialCharLength)));
    }
}
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

package com.github.chaosfirebolt.generator.token;

import com.github.chaosfirebolt.generator.token.part.NumericTokenPart;
import com.github.chaosfirebolt.generator.token.part.SpecialCharacterTokenPart;
import com.github.chaosfirebolt.generator.token.part.UpperAlphabeticTokenPart;
import com.github.chaosfirebolt.generator.token.rule.BaseGeneratorRule;
import com.github.chaosfirebolt.generator.token.rule.GeneratorRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by ChaosFire on 22-Dec-21
 */
public class StringIdentifierGeneratorMethodTests extends IdentifierGeneratorMethodTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringIdentifierGeneratorMethodTests.class);

    public StringIdentifierGeneratorMethodTests() {
        super(LOGGER, 49, 233, 233);
    }

    @Override
    protected BaseIdentifierGenerator<String> getGenerator() {
        GeneratorRule generatorRule = new BaseGeneratorRule(Arrays.asList(new SpecialCharacterTokenPart(11), new UpperAlphabeticTokenPart(111), new NumericTokenPart(111)));
        return new StringIdentifierGenerator(generatorRule);
    }
}
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

import com.github.chaosfirebolt.generator.token.impl.UuidStringTokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ChaosFire on 22-Dec-21
 */
public class UuidStringTokenGeneratorMethodTests extends TokenGeneratorMethodTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(UuidStringTokenGeneratorMethodTests.class);

    public UuidStringTokenGeneratorMethodTests() {
        super(LOGGER, 20, 36, 36);
    }

    @Override
    protected BaseTokenGenerator<String> getGenerator() {
        return new UuidStringTokenGenerator();
    }

    @Override
    public void generateTokenWithLength_LengthIsMoreThanMinimum_TokenShouldHaveCorrectLength() {
        //test not applicable for uuid token generator
    }

    @Override
    public void generateUniqueTokenWithLength_LengthIsMoreThanMinimum_TokenShouldHaveCorrectLength() {
        //test not applicable for uuid token generator
    }
}
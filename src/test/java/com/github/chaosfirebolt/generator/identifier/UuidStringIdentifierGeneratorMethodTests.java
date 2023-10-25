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

package com.github.chaosfirebolt.generator.identifier;

import com.github.chaosfirebolt.generator.identifier.impl.UuidStringIdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ChaosFire on 22-Dec-21
 */
public class UuidStringIdentifierGeneratorMethodTests extends IdentifierGeneratorMethodTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(UuidStringIdentifierGeneratorMethodTests.class);

    public UuidStringIdentifierGeneratorMethodTests() {
        super(LOGGER, 20, 36, 36);
    }

    @Override
    protected BaseIdentifierGenerator<String> getGenerator() {
        return new UuidStringIdentifierGenerator();
    }

    @Override
    public void generateIdentifierWithLength_LengthIsMoreThanMinimum_IdentifierShouldHaveCorrectLength() {
        //test not applicable for uuid identifier generator
    }

    @Override
    public void generateUniqueIdentifierWithLength_LengthIsMoreThanMinimum_IdentifierShouldHaveCorrectLength() {
        //test not applicable for uuid identifier generator
    }
}
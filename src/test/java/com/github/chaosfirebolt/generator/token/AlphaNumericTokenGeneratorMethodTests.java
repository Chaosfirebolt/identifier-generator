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

import com.github.chaosfirebolt.generator.token.impl.AlphaNumericTokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ChaosFire on 22-Dec-21
 */
public class AlphaNumericTokenGeneratorMethodTests extends TokenGeneratorMethodTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlphaNumericTokenGeneratorMethodTests.class);

    public AlphaNumericTokenGeneratorMethodTests() {
        super(LOGGER, 5, 41, 41);
    }

    @Override
    protected BaseTokenGenerator<String> getGenerator() {
        return new AlphaNumericTokenGenerator(15, 15, 11);
    }
}
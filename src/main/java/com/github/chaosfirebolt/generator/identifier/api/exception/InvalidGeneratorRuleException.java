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

package com.github.chaosfirebolt.generator.identifier.api.exception;

import com.github.chaosfirebolt.generator.identifier.api.string.rule.GeneratorRule;
import org.apiguardian.api.API;

/**
 * This exception signals, that a {@link GeneratorRule} is not valid.
 * <br>
 * Created by ChaosFire on 12/5/2021
 */
@API(status = API.Status.STABLE)
public class InvalidGeneratorRuleException extends RuntimeException {

    /**
     * Constructs new InvalidGeneratorRuleException with the specified message
     * @param message the message
     */
    public InvalidGeneratorRuleException(String message) {
        super(message);
    }
}

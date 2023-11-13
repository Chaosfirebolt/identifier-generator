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

package com.github.chaosfirebolt.generator.identifier.api.string.validation;

import com.github.chaosfirebolt.generator.identifier.api.string.rule.GeneratorRule;
import org.apiguardian.api.API;

/**
 * Represents generator for error messages
 * <br>
 * Created by ChaosFire on 12/5/2021
 */
@API(status = API.Status.STABLE)
@FunctionalInterface
public interface ErrorMessageCreator {

    /**
     * Creates error message from the supplied {@link GeneratorRule}
     * @param rule rule to be used for message generation
     * @return created error message
     */
    String create(GeneratorRule rule);
}

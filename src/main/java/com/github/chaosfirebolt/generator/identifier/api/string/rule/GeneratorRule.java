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

package com.github.chaosfirebolt.generator.identifier.api.string.rule;

import com.github.chaosfirebolt.generator.identifier.api.string.part.Part;
import org.apiguardian.api.API;

import java.util.List;

/**
 * Represents a rule for identifier generation.
 * <br>
 * Created by ChaosFire on 12/5/2021
 */
@API(status = API.Status.STABLE)
public interface GeneratorRule {

    /**
     * Get identifier parts required by this rule
     * @return the identifier parts
     */
    List<Part> getParts();

    /**
     * Get identifier length required by this rule
     * @return total length of the identifier
     */
    int getLength();

    /**
     * Get the minimum length required by this rule
     * @return the minimum length of the identifier
     */
    default int getMinLength() {
        return this.getLength();
    }
}
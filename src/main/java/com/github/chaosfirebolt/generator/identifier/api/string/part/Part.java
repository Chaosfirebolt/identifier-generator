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

package com.github.chaosfirebolt.generator.identifier.api.string.part;

import org.apiguardian.api.API;

import java.util.List;

/**
 * Represents a part of this identifier.
 * <br>
 * Created by ChaosFire on 12/5/2021
 */
@API(status = API.Status.STABLE, since = "2.0.0")
public interface Part {

  /**
   * Gets the length of this identifier part
   *
   * @return the length of this part
   */
  int getLength();

  /**
   * Get the minimum length for this identifier part
   *
   * @return the minimum length of this part
   */
  default int minLength() {
    return this.getLength();
  }

  /**
   * Get possible characters for this identifier part.
   *
   * @return possible characters for this part
   */
  List<Character> getCharacters();
}

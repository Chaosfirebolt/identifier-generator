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

package com.github.chaosfirebolt.generator.identifier.internal.util;

import org.apiguardian.api.API;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility methods for characters
 * <br>
 * Created by ChaosFire on 12/5/2021
 */
@API(status = API.Status.INTERNAL, since = "2.0.0")
public final class CharacterUtility {

  private CharacterUtility() {
    throw new RuntimeException("No instances allowed");
  }

  /**
   * Creates a list of characters from supplied range of integers cast to char
   *
   * @param start range start, inclusive
   * @param end   range end, exclusive
   * @return list of chars
   */
  public static List<Character> characterListFromIntRange(int start, int end) {
    List<Character> result = new ArrayList<>(end - start);
    for (int i = start; i < end; i++) {
      result.add((char) i);
    }
    return result;
  }
}

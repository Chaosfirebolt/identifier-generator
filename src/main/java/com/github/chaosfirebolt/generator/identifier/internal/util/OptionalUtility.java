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

import java.util.OptionalInt;
import java.util.function.IntPredicate;

/**
 * Utility methods related to optionals.
 */
@API(status = API.Status.INTERNAL, since = "2.0.0")
public final class OptionalUtility {

  private OptionalUtility() {
    throw new RuntimeException("No instances allowed");
  }

  /**
   * @param value          the value
   * @param emptyCondition condition to determine, whether the optional should be empty
   * @return an optional int
   */
  public static OptionalInt fromInt(int value, IntPredicate emptyCondition) {
    return emptyCondition.test(value) ? OptionalInt.empty() : OptionalInt.of(value);
  }

  /**
   * Creates an {@link OptionalInt} from provided value. If {@code value} is less than 1, returns empty optional.
   *
   * @param value the value
   * @return an optional int
   */
  public static OptionalInt fromInt(int value) {
    return fromInt(value, val -> val < 1);
  }
}

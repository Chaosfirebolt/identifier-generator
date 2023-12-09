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

package com.github.chaosfirebolt.generator.identifier.sequence;

import java.util.function.Function;

class NumberPaddingMapper implements Function<Integer, String> {

  private final char padElement;
  private final int targetLength;

  NumberPaddingMapper(char padElement, int targetLength) {
    this.padElement = padElement;
    this.targetLength = targetLength;
  }

  @Override
  public String apply(Integer integer) {
    String numberAsString = integer.toString();
    if (numberAsString.length() >= this.targetLength) {
      return numberAsString;
    }
    int difference = this.targetLength - numberAsString.length();
    return String.valueOf(this.padElement).repeat(difference) + numberAsString;
  }
}

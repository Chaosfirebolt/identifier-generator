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

package com.github.chaosfirebolt.generator.part;

import com.github.chaosfirebolt.generator.identifier.api.string.part.BasePart;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by ChaosFire on 19-Dec-21
 */
public class BasePartConstructorTests {

  @Test
  public void intIntListConstructor_ListIsNull_ShouldThrowNullPointerException() {
    assertThrows(NullPointerException.class, () -> new BasePart(5, 3, null));
  }

  @Test
  public void intIntListConstructor_ListIsEmpty_ShouldThrowIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> new BasePart(9, 8, Collections.emptyList()));
  }

  @Test
  public void intListConstructor_ListIsNull_ShouldThrowNullPointerException() {
    assertThrows(NullPointerException.class, () -> new BasePart(5, null));
  }

  @Test
  public void intListConstructor_ListIsEmpty_ShouldThrowIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> new BasePart(9, Collections.emptyList()));
  }
}
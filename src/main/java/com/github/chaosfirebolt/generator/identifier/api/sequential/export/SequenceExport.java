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

package com.github.chaosfirebolt.generator.identifier.api.sequential.export;

import org.apiguardian.api.API;

/**
 * The default implementation for Export and Import.
 *
 * @param initialElement the initial element
 * @param latestElement the latest extracted element
 * @param <E> type of the data contained
 */
@API(status = API.Status.EXPERIMENTAL, since = "2.1.0")
public record SequenceExport<E>(E initialElement, E latestElement) implements Export<E>, Import<E> {

  @Override
  public Import<E> toImport() {
    return this;
  }

  @Override
  public E initialValue() {
    return this.initialElement();
  }

  @Override
  public E latestValue() {
    return this.latestElement();
  }
}

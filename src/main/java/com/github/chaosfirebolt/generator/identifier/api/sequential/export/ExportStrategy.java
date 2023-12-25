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
 * A strategy to create an export object from provided data.
 *
 * @param <E> type of the data contained, that the {@link Export} will contain
 */
@API(status = API.Status.EXPERIMENTAL, since = "2.1.0")
public interface ExportStrategy<E> {

  /**
   * Creates an export from the initial and latest values.
   *
   * @param initialValue the initial value of a sequence
   * @param latestValue  the latest value extracted from a sequence
   * @return an export
   */
  Export<E> apply(E initialValue, E latestValue);
}

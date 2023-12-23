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
 * Data exported from {@link Exportable}.
 *
 * @param <E>
 */
@API(status = API.Status.STABLE, since = "2.1.0")
public interface Export<E> {

  /**
   * Gets the initial value.
   *
   * @return the initial value
   */
  E initialValue();

  /**
   * Gets the latest generated value.
   *
   * @return the latest value
   */
  E latestValue();
}

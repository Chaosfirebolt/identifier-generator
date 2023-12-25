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
 * An object, whose internal state can be exported.
 *
 * @param <E> type of the data the export holds.
 */
@API(status = API.Status.EXPERIMENTAL, since = "2.1.0")
public interface Exportable<E> {

  /**
   * Creates an export according to the specified strategy.
   *
   * @param strategy strategy to create the export
   * @return exported data
   */
  Export<E> export(ExportStrategy<E> strategy);

  /**
   * Creates an export using a default strategy.
   *
   * @return exported data
   */
  default Export<E> export() {
    return export(SequenceExport::new);
  }
}

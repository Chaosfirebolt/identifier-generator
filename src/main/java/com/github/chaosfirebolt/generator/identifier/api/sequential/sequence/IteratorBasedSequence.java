/*
 * Copyright 2021-2024 Boyan Georgiev
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

package com.github.chaosfirebolt.generator.identifier.api.sequential.sequence;

import com.github.chaosfirebolt.generator.identifier.api.sequential.export.Export;
import com.github.chaosfirebolt.generator.identifier.api.sequential.export.ExportStrategy;
import org.apiguardian.api.API;

import java.util.Iterator;
import java.util.Optional;

@API(status = API.Status.INTERNAL, since = "2.2.0")
class IteratorBasedSequence<E> implements Sequence<E> {

  private static final String EXPORT_NOT_SUPPORTED = "Export operation not supported";

  protected Iterator<E> iterator;

  IteratorBasedSequence(Iterator<E> iterator) {
    this.iterator = iterator;
  }

  @Override
  public Export<E> export(ExportStrategy<E> strategy) {
    throw new UnsupportedOperationException(EXPORT_NOT_SUPPORTED);
  }

  @Override
  public Export<E> export() {
    throw new UnsupportedOperationException(EXPORT_NOT_SUPPORTED);
  }

  @Override
  public Optional<E> next() {
    if (this.iterator.hasNext()) {
      return Optional.of(this.iterator.next());
    }
    return Optional.empty();
  }

  @Override
  public void reset() {
    throw new UnsupportedOperationException("Reset not supported");
  }
}

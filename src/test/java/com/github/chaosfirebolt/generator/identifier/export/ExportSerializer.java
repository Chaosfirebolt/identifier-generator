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

package com.github.chaosfirebolt.generator.identifier.export;

import com.github.chaosfirebolt.generator.identifier.api.sequential.export.Export;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

final class ExportSerializer {

  static <E> byte[] serialize(Export<E> export) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try (ObjectOutputStream outputStream = new ObjectOutputStream(baos)) {
      outputStream.writeObject(export);
    } catch (IOException exc) {
      throw new RuntimeException(exc);
    }
    return baos.toByteArray();
  }

  static <E> Export<E> deserialize(byte[] bytes) {
    ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
    try (ObjectInputStream inputStream = new ObjectInputStream(bais)) {
      @SuppressWarnings("unchecked")
      Export<E> export = (Export<E>) inputStream.readObject();
      return export;
    } catch (IOException | ClassNotFoundException exc) {
      throw new RuntimeException(exc);
    }
  }
}

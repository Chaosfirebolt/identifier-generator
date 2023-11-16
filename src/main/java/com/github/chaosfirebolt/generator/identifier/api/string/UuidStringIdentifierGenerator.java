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

package com.github.chaosfirebolt.generator.identifier.api.string;

import com.github.chaosfirebolt.generator.identifier.api.string.validation.NoOpRuleValidator;
import org.apiguardian.api.API;

import java.util.UUID;

/**
 * Implementation generating identifiers as random uuid strings.
 * <br>
 * Created by ChaosFire on 12/5/2021
 */
@API(status = API.Status.STABLE, since = "2.0.0")
public class UuidStringIdentifierGenerator extends StringIdentifierGenerator {

  /**
   * Constructs new UuidStringIdentifierGenerator.
   */
  public UuidStringIdentifierGenerator() {
    super(null, null, new NoOpRuleValidator());
  }

  @Override
  public String generate() {
    return generateRandomUuidString();
  }

  @Override
  public String generate(int identifierLength) {
    return generateRandomUuidString();
  }

  private static String generateRandomUuidString() {
    return UUID.randomUUID().toString();
  }
}

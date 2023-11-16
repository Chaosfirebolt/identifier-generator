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

package com.github.chaosfirebolt.generator.identifier.api.string.builders;

import com.github.chaosfirebolt.generator.identifier.api.string.StringIdentifierGenerator;
import com.github.chaosfirebolt.generator.identifier.api.string.rule.GeneratorRule;
import com.github.chaosfirebolt.generator.identifier.internal.builders.BaseStringIdentifierGeneratorBuilder;
import org.apiguardian.api.API;

/**
 * Builder for {@link StringIdentifierGenerator}s.
 * <br>
 * Use this builder, if complete control over the used {@link GeneratorRule} is needed.
 */
@API(status = API.Status.STABLE, since = "2.0.0")
public final class StringIdentifierGeneratorBuilder extends BaseStringIdentifierGeneratorBuilder<StringIdentifierGeneratorBuilder> {

  @API(status = API.Status.INTERNAL, since = "2.0.0")
  StringIdentifierGeneratorBuilder() {
  }

  @Override
  public StringIdentifierGeneratorBuilder setGeneratorRule(GeneratorRule generatorRule) {
    //expose the functionality
    return super.setGeneratorRule(generatorRule);
  }
}

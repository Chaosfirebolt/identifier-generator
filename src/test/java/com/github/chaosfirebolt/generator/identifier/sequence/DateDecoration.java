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

import com.github.chaosfirebolt.generator.identifier.api.sequential.sequence.SequenceDecoration;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

class DateDecoration implements SequenceDecoration<String> {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

  private final Clock clock;
  private LocalDateTime validUntil;

  DateDecoration(Clock clock) {
    this.clock = clock;
  }

  @Override
  public Optional<String> apply(String input) {
    LocalDateTime now = LocalDateTime.now(this.clock);
    if (this.validUntil == null) {
      this.validUntil = now.plusDays(1);
    }
    if (!now.isBefore(this.validUntil)) {
      return Optional.empty();
    }
    String value = FORMATTER.format(now) + input;
    return Optional.of(value);
  }

  @Override
  public void reset() {
    this.validUntil = null;
  }
}

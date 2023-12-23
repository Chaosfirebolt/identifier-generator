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

package com.github.chaosfirebolt.generator.identifier.sequential;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

class DurationSkippingClock extends Clock {

  private final ZoneId zoneId;
  private final Duration skipDuration;
  private Instant currentInstant;

  DurationSkippingClock(ZoneId zoneId, Duration skipDuration, Instant currentInstant) {
    this.zoneId = zoneId;
    this.skipDuration = skipDuration;
    this.currentInstant = currentInstant;
  }

  @Override
  public ZoneId getZone() {
    return this.zoneId;
  }

  @Override
  public Clock withZone(ZoneId zone) {
    if (this.zoneId.equals(zone)) {
      return this;
    }
    return new DurationSkippingClock(zone, this.skipDuration, this.currentInstant);
  }

  @Override
  public Instant instant() {
    Instant current = this.currentInstant;
    this.currentInstant = current.plus(this.skipDuration);
    return current;
  }
}

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

package com.github.chaosfirebolt.generator.identifier.api.exception;

import org.apiguardian.api.API;

/**
 * This exception signals, that the maximum number of attempts to generate unique identifier have been reached.
 * <br>
 * Created by ChaosFire on 21-Dec-21
 */
@API(status = API.Status.STABLE, since = "2.0.0")
public class TooManyAttemptsException extends RuntimeException {

  /**
   * @param message detailed message for the exception
   */
  public TooManyAttemptsException(String message) {
    super(message);
  }
}

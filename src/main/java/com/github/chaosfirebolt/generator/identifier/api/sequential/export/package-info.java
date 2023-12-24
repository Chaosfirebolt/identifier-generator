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

/**
 * <strong>Experimental</strong> feature.
 * The goal is to be able to persist the state of a sequence based generators, in order to restore them with a state, which allows to continue generating identifiers from where they left of.
 * For example after application restart.
 * <br>
 * Use with <strong>caution</strong>! The classes in this package <strong>may</strong> be subject to sudden backwards incompatible changes.
 */
@API(status = API.Status.EXPERIMENTAL, since = "2.1.0")
package com.github.chaosfirebolt.generator.identifier.api.sequential.export;

import org.apiguardian.api.API;
/*
 * Copyright 2021-2022 Boyan Georgiev
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

package com.github.chaosfirebolt.generator.identifier.constructor;

import com.github.chaosfirebolt.generator.identifier.IdentifierGenerator;
import com.github.chaosfirebolt.generator.identifier.exception.InvalidGeneratorRuleException;
import com.github.chaosfirebolt.generator.identifier.validation.BaseRuleValidator;
import com.github.chaosfirebolt.generator.identifier.validation.RuleValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by ChaosFire on 23-Dec-21
 */
public abstract class IdentifierGeneratorConstructorTests {

    private static final String ERROR_MESSAGE = "identifier length can't be less than 30";
    protected static final RuleValidator MIN_IDENTIFIER_LENGTH_VALIDATOR = new BaseRuleValidator(rule -> rule.getLength() >= 30, rule -> ERROR_MESSAGE);

    private final List<InvalidConstructorInvocationWrapper> lengthParamNegative;
    private final List<InvalidConstructorInvocationWrapper> lengthParamZero;
    private final List<Callable<? extends IdentifierGenerator<?>>> validLengthParams;
    private final List<InvalidConstructorInvocationWrapper> paramsDoNotConformRules;
    private final List<Callable<? extends IdentifierGenerator<?>>> paramsConformRules;
    private final List<InvalidConstructorInvocationWrapper> paramsWithRandomDoNotConformRules;
    private final List<Callable<? extends IdentifierGenerator<?>>> paramsWithRandomConformRules;

    protected IdentifierGeneratorConstructorTests() {
        this.lengthParamNegative = this.getLengthParamNegative();
        this.lengthParamZero = this.getLengthParamZero();
        this.validLengthParams = this.getValidLengthParams();
        this.paramsDoNotConformRules = this.getParamsDoNotConformRules();
        this.paramsConformRules = this.getParamsConformRules();
        this.paramsWithRandomDoNotConformRules = this.getParamsWithRandomDoNotConformRules();
        this.paramsWithRandomConformRules = this.getParamsWithRandomConformRules();
    }

    protected abstract List<InvalidConstructorInvocationWrapper> getLengthParamNegative();

    protected abstract List<InvalidConstructorInvocationWrapper> getLengthParamZero();

    protected abstract List<Callable<? extends IdentifierGenerator<?>>> getValidLengthParams();

    protected abstract List<InvalidConstructorInvocationWrapper> getParamsDoNotConformRules();

    protected abstract List<Callable<? extends IdentifierGenerator<?>>> getParamsConformRules();

    protected abstract List<InvalidConstructorInvocationWrapper> getParamsWithRandomDoNotConformRules();

    protected abstract List<Callable<? extends IdentifierGenerator<?>>> getParamsWithRandomConformRules();

    protected static InvalidConstructorInvocationWrapper buildWrapperForIllegalArgument(Executable executable, int length) {
        return new InvalidConstructorInvocationWrapper(executable, IllegalArgumentException.class, String.format("Part length can't be less than '1', but was '%d'", length));
    }

    protected static InvalidConstructorInvocationWrapper buildWrapperForInvalidGeneratorRule(Executable executable) {
        return new InvalidConstructorInvocationWrapper(executable, InvalidGeneratorRuleException.class, ERROR_MESSAGE);
    }

    @Test
    public void anyLengthParamIsNegative_ShouldThrowInvalidArgumentException() {
        assertException(this.lengthParamNegative);
    }

    private static void assertException(List<InvalidConstructorInvocationWrapper> wrappers) {
        for (InvalidConstructorInvocationWrapper wrapper : wrappers) {
            Throwable throwable = assertThrows(wrapper.getExpectedException(), wrapper.getInvalidInvocation());
            assertEquals(wrapper.getExpectedErrorMessage(), throwable.getMessage());
        }
    }

    @Test
    public void anyLengthParamIsZero_ShouldThrowInvalidArgumentException() {
        assertException(this.lengthParamZero);
    }

    @Test
    public void validLengthParams_ShouldNotThrow() throws Throwable {
        assertInstancesGenerated(this.validLengthParams);
    }

    private static void assertInstancesGenerated(List<Callable<? extends IdentifierGenerator<?>>> constructorInvocations) throws Exception {
        Set<IdentifierGenerator<?>> instances = new HashSet<>(constructorInvocations.size());
        for (Callable<? extends IdentifierGenerator<?>> constructorInvocation : constructorInvocations) {
            IdentifierGenerator<?> instance = constructorInvocation.call();
            assertNotNull(instance, "New instance was null");
            boolean isNewInstance = instances.add(instance);
            assertTrue(isNewInstance, "Instance was reused");
        }
    }

    @Test
    public void lengthValidatorsConstructor_ParamsDoNotConformToRules_ShouldThrowInvalidGeneratorRuleException() {
        assertException(this.paramsDoNotConformRules);
    }

    @Test
    public void lengthValidatorsConstructor_ParamsConformToRules_ShouldNotThrow() throws Exception {
        assertInstancesGenerated(this.paramsConformRules);
    }

    @Test
    public void randomIntsValidatorsConstructor_ParamsDoNotConformToRules_ShouldThrowInvalidGeneratorRuleException() {
        assertException(this.paramsWithRandomDoNotConformRules);
    }

    @Test
    public void randomIntsValidatorsConstructor_ParamsConformToRules_ShouldNotThrow() throws Exception {
        assertInstancesGenerated(this.paramsWithRandomConformRules);
    }
}
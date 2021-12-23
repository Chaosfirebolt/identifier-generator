package com.github.chaosfirebolt.generator.token.constructor;

import com.github.chaosfirebolt.generator.token.TokenGenerator;
import com.github.chaosfirebolt.generator.token.exception.InvalidGeneratorRuleException;
import com.github.chaosfirebolt.generator.token.validation.BaseRuleValidator;
import com.github.chaosfirebolt.generator.token.validation.RuleValidator;
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
public abstract class TokenGeneratorConstructorTests {

    private static final String ERROR_MESSAGE = "Token length can't be less than 30";
    protected static final RuleValidator MIN_TOKEN_LENGTH_VALIDATOR = new BaseRuleValidator(rule -> rule.getLength() >= 30, rule -> ERROR_MESSAGE);

    private final List<InvalidConstructorInvocationWrapper> lengthParamNegative;
    private final List<InvalidConstructorInvocationWrapper> lengthParamZero;
    private final List<Callable<? extends TokenGenerator<?>>> validLengthParams;
    private final List<InvalidConstructorInvocationWrapper> paramsDoNotConformRules;
    private final List<Callable<? extends TokenGenerator<?>>> paramsConformRules;
    private final List<InvalidConstructorInvocationWrapper> paramsWithRandomDoNotConformRules;
    private final List<Callable<? extends TokenGenerator<?>>> paramsWithRandomConformRules;

    protected TokenGeneratorConstructorTests() {
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

    protected abstract List<Callable<? extends TokenGenerator<?>>> getValidLengthParams();

    protected abstract List<InvalidConstructorInvocationWrapper> getParamsDoNotConformRules();

    protected abstract List<Callable<? extends TokenGenerator<?>>> getParamsConformRules();

    protected abstract List<InvalidConstructorInvocationWrapper> getParamsWithRandomDoNotConformRules();

    protected abstract List<Callable<? extends TokenGenerator<?>>> getParamsWithRandomConformRules();

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

    private static void assertInstancesGenerated(List<Callable<? extends TokenGenerator<?>>> constructorInvocations) throws Exception {
        Set<TokenGenerator<?>> instances = new HashSet<>(constructorInvocations.size());
        for (Callable<? extends TokenGenerator<?>> constructorInvocation : constructorInvocations) {
            TokenGenerator<?> instance = constructorInvocation.call();
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
package com.github.chaosfirebolt.generator.rule;

import com.github.chaosfirebolt.generator.token.rule.GeneratorRule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by ChaosFire on 21-Dec-21
 */
public abstract class GeneratorRuleTests {

    private final GeneratorRule generatorRule;

    protected GeneratorRuleTests(GeneratorRule generatorRule) {
        this.generatorRule = generatorRule;
    }

    @Test
    public void getParts_ShouldReturnCorrectNumberOfParts() {
        int size = this.generatorRule.getParts().size();
        assertEquals(this.expectedNumberOfParts(), size);
    }

    protected abstract int expectedNumberOfParts();

    @Test
    public void getLength_ShouldReturnCorrectLength() {
        int length = this.generatorRule.getLength();
        assertEquals(this.expectedLength(), length);
    }

    protected abstract int expectedLength();

    @Test
    public void getMinLength_ShouldReturnCorrectMinLength() {
        int minLength = this.generatorRule.getMinLength();
        assertEquals(this.expectedMinLength(), minLength);
    }

    protected abstract int expectedMinLength();
}
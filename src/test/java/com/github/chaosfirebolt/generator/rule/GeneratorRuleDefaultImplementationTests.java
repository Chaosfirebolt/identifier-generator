package com.github.chaosfirebolt.generator.rule;

import com.github.chaosfirebolt.generator.token.part.TokenPart;
import com.github.chaosfirebolt.generator.token.part.UpperAlphabeticTokenPart;
import com.github.chaosfirebolt.generator.token.rule.GeneratorRule;

import java.util.Collections;
import java.util.List;

/**
 * Created by ChaosFire on 24-Dec-21
 */
public class GeneratorRuleDefaultImplementationTests extends GeneratorRuleTests {

    public GeneratorRuleDefaultImplementationTests() {
        super(createRule());
    }

    private static GeneratorRule createRule() {
        return new UnvalidatedGeneratorRule(Collections.singletonList(new UpperAlphabeticTokenPart(10)), 10);
    }

    @Override
    protected int expectedNumberOfParts() {
        return 1;
    }

    @Override
    protected int expectedLength() {
        return 10;
    }

    @Override
    protected int expectedMinLength() {
        return this.expectedLength();
    }

    private static final class UnvalidatedGeneratorRule implements GeneratorRule {

        private final List<TokenPart> parts;
        private final int length;

        private UnvalidatedGeneratorRule(List<TokenPart> parts, int length) {
            this.parts = parts;
            this.length = length;
        }

        @Override
        public List<TokenPart> getParts() {
            return this.parts;
        }

        @Override
        public int getLength() {
            return this.length;
        }
    }
}
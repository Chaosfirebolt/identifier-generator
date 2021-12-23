package com.github.chaosfirebolt.generator.token.constructor;

import com.github.chaosfirebolt.generator.token.StringTokenGenerator;
import com.github.chaosfirebolt.generator.token.exception.InvalidGeneratorRuleException;
import com.github.chaosfirebolt.generator.token.part.NumericTokenPart;
import com.github.chaosfirebolt.generator.token.part.TokenPart;
import com.github.chaosfirebolt.generator.token.rule.BaseGeneratorRule;
import com.github.chaosfirebolt.generator.token.rule.GeneratorRule;
import com.github.chaosfirebolt.generator.token.util.CharacterUtility;
import com.github.chaosfirebolt.generator.token.validation.BaseRuleValidator;
import com.github.chaosfirebolt.generator.token.validation.NoOpRuleValidator;
import com.github.chaosfirebolt.generator.token.validation.RuleValidator;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by ChaosFire on 23-Dec-21
 */
public class StringTokenGeneratorConstructorTests {

    private static final String ERROR_MESSAGE = "Token minimum length can not be less than 50";
    private static final RuleValidator VALIDATOR = new BaseRuleValidator(rule -> rule.getMinLength() >= 50, rule -> ERROR_MESSAGE);

    @Test
    public void ruleValidatorsConstructor_RuleDoesNotConform_ShouldThrowInvalidGeneratorRuleException() {
        TokenPart part = new NumericTokenPart(60, 10);
        GeneratorRule rule = new BaseGeneratorRule(Collections.singletonList(part));
        InvalidGeneratorRuleException exception = assertThrows(InvalidGeneratorRuleException.class, () -> new StringTokenGenerator(rule, Collections.singletonList(VALIDATOR)));
        assertEquals(ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    public void ruleValidatorsConstructor_RuleDoesConform_Test1_ShouldNotThrow() {
        TokenPart part = new NumericTokenPart(60, 50);
        GeneratorRule rule = new BaseGeneratorRule(Collections.singletonList(part));
        new StringTokenGenerator(rule, Collections.singletonList(VALIDATOR));
    }

    @Test
    public void ruleValidatorsConstructor_RuleDoesConform_Test2_ShouldNotThrow() {
        TokenPart part = new NumericTokenPart(60, 55);
        GeneratorRule rule = new BaseGeneratorRule(Collections.singletonList(part));
        new StringTokenGenerator(rule, Collections.singletonList(VALIDATOR));
    }

    @Test
    public void ruleValidatorsConstructor_NoOpValidator_ShouldNotThrow() {
        List<TokenPart> parts = Collections.singletonList(new UnvalidatedTokenPart(0, CharacterUtility.characterListFromIntRange(48, 58)));
        GeneratorRule rule = new BaseGeneratorRule(parts);
        new StringTokenGenerator(rule, Collections.singletonList(new NoOpRuleValidator()));
    }

    @Test
    public void randomRuleValidatorsConstructor_RuleDoesNotConform_ShouldThrowInvalidGeneratorRuleException() {
        TokenPart part = new NumericTokenPart(60, 10);
        GeneratorRule rule = new BaseGeneratorRule(Collections.singletonList(part));
        InvalidGeneratorRuleException exception = assertThrows(InvalidGeneratorRuleException.class, () -> new StringTokenGenerator(new Random(), rule, Collections.singletonList(VALIDATOR)));
        assertEquals(ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    public void randomRuleValidatorsConstructor_RuleDoesConform_Test1_ShouldNotThrow() {
        TokenPart part = new NumericTokenPart(60, 50);
        GeneratorRule rule = new BaseGeneratorRule(Collections.singletonList(part));
        new StringTokenGenerator(new Random(), rule, Collections.singletonList(VALIDATOR));
    }

    @Test
    public void randomRuleValidatorsConstructor_RuleDoesConform_Test2_ShouldNotThrow() {
        TokenPart part = new NumericTokenPart(60, 55);
        GeneratorRule rule = new BaseGeneratorRule(Collections.singletonList(part));
        new StringTokenGenerator(new Random(), rule, Collections.singletonList(VALIDATOR));
    }

    private static final class UnvalidatedTokenPart implements TokenPart {

        private final int length;
        private final List<Character> characters;

        private UnvalidatedTokenPart(int length, List<Character> characters) {
            this.length = length;
            this.characters = characters;
        }

        @Override
        public int getLength() {
            return this.length;
        }

        @Override
        public List<Character> getCharacters() {
            return this.characters;
        }
    }
}
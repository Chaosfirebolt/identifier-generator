package com.github.chaosfirebolt.generator.part;

import com.github.chaosfirebolt.generator.token.part.BaseTokenPart;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by ChaosFire on 19-Dec-21
 */
public class BaseTokenPartConstructorTests {

    @Test
    public void intIntListConstructor_ListIsNull_ShouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BaseTokenPart(5, 3, null));
    }

    @Test
    public void intIntListConstructor_ListIsEmpty_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new BaseTokenPart(9, 8, Collections.emptyList()));
    }

    @Test
    public void intListConstructor_ListIsNull_ShouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BaseTokenPart(5, null));
    }

    @Test
    public void intListConstructor_ListIsEmpty_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new BaseTokenPart(9, Collections.emptyList()));
    }
}
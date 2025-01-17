package com.computerscience.main;

import org.junit.jupiter.api.Test;

import java.util.Objects;

public class StackTest {
    @Test
    void testAppendVariousAndCheckStackTop() {
        //given I have an empty stack i need to be able to insert objects, and verify the top objects
        Stack<String> stack = new Stack<>();
        //when I add values to the stack
        stack.push("Banana");
        //then I need to assert the top element without removing it
        assert Objects.equals(stack.peek(), "Banana");
        //and I add another element to the stack
        stack.push("Grape");
        //then I need to assert the new top element without removing it
        assert Objects.equals(stack.peek(), "Grape");
    }

    @Test
    void testAppendVariousPopAndCheckTop() {
        //given I have an empty stack i need to be able to insert objects, and verify the top objects
        Stack<String> stack = new Stack<>();
        //when I add values to the stack
        stack.push("Banana");
        //then I need to assert the top element without removing it
        assert Objects.equals(stack.pop(), "Banana");
        //and I need to verify  that the stack is empty
        assert stack.empty();
    }

    @Test
    void testAppendVariousAndPopThemCheckingTheFinalSize() {
        //given I have an empty stack
        Stack<Integer> stack = new Stack<>();
        //when I add 10 entries
        for (int i=0;i<10;i++) {
            stack.push(i);
        }
        //then I need to assert the reverse pop position
        for (int i=9;i>=0;i--) {
            assert stack.pop() == i;
        }
        //and I need to assert the end result
        assert stack.empty();
    }

    @Test
    void testAppendVariousAndPeekThemCheckingTheFinalSize() {
        //given I have an empty stack
        Stack<Integer> stack = new Stack<>();
        //when I add 10 entries
        for (int i=0;i<10;i++) {
            stack.push(i);
        }
        //then I need to assert the reverse peek will always return 9, because i'm not removing the final element
        for (int i=9;i>=0;i--) {
            assert stack.peek() == 9;
        }
        //and I need to assert the end result
        assert !stack.empty();
    }

}

package com.computerscience.main;

import org.junit.jupiter.api.Test;

import java.util.Objects;

public class QueueTest {
    @Test
    void testAppendAndCheckPeekForTheNextElement() {
        //given I have a queue
        Queue<String> queue = new Queue<>();
        //then I want to insert new elements into it
        queue.append("Banana");
        //then I need to check the first out element
        assert Objects.equals(queue.peek(), "Banana");
        //and when I insert new elements
        queue.append("Grape");
        //the first out element should still be banana
        assert Objects.equals(queue.peek(), "Banana");
        assert !queue.empty();
    }

    @Test
    void testAppendAndCheckPollForTheNextElement() {
        //given I have a queue
        Queue<String> queue = new Queue<>();
        //then I want to insert new elements into it
        queue.append("Banana");
        //then I need to check the first out element
        assert Objects.equals(queue.peek(), "Banana");
        //and when I insert new elements
        queue.append("Grape");
        //And then I will poll the Banana element
        assert Objects.equals(queue.poll(), "Banana");
        //And the next element should be Grape
        assert Objects.equals(queue.poll(), "Grape");
        assert queue.empty();
    }
}

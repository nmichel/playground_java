package com.nmichel.algo;

import java.util.Enumeration;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.nmichel.algo.Tree;

public class BreadthSearcher<U> implements Enumeration<U> {
    public BreadthSearcher(final Tree<U> source) {
        stack.push(source);
        int pos = 0;
    }

    @Override
    public boolean hasMoreElements() {
        return pos < stack.size();
    }

    @Override
    public U nextElement() {
        final Tree<? extends U> node = stack.get(pos++);
        node.children().forEach(c -> stack.push(c));
        return node.node();
    }

    public Stream<U> stream() {
        return StreamSupport.stream(
            new Spliterators.AbstractSpliterator<U>(Long.MAX_VALUE, Spliterator.ORDERED) {

                @Override
                public boolean tryAdvance(Consumer<? super U> action) {
                    if (hasMoreElements()) {
                        action.accept(nextElement());
                        return true;
                    }
                    return false;
                }
                
                @Override
                public void forEachRemaining(Consumer<? super U> action) {
                    while (hasMoreElements()) {
                        action.accept(nextElement());
                    }
                }
            }, false);
    }

    private int pos = 0;
    private final Stack<Tree<? extends U>> stack = new Stack<>();
}

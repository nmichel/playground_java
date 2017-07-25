package com.nmichel.algo;

import java.util.Enumeration;
import java.util.Stack;
import java.util.stream.Stream;

import com.nmichel.algo.Tree;

public class BreadthWalker<U> implements Enumeration<U> {
    public BreadthWalker(final Tree<U> source) {
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
        return Streamer.streamOf(this);
    }

    private int pos = 0;
    private final Stack<Tree<? extends U>> stack = new Stack<>();
}

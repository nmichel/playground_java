package com.nmichel.algo;

import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.Queue;
import java.util.stream.Stream;

import com.nmichel.algo.Tree;

public class BreadthWalker<U> implements Enumeration<U> {
    public BreadthWalker(final Tree<U> source) {
        queue.add(source);
    }

    @Override
    public boolean hasMoreElements() {
        return !queue.isEmpty();
    }

    @Override
    public U nextElement() {
        final Tree<? extends U> node = queue.remove();
        node.children().forEach(c -> queue.add(c));
        return node.value();
    }

    public Stream<U> stream() {
        return Streamer.streamOf(this);
    }

    private final Queue<Tree<? extends U>> queue = new ArrayDeque<>(100);
}

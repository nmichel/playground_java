package com.nmichel.algo;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Stack;
import java.util.stream.Stream;

import com.nmichel.algo.Tree;

public class DepthWalker<U> implements Enumeration<U> {
    public DepthWalker(final Tree<U> source) {
        stack.push(new Entry<>(source));
    }

    @Override
    public boolean hasMoreElements() {
        return !stack.isEmpty();
    }

    @Override
    public U nextElement() {
        final Entry<U> entry = stack.peek();
        final U res = entry.tree.value();
        if (entry.nextChild.hasNext()) {
            stack.push(new Entry<>(entry.nextChild.next()));
        }
        else {
            stack.pop();
            while (!stack.empty()) {
                final Entry<U> parent = stack.peek();
                if (!parent.nextChild.hasNext()) {
                    stack.pop();
                    continue; // <== 
                }

                stack.push(new Entry<>(parent.nextChild.next()));
                break; // <== 
            }
        }
        return res;
    }

    public Stream<U> stream() {
        return Streamer.streamOf(this);
    }

    private static class Entry<U> {
        final Tree<U> tree;
        final Iterator<Tree<U>> nextChild;

        Entry(final Tree<U> t) {
            tree = t;
            nextChild = tree.children().iterator();
        }
    }

    private final Stack<Entry<U>> stack = new Stack<>();
}

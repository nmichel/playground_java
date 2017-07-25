package com.nmichel.algo;

import java.util.Enumeration;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class Streamer {
    public static <U> Stream<U> streamOf(final Enumeration<U> e) {
        return StreamSupport.stream(
            new Spliterators.AbstractSpliterator<U>(Long.MAX_VALUE, Spliterator.ORDERED) {

                @Override
                public boolean tryAdvance(Consumer<? super U> action) {
                    if (e.hasMoreElements()) {
                        action.accept(e.nextElement());
                        return true;
                    }
                    return false;
                }
                
                @Override
                public void forEachRemaining(Consumer<? super U> action) {
                    while (e.hasMoreElements()) {
                        action.accept(e.nextElement());
                    }
                }
            }, false);
    }
};

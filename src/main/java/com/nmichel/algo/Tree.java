package com.nmichel.algo;

import java.util.stream.Stream;

public interface Tree<T> {

    T node();

    Stream<Tree<? extends T>> children();
}

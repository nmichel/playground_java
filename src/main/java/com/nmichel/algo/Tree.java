package com.nmichel.algo;

import java.util.stream.Stream;

public interface Tree<T> {

    T value();

    Stream<Tree<? extends T>> children();
}

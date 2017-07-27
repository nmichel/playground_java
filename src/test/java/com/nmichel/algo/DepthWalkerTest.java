package com.nmichel.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DepthWalkerTest 
    extends TestCase
{
    public DepthWalkerTest(String testName)
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite(DepthWalkerTest.class);
    }

    @Override
    protected void setUp() {
        root =
            new TreeOfInt(0)
                .addChildren(
                    new TreeOfInt(1)
                        .addChildren(new TreeOfInt(2), new TreeOfInt(3)),
                    new TreeOfInt(4)
                        .addChildren(new TreeOfInt(5), new TreeOfInt(6)),
                    new TreeOfInt(7));
    }
    
    public void testfindItem()
    {
        assert((new DepthWalker<>(root)).stream()
            .filter(e -> e == 4)
            .findFirst().get() != null);
    }
    public void testWalkAsExpected()
    {
        assert((new DepthWalker<>(root)).stream()
            .collect(Collectors.toCollection(ArrayList::new))
            .equals(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7)));
    }

    private static class TreeOfInt implements Tree<Integer> {

        private final Integer value;
        private final List<Tree<Integer>> children = new ArrayList<>();

        public TreeOfInt(final int v) {
            value = v;
        }

        public TreeOfInt addChildren(final Tree<Integer>... subtrees) {
            children.addAll(Arrays.asList(subtrees));
            return this;
        }

        @Override
        public Integer value() {
            return value;
        }

        @Override
        public Stream<Tree<Integer>> children() {
            return children.stream();
        }
    }

    private TreeOfInt root;
}

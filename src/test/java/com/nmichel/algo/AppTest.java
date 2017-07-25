package com.nmichel.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }
    
    /**
     * Rigourous Test :-)
     */
    public void testfindItem()
    {
        final TreeOfInt root = new TreeOfInt(1);
        root
            .addChild(new TreeOfInt(0)
                .addChild(new TreeOfInt(2))
                .addChild(new TreeOfInt(3)))
            .addChild(new TreeOfInt(4)
                .addChild(new TreeOfInt(5))
                .addChild(new TreeOfInt(6)))
            .addChild(new TreeOfInt(7))
        ;

        assert((new BreadthSearcher<>(root)).stream()
            .filter(e -> e == 4)
            .findFirst().get() != null);
    }
    public void testWalkAsExpected()
    {
        final TreeOfInt root = new TreeOfInt(0);
        root
            .addChild(new TreeOfInt(1)
                .addChild(new TreeOfInt(2))
                .addChild(new TreeOfInt(3)))
            .addChild(new TreeOfInt(4)
                .addChild(new TreeOfInt(5))
                .addChild(new TreeOfInt(6)))
            .addChild(new TreeOfInt(7))
        ;

        assert((new BreadthSearcher<>(root)).stream()
            .collect(Collectors.toCollection(ArrayList::new))
            .equals(Arrays.asList(0, 1, 4, 7, 2, 3, 5, 6)));
    }

    private static class TreeOfInt implements Tree<Integer> {

        private final Integer value;
        private final List<Tree<? extends Integer>> children = new ArrayList<>();

        public TreeOfInt(final int v) {
            value = v;
        }

        public TreeOfInt addChild(final Tree<? extends Integer> child) {
            children.add(child);
            return this;
        }

        @Override
        public Integer node() {
            return value;
        }

        @Override
        public Stream<Tree<? extends Integer>> children() {
            return children.stream();
        }
    }
}

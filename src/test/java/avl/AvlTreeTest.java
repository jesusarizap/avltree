package avl;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import org.junit.jupiter.api.Nested;

/**
 * Created with IntelliJ IDEA. User: Antonio J. Nebro Date: 08/07/13
 */
public class AvlTreeTest {

    AvlTree<Integer> avlTree;
    Comparator<?> comparator;

    @BeforeEach
    public void setUp() throws Exception {
        comparator = Comparator.comparingInt((Integer o) -> o);
        avlTree = new AvlTree(comparator);
    }

    @AfterEach
    public void tearDown() throws Exception {
        avlTree = null;
        comparator = null;
    }

    @Test
    public void testSearchClosestNodeWithNullTop() {
        AvlTree<Integer> tree = new AvlTree<>(null);
        AvlNode<Integer> node = new AvlNode<>(5);

        int result = tree.searchClosestNode(node);

        assertEquals(0, result);
        assertNull(node.getClosestNode());
    }

    @Nested
    class GIVEN_aNewTree {
        @Nested
        class WHEN_comparingNodes {
            @Test
            public void THEN_recognizesGreaterFirst() {
                AvlNode<Integer> node1 = new AvlNode<Integer>(4);
                AvlNode<Integer> node2 = new AvlNode<Integer>(5);

                int expected = -1;
                int obtained = avlTree.compareNodes(node1, node2);

                assertEquals(expected, obtained);

            }

            @Test
            public void THEN_recognizesGreaterSecond() {
                AvlNode<Integer> node1 = new AvlNode<Integer>(5);
                AvlNode<Integer> node2 = new AvlNode<Integer>(4);

                int expected = 1;
                int obtained = avlTree.compareNodes(node1, node2);

                assertEquals(expected, obtained);
            }

            @Test
            public void THEN_recognizesEqual() {
                AvlNode<Integer> node1 = new AvlNode<Integer>(5);
                AvlNode<Integer> node2 = new AvlNode<Integer>(5);

                int expected = 0;
                int obtained = avlTree.compareNodes(node1, node2);

                assertEquals(expected, obtained);

            }
        }

        @Nested
        class RebalanceTests {
            @Test
            void rebalance_singleNodeTree_noChange() {
                avlTree.insert(10);
                avlTree.rebalance(avlTree.getTop());
                assertEquals(10, avlTree.getTop().getItem());
            }

            @Test
            void rebalance_leftRotation_needed() {
                avlTree.insert(10);
                avlTree.insert(20);
                avlTree.insert(30);
                avlTree.rebalance(avlTree.getTop());
                assertEquals(20, avlTree.getTop().getItem());
                assertEquals(10, avlTree.getTop().getLeft().getItem());
                assertEquals(30, avlTree.getTop().getRight().getItem());
            }

            @Test
            void rebalance_rightRotation_needed() {
                avlTree.insert(30);
                avlTree.insert(20);
                avlTree.insert(10);
                avlTree.rebalance(avlTree.getTop());
                assertEquals(20, avlTree.getTop().getItem());
                assertEquals(10, avlTree.getTop().getLeft().getItem());
                assertEquals(30, avlTree.getTop().getRight().getItem());
            }

            @Test
            void rebalance_doubleLeftRotation_needed() {
                avlTree.insert(30);
                avlTree.insert(10);
                avlTree.insert(20);
                avlTree.rebalance(avlTree.getTop());
                assertEquals(20, avlTree.getTop().getItem());
                assertEquals(10, avlTree.getTop().getLeft().getItem());
                assertEquals(30, avlTree.getTop().getRight().getItem());
            }

            @Test
            void rebalance_doubleRightRotation_needed() {
                avlTree.insert(10);
                avlTree.insert(30);
                avlTree.insert(20);
                avlTree.rebalance(avlTree.getTop());
                assertEquals(20, avlTree.getTop().getItem());
                assertEquals(10, avlTree.getTop().getLeft().getItem());
                assertEquals(30, avlTree.getTop().getRight().getItem());
            }
        }

        @Nested
        class EmptyTreeTests {
            @Test
            void isEmpty_emptyTree_returnsTrue() {
                assertTrue(avlTree.avlIsEmpty());
            }

            @Test
            void isEmpty_afterInserting_returnsFalse() {
                avlTree.insert(10);
                assertFalse(avlTree.avlIsEmpty());
            }

            @Test
            void search_emptyTree_returnsNull() {
                assertNull(avlTree.search(10));
            }

            @Test
            void delete_emptyTree_doesNothing() {
                avlTree.delete(10);
                assertTrue(avlTree.avlIsEmpty());
            }

            @Test
            void getBalance_emptyTree_returns0() {
                assertEquals(0, avlTree.getBalance(new AvlNode<>(1)));
            }

            @Test
            void getHeight_emptyTree_returnsNegative1() {
                assertEquals(-1, avlTree.height(null));
            }
        }

        @Nested
        class AND_isNotEmpty {
            @Test
            public void WHEN_CheckingIfIsEmpty_THEN_returnsFALSE() {
                avlTree.insertTop(new AvlNode(5));
                Boolean expected = avlTree.avlIsEmpty();

                assertFalse(expected, "TestAvlIsEmpty");
            }

            @Nested
            class WHEN_inserting {
                @Test
                public void AND_insertedIsGreater_THEN_isAddedToTheRight() {
                    avlTree.insert(5);
                    avlTree.insert(7);
                    AvlNode<Integer> node = avlTree.search(7);
                    assertEquals(7, node.getItem());
                    assertNull(node.getLeft());
                    assertNull(node.getRight());
                }

                @Test
                public void AND_insertedIsSmaller_THEN_isAddedToTheLeft() {
                    avlTree.insert(5);
                    avlTree.insert(3);
                    AvlNode<Integer> node = avlTree.search(3);
                    assertEquals(3, node.getItem());
                    assertNull(node.getLeft());
                    assertNull(node.getRight());
                }

                @Test
                public void AND_isEqualToANumberInTheTree_THEN_doesNothing() {
                    avlTree.insert(5);
                    avlTree.insert(5);
                    AvlNode<Integer> node = avlTree.search(5);
                    assertEquals(5, node.getItem());
                    assertNull(node.getLeft());
                    assertNull(node.getRight());
                }
            }

            @Nested
            class WHEN_searching {

                @Test
                public void AND_exists_THEN_returnsNode() {
                    avlTree.insert(5);

                    AvlNode<Integer> result = avlTree.search(5);

                    assertEquals(5, result.getItem());
                }

                @Test
                public void AND_doesNotExists_THEN_returnsNull() {
                    avlTree.insert(5);

                    AvlNode<Integer> result = avlTree.search(10);

                    assertNull(result);
                }
            }

            @Nested
            class WHEN_deleting {
                @Nested
                class AND_exists {
                    @Test
                    public void AND_isLeaf_THEN_deletesNodeAndRebalances() {
                        avlTree.insert(5);
                        avlTree.insert(3);
                        avlTree.insert(7);
                        avlTree.insert(2);

                        avlTree.delete(3);

                        assertNull(avlTree.search(3));
                        assertEquals(" | 5 | 2 | 7", avlTree.toString());
                    }

                    @Test
                    public void AND_isNotLeaf_THEN_deletesAndRotates() {
                        avlTree.insert(5);
                        avlTree.insert(3);
                        avlTree.insert(7);
                        avlTree.insert(2);
                        avlTree.insert(4);

                        avlTree.delete(3);

                        assertNull(avlTree.search(3));
                        assertEquals(" | 5 | 4 | 2 | 7", avlTree.toString());
                    }
                }

                @Test
                public void AND_doesNotExist_THEN_doesNothing() {
                    avlTree.insert(5);
                    avlTree.insert(3);
                    avlTree.insert(7);

                    avlTree.delete(2);

                    assertNotNull(avlTree.search(5));
                    assertNotNull(avlTree.search(3));
                    assertNotNull(avlTree.search(7));
                    assertEquals(" | 5 | 3 | 7", avlTree.toString());
                }
            }
        }

        @Nested
        class WHEN_findingSuccessor {
            @Test
            public void AND_doesNotExist_THEN_returnsParent() {
                avlTree.insert(3);
                avlTree.insert(1);
                avlTree.insert(5);

                AvlNode<Integer> node = new AvlNode<>(2);
                avlTree.insertAvlNode(node);

                AvlNode<Integer> successor = avlTree.findSuccessor(node);
                assertEquals(3, successor.getItem());
            }

            @Test
            public void AND_doesExist_THEN_returnNull() {
                avlTree.insert(3);
                avlTree.insert(1);
                avlTree.insert(5);

                AvlNode<Integer> node = new AvlNode<>(3);
                avlTree.insertAvlNode(node);

                AvlNode<Integer> successor = avlTree.findSuccessor(node);
                assertNull(successor);
            }
        }

        @Test
        public void WHEN_insertingTop_THEN_parentIsNew() {
            avlTree.insert(3);
            avlTree.insert(1);

            AvlNode<Integer> node = new AvlNode<>(5);
            avlTree.insertAvlNode(node);

            assertEquals(avlTree.getTop(), node.getParent());
        }

        @Test
        public void WHEN_getTop_ReturnsFirstNode() {
            avlTree.insert(3);
            avlTree.insert(1);
            avlTree.insert(5);

            AvlNode<Integer> top = avlTree.getTop();

            assertEquals(3, top.getItem());
        }

        @Test
        public void WHEN_getHeight_THEN_returnsHeight() {
            avlTree.insert(3);
            avlTree.insert(1);
            avlTree.insert(5);

            AvlNode<Integer> node = new AvlNode<>(7);
            avlTree.insertAvlNode(node);

            int height = avlTree.height(node);

            assertEquals(0, height);
        }

    }
}
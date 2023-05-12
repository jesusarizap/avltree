package avl;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Given new AvlNode")
class AvlNodeTest {
    AvlNode<Integer> node;
    @BeforeEach
    void setUp() {
        node = new AvlNode<>(1);
    }

    @AfterEach
    void tearDown() {
        node = null;
    }

    @DisplayName("In new AvlNode father is null")
    @Test
    void testGetParentInNewNodeReturnsNull() {
        AvlNode<Integer> actualNode = node.getParent();

        assertNull(actualNode);
    }

    @DisplayName("if item is null throws illegalArgumentException")
    @Test
    void testConstructorWithNullItemThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new AvlNode<>(null));
    }

    @DisplayName("In new AvlNode left is null")
    @Test
    void testGetLeftInNewNodeReturnsNull() {
        AvlNode<Integer> actualNode = node.getLeft();

        assertNull(actualNode);
    }

    @DisplayName("In new AvlNode right is null")
    void testGetRightInNewNodeReturnsNull() {
        AvlNode<Integer> actualNode = node.getRight();

        assertNull(actualNode);
    }

    @DisplayName("When setLeft")
    @Nested
    class setLeftTestCases{
        @DisplayName("When setLeft with null")
        @Test
        void testSetLeftWithNull() {
            node.setLeft(null);

            AvlNode<Integer> actualNode = node.getLeft();


            assertNull(actualNode);
        }

        @DisplayName("When setLeft with new AvlNode")
        @Test
        void testSetLeftWithNewNode() {
            AvlNode<Integer> expectedNode = new AvlNode<>(2);
            node.setLeft(expectedNode);
            int expectedHeightFather = 1;
            int expectedHeightChild = 0;

            AvlNode<Integer> actualNode = node.getLeft();
            int actualHeightFather = node.getHeight();
            int actualHeightChild = actualNode.getHeight();

            assertEquals(expectedNode, actualNode);
            assertEquals(expectedHeightFather, actualHeightFather);
            assertEquals(expectedHeightChild, actualHeightChild);
        }
    }



    @DisplayName("When setRight")
    @Nested
    class setRightTestCases{
        @DisplayName("When setRight with null")
        @Test
        void testSetRightWithNull() {
            node.setRight(null);

            AvlNode<Integer> actualNode = node.getRight();

            assertNull(actualNode);
        }

        @DisplayName("When setRight with new AvlNode")
        @Test
        void testSetRightWithNewNode() {
            AvlNode<Integer> expectedNode = new AvlNode<>(2);
            int expectedHeightFather = 1;
            int expectedHeightChild = 0;

            node.setRight(expectedNode);


            AvlNode<Integer> actualNode = node.getRight();
            int actualHeightFather = node.getHeight();
            int actualHeightChild = actualNode.getHeight();

            assertEquals(expectedNode, node.getRight());
            assertEquals(expectedHeightFather, actualHeightFather);
            assertEquals(expectedHeightChild, actualHeightChild);
        }
    }

    @Nested
    @DisplayName("When isLeaf()")
    class isLeafTestCases{
        @DisplayName("When isLeaf() in new AvlNode returns true")
        @Test
        void testIsLeafInNewNode() {
            boolean actualResult = node.isLeaf();

            assertTrue(actualResult);
        }

        @DisplayName("When isLeaf() in AvlNode with one child in left returns false")
        @Test
        void testIsLeafInNodeWithOneChild() {
            AvlNode<Integer> childNode = new AvlNode<>(2);
            node.setLeft(childNode);
            childNode.setParent(node);

            boolean actualResult = node.isLeaf();

            assertFalse(actualResult);
        }

        @DisplayName("When isLeaf() in AvlNode with one child in right returns false")
        @Test
        void testIsLeafInNodeWithOneChildInRight() {
            AvlNode<Integer> childNode = new AvlNode<>(2);
            node.setRight(childNode);
            childNode.setParent(node);

            boolean actualResult = node.isLeaf();

            assertFalse(actualResult);
        }


        @DisplayName("When isLeaf() in AvlNode with two children returns false")
        @Test
        void testIsLeafWithTwoChildren() {
            AvlNode<Integer> childNode = new AvlNode<>(2);
            AvlNode<Integer> childNode2 = new AvlNode<>(3);
            node.setLeft(childNode);
            node.setRight(childNode2);

            boolean actualResult = node.isLeaf();

            assertFalse(actualResult);
        }
    }

    @DisplayName("When hasOnlyALeftChild()")
    @Nested
    class hasOnlyALeftChildTestCases{
        @DisplayName("When hasOnlyALeftChild() in new AvlNode returns false")
        @Test
        void testHasOnlyALeftChildInNewNode() {
            boolean actualResult = node.hasOnlyALeftChild();

            assertFalse(actualResult);
        }

        @DisplayName("When hasOnlyALeftChild() in AvlNode with one child returns true")
        @Test
        void testHasOnlyALeftChildInNodeWithOneChild() {
            AvlNode<Integer> childNode = new AvlNode<>(2);
            node.setLeft(childNode);

            boolean actualResult = node.hasOnlyALeftChild();

            assertTrue(actualResult);
        }

        @DisplayName("When hasOnlyALeftChild() in AvlNode with two children returns false")
        @Test
        void testHasOnlyALeftChildInNodeWithTwoChildren() {
            AvlNode<Integer> childNode1 = new AvlNode<>(2);
            AvlNode<Integer> childNode2 = new AvlNode<>(3);
            node.setLeft(childNode1);
            node.setRight(childNode2);


            boolean actualResult = node.hasOnlyALeftChild();

            assertFalse(actualResult);
        }
    }

    @DisplayName("When hasOnlyARightChild()")
    @Nested
    class hasOnlyARightChildTestCases {
        @DisplayName("When hasOnlyARightChild() in new AvlNode returns false")
        @Test
        void testHasOnlyARightChildInNewNode() {
            boolean actualResult = node.hasOnlyARightChild();

            assertFalse(actualResult);
        }

        @DisplayName("When hasOnlyARightChild() in AvlNode with one child returns true")
        @Test
        void testHasOnlyARightChildInNodeWithOneChild() {
            AvlNode<Integer> childNode = new AvlNode<>(2);
            node.setRight(childNode);

            boolean actualResult = node.hasOnlyARightChild();

            assertTrue(actualResult);
        }

        @DisplayName("When hasOnlyARightChild() in AvlNode with two children returns false")
        @Test
        void testHasOnlyARightChildInNodeWithTwoChildren() {
            AvlNode<Integer> childNode1 = new AvlNode<>(2);
            AvlNode<Integer> childNode2 = new AvlNode<>(3);
            node.setLeft(childNode1);
            node.setRight(childNode2);


            boolean actualResult = node.hasOnlyARightChild();

            assertFalse(actualResult);
        }

    }

    @DisplayName("When hasParent")
    @Nested
    class hasParentTestCases{
        @DisplayName("When hasParent in new AvlNode returns false")
        @Test
        void testHasParentInNewNode() {
            boolean actualResult = node.hasParent();

            assertFalse(actualResult);
        }

        @DisplayName("When hasParent in AvlNode with parent returns true")
        @Test
        void testHasParentInNodeWithParent() {
            AvlNode<Integer> parentNode = new AvlNode<>(2);
            node.setParent(parentNode);

            boolean actualResult = node.hasParent();

            assertTrue(actualResult);
        }
    }

    @DisplayName("When setClosestNode")
    @Nested
    class setClosestNodeTestCases{
        @DisplayName("When setClosestNode with null")
        @Test
        void testSetClosestNodeWithNull() {
            node.setClosestNode(null);

            AvlNode<Integer> actualNode = node.getClosestNode();

            assertNull(actualNode);
        }

        @DisplayName("When setClosestNode with new AvlNode")
        @Test
        void testSetClosestNodeWithNewNode() {
            AvlNode<Integer> expectedNode = new AvlNode<>(2);

            node.setClosestNode(expectedNode);

            AvlNode<Integer> actualNode = node.getClosestNode();

            assertEquals(expectedNode, actualNode);
        }
    }

    @DisplayName("When getClosestNode")
    @Nested
    class getClosestNodeTestCases{
        @DisplayName("When getClosestNode in new AvlNode returns null")
        @Test
        void testGetClosestNodeInNewNode() {
            AvlNode<Integer> actualNode = node.getClosestNode();

            assertNull(actualNode);
        }

        @DisplayName("When getClosestNode in AvlNode with closestNode returns closestNode")
        @Test
        void testGetClosestNodeInNodeWithClosestNode() {
            AvlNode<Integer> expectedNode = new AvlNode<>(2);
            node.setClosestNode(expectedNode);

            AvlNode<Integer> actualNode = node.getClosestNode();

            assertEquals(expectedNode, actualNode);
        }
    }

    @Nested
    @DisplayName("When setItem")
    class setItemTestCases{

        @DisplayName("When setItem with new AvlNode")
        @Test
        void testSetItemWithNewNode() {
            node.setItem(2);

            int expectedItem = 2;

            assertEquals(expectedItem, node.getItem());
        }
    }

    @Nested
    @DisplayName("When getParent")
    class getParentTestCases{
        @Test
        @DisplayName("When getParent in new AvlNode returns null")
        void testGetParentInNewNode() {
            AvlNode<Integer> actualNode = node.getParent();

            assertNull(actualNode);
        }


    }

    @Nested
    @DisplayName("When setParent")
    class setParentTestCases{
        @Test
        @DisplayName("When setParent with new AvlNode")
        void testSetParentWithNewNode() {
            AvlNode<Integer> expectedNode = new AvlNode<>(2);

            node.setParent(expectedNode);

            AvlNode<Integer> actualNode = node.getParent();

            assertEquals(expectedNode, actualNode);
        }

        @Test
        @DisplayName("When setParent with null")
        void testSetParentWithNull() {
            node.setParent(null);

            AvlNode<Integer> actualNode = node.getParent();

            assertNull(actualNode);
        }
    }

    @Nested
    @DisplayName("When updateHeight")
    class updateHeightTestCases {

        @DisplayName("When updateHeight with new AvlNode")
        @Test
        void testUpdateHeightWithNewNode() {
            int expectedHeight = 0;

            node.updateHeight();

            int actualHeight = node.getHeight();

            assertEquals(expectedHeight, actualHeight);
        }

        @DisplayName("when updateHeight with a node with one child")
        @Test
        void testUpdateHeightWithOneChild() {
            AvlNode<Integer> childNode = new AvlNode<>(2);
            node.setLeft(childNode);
            childNode.setParent(node);

            node.updateHeight();

            int expectedHeight = 1;
            int actualHeight = node.getHeight();

            assertEquals(expectedHeight, actualHeight);
        }

        @DisplayName("when updateHeight with a node with two children")
        @Test
        void testUpdateHeightWithTwoChildren() {
            AvlNode<Integer> childNode = new AvlNode<>(2);
            node.setLeft(childNode);
            childNode.setParent(node);

            AvlNode<Integer> childNode2 = new AvlNode<>(3);
            childNode.setLeft(childNode2);
            childNode2.setParent(childNode);

            node.updateHeight();


            int expectedHeight = 2;
            int actualHeight = node.getHeight();

            assertEquals(expectedHeight, actualHeight);
        }

        @DisplayName("when updateHeight with a node with three children in left side and two in right side")
        @Test
        void testUpdateHeightWithThreeChildrenInLeftSideAndTwoInRightSide() {
            //Parte izquierda
            AvlNode<Integer> childNodeLeft = new AvlNode<>(2);
            node.setLeft(childNodeLeft);

            AvlNode<Integer> childNode2Left = new AvlNode<>(3);
            childNodeLeft.setLeft(childNode2Left);

            AvlNode<Integer> childNode3Left = new AvlNode<>(4);
            childNode2Left.setLeft(childNode3Left);

            AvlNode<Integer> childNode4Left = new AvlNode<>(5);
            childNode3Left.setLeft(childNode4Left);




            AvlNode<Integer> childNodeRight = new AvlNode<>(5);
            node.setRight(childNodeRight);

            AvlNode<Integer> childNode2Right = new AvlNode<>(6);
            childNodeRight.setRight(childNode2Right);



            node.updateHeight();
            int expectedRightHeight = 1;
            int expectedLeftHeight = 3;
            int expectedHeight = 4;



            int actualHeight = node.getHeight();
            int actualLeftHeight = node.getLeft().getHeight();
            int actualRightHeight = node.getRight().getHeight();

            assertEquals(expectedHeight, actualHeight);
            assertEquals(expectedLeftHeight, actualLeftHeight);
            assertEquals(expectedRightHeight, actualRightHeight);
        }
    }



}
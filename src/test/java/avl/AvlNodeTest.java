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
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

            AvlNode<Integer> actualNode = node.getLeft();

            assertEquals(expectedNode, actualNode);
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


            node.setRight(expectedNode);

            AvlNode<Integer> actualNode = node.getRight();

            assertEquals(expectedNode, node.getRight());
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
    class updateHeightTestCases{

            @DisplayName("When updateHeight with new AvlNode")
            @Test
            void testUpdateHeightWithNewNode() {
                int expectedHeight = 0;

                node.updateHeight();

                int actualHeight = node.getHeight();

                assertEquals(expectedHeight, actualHeight);
            }

    }



}
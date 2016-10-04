package org.land;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LandRectangleTest {

    private void assertIsAdjacent(boolean trueOrFalse, LandRectangle rect1, LandRectangle rect2) {
        assertEquals(trueOrFalse, rect1.adjacentTo(rect2));
        assertEquals(trueOrFalse, rect2.adjacentTo(rect1));
    }

    @Test
    public void testBadCoordinates_input1() {
        try {
            new LandRectangle(true, 3, 3, 1, 6);
        } catch( RuntimeException re ) {
            assertEquals("Not a real land rectangle", re.getMessage());
        }
    }

    @Test
    public void testBadCoordinates_input2() {
        try {
            new LandRectangle(true, -1, 3, 1, 6);
        } catch( RuntimeException re ) {
            assertEquals("Not a real land rectangle", re.getMessage());
        }
    }
    
    @Test
    public void testAdjacentTo_input1() {
        LandRectangle rect1 = new LandRectangle(true, 3, 3, 6, 6);
        LandRectangle rect2 = new LandRectangle(true, 6, 6, 8, 8);
        assertIsAdjacent(false, rect1, rect2);
    }
    
    @Test
    public void testAdjacentTo_input2() {
        LandRectangle rect1 = new LandRectangle(true, 3, 3, 6, 6);
        LandRectangle rect2 = new LandRectangle(true, 6, 2, 8, 3);
        assertIsAdjacent(false, rect1, rect2);
    }
    
    @Test
    public void testAdjacentTo_input3() {
        LandRectangle rect1 = new LandRectangle(true, 3, 3, 6, 6);
        LandRectangle rect2 = new LandRectangle(true, 6, 2, 8, 5);
        assertIsAdjacent(true, rect1, rect2);
    }
    
    @Test
    public void testAdjacentTo_input4() {
        LandRectangle rect1 = new LandRectangle(true, 3, 3, 6, 6);
        LandRectangle rect2 = new LandRectangle(true, 6, 2, 8, 6);
        assertIsAdjacent(true, rect1, rect2);
    }
    
    @Test
    public void testAdjacentTo_input5() {
        LandRectangle rect1 = new LandRectangle(true, 3, 3, 6, 6);
        LandRectangle rect2 = new LandRectangle(true, 6, 2, 8, 7);
        assertIsAdjacent(true, rect1, rect2);
    }
    
    @Test
    public void testAdjacentTo_input6() {
        LandRectangle rect1 = new LandRectangle(true, 3, 3, 6, 6);
        LandRectangle rect2 = new LandRectangle(true, 6, 3, 8, 5);
        assertIsAdjacent(true, rect1, rect2);
    }
    
    @Test
    public void testAdjacentTo_input7() {
        LandRectangle rect1 = new LandRectangle(true, 3, 3, 6, 6);
        LandRectangle rect2 = new LandRectangle(true, 6, 3, 8, 6);
        assertIsAdjacent(true, rect1, rect2);
    }
    
    @Test
    public void testAdjacentTo_input8() {
        LandRectangle rect1 = new LandRectangle(true, 3, 3, 6, 6);
        LandRectangle rect2 = new LandRectangle(true, 6, 3, 8, 7);
        assertIsAdjacent(true, rect1, rect2);
    }
    
    @Test
    public void testAdjacentTo_input9() {
        LandRectangle rect1 = new LandRectangle(true, 3, 3, 6, 6);
        LandRectangle rect2 = new LandRectangle(true, 6, 4, 8, 5);
        assertIsAdjacent(true, rect1, rect2);
    }
    
    @Test
    public void testAdjacentTo_input910() {
        LandRectangle rect1 = new LandRectangle(true, 3, 3, 6, 6);
        LandRectangle rect2 = new LandRectangle(true, 6, 4, 8, 6);
        assertEquals(true, rect1.adjacentTo(rect2));
        assertIsAdjacent(true, rect1, rect2);
    }
    
    @Test
    public void testAdjacentTo_input11() {
        LandRectangle rect1 = new LandRectangle(true, 3, 3, 6, 6);
        LandRectangle rect2 = new LandRectangle(true, 6, 4, 8, 7);
        assertIsAdjacent(true, rect1, rect2);
    }
    
    @Test
    public void testAdjacentTo_input12() {
        LandRectangle rect1 = new LandRectangle(true, 3, 3, 6, 6);
        LandRectangle rect2 = new LandRectangle(true, 1, 1, 3, 3);
        assertIsAdjacent(false, rect1, rect2);
    }
    
    @Test
    public void testAdjacentTo_input13() {
        LandRectangle rect1 = new LandRectangle(true, 3, 3, 6, 6);
        LandRectangle rect2 = new LandRectangle(true, 1, 6, 3, 8);
        assertIsAdjacent(false, rect1, rect2);
    }
    
    @Test
    public void testAdjacentTo_input14() {
        LandRectangle rect1 = new LandRectangle(true, 3, 3, 6, 6);
        LandRectangle rect2 = new LandRectangle(true, 1, 6, 4, 8);
        assertIsAdjacent(true, rect1, rect2);
    }
    
    @Test
    public void testAdjacentTo_input15() {
        LandRectangle rect1 = new LandRectangle(true, 3, 3, 6, 6);
        LandRectangle rect2 = new LandRectangle(true, 1, 6, 6, 8);
        assertIsAdjacent(true, rect1, rect2);
    }
    
    @Test
    public void testAdjacentTo_input16() {
        LandRectangle rect1 = new LandRectangle(true, 3, 3, 6, 6);
        LandRectangle rect2 = new LandRectangle(true, 1, 6, 7, 8);
        assertIsAdjacent(true, rect1, rect2);
    }
    
    @Test
    public void testAdjacentTo_input17() {
        LandRectangle rect1 = new LandRectangle(true, 3, 3, 6, 6);
        LandRectangle rect2 = new LandRectangle(true, 3, 6, 5, 8);
        assertIsAdjacent(true, rect1, rect2);
    }
    
    @Test
    public void testAdjacentTo_input18() {
        LandRectangle rect1 = new LandRectangle(true, 3, 3, 6, 6);
        LandRectangle rect2 = new LandRectangle(true, 3, 6, 6, 8);
        assertIsAdjacent(true, rect1, rect2);
    }
    
    @Test
    public void testAdjacentTo_input19() {
        LandRectangle rect1 = new LandRectangle(true, 3, 3, 6, 6);
        LandRectangle rect2 = new LandRectangle(true, 3, 6, 7, 8);
        assertIsAdjacent(true, rect1, rect2);
    }
    
    @Test
    public void testAdjacentTo_input20() {
        LandRectangle rect1 = new LandRectangle(true, 3, 3, 6, 6);
        LandRectangle rect2 = new LandRectangle(true, 4, 6, 5, 8);
        assertIsAdjacent(true, rect1, rect2);
    }
    
    @Test
    public void testAdjacentTo_input21() {
        LandRectangle rect1 = new LandRectangle(true, 3, 3, 6, 6);
        LandRectangle rect2 = new LandRectangle(true, 4, 6, 6, 8);
        assertIsAdjacent(true, rect1, rect2);
    }
    
    @Test
    public void testAdjacentTo_input22() {
        LandRectangle rect1 = new LandRectangle(true, 3, 3, 6, 6);
        LandRectangle rect2 = new LandRectangle(true, 4, 6, 7, 8);
        assertIsAdjacent(true, rect1, rect2);
    }
    
    @Test
    public void testAdjacentTo_input23() {
        LandRectangle rect1 = new LandRectangle(true, 3, 3, 6, 6);
        LandRectangle rect2 = new LandRectangle(true, 6, 6, 8, 8);
        assertIsAdjacent(false, rect1, rect2);
    }
    
    /*
    public static void main( String[] args ) {
        LandRectangle rect1 = new LandRectangle(true, 3, 3, 6, 6);
        LandRectangle rect2 = new LandRectangle(true, 6, 6, 8, 8);
        System.out.println( rect1 + " | " + rect2 + " | " + rect1.adjacentTo(rect2) + " | " + rect2.adjacentTo(rect1));

        rect1 = new LandRectangle(true, 3, 3, 6, 6);
        rect2 = new LandRectangle(true, 6, 2, 8, 3);
        System.out.println( rect1 + " | " + rect2 + " | " + rect1.adjacentTo(rect2) + " | " + rect2.adjacentTo(rect1));

        rect1 = new LandRectangle(true, 3, 3, 6, 6);
        rect2 = new LandRectangle(true, 6, 2, 8, 5);
        System.out.println( rect1 + " | " + rect2 + " | " + rect1.adjacentTo(rect2) + " | " + rect2.adjacentTo(rect1));

        rect1 = new LandRectangle(true, 3, 3, 6, 6);
        rect2 = new LandRectangle(true, 6, 2, 8, 6);
        System.out.println( rect1 + " | " + rect2 + " | " + rect1.adjacentTo(rect2) + " | " + rect2.adjacentTo(rect1));

        rect1 = new LandRectangle(true, 3, 3, 6, 6);
        rect2 = new LandRectangle(true, 6, 2, 8, 7);
        System.out.println( rect1 + " | " + rect2 + " | " + rect1.adjacentTo(rect2) + " | " + rect2.adjacentTo(rect1));
        
        rect1 = new LandRectangle(true, 3, 3, 6, 6);
        rect2 = new LandRectangle(true, 6, 3, 8, 5);
        System.out.println( rect1 + " | " + rect2 + " | " + rect1.adjacentTo(rect2) + " | " + rect2.adjacentTo(rect1));
        
        rect1 = new LandRectangle(true, 3, 3, 6, 6);
        rect2 = new LandRectangle(true, 6, 3, 8, 6);
        System.out.println( rect1 + " | " + rect2 + " | " + rect1.adjacentTo(rect2) + " | " + rect2.adjacentTo(rect1));

        rect1 = new LandRectangle(true, 3, 3, 6, 6);
        rect2 = new LandRectangle(true, 6, 3, 8, 7);
        System.out.println( rect1 + " | " + rect2 + " | " + rect1.adjacentTo(rect2) + " | " + rect2.adjacentTo(rect1));

        rect1 = new LandRectangle(true, 3, 3, 6, 6);
        rect2 = new LandRectangle(true, 6, 4, 8, 5);
        System.out.println( rect1 + " | " + rect2 + " | " + rect1.adjacentTo(rect2) + " | " + rect2.adjacentTo(rect1));
        
        rect1 = new LandRectangle(true, 3, 3, 6, 6);
        rect2 = new LandRectangle(true, 6, 4, 8, 6);
        System.out.println( rect1 + " | " + rect2 + " | " + rect1.adjacentTo(rect2) + " | " + rect2.adjacentTo(rect1));
        
        rect1 = new LandRectangle(true, 3, 3, 6, 6);
        rect2 = new LandRectangle(true, 6, 4, 8, 7);
        System.out.println( rect1 + " | " + rect2 + " | " + rect1.adjacentTo(rect2) + " | " + rect2.adjacentTo(rect1));

        rect1 = new LandRectangle(true, 3, 3, 6, 6);
        rect2 = new LandRectangle(true, 1, 1, 3, 3);
        System.out.println( rect1 + " | " + rect2 + " | " + rect1.adjacentTo(rect2) + " | " + rect2.adjacentTo(rect1));

        rect1 = new LandRectangle(true, 3, 3, 6, 6);
        rect2 = new LandRectangle(true, 1, 6, 3, 8);
        System.out.println( rect1 + " | " + rect2 + " | " + rect1.adjacentTo(rect2) + " | " + rect2.adjacentTo(rect1));

        rect1 = new LandRectangle(true, 3, 3, 6, 6);
        rect2 = new LandRectangle(true, 1, 6, 4, 8);
        System.out.println( rect1 + " | " + rect2 + " | " + rect1.adjacentTo(rect2) + " | " + rect2.adjacentTo(rect1));

        rect1 = new LandRectangle(true, 3, 3, 6, 6);
        rect2 = new LandRectangle(true, 1, 6, 6, 8);
        System.out.println( rect1 + " | " + rect2 + " | " + rect1.adjacentTo(rect2) + " | " + rect2.adjacentTo(rect1));

        rect1 = new LandRectangle(true, 3, 3, 6, 6);
        rect2 = new LandRectangle(true, 1, 6, 7, 8);
        System.out.println( rect1 + " | " + rect2 + " | " + rect1.adjacentTo(rect2) + " | " + rect2.adjacentTo(rect1));

        rect1 = new LandRectangle(true, 3, 3, 6, 6);
        rect2 = new LandRectangle(true, 3, 6, 5, 8);
        System.out.println( rect1 + " | " + rect2 + " | " + rect1.adjacentTo(rect2) + " | " + rect2.adjacentTo(rect1));

        rect1 = new LandRectangle(true, 3, 3, 6, 6);
        rect2 = new LandRectangle(true, 3, 6, 6, 8);
        System.out.println( rect1 + " | " + rect2 + " | " + rect1.adjacentTo(rect2) + " | " + rect2.adjacentTo(rect1));

        rect1 = new LandRectangle(true, 3, 3, 6, 6);
        rect2 = new LandRectangle(true, 3, 6, 7, 8);
        System.out.println( rect1 + " | " + rect2 + " | " + rect1.adjacentTo(rect2) + " | " + rect2.adjacentTo(rect1));

        rect1 = new LandRectangle(true, 3, 3, 6, 6);
        rect2 = new LandRectangle(true, 4, 6, 5, 8);
        System.out.println( rect1 + " | " + rect2 + " | " + rect1.adjacentTo(rect2) + " | " + rect2.adjacentTo(rect1));

        rect1 = new LandRectangle(true, 3, 3, 6, 6);
        rect2 = new LandRectangle(true, 4, 6, 6, 8);
        System.out.println( rect1 + " | " + rect2 + " | " + rect1.adjacentTo(rect2) + " | " + rect2.adjacentTo(rect1));

        rect1 = new LandRectangle(true, 3, 3, 6, 6);
        rect2 = new LandRectangle(true, 4, 6, 7, 8);
        System.out.println( rect1 + " | " + rect2 + " | " + rect1.adjacentTo(rect2) + " | " + rect2.adjacentTo(rect1));

        rect1 = new LandRectangle(true, 3, 3, 6, 6);
        rect2 = new LandRectangle(true, 6, 6, 8, 8);
        System.out.println( rect1 + " | " + rect2 + " | " + rect1.adjacentTo(rect2) + " | " + rect2.adjacentTo(rect1));

    }*/
}

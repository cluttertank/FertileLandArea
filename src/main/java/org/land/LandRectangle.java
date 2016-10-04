package org.land;

public class LandRectangle {

    final int x1, y1, x2, y2;
    
    final boolean isFertile;
    
    boolean parsed;
    
    LandRectangle(boolean isFertile, int x1, int y1, int x2, int y2) {
        if( x2 <= x1 || y2 <=y1 || x1 < 0 || y1 < 0 ) {
            throw new RuntimeException("Not a real land rectangle");
        }
        this.isFertile = isFertile;
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
    }
    
    int area() {
        return (x2-x1) * (y2-y1); 
    }
    
    boolean adjacentTo(LandRectangle other) {
        boolean adjacent = false;
        
        if( this.isFertile == other.isFertile ) {
            if( this.x1 == other.x2 || this.x2 == other.x1 ) {
                if( (this.y1 <= other.y1 && this.y2 > other.y1) || (this.y2 >= other.y2 && this.y1 < other.y2)
                    || (this.y1 >= other.y1 && this.y2 <= other.y2) || (this.y2 >= other.y2 && this.y1 <= other.y1) ) {
                    adjacent = true;
                }
            } else if( this.y1 == other.y2 || this.y2 == other.y1 ) {
                if( (this.x1 <= other.x1 && this.x2 > other.x1) || (this.x2 >= other.x2 && this.x1 < other.x2)
                    || (this.x1 >= other.x1 && this.x2 <= other.x2) || (this.x2 >= other.x2 && this.x1 <= other.x1) ) {
                    adjacent = true;
                }
            }
        }
//        System.out.println( this + " adjacent to " + other + " : " + adjacent);
        
        return adjacent;
    }
    
//    @Override
//    public String toString() {
//        return "(" + x1 + "," + y1 + "):(" + x2 + "," + y2 + ")";
//    }
    
}

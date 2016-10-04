package org.land;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AgriculturalLandTest {

    @Test
    public void testFertileLandAnalysis_input1() {
        AgriculturalLand land = new AgriculturalLand(0, 0, 400, 600);
        land.parseBarrenLands("{\"0 292 399 307\"}");
        assertEquals("116800 116800", land.getFertileLandAreas());
    }

    @Test
    public void testFertileLandAnalysis_input2() {
        AgriculturalLand land = new AgriculturalLand(0, 0, 400, 600);
        land.parseBarrenLands("{“48 192 351 207”, “48 392 351 407”, “120 52 135 547”, “260 52 275 547”}");
        assertEquals("22816 192608", land.getFertileLandAreas());
    }

    @Test
    public void testFertileLandAnalysis_input2() {
        AgriculturalLand land = new AgriculturalLand(0, 0, 400, 600);
        land.parseBarrenLands("{\"199 299 200 300\"}");
        assertEquals("22816 192608", land.getFertileLandAreas());
    }

    @Test
    public void testInvalidInputFormat_input1() {
        AgriculturalLand land = new AgriculturalLand(0, 0, 400, 600);
        try {
            land.parseBarrenLands("  {  “ 48 192 351 207 547”}");
        } catch( RuntimeException re ) {
            assertEquals("Unparseable input exception", re.getMessage());
        }
    }

    @Test
    public void testInvalidInputFormat_input2() {
        AgriculturalLand land = new AgriculturalLand(0, 0, 400, 600);
        try {
            land.parseBarrenLands("  {  “ 48 192 351 207”, “  48  547”}");
        } catch( RuntimeException re ) {
            assertEquals("Unparseable input exception", re.getMessage());
        }
    }

    @Test
    public void testInvalidBarrenLandCoordinates_input1() {
        AgriculturalLand land = new AgriculturalLand(0, 0, 400, 600);
        try {
            land.parseBarrenLands("{\"0 292 401 307\"}");
        } catch( RuntimeException re ) {
            assertEquals("Invalid barren land co-ordinates", re.getMessage());
        }
    }

    @Test
    public void testInvalidAgriculturalLandCoordinates_input1() {
        try {
            new AgriculturalLand( 3, 3, 1, 6);
        } catch( RuntimeException re ) {
            assertEquals("Not a real land rectangle", re.getMessage());
        }
    }

}

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
    public void testFertileLandAnalysis_input3() {
        AgriculturalLand land = new AgriculturalLand(0, 0, 400, 600);
        land.parseBarrenLands("{\"199 299 200 300\"}");
        assertEquals("239996", land.getFertileLandAreas());
    }

    @Test
    public void testFertileLandAnalysis_input4() {
        AgriculturalLand land = new AgriculturalLand(0, 0, 400, 600);
        land.parseBarrenLands("{\"0 0 399 0\", \"0 0 0 599\", \"0 599 399 599\", \"399 0 399 599\"}");
        assertEquals("238004", land.getFertileLandAreas());
    }

    @Test
    public void testFertileLandAnalysis_input5() {
        AgriculturalLand land = new AgriculturalLand(0, 0, 400, 600);
        land.parseBarrenLands("{\"0 299 399 299\", \"199 0 199 599\"}");
        assertEquals("59501 59700 59800 60000", land.getFertileLandAreas());
    }

    @Test
    public void testFertileLandAnalysis_input6() {
        AgriculturalLand land = new AgriculturalLand(0, 0, 400, 600);
        land.parseBarrenLands("{\"0 0 0 0\", \"399 0 399 0\", \"0 599 0 599\", \"399 599 399 599\"}");
        assertEquals("239996", land.getFertileLandAreas());
    }

    @Test
    public void testFertileLandAnalysis_input7() {
        AgriculturalLand land = new AgriculturalLand(0, 0, 400, 600);
        land.parseBarrenLands("{\"1 0 1 0\", \"0 1 0 1\", \"398 0 398 0\", \"399 1 399 1\", \"0 598 0 598\", \"1 599 1 599\", \"398 599 398 599\", \"399 598 399 598\"}");
        assertEquals("1 1 1 1 239988", land.getFertileLandAreas());
    }

    @Test
    public void testFertileLandAnalysis_input8() {
        AgriculturalLand land = new AgriculturalLand(0, 0, 400, 600);
        land.parseBarrenLands("{\"0 199 399 199\", \"0 399 399 400\", \"199 0 200 599\"}");
        assertEquals("39601 39601 39601 39601 39601 39601", land.getFertileLandAreas());
    }

    @Test
    public void testEmptyInput() {
        AgriculturalLand land = new AgriculturalLand(0, 0, 400, 600);
        land.parseBarrenLands("");
        assertEquals("240000", land.getFertileLandAreas());
    }

    @Test
    public void testNoCoordinates() {
        AgriculturalLand land = new AgriculturalLand(0, 0, 400, 600);
        land.parseBarrenLands(" { } ");
        assertEquals("240000", land.getFertileLandAreas());
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

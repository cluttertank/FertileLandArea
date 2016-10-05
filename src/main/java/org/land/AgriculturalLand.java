package org.land;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AgriculturalLand {

    // Land Coordinates
    private final int x1, y1, x2, y2;
    
    // List of Barren land rectangles within Agricultural Land
    private List<LandRectangle> barrenLands;
    // list of all x coordinates of barren land
    private List<Integer> xs;
    // list of all y coordinates of barren land
    private List<Integer> ys;
    // the x and y coordinates are used of simplification of fertile land analysis
    
    // List of Fertile land rectangles within Agricultural Land
    private List<Integer> fertileLandAreaList;
    
    // Contiguous fertile land rectangle map, key is just an indicator of contiguous land area
    private Map<Integer, List<LandRectangle>> fertileLandRectangleMap;
    
    // Contiguous fertile land area in meter square, key is the indicator of contiguous land area
    private Map<Integer, Integer> fertileLandAreaMap;
    
    
    public AgriculturalLand(int x1, int y1, int x2, int y2) {
        if( x2 <= x1 || y2 <=y1 || x1 < 0 || y1 < 0 ) {
            throw new RuntimeException("Not a real land rectangle");
        }
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
        
        fertileLandRectangleMap = new HashMap<Integer, List<LandRectangle>>();
        fertileLandAreaMap = new HashMap<Integer, Integer>();
        barrenLands = new ArrayList<LandRectangle>();
        xs = new ArrayList<Integer>();
        ys = new ArrayList<Integer>();
        xs.add(x1);
        xs.add(x2);
        ys.add(y1);
        ys.add(y2);
    }

    public void parseBarrenLands(String barrenLandInputString) {
        parseBarrenLandInputString(barrenLandInputString);
        performFertileLandAnalysis();
    }

    // Validate and parse the input string.
    // Input Example: {“48 192 351 207”, “48 392 351 407”, “120 52 135 547”, “260 52 275 547”}
    private void parseBarrenLandInputString(String barrenLandInputString) {
        
        // If input is empty or has no coordinates, return
        if( barrenLandInputString == null || barrenLandInputString.trim().length() == 0
            || Pattern.compile("\\s*\\{\\s*\\}\\s*").matcher(barrenLandInputString).matches() ) {
            return;
        }
        
        // If input is not in the right format throw an error
        Pattern p = Pattern.compile("\\s*\\{\\s*[\"|“]\\s*\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s*[\"|”]\\s*(,\\s*[\"|“]\\s*\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s*[\"|”]\\s*){0,}\\}\\s*");
        Matcher m = p.matcher(barrenLandInputString);
        if( ! m.matches() ){
            throw new RuntimeException("Unparseable input exception");
        }
        barrenLandInputString = barrenLandInputString.replaceAll("\\{|\\}", "");
        String[] rects = barrenLandInputString.split("\\s*,\\s*");

        // set of all x coordinates
        Set<Integer> xSet = new LinkedHashSet<>();
        xSet.add(x1);
        xSet.add(x2);

        // set of all y coordinates
        Set<Integer> ySet = new LinkedHashSet<>();
        ySet.add(y1);
        ySet.add(y2);
        
        // parse the input string to derive barren lands as land rectangles
        for (String r : rects) {
            r = r.replaceAll("\"|“|”", "");
            String coordinates[] = r.split(" ");
            if (coordinates.length == 4) {
                int x1 = Integer.valueOf(coordinates[0]);
                int y1 = Integer.valueOf(coordinates[1]);
                int x2 = Integer.valueOf(coordinates[2]) + 1;
                int y2 = Integer.valueOf(coordinates[3]) + 1;
                if( x1 < this.x1 || y1 < this.y1 || x2 > this.x2 || y2 > this.y2 ) {
                    throw new RuntimeException("Invalid barren land co-ordinates");
                }
                this.barrenLands.add(new LandRectangle(x1, y1, x2, y2));
                xSet.add(x1);
                xSet.add(x2);
                ySet.add(y1);
                ySet.add(y2);
                
            }
        }
        xs = new ArrayList<Integer>(xSet);
        ys = new ArrayList<Integer>(ySet);
        // sort the lists in ascending order
        Collections.sort(xs);
        Collections.sort(ys);
    }

    // This is the key method which parses the barren land rectangles and calculates the fertile land area
    // Based on barren land coordinates, the agricultural land is divided into rectangles.
    // The fertile land rectangles grouped if they are adjacent to each other and their area is combined into collections.
    private void performFertileLandAnalysis() {

        Integer fertileLandAreaCount = 1;
        Integer fertileLandAreaIndex = 0;
        
        for( int i=0; i < xs.size()-1; i++ ) {
            int x1 = xs.get(i);
            int x2 = xs.get(i+1);
            
            for( int j=0; j < ys.size()-1; j++ ) {
                int y1 = ys.get(j);
                int y2 = ys.get(j+1);
                boolean fertile = !barrenLands.stream().anyMatch( barrenLand -> {
                    if( (x1 >= barrenLand.x1 && x2 <= barrenLand.x2) && (y1 >= barrenLand.y1 && y2 <= barrenLand.y2) )
                        return true;
                    else
                        return false;
                } );
                if( fertile ) {
                    LandRectangle fertileLandRectangle = new LandRectangle(x1, y1, x2, y2);
                    if( fertileLandRectangleMap.isEmpty() ) {
                        List<LandRectangle> fertileLandRectangleList = new ArrayList<LandRectangle>();
                        fertileLandRectangleList.add(fertileLandRectangle);
                        fertileLandRectangleMap.put(fertileLandAreaIndex, fertileLandRectangleList);
                        fertileLandAreaMap.put(fertileLandAreaIndex, fertileLandRectangle.area());
                    } else {
                        fertileLandAreaIndex = fertileLandRectangleMap.entrySet().stream()
                            .filter( entry -> {
                                if( entry.getValue().stream().anyMatch(land -> fertileLandRectangle.adjacentTo(land)) ) {
                                    entry.getValue().add(fertileLandRectangle);
                                    return true;
                                }
                                return false;
                            } )
                            .map( entry -> entry.getKey() )
                            .findFirst().orElse(fertileLandAreaCount++);
                        Integer fertileLandArea = fertileLandAreaMap.getOrDefault(fertileLandAreaIndex, 0);
                        fertileLandAreaMap.put(fertileLandAreaIndex, fertileLandArea + fertileLandRectangle.area());
                        List<LandRectangle> fertileLandRectangleList = fertileLandRectangleMap.getOrDefault(fertileLandAreaIndex, new ArrayList<LandRectangle>());
                        fertileLandRectangleList.add(fertileLandRectangle);
                        fertileLandRectangleMap.put(fertileLandAreaIndex, fertileLandRectangleList);
                    }
                }
            }
        }
        reconcileDistributedAreas();
        fertileLandAreaList = fertileLandAreaMap.values().stream()
            .filter( area -> area != 0 )
            .sorted()
            .collect(Collectors.toList());
    }
    
    public String getFertileLandAreas() {
        return fertileLandAreaList.toString().replaceAll("[\\[\\],]", "");
    }
    
    // Initial parse is sequential parse due to which we can end with several disjoint area of fertile land even if they are actually contiguous
    // this method goes through all fertile land ares and reconciles such disjoint ares into one
    private void reconcileDistributedAreas() {
        fertileLandRectangleMap.forEach( (areaIndex1, landList1) -> {
                if( !landList1.isEmpty() ) {
                    fertileLandRectangleMap.forEach( (areaIndex2, landList2) -> {
                        if( areaIndex1 != areaIndex2 && !landList2.isEmpty() ) {
                            if( landList1.stream().anyMatch( land1 -> 
                                landList2.stream().anyMatch( land2 -> land1.adjacentTo(land2) ) ) ) {
                                landList1.addAll(landList2);
                                landList2.clear();
                                fertileLandAreaMap.put(areaIndex1, fertileLandAreaMap.get(areaIndex1) + fertileLandAreaMap.get(areaIndex2));
                                fertileLandAreaMap.put(areaIndex2, 0);
                            }
                        }
                    } );
                }
            } );
    }

}


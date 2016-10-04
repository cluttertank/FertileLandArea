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

    private final int x1, y1, x2, y2;
    
    private List<LandRectangle> barrenLands;
    
    private List<Integer> fertileLandAreaList;
    
    private Map<Integer, List<LandRectangle>> fertileLandRectangleMap;

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
    }

    public void parseBarrenLands(String barrenLandInputString) {
        parseBarrenLandInputString(barrenLandInputString);
        parseBarrenLands();
    }

    private void parseBarrenLandInputString(String barrenLandInputString) {
        if( barrenLandInputString == null || barrenLandInputString.length() == 0 )
            return;
        
        Pattern p = Pattern.compile("\\s*\\{\\s*[\"|“]\\s*\\d+\\s*\\d+\\s*\\d+\\s*\\d+\\s*[\"|”]\\s*(,\\s*[\"|“]\\s*\\d+\\s*\\d+\\s*\\d+\\s*\\d+\\s*[\"|”]\\s*){0,}\\}\\s*");
        Matcher m = p.matcher(barrenLandInputString);
        if( ! m.matches() ){
            throw new RuntimeException("Unparseable input exception");
        }
        barrenLandInputString = barrenLandInputString.replaceAll("\\{|\\}", "");
        String[] rects = barrenLandInputString.split("\\s*,\\s*");

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
                this.barrenLands.add(new LandRectangle(false, x1, y1, x2, y2));
            }
        }
    }

    private void parseBarrenLands() {
        
        List<Integer> xs = new ArrayList<Integer>();
        xs.add(x1);
        xs.add(x2);
        List<Integer> ys = new ArrayList<Integer>();
        ys.add(y1);
        ys.add(y2);
        
        barrenLands.forEach( barrenLand -> {
            xs.add(barrenLand.x1);
            xs.add(barrenLand.x2);
            ys.add(barrenLand.y1);
            ys.add(barrenLand.y2);
        } );
        
        Set<Integer> xSet = new LinkedHashSet<>(xs);
        xs.clear();
        xs.addAll(xSet);

        Set<Integer> ySet = new LinkedHashSet<>(ys);
        ys.clear();
        ys.addAll(ySet);
        
        Collections.sort(xs);
        Collections.sort(ys);

//        System.out.println( "xs: " + xs );
//        System.out.println( "ys: " + ys );
        
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
//                System.out.println( "Fertile Land: " + fertile + " = (" + x1 + "," + y1 + "):(" + x2 + "," + y2 + ")" );
                if( fertile ) {
                    LandRectangle fertileLandRectangle = new LandRectangle(true, x1, y1, x2, y2);
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
//                        System.out.println( "fertileLandAreaIndex: " + fertileLandAreaIndex );
                        Integer fertileLandArea = fertileLandAreaMap.getOrDefault(fertileLandAreaIndex, 0);
                        fertileLandAreaMap.put(fertileLandAreaIndex, fertileLandArea + fertileLandRectangle.area());
                        List<LandRectangle> fertileLandRectangleList = fertileLandRectangleMap.getOrDefault(fertileLandAreaIndex, new ArrayList<LandRectangle>());
                        fertileLandRectangleList.add(fertileLandRectangle);
                        fertileLandRectangleMap.put(fertileLandAreaIndex, fertileLandRectangleList);
                    }
                }
            }
        }
//        System.out.println( "fertileLandAreaMap: " + fertileLandAreaMap );
        reconcileDistributedAreas();
        fertileLandAreaList = fertileLandAreaMap.values().stream()
            .filter( area -> area != 0 )
            .sorted()
            .collect(Collectors.toList());
//        System.out.println( "filteredLandAreaList: " + fertileLandAreaList );
    }
    
    public String getFertileLandAreas() {
        return fertileLandAreaList.toString().replaceAll("[\\[\\],]", "");
    }
    
    private void reconcileDistributedAreas() {
        fertileLandRectangleMap.forEach( (areaIndex1, landList1) -> {
                if( !landList1.isEmpty() ) {
//                    System.out.println("Map1 -> " + areaIndex1 + " = ");
                    fertileLandRectangleMap.forEach( (areaIndex2, landList2) -> {
//                        System.out.println("Map2 -> " + areaIndex2 + " = ");
                        if( areaIndex1 != areaIndex2 && !landList2.isEmpty() ) {
//                            System.out.println("Here");
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


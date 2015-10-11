// THIS CLASS IS COMMENTED OUT BECAUSE IT IS UNDER CONSTRUCTION AND DOES NOT CURRENTLY COMPILE
// /**
// * Class that represents the shortest route between two tiles on a Map, taking Terrain into account
// * @author Ryan Voor
// * @version 1.0
// */
// import java.util.ArrayList;
//
// public class Route {
//
//   // start is index 0 and end is the last index
//   TileContainer[] route;
//
//   /**
//   * constructor for the Route class
//   * @param map the map in which this Route is located
//   * @param startX the x coordinate of the start of the Route
//   * @param startY the y coordinate of the start of the Route
//   * @param endX the x coordinate of the end of the Route
//   * @param endY the y coordinate of the end of the Route
//   */
//   public Route(Map map, int startX, int startY, int endX, int endY) {
//     this.route = calculateShortestRoute(map, startX, startY, endX, endY);
//   }
//
//   /**
//   * calculates the shortest route between the locations specified by the parameter coordinates
//   * located in the parameter map, does take Terrain into account
//   * @return TileContainer[] the route between the two locations
//   */
//   private TileContainer[] calculateShortestRoute(Map map, int startX, int startY, int endX, int endY) {
//     // represents the slowly growing 'cloud' of Tiles that I will expand as I go through the algorithm
//     ArrayList<CloudNode> cloud = new ArrayList<CloudNode>();
//     cloud.add(new CloudNode(map.getTile(startX, startY), 0));
//     // the final route that this method will return
//     TileContainer[] route;
//
//     // trying to implement the algorithm shown in the response here: http://stackoverflow.com/questions/2311486/how-to-calculate-the-shortest-path-between-two-points-in-a-grid
//     boolean finished = false;
//     while (!finished) {
//       cloud = expandCloud(map, endX, endY, cloud);
//       if (cloud.contains(map.getTile(endX, endY))) {
//         finished = true;
//       }
//     }
//     route = calculatePath(cloud, endX, endY);
//     return route;
//   }
//
//   /**
//   * expands the parameter cloud by getting the neighbors of all the Nodes that haven't gotten neighbors yet
//   * @param map the map in which this Route is located
//   * @param endX the x-coordinate of the end Tile
//   * @param endY the y-coordinate of the end Tile
//   * @param cloud the cloud to be expanded
//   * @return ArrayList<CloudNode> the expanded cloud
//   */
//   private ArrayList<CloudNode> expandCloud(Map map, int endX, int endY, ArrayList<CloudNode> cloud) {
//     for (CloudNode node: cloud) {
//       if (!node.hasBeenExpanded()) {
//         // .getNeighboringNodesForCloud should only return nodes that are not already in the parameter cloud
//         // .getNeighboringNodesForCloud should also calculate the movementCost stuff and decrement those as it
//         // goes through (THIS DOESN'T SEEM RIGHT... this will only work if this method checks the nodes that are already in the cloud and decrements them... figure this out!)
//         cloud.addAll(node.getNeighboringNodesForCloud(map, int endX, int endY, cloud));
//       }
//     }
//     return cloud;
//   }
//
//   // CONSIDER MAKING THE CHANGE THAT TILECONTAINERS NOW KEEP TRACK OF WHERE THEY ARE IN THE MAP SO THAT WAY YOU CAN JUST GET THE INFORMATION THERE? GIVE THIS SOME THOUGHT
//
//   /**
//   * calculates the shortest path between the start and end positions given
//   * @param cloud the cloud through which the path will be calcualted
//   * @return TileContainer[] the array that contains the shortest route between the start and end locations
//   */
//   private TileContainer[] calculatePath(ArrayList<CloudNode> cloud) {
//
//   }
//
//   // MIGHT NEED TO MAKE PRIVATE INNER CLASS TO USE TO KEEP TRACK OF STUFF FOR THE TILECONTAINERS LIKE TERRAIN MOVEMENT COST (WILL BE DECREMENTING IT) AND WHETHER I'VE GOTTEN THE NEIGHBORS OF A TILE OR NOT
//   private class CloudNode {
//
//     private TileContainer tileContainer;
//     private int xCoordinate;
//     private int yCoordinate;
//     private int movementCost;
//     private boolean isEndNode;
//     private boolean hasBeenExpanded;
//
//     /**
//     * constructor for the private inner class CloudNode
//     * @param tileContainer the TileContainer that this CloudNode contains
//     * @param movementCost the movement cost of the Terrain of the Tile
//     * @param isEndNode whether this Tile is the end Node for the route
//     */
//     CloudNode(TileContainer tileContainer, int xCoordinate, int yCoordinate, int movementCost, boolean isEndNode) {
//       this.tileContainer = tileContainer;
//       this.xCoordinate = xCoordinate;
//       this.yCoordinate = yCoordinate;
//       this.movementCost = movementCost;
//       this.isEndnode = isEndNode;
//       hasBeenExpanded = false;
//     }
//
//     /**
//     * getter for the tileContainer field of this CloudNode
//     * @return TileContainer the TileContainer of this CloudNode
//     */
//     public TileContainer getTileContainer() {
//       return this.tileContainer;
//     }
//
//     // NEED TO WRITE GETTERS FOR THE COORDINATE FIELDS
//
//     /**
//     * getter for the movementCost field of this CloudNode
//     * @return int the movementCost of the Terrain of the Tile of this CloudNode
//     */
//     public int getMovementCost() {
//       return this.movementCost();
//     }
//
//     /**
//     * getter for the isEndNode field of this CloudNode
//     * @return boolean whether this CloudNode is the end node for the Route
//     */
//     public boolean isEndNode() {
//       return this.isEndNode;
//     }
//
//     /**
//     * getter for the hasBeenExpanded field of this CloudNode
//     * @return boolean whether this CloudNode has been expanded
//     */
//     public boolean hasBeenExpanded() {
//       return this.hasBeenExpanded;
//     }
//
//     /**
//     * gets the neighboring nodes for this CloudNode that are not already in the parameter cloud
//     * @param map the map in which this Route is located
//     * @param endX the x-coordinate of the end Tile of this Route
//     * @param endY the y-coordinate of the end Tile of this Route
//     * @param cloud the cloud which is being expanded
//     * @return ArrayList<CloudNode> the neighboring tiles of this tile that are not already in the parameter cloud
//     */
//     public ArrayList<CloudNode> getNeightboringNodesForCloud(Map map, int endX, int endY, ArrayList<CloudNode> cloud) {
//       ArrayList<CloudNode> result = new ArrayList<CloudNode>(4);
//       TileContainer[] neighboringTiles = map.getNeighboringTiles(this.getXCoordinate(), this.getYCoordinate());
//       // WILL HAVE TO WRITE AN EQUALS METHOD FOR THE TILECONTAINER CLASS
//       // check to see if any of these tiles are already in the cloud
//       for (TileContainer tile : neighboringTiles) {
//         if (!cloud.contains(tile)) {
//           // not sure how to get the coordinate information, check not about making tilecontainers keep track of their own coordinates
//           result.add(new CloudNode(tile, ???, ???, tile.getTile().getTerrain().getMovementCost(), tile.equals(map.getTile(endX, endY))));
//         }
//       }
//     }
//   }
// }

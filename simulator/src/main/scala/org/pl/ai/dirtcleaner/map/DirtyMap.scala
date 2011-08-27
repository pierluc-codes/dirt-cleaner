/*
 * Copyright 2011 Pier-Luc Caron St-Pierre
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.pl.ai.dirtcleaner.map

import scala.Int
import collection.mutable.HashMap

/**
 * Represent a map that will be used to run the simulation
 * @author Pier-Luc Caron St-Pierre <pierluc.caronstpierre@gmail.com>
 *
 * @param width Width of the map
 * @param height Height of the map
 * @mapName name of the map
 */
final class DirtyMap(val width: Int, val height: Int, val mapName: String) {
  if (width < 1 || height < 1) {
    throw new IllegalArgumentException("Invalid size of map (width : " + width + ", height : " + height)
  }

  if (mapName == null) {
    throw new IllegalArgumentException("Map name cannot be null")
  }

  private val map = new HashMap[(Int, Int), Tile]

  /**
   * Get the selected tile. If the position do not exists, it return a wall.
   * If the tile never been set, it return a clean floor
   * The tile returned is a deep copy of the tile.
   *
   * @param x position in x of the tile to get
   * @param y position in y of the tile to get
   * @return the selected tile
   */
  def getTile(x: Int, y: Int): Tile = {

    // If outside limits, return a wall
    if (x < 0 || x >= width || y < 0 || y >= height) {
      return new Tile(false, TileType.Wall)
    }

    map.getOrElseUpdate((x, y), new Tile(false, TileType.Floor)).copyTile
  }

  /**
   * Set the tile on the specified position
   * @param x position in x of the tile to set
   * @param y position in y of the tile to set
   * @param tile the tile to set
   */
  def setTile(x: Int, y: Int, tile: Tile): Unit = {
    map += (x, y) -> tile
  }

  /**
   * Return a deep copy of the map.
   * @return a copy of the map
   */
  def copyMap: DirtyMap = {
    val newMap = new DirtyMap(width, height, mapName)

    map.foreach {
      case (position, tile) =>
        newMap.map(position) = tile.copyTile
    }

    newMap
  }

  /**
   * Count the number of clean tile in the map.
   * Dirty tile and wall are excluded of the count.
   * @return the number of clean tile
   */
  def getNumberOfCleanTile: Int = {
    var count = width * height

    map.foreach {
      case (position, tile) =>
        if (tile.isDirty || tile.tileType == TileType.Wall) {
          count -= 1
        }
    }

    count
  }
}
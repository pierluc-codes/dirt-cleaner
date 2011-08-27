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

import io.Source
/**
 * Custom map loader used for load text files.
 * The x character specifies that the tile is dirty
 * The * character specifies that the tile is a wall
 * @author Pier-Luc Caron St-Pierre <pierluc.caronstpierre@gmail.com>
 *
 * @param mapName the name of the map
 * @param filePath the path of the file to load
 */
class MapLoaderTextFile(val mapName: String, val filePath: String) extends MapLoader {

  val WALL_CHARACTER = '*'
  val DIRT_CHARACTER = 'x'

  /**
   * PreLoad the file.
   * This method read the file a first time to load the size of the map.
   * @return It return a tuple (lines length max, lines count).
   */
  private def preLoad: (Int, Int) = {
    val source = Source.fromFile(filePath)
    val lines = source.getLines

    var lineLengthMax = 0
    var linesCount = 0

    for (line <- lines) {
      if (line.length > lineLengthMax) {
        // Find the max line length
        lineLengthMax = line.length
      }

      // Count the number of lines
      linesCount += 1
    }

    source.close

    (lineLengthMax, linesCount)
  }

  /**
   * @{inheritDoc}
   */
  def loadMap: DirtyMap = {
    // Preload to file to obtains the size of the map
    val (sizeX, sizeY) = preLoad

    val dirtyMap = new DirtyMap(sizeX, sizeY, mapName)

    val source = Source.fromFile(filePath)
    val lines = source.getLines

    var rowNumber = 0

    // Read each line
    for (line <- lines) {
      var columnNumber = 0

      for (character <- line) {
        // Check if it is a special tile
        var tileType = TileType.Floor
        var isDirty = false

        if (character == WALL_CHARACTER) {
          tileType = TileType.Wall
        }

        if (character == DIRT_CHARACTER) {
          isDirty = true
        }

        // Create the tile
        val tile = new Tile(isDirty, tileType)

        // Add it to the maap
        dirtyMap.setTile(columnNumber, rowNumber, tile)

        columnNumber += 1
      }

      rowNumber += 1
    }

    source.close

    dirtyMap
  }
}
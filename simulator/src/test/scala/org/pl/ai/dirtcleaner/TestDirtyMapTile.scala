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

package org.pl.ai.dirtcleaner

import map.{TileType, Tile, DirtyMap}
import org.junit.{Before, Test}
import org.junit.Assert._
import org.scalatest.junit.AssertionsForJUnit

/**
 * @author Pier-Luc Caron St-Pierre <pierluc.caronstpierre@gmail.com>
 *
 */
class TestDirtyMapTile extends AssertionsForJUnit {
  var dirtyMap: DirtyMap = _

  var dirtyFloorTile: Tile = _
  var cleanFloorTile: Tile = _
  var wallTile: Tile = _

  @Before
  def setup = {
    dirtyMap = new DirtyMap(10, 10, "MyMap")

    dirtyFloorTile = new Tile(true, TileType.Floor)
    dirtyMap.setTile(1, 1, dirtyFloorTile)

    cleanFloorTile = new Tile(false, TileType.Floor)
    dirtyMap.setTile(2, 2, cleanFloorTile)

    wallTile = new Tile(false, TileType.Wall)
    dirtyMap.setTile(3, 3, wallTile)
  }

  @Test
  def testTileOutsideLimitShouldBeWall = {
    var tile = dirtyMap.getTile(-1, 1)
    assertEquals(TileType.Wall, tile.tileType)

    tile = dirtyMap.getTile(1, -1)
    assertEquals(TileType.Wall, tile.tileType)

    tile = dirtyMap.getTile(-1, 1)
    assertEquals(TileType.Wall, tile.tileType)

    tile = dirtyMap.getTile(10, 1)
    assertEquals(TileType.Wall, tile.tileType)

    tile = dirtyMap.getTile(-1, 1)
    assertEquals(TileType.Wall, tile.tileType)

    tile = dirtyMap.getTile(1, -10)
    assertEquals(TileType.Wall, tile.tileType)

    tile = dirtyMap.getTile(-1, 1)
    assertEquals(TileType.Wall, tile.tileType)

  }

  @Test
  def testSetFloor = {
    // We need to test each attributes because of the deep copy
    var testingTile = dirtyMap.getTile(1, 1)
    assertEquals(true, testingTile.isDirty)
    assertEquals(TileType.Floor, testingTile.tileType)

    testingTile = dirtyMap.getTile(2, 2)
    assertEquals(false, testingTile.isDirty)
    assertEquals(TileType.Floor, testingTile.tileType)

    testingTile = dirtyMap.getTile(3, 3)
    assertEquals(TileType.Wall, testingTile.tileType)
  }

  @Test
  def testDeepCopy = {
    var testingTile = dirtyMap.getTile(1, 1)

    // Not the same object
    assertNotSame(dirtyFloorTile, testingTile)

    // But have the same value
    assertEquals(true, testingTile.isDirty)
    assertEquals(TileType.Floor, testingTile.tileType)
  }

  @Test
  def testCopyMap = {
    val dirtyMapCopy = dirtyMap.copyMap

    assertNotSame(dirtyMap, dirtyMapCopy)

    assertNotSame(dirtyMap.getTile(0, 0), dirtyMapCopy.getTile(0, 0))

    val dirtyFloorTileCopy = dirtyMapCopy.getTile(1, 1)
    assertNotSame(dirtyFloorTile, dirtyFloorTileCopy)
    assertEquals(dirtyFloorTile.isDirty, dirtyFloorTileCopy.isDirty)
    assertEquals(dirtyFloorTile.tileType, dirtyFloorTileCopy.tileType)

    val cleanFloorTileCopy = dirtyMapCopy.getTile(2, 2)
    assertNotSame(cleanFloorTile, cleanFloorTileCopy)
    assertEquals(cleanFloorTile.isDirty, cleanFloorTileCopy.isDirty)
    assertEquals(cleanFloorTile.tileType, cleanFloorTileCopy.tileType)

    val wallTileCopy = dirtyMapCopy.getTile(3, 3)
    assertNotSame(wallTile, wallTileCopy)
    assertEquals(wallTile.tileType, wallTileCopy.tileType)
  }

  @Test
  def testCleanTile = {
    assertEquals(98, dirtyMap.getNumberOfCleanTile)

    dirtyMap.setTile(5, 5, new Tile(true, TileType.Floor))
    assertEquals(97, dirtyMap.getNumberOfCleanTile)

    dirtyMap.setTile(5, 5, new Tile(true, TileType.Floor))
    assertEquals(97, dirtyMap.getNumberOfCleanTile)
  }

}
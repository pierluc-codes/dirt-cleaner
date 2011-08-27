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

import map.{TileType, DirtyMap, MapLoaderTextFile}
import org.scalatest.junit.AssertionsForJUnit

/**
 * @author Pier-Luc Caron St-Pierre <pierluc.caronstpierre@gmail.com>
 *
 */

import org.junit.{Test, Before}
import org.junit.Assert._

class TestMapLoaderTextFile extends AssertionsForJUnit {

  var map: DirtyMap = _

  @Before
  def setup = {
    val mapLoader = new MapLoaderTextFile("MyMap", "myMap.map")
    map = mapLoader.loadMap
  }

  @Test
  def testMapName = {
    assertEquals("MyMap", map.mapName)
  }

  @Test
  def testSize = {
    assertEquals(10, map.width)
    assertEquals(5, map.height)
  }

  @Test
  def testCleanTile = {
    val tile = map.getTile(1, 1)

    assertEquals(TileType.Floor, tile.tileType)
    assertEquals(false, tile.isDirty)
  }

  @Test
  def testDirtyTile = {
    val tile = map.getTile(2, 1)

    assertEquals(TileType.Floor, tile.tileType)
    assertEquals(true, tile.isDirty)
  }

  @Test
  def testWall = {
    val tile = map.getTile(0, 0)

    assertEquals(TileType.Wall, tile.tileType)
  }

}
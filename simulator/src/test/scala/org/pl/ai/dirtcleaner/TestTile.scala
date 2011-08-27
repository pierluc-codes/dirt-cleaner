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

import map.{TileType, Tile}
import org.scalatest.junit.AssertionsForJUnit
import org.junit.Test
import org.junit.Assert._

/**
 * @author Pier-Luc Caron St-Pierre <pierluc.caronstpierre@gmail.com>
 *
 */
class TestTile extends AssertionsForJUnit {
  @Test
  def testCanMoveOnFloor = {
    val tile = new Tile(true, TileType.Floor)
    assertTrue(tile.canMove)
  }

  @Test
  def testCannotMoveOnWall = {
    val tile = new Tile(true, TileType.Wall)
    assertFalse(tile.canMove)
  }

  @Test
  def testDeepCopy = {
    val tile = new Tile(true, TileType.Wall)
    val tileCopy = tile.copyTile

    assertNotSame(tile, tileCopy)
    assertEquals(tile.isDirty, tileCopy.isDirty)
    assertEquals(tile.tileType, tileCopy.tileType)
  }
}
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

import agent.{CleanerAction, Cleaner}
import engine.TurnEngine
import map.{TileType, Tile, DirtyMap}
import org.scalatest.junit.AssertionsForJUnit
import org.junit.{Test, Before}
import org.junit.Assert._
import java.lang.Exception
/**
 * @author Pier-Luc Caron St-Pierre <pierluc.caronstpierre@gmail.com>
 *
 */
class TestTurnEngine extends AssertionsForJUnit {
  var dirtyMap: DirtyMap = _
  var rightyCleaner: Cleaner = _
  var cleanyCleaner: Cleaner = _
  var turnEngineForRighty: TurnEngine = _
  var turnEngineForExceptional: TurnEngine = _
  var turnEngineForCleany: TurnEngine = _

  @Before
  def setup = {

    val rightyCleaner = new Cleaner {
      def getNextMove(dirtyMap: DirtyMap, x: Int, y: Int) = CleanerAction.Right

      def getName = "Righty"
    }

    val exceptionalCleaner = new Cleaner {
      def getNextMove(dirtyMap: DirtyMap, x: Int, y: Int) = throw new Exception("Haha! Got you!")

      def getName = "Exceptional"
    }

    val cleanyCleaner = new Cleaner {
      def getNextMove(dirtyMap: DirtyMap, x: Int, y: Int) = CleanerAction.Suck

      def getName = "Cleany"
    }

    dirtyMap = new DirtyMap(5, 5, "MyMap")

    dirtyMap.setTile(2, 1, new Tile(true, TileType.Floor))
    dirtyMap.setTile(3, 1, new Tile(false, TileType.Wall))

    turnEngineForRighty = new TurnEngine(rightyCleaner, dirtyMap)
    turnEngineForExceptional = new TurnEngine(exceptionalCleaner, dirtyMap)
    turnEngineForCleany = new TurnEngine(cleanyCleaner, dirtyMap)
  }

  @Test
  def shouldCatchCleanerException: Unit = {
    turnEngineForExceptional.playTurn(1, 1)

    //Shoud catch cleaner generated exception
  }

  @Test
  def shouldMoveCleanerOnFloor = {
    val (newX, newY) = turnEngineForRighty.playTurn(0, 1)

    assertEquals(1, newX)
    assertEquals(1, newY)
  }

  @Test
  def shouldNotMoveCleanerOnWall = {
    val (newX, newY) = turnEngineForRighty.playTurn(2, 1)

    assertEquals(2, newX)
    assertEquals(1, newY)
  }

  @Test
  def shouldCleanDirtyFloor = {
    assertEquals(true, dirtyMap.getTile(2, 1).isDirty)

    val (newX, newY) = turnEngineForCleany.playTurn(2, 1)

    assertEquals(false, dirtyMap.getTile(2, 1).isDirty)
  }

  @Test
  def shouldCleanCleanFloor = {
    val (newX, newY) = turnEngineForCleany.playTurn(1, 1)

    assertEquals(false, dirtyMap.getTile(1, 1).isDirty)
  }


}
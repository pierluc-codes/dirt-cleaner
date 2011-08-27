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
import engine.SimulationEngine
import map.{TileType, Tile, DirtyMap}
import org.junit.{Before, Test}
import org.scalatest.junit.AssertionsForJUnit
import org.junit.Assert._

/**
 * @author Pier-Luc Caron St-Pierre <pierluc.caronstpierre@gmail.com>
 *
 */
class TestSimulationEngine extends AssertionsForJUnit {

  var rightyCleanCleaner: Cleaner = _
  var standyCleaner: Cleaner = _
  var dirtyMap: DirtyMap = _

  @Before
  def setup = {
    dirtyMap = new DirtyMap(4, 4, "MyMap")

    // First row dirty
    dirtyMap.setTile(0, 0, new Tile(true, TileType.Floor))
    dirtyMap.setTile(1, 0, new Tile(true, TileType.Floor))
    dirtyMap.setTile(2, 0, new Tile(true, TileType.Floor))
    dirtyMap.setTile(3, 0, new Tile(true, TileType.Floor))


    rightyCleanCleaner = new Cleaner {
      def getNextMove(dirtyMap: DirtyMap, x: Int, y: Int): CleanerAction = {
        var nextAction: CleanerAction = CleanerAction.Right

        if (dirtyMap.getTile(x, y).isDirty) {
          nextAction = CleanerAction.Suck
        }

        nextAction
      }

      def getName = "RightyCleanCleaner"
    }

    standyCleaner = new Cleaner {
      def getNextMove(dirtyMap: DirtyMap, x: Int, y: Int): CleanerAction = CleanerAction.Stand
      def getName = "StandyCleaner"
    }

  }

  @Test
  def testSimulationForCleaner = {
    val simulation = new SimulationEngine(8, dirtyMap)

    val score = simulation.playSimulationForCleaner(rightyCleanCleaner, (0, 0))

    assertEquals(116, score)
  }

  @Test
  def testScoreBoardForCleaner = {
    val simulation = new SimulationEngine(8, dirtyMap)

    simulation.addCleaner(standyCleaner, 2, 2)
    simulation.addCleaner(rightyCleanCleaner, 0, 0)

    val scoreboard = simulation.playSimulation

    assertEquals(96, scoreboard.getScore(standyCleaner))
    assertEquals(116, scoreboard.getScore(rightyCleanCleaner))
  }
}
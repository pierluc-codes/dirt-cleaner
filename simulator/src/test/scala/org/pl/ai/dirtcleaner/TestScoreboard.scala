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
import map.DirtyMap
import org.junit.{Before, Test}
import org.junit.Assert._
import org.scalatest.junit.AssertionsForJUnit

/**
 * @author Pier-Luc Caron St-Pierre <pierluc.caronstpierre@gmail.com>
 *
 */
class TestScoreboard extends AssertionsForJUnit {
  var testingCleaner: Cleaner = _

  @Before
  def setup = {
    testingCleaner = new Cleaner {
      def getNextMove(dirtyMap: DirtyMap, x: Int, y: Int) = CleanerAction.Stand

      def getName = "DoNotMoveCleaner"
    }
  }

  @Test
  def testGetScore = {
    val scoreboard = new Scoreboard

    scoreboard.addScore(testingCleaner, 10)

    assertEquals(10, scoreboard.getScore(testingCleaner))
  }

  @Test
  def addScoreIsIncremental = {
    val scoreboard = new Scoreboard

    scoreboard.addScore(testingCleaner, 10)
    scoreboard.addScore(testingCleaner, 10)

    assertEquals(20, scoreboard.getScore(testingCleaner))
  }

  @Test
  def defaultScoreIsZero = {
    val scoreboard = new Scoreboard

    assertEquals(0, scoreboard.getScore(testingCleaner))
  }
}
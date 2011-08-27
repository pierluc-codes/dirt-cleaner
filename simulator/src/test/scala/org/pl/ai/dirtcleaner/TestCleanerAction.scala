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

import agent.CleanerAction

import org.junit.Test
import org.junit.Assert._
import org.scalatest.junit.AssertionsForJUnit

/**
 * @author Pier-Luc Caron St-Pierre <pierluc.caronstpierre@gmail.com>
 *
 */
class TestCleanerAction extends AssertionsForJUnit {

  @Test
  def testLeft {
    val nextX = CleanerAction.Left.getNextX(5)
    val nextY = CleanerAction.Left.getNextY(6)

    assertEquals(4, nextX)
    assertEquals(6, nextY)
  }

  @Test
  def testUp {
    val nextX = CleanerAction.Up.getNextX(5)
    val nextY = CleanerAction.Up.getNextY(6)

    assertEquals(5, nextX)
    assertEquals(5, nextY)
  }

  @Test
  def testRight {
    val nextX = CleanerAction.Right.getNextX(5)
    val nextY = CleanerAction.Right.getNextY(6)

    assertEquals(6, nextX)
    assertEquals(6, nextY)
  }

  @Test
  def testDown {
    val nextX = CleanerAction.Down.getNextX(5)
    val nextY = CleanerAction.Down.getNextY(6)

    assertEquals(5, nextX)
    assertEquals(7, nextY)
  }

  @Test
  def testStand {
    val nextX = CleanerAction.Stand.getNextX(5)
    val nextY = CleanerAction.Stand.getNextY(6)

    assertEquals(5, nextX)
    assertEquals(6, nextY)
  }

  @Test
  def testSuck {
    val nextX = CleanerAction.Suck.getNextX(5)
    val nextY = CleanerAction.Suck.getNextY(6)

    assertEquals(5, nextX)
    assertEquals(6, nextY)
  }
}
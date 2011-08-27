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

import org.pl.ai.dirtcleaner.agent.Cleaner
import collection.mutable.HashMap

/**
 * Class that represent a scoreboard for the game
 * @author Pier-Luc Caron St-Pierre <pierluc.caronstpierre@gmail.com>
 *
 */
class Scoreboard {
  private val scoreboard = new HashMap[Cleaner, Int]

  /**
   * Add a number of point to the current score of the cleaner
   * If the cleaner is not in the scoreboard, add it
   * @param cleaner The cleaner associated with the score
   * @param score The number of point to add to the current score
   */
  def addScore(cleaner: Cleaner, score: Int): Unit = {
    val currentScore = scoreboard.getOrElse(cleaner, 0) + score

    scoreboard += cleaner -> currentScore
  }

  /**
   * Return the current score of the specified cleaner
   * If the score was never set return 0
   * @param cleaner The cleaner we want the score of
   * @return the currrent score of the cleaner
   */
  def getScore(cleaner: Cleaner): Int = {
    scoreboard.getOrElse(cleaner, 0)
  }
}
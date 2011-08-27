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

package org.pl.ai.dirtcleaner.engine

import org.pl.ai.dirtcleaner.agent.Cleaner
import collection.mutable.HashMap

import com.weiglewilczek.slf4s.Logger
import org.pl.ai.dirtcleaner.Scoreboard
import org.pl.ai.dirtcleaner.map.DirtyMap
/**
 * Class that represent the simulation.
 * The score is calculated by counting the total of clean floor for each turn.
 * @author Pier-Luc Caron St-Pierre <pierluc.caronstpierre@gmail.com>
 *
 * @param nbTurn number of turn of the simulation
 * @param dirtyMap The map to use
 */
final class SimulationEngine(val nbTurn: Int, val dirtyMap: DirtyMap) {

  val logger = Logger("SimulationEngine")

  private val agentStartingPosition = new HashMap[Cleaner, (Int, Int)] //Keep track of agent location
  private var scoreboard = new Scoreboard

  if (nbTurn < 1) {
    throw new IllegalArgumentException("nbTurn cannot be less than 1")
  }

  if (dirtyMap == null) {
    throw new NullPointerException("dirtyMap cannot be null")
  }

  logger.info("The simulation is going to be " + nbTurn + " turns and the map is " + dirtyMap.mapName)

  /**
   * Add a cleaner to the simulation
   * @param cleaner the cleaner to add
   * @param startX the starting position in x of the cleaner
   * @param startY the staring position in y of the cleaner
   * @throws IllegalArgumentException, if the position is invalid or if the cleaner is already added
   */
  def addCleaner(cleaner: Cleaner, startX: Int, startY: Int): Unit = {
    if (startX < 0 || startX >= dirtyMap.width) {
      throw new IllegalArgumentException("Starting location in x is invalid")
    }

    if (startY < 0 || startY >= dirtyMap.height) {
      throw new IllegalArgumentException("Starting location in y is invalid")
    }

    logger.info("Adding " + cleaner.getName + " at location (" + startX + ", " + startY + ")")


    if (agentStartingPosition.contains(cleaner)) {
      throw new IllegalArgumentException("Cleaner already added to the simulation")
    }

    agentStartingPosition += cleaner -> (startX, startY)
  }

  /**
   * Run the simulation
   * @return the scoreboard of the simulation
   */
  def playSimulation: Scoreboard = {
    scoreboard = new Scoreboard

    logger.info("Starting the simulation")

    agentStartingPosition.foreach {
      keyValue: (Cleaner, (Int, Int)) =>
        val (cleaner, position) = keyValue

        val cleanerScore = playSimulationForCleaner(cleaner, position)

        scoreboard.addScore(cleaner, cleanerScore)
    }


    scoreboard
  }

  def playSimulationForCleaner(cleaner: Cleaner, position: (Int, Int)): Int = {
    val simulationMap = dirtyMap.copyMap

    val turnEngine = new TurnEngine(cleaner, simulationMap)

    var cleanerScore = 0

    val (posX, posY) = position
    var currentX = posX
    var currentY = posY

    for (i <- 0 until nbTurn) {
      logger.debug("Playing turn " + (i + 1) + " for cleaner " + cleaner.getName)

      val (newX, newY) = turnEngine.playTurn(currentX, currentY)
      currentX = newX
      currentY = newY

      val points = simulationMap.getNumberOfCleanTile

      logger.debug("Cleaner " + cleaner.getName + " makes " + points + " points during that round.")

      cleanerScore += points
    }

    cleanerScore
  }
}
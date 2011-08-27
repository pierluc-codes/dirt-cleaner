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

package org.pl.ai.dirtcleaner.agent;

import org.pl.ai.dirtcleaner.map.DirtyMap;

/**
 * Interface to implement to create a new agent
 *
 * @author Pier-Luc Caron St-Pierre <pierluc.caronstpierre@gmail.com>
 */
public interface Cleaner {

  /**
   * Return to the name of the agent
   *
   * @return the name of the agent
   */
  public String getName();

  /**
   * Return the next move of the agent
   *
   * @param dirtyMap A copy of the map that the cleaner interact with
   * @param x        the current agent position in x
   * @param y        the current agent position in y
   * @return The next move of the agent
   */
  public CleanerAction getNextMove(DirtyMap dirtyMap, int x, int y);
}

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

package org.pl.ai.dirtcleaner.map

/**
 * Class that represent a tile
 * @author Pier-Luc Caron St-Pierre <pierluc.caronstpierre@gmail.com>
 *
 * @param isDirty if the tile is dirty
 * @param tileType the type of the tile
 */
final class Tile(var isDirty: Boolean, val tileType: TileType) {

  /**
   * The tile will a be a floor
   * @param isDirty If the floor tile is dirty or not
   */
  def this(isDirty: Boolean) {
    this (isDirty, TileType.Floor)
  }

  /**
   * Default constructor, the tile will be a clean floor
   */
  def this() = {
    this (false)
  }


  /**
   * Deep copy the tile
   * @return a copy of the tile
   */
  def copyTile: Tile = {
    new Tile(isDirty, tileType)
  }


  /**
   * Check if a mouvement is possible on this tile
   * (If the tile is a floor)
   */
  def canMove: Boolean = {
    tileType == TileType.Floor
  }
}
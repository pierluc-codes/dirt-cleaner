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

import map.DirtyMap
import org.junit.Test
import org.scalatest.junit.AssertionsForJUnit

/**
 * @author Pier-Luc Caron St-Pierre <pierluc.caronstpierre@gmail.com>
 *
 */
class TestDirtyMapConstructor extends AssertionsForJUnit {

  @Test(expected = classOf[IllegalArgumentException])
  def testWidthException = {
    val dirtyMap = new DirtyMap(-1, 10, "MyMap")
  }

  @Test(expected = classOf[IllegalArgumentException])
  def testHeightException = {
    val dirtyMap = new DirtyMap(10, -1, "MyMap")
  }

  @Test(expected = classOf[IllegalArgumentException])
  def testNameException = {
    val dirtyMap = new DirtyMap(10, 10, null)
  }
}
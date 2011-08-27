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

/**
 * Enum that regroup all the possible cleaner action
 *
 * @author Pier-Luc Caron St-Pierre <pierluc.caronstpierre@gmail.com>
 */
public enum CleanerAction {
  Left {
    @Override
    public int getNextX(int currentX) {
      return currentX - 1;
    }

    @Override
    public int getNextY(int currentY) {
      return currentY;
    }
  },
  Up {
    @Override
    public int getNextX(int currentX) {
      return currentX;
    }

    @Override
    public int getNextY(int currentY) {
      return currentY - 1;
    }
  },
  Right {
    @Override
    public int getNextY(int currentY) {
      return currentY;
    }

    @Override
    public int getNextX(int currentX) {
      return currentX + 1;
    }
  },
  Down {
    @Override
    public int getNextX(int currentX) {
      return currentX;
    }

    @Override
    public int getNextY(int currentY) {
      return currentY + 1;
    }
  },
  Stand {
    @Override
    public int getNextX(int currentX) {
      return currentX;
    }

    @Override
    public int getNextY(int currentY) {
      return currentY;
    }
  },
  Suck {
    @Override
    public int getNextX(int currentX) {
      return currentX;
    }

    @Override
    public int getNextY(int currentY) {
      return currentY;
    }
  };

  /**
   * Calculate the next position in x after the next action.
   *
   * @param currentX The current position x
   * @return The next position in x
   */
  public abstract int getNextX(int currentX);

  /**
   * Calculate the next position in y after the next action.
   *
   * @param currentY The current position y
   * @return The next position in y
   */
  public abstract int getNextY(int currentY);
}

/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
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
 * limitations under the License.
 */

package com.skydoves.disneymotions.utils

import com.skydoves.disneymotions.model.Poster

object MockTestUtil {

  fun mockPoster() = Poster(
    id = 0,
    name = "Frozen II",
    release = "2019",
    playtime = "1 h 43 min",
    description = "Frozen II, also known as Frozen 2, is a 2019 American 3D computer-animated musical fantasy film produced by Walt Disney Animation Studios. The 58th animated film produced by the studio, it is the sequel to the 2013 film Frozen and features the return of directors Chris Buck and Jennifer Lee, producer Peter Del Vecho, songwriters Kristen Anderson-Lopez and Robert Lopez, and composer Christophe Beck. Lee also returns as screenwriter, penning the screenplay from a story by her, Buck, Marc E. Smith, Anderson-Lopez, and Lopez,[2] while Byron Howard executive-produced the film.[a][1] Veteran voice cast Kristen Bell, Idina Menzel, Josh Gad, Jonathan Groff, and Ciarán Hinds return as their previous characters, and they are joined by newcomers Sterling K. Brown, Evan Rachel Wood, Alfred Molina, Martha Plimpton, Jason Ritter, Rachel Matthews, and Jeremy Sisto.",
    plot = "King Agnarr of Arendelle tells a story to his young children, Elsa and Anna, that their grandfather, King Runeard, established a treaty with the neighboring tribe of Northuldra by building a dam in their homeland, the Enchanted Forest. However, a fight occurs, resulting in Runeard's death. The battle enrages the elemental spirits of Earth, Fire, Water, and Air of the forest. The spirits disappear and a wall of mist traps everyone in the Enchanted Forest. Young Agnarr barely escapes due to the help of an unknown savior.",
    poster = "https://user-images.githubusercontent.com/24237865/75087936-5c1d9f80-553e-11ea-81d3-a912634dd8f7.jpg"
  )

  fun mockPosterList() = listOf(mockPoster())
}

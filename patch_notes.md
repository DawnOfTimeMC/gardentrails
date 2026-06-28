## 🌱 Garden Trails Patch Notes v0.2.1

![new blocks](https://i.imgur.com/jxUkcwd.png)

### New Content
**Geraniums**
- Added 5 new Geranium color variants: Orange, Purple, Purplish, Red, and White
- Each variant comes with its own flower pot
- Each geranium variation will give you a proper dye

**Drying Trays**
- Added 8 new wood variants for the Drying Tray: Oak, Birch, Acacia, Mangrove, Jungle, Cherry, Dark Oak and Spruce
- They now request 4 items instead of one

**Pergolas**
- Added Copper Pergola and Oxidized Copper Pergola
- Both support Vine, Ivy and Grape attachments

### Plant changes
- Reduced plant spawning chance for all the plants, to make these more rare and less invasive
- Reworked all the plant biome list to make these more related to their base irl biome (for example the cypress and the savanna)
- Made the Boxwood, Geranium and the Camellia colorize following the climate of the biome
- Improved several textures
### Yet Another Config is no longer needed
- Removed YACL3 dependency: the config is now a simple txt file in the config folder
- The config now exposes a per-plant biome list, allowing full control over where each plant spawns in world generation
- Reworked the tooltip/lore system: entries now use the `lore.gardentrails.*` format and support dynamic references to other item or block names
- Updated all the tooltips, making the pergola, drying tray and silk process way more explicit
### Fixes and Cleanup
- Added a whole new inventory sorting system, ordering each plant/items/pots correctly 
- Fixed the issue with the double crops not being able to properly receive bone meal
- Fixed the issue making the silkworms not growing on the stick bundle
- Fixed the issue making the silkmoth crashing the game
- Fixed all strange textures (white/black overlay) with a lot of plants, drying tray and pot (fabric only)
- Removed the social media buttons from the GUI
- Added BiomeColorHandlers on both Fabric and NeoForge for proper tint support
- Improved Camellia and Cypress Leaves textures (reduced file size, better clarity)

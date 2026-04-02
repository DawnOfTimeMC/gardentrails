# CLAUDE.md — Garden Trails

## Présentation du projet

**Garden Trails** est un mod Minecraft Java Edition **1.20.1** (multi-loader : Forge + Fabric) qui ajoute des plantes culturellement inspirées, des blocs décoratifs, un système de production de soie, et un système complet de nourriture/brassage connecté à Dawn of Time et Armor of the Ages.

Ce mod est issu d'une **séparation du projet Dawn of Time (DoT)** : il en extrait les plantes et le craft de la soie pour en faire un mod autonome.

- **Auteurs** : Poulpinou & TheGoldenWorld
- **Version actuelle** : 0.1.7
- **Licence** : MIT
- **MOD_ID** : `gardentrails`

---

## Structure du projet

```
gardentrails/
├── common/          # Code partagé
│   └── src/main/
│       ├── java/    # Classes Java communes
│       └── resources/
│           ├── assets/gardentrails/    # Modèles, textures, lang
│           └── data/gardentrails/     # Recettes, loot tables, worldgen, tags
├── fabric/          # Classes spécifiques Fabric (entry points, registries)
├── forge/           # Classes spécifiques Forge (entry points, data gen)
├── buildSrc/        # Plugins Gradle custom
├── build.gradle
├── gradle.properties
└── settings.gradle
```

### Versions & dépendances

| Composant | Version |
|-----------|---------|
| Minecraft | 1.20.1 |
| Forge | 47.2.30 |
| Fabric Loader | 0.16.9 |
| Fabric API | 0.92.1+1.20.1 |
| Java | 17 |
| Parchment mappings | 2023.09.03 |
| YACL3 (config) | 3.6.6+1.20.1 |
| Mod Menu | 7.2.2 |

---

## Features du mod

### 1. Plantes (17 espèces, organisées par culture)

**Françaises / Européennes**
- Buis (Boxwood) : variantes Buisson, Grande Haie, Petite Haie
- Cyprès
- Géranium rose (+ variante jardinière/planter)
- Lierre (Ivy)

**Japonaises**
- Érable rouge japonais (tronc `MapleTrunkBlock`, feuilles `MapleLeavesBlock`, sapling normal + paused)
- Mûrier (Mulberry) — clé pour la production de soie

**Précolombien**
- Maïs sauvage (`WildMaizeBlock`) et Maïs cultivé (double-bloc)

**Général / Asie**
- Raisin (Grapes) : sauvage et cultivé, grimpant sur pergola
- Riz (Rice) : culture aquatique, double-bloc
- Camélia : graines, feuilles de thé
- Commelina : culture sur sol
- Raisin sauvage

**Règle générale** : chaque plante a une variante sauvage (génération naturelle) et une version en pot.

---

### 2. Système de production de soie (mécanique centrale)

Chaîne de production complète :
```
Œufs de vers à soie
  → Vers à soie (Silk Worms)
  → Cocons de soie (Silk Cocoons) — tissés sur un Fagot de bâtons (Stick Bundle)
  → Soie (Silk) — matériau de craft
```

**Entité Silkmoth** (`SilkmothEntity`) :
- Spawn sur les blocs Mûrier (configurable, défaut 1/400 par tick)
- Vol circulaire autour d'un point de rotation (rayon configurable)
- Meurt après 1 jour Minecraft (configurable)
- Dépose des cocons à sa mort
- Son muable via config

**Fagot de bâtons** (`StickBundle`) :
- Les vers à soie y tissent des cocons
- Chance de croissance configurable (défaut 1/25)

**Plateau de séchage** (`DryerBlock`) :
- Bloc fonctionnel avec 2 slots
- Recette custom (`drying`) gérée par `DryerRecipe` + `DryerRecipeSerializer`
- Block entity associé : `DryerBlockEntity`
- Empilable (propriété `SIZE_0_2`)

---

### 3. Plantes grimpantes

- Lierre (Ivy) et Raisin grimpent sur des structures
- Supportées par les **Pergolas en fer** (`PergolaBlock`)
- 3 variants de pergola avec plantes : `iron_pergola_grape`, `iron_pergola_ivy`, `iron_pergola_vine`
- Croissance et propagation configurables

---

### 4. Système de nourriture & effets (v0.1.7)

Chaque plante comestible a une valeur nutritive et un effet de statut distinct.

| Item | Nutrition | Saturation | Effet |
|------|-----------|------------|-------|
| Raisin (Grapes) | 3 | 0.6 | Speed I 5s |
| Maïs (Maize) | 3 | 0.6 | Strength I 5s |
| Mûre (Mulberry) | 1 | 0.5 | Regeneration I 5s |
| Riz (Rice) | 4 | 0.6 | Slowness I 5s |
| Graines de raisin (Grape Seeds) | 1 | 0.1 | — (fast) |
| Maïs séché (Dried Maize) | 4 | 0.8 | Strength I 45s |
| Raisin séché (Dried Grape) | 4 | 0.8 | Speed I 45s |
| Riz cuit (Cooked Rice) | 7 | 1.0 | — |
| Riz fermenté (Fermented Rice) | 9 | 1.5 | Slowness I 25s |
| Jus de mûre (Mulberry Juice) | 2 | 0.4 | Regeneration I 5s (boisson) |
| Jus fermenté (Fermented Mulberry Juice) | 3 | 0.6 | Regeneration I 15s (boisson) |

---

### 5. Chaîne de transformation — Bamboo Drying Tray (v0.1.7)

Le plateau de séchage accepte désormais les cultures en plus des items de soie :

| Input | Output | Temps (ticks) |
|-------|--------|---------------|
| Maïs | Dried Maize | 800 |
| Raisin | Dried Grape | 800 |
| Riz cuit | Fermented Rice | 1200 |
| Jus de mûre | Fermented Mulberry Juice | 1200 |

**Items intermédiaires de craft** (obtenus par crafting table) :
- `crushed_maize` — maïs séché broyé
- `crushed_grape` — raisin séché broyé
- `mulberry_juice` — pressé depuis les mûres

---

### 6. Système de Sake (v0.1.7 — nécessite Dawn of Time)

La sake bottle de DoT est désormais activable en boisson en la combinant avec du Fermented Rice.

**Mécanique de paliers** : chaque bouteille bue consécutivement change les effets (détection via `player.hasEffect()` sur les effets vanilla). La logique va du plus spécifique au moins spécifique pour éviter les ambiguïtés.

#### SakeItem — paliers (effets cumulatifs)

| Palier | Condition de détection | Effets appliqués |
|--------|------------------------|------------------|
| 1er | aucun effet actif | Slowness I 25s |
| 2ème | Slowness | Poison I 45s |
| 3ème | Slowness + Poison | Nausea I 15s |
| 4ème | Slowness + Poison + Nausea | Resistance II 150s |
| 5ème | Poison + Nausea + Resistance | Hunger I 10s + Instant Damage II |
| 6ème | Poison + Nausea + Hunger | Hunger I 45s + Instant Damage III + Resistance III 300s |

#### VariantSakeItem — variantes (Speed / Strength / Regen selon ingrédient)

| Palier | Effets communs | Effet variant |
|--------|----------------|---------------|
| 1er | Slowness I 25s | Buff I 50s |
| 2ème | Poison I 45s | — |
| 3ème | Nausea I 15s | — |
| 4ème | Resistance I 40s | Buff I 120s |
| 5ème | Hunger I 10s + Instant Damage II | — |
| 6ème | Hunger I 45s + Instant Damage III + Resistance I 60s | Buff II 200s |

**3 variantes** (classe `VariantSakeItem`, paramétré par `MobEffect`) :
- `sake_grape` → Speed (Rarity.RARE, nom bleu)
- `sake_maize` → Strength (Rarity.RARE, nom bleu)
- `sake_mulberry` → Regeneration (Rarity.RARE, nom bleu)

**Lore direct** (sans Shift) via `appendHoverText()` — texte défini par clé de langue.

**Recettes** (craftées par le joueur, JSON dans `data/gardentrails/recipes/`) :
- `sake_bottle.json` : sake_bottle (DoT) + fermented_rice × 8
- `sake_bottle_grape.json` : sake_bottle (DoT) + crushed_grape × 8
- `sake_bottle_maize.json` : sake_bottle (DoT) + crushed_maize × 8
- `sake_bottle_mulberry.json` : sake_bottle (DoT) + fermented_mulberry_juice × 8

---

### 7. Intégrations tierces

| Mod | Type | Description |
|-----|------|-------------|
| Serene Seasons | Tags | Cultures d'automne/été (`autumn_crops`, `summer_crops`) |
| Vinery | Tags | Compatibilité raisin rouge (`items/red_grape`) |
| Critters & Companions | Recettes JSON | Silk Lead, Grappling Hook |
| Dawn of Time | Recettes dynamiques | 5 blocs déco craftés avec Silk (futon, tatami, chaise, drapeau, coussin) + Sake activé |
| Armor of the Ages | Recettes dynamiques | 7 pièces d'armure craftées avec Silk (Raijin, Pharaon, Armure légère japonaise) |

**Recettes DoT dynamiques** (activées si `dawnOfTimeUseSilk = true`) :

| Résultat | Ingrédients |
|---|---|
| Light Gray Futon | Silk × 3 + Thatch Bamboo × 3 |
| Small Tatami Mat | Silk × 2 + Thatch Bamboo Slab |
| Spruce Legless Chair | Silk × 3 + Spruce Slab × 3 |
| White Little Flag | Silk × 2 + Stick × 2 |
| White Cushion | Silk × 2 + Feather |

**Recettes AotA dynamiques** (activées si `armorOfTheAgesUseSilk = true`) :

| Résultat | Ingrédients |
|---|---|
| Raijin Chestplate | Silk × 4 + Diamond Chestplate + Redstone Block × 2 + Gold Block |
| Raijin Leggings | Silk × 4 + Diamond Leggings + Red Dye × 2 |
| Pharaoh Chestplate | Silk × 4 + Gold Chestplate + Gold Block × 2 |
| Pharaoh Leggings | Silk × 3 + Gold Leggings + Gold Block |
| Japanese Light Helmet | Silk × 3 + Leather Helmet |
| Japanese Light Chestplate | Silk × 4 + Leather Chestplate + Leather × 3 |
| Japanese Light Leggings | Silk × 3 + Leather Leggings + Leather × 2 |

---

## Architecture Java

### Pattern multi-loader
- **Common** : toute la logique métier
- **ServiceLoader** (`Services` + `IPlatformHelper`) pour abstraire Forge/Fabric
- Fabric et Forge implémentent chacun `IPlatformHelper`

### Hiérarchie des blocs
```
Block (Minecraft)
└── BlockGT                         # Base GT avec propriétés custom
    ├── BushBlockGT                  # Plantes récoltables
    ├── WaterloggedBlock             # Blocs supportant l'eau
    │   ├── DryerBlock              # Plateau de séchage (dual-slot)
    │   └── PergolaBlock            # Pergola (support plantes grimpantes)
    └── SoilCropsBlock              # Base cultures agricoles
        ├── GrowingBushBlock        # Cultures avec âge 0-5 + état CUT
        ├── DoubleCropsBlock        # Cultures haute (2 blocs)
        └── WaterDoubleCropsBlock   # Cultures aquatiques (riz)
```

### Hiérarchie des items (nouveaux en v0.1.7)
```
Item
└── ItemGT
    ├── DrinkItem                   # Boisson générique (UseAnim.DRINK)
    ├── SakeItem                    # Sake de base — logique de paliers
    └── VariantSakeItem             # Sake varianté — prend MobEffect + loreKey en paramètre
```

### Interfaces importantes
- `IBiomeColoredBlock` — teinture biome (FOLIAGE, GRASS, WATER)
- `IBlockChain` — mécanique de croissance en chaîne
- `IBlockGeneration` — placement custom en world gen
- `IBlockSpecialDisplay` — variantes de rendu
- `IFlammable` — propriétés d'inflammabilité
- `IHasFlowerPot` — blocs avec version en pot

### Block states custom
- `AGE_0_5` — stades de croissance
- `CUT` — croissance en pause
- `SIZE_0_2` — empilement (plateau de séchage)
- `WATERLOGGED` — support eau

### Registries (package `registry/`)
| Classe | Rôle |
|--------|------|
| `GTBlocksRegistry` | 39+ blocs avec tags |
| `GTItemsRegistry` | Items + gestion flower pots |
| `GTEntitiesRegistry` | Entité Silkmoth |
| `GTBlockEntitiesRegistry` | Block entity Dryer |
| `GTRecipeTypesRegistry` | Type de recette `drying` |
| `GTRecipeSerializersRegistry` | Sérialisation recettes |
| `GTCreativeModeTabsRegistry` | Onglet créatif |
| `GTFeaturesRegistry` | Features de world gen |
| `GTDynamicRecipes` | Recettes générées à runtime |
| `GTTags` | Références aux tags |

---

## Configuration (YACL3)

Fichier : `GTConfig.java`

### Entité Silkmoth
- `silkmothSpawnChance` (1/X par tick) — défaut 400
- `silkmothRotationMaxRange` — défaut 2 (rayon de vol)
- `silkmothMustDie` — défaut true (meurt après 1 jour)
- `silkmothRotationChange` (1/X par tick) — défaut 400
- `silkmothMute` — défaut false

### Blocs
- `dryingTimeVariation` (%) — défaut 30
- `climbingPlantGrowthChance` (1/X) — défaut 16
- `climbingPlantSpreadChance` (1/X) — défaut 5
- `stickBundleGrowthChance` (1/X) — défaut 25

### Loot (génération en coffres)
- `generateChestLoot` — défaut true
- `generateSilk`, `generateGrapes`, `generateMaize`, `generateRice`, `generateMulberry` — défaut true

### Recettes dynamiques
- `dawnOfTimeUseSilk` — active les recettes Silk → blocs DoT (défaut true)
- `armorOfTheAgesUseSilk` — active les recettes Silk → armures AotA (défaut true)

---

## Conventions du projet

### Nommage
- Classes : `NomBlock`, `NomItem`, `NomEntity` avec suffixe `GT` quand c'est une classe de base (`BlockGT`, `ItemGT`)
- Fichiers JSON : `snake_case` avec préfixe culturel quand pertinent (ex: `french/boxwood_bush.json`)
- Tags : suivent les conventions Minecraft (`blocks/mineable/axe`, etc.)

### Organisation des assets
Les modèles de blocs sont organisés par région culturelle :
- `french/`, `german/`, `japanese/`, `roman/`, `precolumbian/`, `general/`

### Recettes dynamiques
Utiliser `GTDynamicRecipes` pour toute recette conditionnelle à la config ou aux mods présents. Ne pas hardcoder dans les JSON si c'est conditionnel.

### Recettes drying
Les recettes de type `gardentrails:dryer` sont des fichiers JSON dans `data/gardentrails/recipes/`.
Format : `type`, `ingredient` (item ou tag), `result`, `dryingTime` (ticks).
Présentes dans `fabric/` et `forge/` séparément.

### World gen
- Feature configurée → placed feature (toujours en paire)
- Emplacement : `data/gardentrails/worldgen/configured_feature/` et `/placed_feature/`
- Type custom : `dot_feature` (implémentation `GTFeature`)

### Mécanique de paliers (Sake)
- Ne jamais utiliser d'effets invisibles comme marqueurs — trop fragile
- Détecter les paliers du plus spécifique au moins spécifique dans les `if/else if`
- Utiliser uniquement des effets vanilla (`MobEffects.*`) — pas d'effets custom

---

## Mods de la même équipe (compatibilités à surveiller)

- **Dawn of Time** — mod parent dont GT est extrait
- **Armor of the Ages**
- **Armored Doggo**
- **Ancient Structures**
- **Forgotten Ruins**

---

## Ce qui manque / points d'attention

- **Traductions** : seulement `en_us`, pas de `fr_fr` ni autres langues
- **Tests** : aucun test automatisé
- **Système de thé** : prévu — Camélia → feuilles de thé → effets spéciaux, à venir
- **DryerRenderer** : rendu des items 2D corrigé (les `SoilSeedsItem` étendant `BlockItem` déclenchaient le mauvais chemin de rendu — traiter comme sprite plat)

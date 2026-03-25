# CLAUDE.md — Garden Trails

## Présentation du projet

**Garden Trails** est un mod Minecraft Java Edition **1.20.1** (multi-loader : Forge + Fabric) qui ajoute des plantes culturellement inspirées, des blocs décoratifs et un système de production de soie.

Ce mod est issu d'une **séparation du projet Dawn of Time (DoT)** : il en extrait les plantes et le craft de la soie pour en faire un mod autonome.

- **Auteurs** : Poulpinou & TheGoldenWorld
- **Version actuelle** : 0.1.6
- **Licence** : MIT
- **MOD_ID** : `gardentrails`

---

## Structure du projet

```
gardentrails/
├── common/          # Code partagé (73 classes Java, 448 ressources)
│   └── src/main/
│       ├── java/    # Classes Java communes
│       └── resources/
│           ├── assets/gardentrails/    # Modèles, textures, lang
│           └── data/gardentrails/     # Recettes, loot tables, worldgen, tags
├── fabric/          # 8 classes spécifiques Fabric (entry points, registries)
├── forge/           # 15 classes spécifiques Forge (entry points, data gen)
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

### 4. Nourriture

| Item | Nutrition | Saturation |
|------|-----------|-----------|
| Raisin (Grapes) | 4 | 0.2 |
| Maïs (Maize) | 6 | 1.0 |
| Mûre (Mulberry) | 1 | 0.5 |

---

### 5. Intégrations tierces

| Mod | Type | Description |
|-----|------|-------------|
| Serene Seasons | Tags | Cultures d'automne/été (`autumn_crops`, `summer_crops`) |
| Vinery | Tags | Compatibilité raisin rouge (`items/red_grape`) |
| Critters & Companions | Recette | Grappling hook crafté avec de la soie |
| Dawn of Time | Recettes dynamiques | Remplace laine par soie (si activé en config) |
| Armor of the Ages | Recettes dynamiques | Remplace fil par soie (si activé en config) |

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

## Ressources & assets

| Catégorie | Nombre |
|-----------|--------|
| Modèles de blocs | 229 |
| Textures | 108 |
| Block states | 36 |
| Modèles d'items | 39 |
| Recettes | 5 (+ recettes drying non-JSON) |
| Loot tables | 34 |
| Fichiers world gen | 20 |
| Tags | 9 |
| Fichiers de langue | 1 (en_us) |

**Points notables** :
- Le raisin a 28 modèles de stades de croissance (stages 0-6 × 5 orientations : a, b, c, d, x_z, y)
- Les loot tables de pergola sont séparées par état (`iron_pergola_grape_5.json`, etc.)
- Pas de traduction autre que `en_us` pour l'instant

---

## Configuration (YACL3)

Fichier : `GTConfig.java` — 13 paramètres

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
- `dawnOfTimeUseSilk` — remplace laine par soie (défaut true)
- `armorOfTheAgesUseSilk` — remplace fil par soie (défaut true)

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

### World gen
- Feature configurée → placed feature (toujours en paire)
- Emplacement : `data/gardentrails/worldgen/configured_feature/` et `/placed_feature/`
- Type custom : `dot_feature` (implémentation `GTFeature`)

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
- **Documentation des recettes `drying`** : pas de fichiers JSON visibles pour les recettes de séchage (probablement hardcodées en Java)
- **Séparation DoT récente** : le dernier commit mentionne le nettoyage des tags restants de DoT — vérifier qu'il n'y a plus de références orphelines à DoT

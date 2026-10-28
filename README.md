# CropCritters Scarecrow

A small addon for [CropCritters](https://www.curseforge.com/minecraft/mc-mods/cropcritters) that adds a scarecrow structure protecting your farm.

## The structure

Build it bottom to top:

```
  Carved Pumpkin     <- the pumpkin is the centre of the effect
  Hay Bale
  Any Fence
```

Any block from `#minecraft:fences` works. Only a **carved pumpkin** counts as the head — a plain pumpkin or a jack o'lantern will not activate it.

## What it does

Within the configured radius (square, default **32 blocks**, configurable **1–64**):

| Effect | Blocked |
|---|---|
| Crop critters spawning from mature crops | yes |
| Weeds growing | yes |
| Farmland degrading when a crop matures | yes |

## What it does *not* touch

The mod deliberately leaves vanilla and other mod mechanics alone:

- **Vanilla farmland trampling** — jumping onto farmland still turns it to dirt
- **Lightning** — still degrades farmland
- **Snow** — still degrades farmland
- **Manual conversion** — feeding a Lost Soul to a mature crop still spawns a critter on purpose
- Everything outside the radius behaves exactly as without this mod

The scarecrow is tracked separately for each dimension, so a scarecrow in the Nether does not affect the Overworld at the same coordinates.

## Behaviour notes

- Takes effect **the moment the structure is complete** — no right-click, no item
- Stops **the moment any block is removed**
- Survives world reloads (stored per-dimension under `data/cropcritters_scarecrow/`)
- If the pumpkin is in an unloaded chunk, the scarecrow is inactive there (it is not deleted)

## Configuration

`config/cropcritters_scarecrow-common.toml`

```toml
[scarecrow]
    radius = 32
```

`radius` is the square radius measured from the carved pumpkin in blocks. X, Z and Y are each limited to ±radius.

Changes require a game restart.

## Requirements

| Mod | Version | Required |
|---|---|---|
| Minecraft | 1.20.1 | yes |
| Forge | 47.x | yes |
| [CropCritters](https://www.curseforge.com/minecraft/mc-mods/cropcritters) | 1.4.2+ | yes |
| GeckoLib | 4.x | yes (required by CropCritters) |

Works with both CropCritters **1.4.2** and **1.5.0** — all intercepted methods are signature-identical between those versions.

## Known limitations

- **Not tested in a large modpack.** The mixins target `CropBlock#randomTick` and `Level#setBlock`, which are very widely used methods. Another mod that also mixes into these could conflict. If you hit a crash, please report it with the full log.
- The effect is a **square** region, not a sphere.
- No visual indication on the block itself — no particles or outline showing the protected area.

## Source

GPL-3.0 — see [LICENSE](LICENSE). Source: https://github.com/biantwin/cropcritters-scarecrow

## Credits

- **Yelf42** — author of CropCritters, which this addon extends

# CropCritters Scarecrow
An unofficial addon for [CropCritters](https://github.com/Yelf42/cropcritters-multiloader) that adds a scarecrow protecting your farm.

## The scarecrow
Build it bottom to top: any fence, a hay bale, a carved pumpkin. The pumpkin is the centre of the effect. Plain pumpkins and jack o'lanterns do not count.

Within the configured radius (default 32 blocks, configurable 1-64) mature crops no longer turn into crop critters, weeds no longer grow, and farmland no longer degrades.

Everything else is left alone: trampling farmland still turns it to dirt, lightning and snow still degrade it, and feeding a Lost Soul to a mature crop still spawns a critter on purpose.

## Config
`config/cropcritters_scarecrow-common.toml`
```toml
[scarecrow]
    radius = 32
```

## Requirements
CropCritters 1.4.2+ and its own dependency GeckoLib.

## Credits
[Yelf42](https://github.com/Yelf42) for CropCritters, which is licensed CC0-1.0.

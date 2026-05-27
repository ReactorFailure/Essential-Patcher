# Changelog

## 1.0.3

- Fixed Fabric: all mixins now apply correctly. Essential loads its classes late on Fabric, so the patcher now registers its mixin config after Essential has finished loading. Previously, all Essential-targeting patches were silently skipped on Fabric.
- Added mod icon.
- Added pack.mcmeta for all versions.
- Added YACL as a required dependency on all platforms.
- Removed the in-game packet sync system. Cosmetic sync between patcher users now uses the HTTP sync server exclusively.
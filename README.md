# Voxy World Gen V2

![Logo](src/main/resources/logo.png)

Background chunk pre-generation for [Voxy](https://modrinth.com/mod/voxy). Generates chunks silently in the background and auto-ingests them into Voxy's LOD system — no need to manually fly around.

## Features

- Fast background chunk generation with automatic Voxy ingestion
- Configurable generation speed and queue size
- TPS-aware throttling — backs off automatically when server is under load
- Tellus integration for terrain sampling
- Server-side support
- `/voxygen` commands for runtime control
- F3 debug overlay showing generation stats, rate, ETA

## Commands

| Command | Description |
|---------|-------------|
| `/voxygen start` | Resume background generation (also enables HUD) |
| `/voxygen stop` | Pause background generation (also hides HUD) |
| `/voxygen status` | Show current status, active tasks, remaining chunks |
| `/voxygen hud` | Toggle the F3 debug overlay independently |

> Requires OP level 2.

## HUD

Open F3 to see generation stats in the bottom-right corner: status, chunks completed, skipped, remaining, active tasks, rate (chunks/sec), and ETA.

## Dependencies

- **Minecraft**: 1.20.1
- **Fabric Loader**: >= 0.18.4
- **Java**: 17+
- **Fabric API**
- **Cloth Config**: >= 11.1.136
- **Voxy**: compatible release for 1.20.1

## Building

```bash
git clone <repo>
./gradlew build
```

Artifacts are output to `build/libs/`.

## Configuration

Config file: `config/voxyworldgenv2.json`

## License

CUSTOM, refer to LICENSE file for more information.

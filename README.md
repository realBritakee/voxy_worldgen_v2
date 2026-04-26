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
<<<<<<< Updated upstream
- F3 debug overlay showing generation stats, rate, ETA
=======
- Colored chat feedback: gold `Voxygen |` prefix, green for success, red for errors
- Smart state detection — warns if generation is already running/stopped
- Generation starts **paused** by default — requires `/voxygen start` (configurable via `autoStartOnLoad`)
- F3 debug overlay showing generation stats, rate, ETA
- HUD auto-enables on `/voxygen start`, auto-hides on `/voxygen stop`
- HUD hidden by default when generation is paused (on world load)
>>>>>>> Stashed changes

## Commands

| Command | Description |
|---------|-------------|
<<<<<<< Updated upstream
| `/voxygen start` | Resume background generation (also enables HUD) |
| `/voxygen stop` | Pause background generation (also hides HUD) |
| `/voxygen status` | Show current status, active tasks, remaining chunks |
=======
| `/voxygen start` | Resume background generation (enables HUD) |
| `/voxygen stop` | Pause background generation (hides HUD) |
| `/voxygen status` | Show current status (PAUSED/RUNNING/THROTTLED), active tasks, remaining chunks |
>>>>>>> Stashed changes
| `/voxygen hud` | Toggle the F3 debug overlay independently |

> Requires OP level 2.

## HUD
<<<<<<< Updated upstream
=======

Open F3 to see generation stats in the bottom-right corner: status, chunks completed, skipped, remaining, active tasks, rate (chunks/sec), and ETA. The HUD automatically shows/hides with generation start/stop, or can be toggled manually with `/voxygen hud`.

## Configuration
>>>>>>> Stashed changes

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

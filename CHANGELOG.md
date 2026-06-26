# Changelog - Voxy WorldGen V2 (1.20.1)

All changes to the Voxy WorldGen V2 mod for Minecraft 1.20.1.

---

## [2.2.1] - 2026-04-26

### Added
- `/voxygen start` - resume background chunk generation (enables HUD)
- `/voxygen stop` - pause background chunk generation (hides HUD)
- `/voxygen status` - show generation status, active tasks, remaining chunks, throttle state
- `/voxygen hud` - toggle F3 debug overlay independently
- Colored chat messages: gold `Voxygen |` prefix, green for success, red for errors
- Smart state detection - warns if generation is already running/stopped
- Status display shows PAUSED (red), THROTTLED (yellow), or RUNNING (green) with colored stats
- Start message includes hint: "Use /voxygen hud to hide the HUD"
- F3 debug overlay shows "paused" status (grey) when generation is manually stopped
- Generation starts **paused** on world load - requires `/voxygen start` to begin
- HUD auto-hides on `/voxygen stop`, auto-shows on `/voxygen start`
- HUD hidden by default when generation is paused (on world load)
- `autoStartOnLoad` config option - set to `true` to auto-start generation on world load (default: `false`)
- When `autoStartOnLoad` is `true`, HUD also auto-enables on boot

### Changed
- Worker thread stays alive when paused (500ms sleep loop) - no thread recreation on resume
- `manuallyPaused` flag is independent from TPS throttle - both can be active simultaneously
- Log message on initialize reflects auto-start vs paused state

### Fixed
- F3 HUD status now correctly reflects manual pause state

---

## [2.2.0] - Initial

- Background chunk pre-generation with automatic Voxy ingestion
- TPS-aware throttling
- Tellus integration
- Server-side support with multiplayer chunk streaming
- F3 debug overlay with generation stats

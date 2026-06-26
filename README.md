# Voxy World Gen V2 - NeoForge Port

![Banner](https://raw.githubusercontent.com/realBritakee/mc-publish/main/images/Overview.png)

---

<div align="center">

[![Discord](https://img.shields.io/badge/DISCORD-SERVER-F16436?labelColor=2d2d2d&logo=discord&logoColor=white&style=flat-square)](https://discord.gg/gCRv62araB) &nbsp; [![CurseForge](https://img.shields.io/badge/CURSEFORGE-PROJECTS-F16436?labelColor=2d2d2d&logo=curseforge&logoColor=white&style=flat-square)](https://www.curseforge.com/members/britakee/projects) &nbsp; [![Modrinth](https://img.shields.io/badge/MODRINTH-PROJECTS-00AF5C?labelColor=2d2d2d&logo=modrinth&logoColor=white&style=flat-square)](https://modrinth.com/user/britakee)

<br>

[![Ko-fi](https://ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/britakeestudio)

</div>

---

**The background chunk pre-generation companion mod for Voxy.**

> Supports **NeoForge** | **Forge** — Minecraft **1.21.1 & 1.20.1**

---

A custom NeoForge port of Voxy World Gen V2, built specifically to work alongside Voxy.

Voxy requires chunks to be generated before it can build LoD data for them. Without pre-generation, you will see blank LoD tiles in unvisited areas around the world. Voxy World Gen V2 solves this by silently pre-generating chunks in the background while you play.

Your LoD map fills in automatically without any manual effort or commands, prioritizing areas near the player first.

---

![World Gen](https://raw.githubusercontent.com/realBritakee/mc-publish/main/images/World%20Gen.png)

- **Automatic Background Generation** — No commands needed, runs automatically in the background.
- **Smart Prioritization** — Prioritizes chunks and areas near the player first to ensure immediate vistas.
- **Seamless Integration** — Works alongside Voxy's LoD rendering seamlessly.

---

![Features](https://raw.githubusercontent.com/realBritakee/mc-publish/main/images/Features.png)

- **Configurable Processing** — Fully configurable radius and generation speed to match your server or client CPU power.
- **Performance Conscious** — Ensures background generation doesn't completely overwhelm the main tick thread.

---

![Dependencies](https://raw.githubusercontent.com/realBritakee/mc-publish/main/images/Dependencies.png)

### Required
- [Voxy](https://modrinth.com/mod/voxy) - The LoD rendering mod this companion supports.

---

![Development](https://raw.githubusercontent.com/realBritakee/mc-publish/main/images/Development.png)

### Building from Source

To build this mod from source, clone the repository and run the standard Gradle build command:

```bash
git clone https://github.com/realBritakee/Workspace.git
cd CreateCities-dev/projects/Minecraft/[ModFolder]/[LoaderFolder]
./gradlew build
```

The compiled `.jar` file will be located in the `build/libs` directory.

---

![Support](https://raw.githubusercontent.com/realBritakee/mc-publish/main/images/Support.png)

The fastest way to reach me is the Discord server.

- 💬 [Discord](https://discord.gg/gCRv62araB)
- 🐛 [Issue Tracker](https://github.com/realBritakee/Workspace/issues)
- ☕ [Ko-fi](https://ko-fi.com/britakeestudio)

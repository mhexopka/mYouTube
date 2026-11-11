# mYouTube - YouTube Stream & Video Alert Plugin

## Overview

mYouTube is a Minecraft Paper plugin (version 1.21.11) that enables server administrators and content creators to broadcast YouTube stream and video notifications to all players on the server. The plugin provides a simple command-based notification system with customizable messages and permission-based access control.

**Core Purpose:** Facilitate real-time communication of YouTube content updates to Minecraft server players through in-game notifications.

## Recent Changes

**October 10, 2025:**
- Created complete Maven project structure with src/main/java and src/main/resources
- Implemented VerseYT main plugin class and VerseYTCommand command handler
- Created plugin.yml with command and permission definitions
- Created config.yml with customizable message templates
- Set up Maven build workflow for automatic compilation
- Added .gitignore for Java/Maven projects
- Renamed plugin from YTAlert to VerseYT
- Added beautiful ASCII-art config with styled messages
- Implemented auto-streamer detection from YouTube links
- Added error messages, usage help, and success notifications
- Added 3 alternative message styles (minimal, modern, rainbow)
- Added console logging feature
- Project now targets Paper 1.21.11 and builds as `mYouTube-1.0.0.jar`.

## User Preferences

Preferred communication style: Simple, everyday language.

## System Architecture

### Plugin Architecture
- **Platform:** Minecraft Paper 1.21.11 (Bukkit/Spigot API)
- **Build System:** Maven-based project structure
- **Language:** Java 21 (targeting Minecraft 1.21.11 compatibility)

### Core Components

1. **Command System**
   - Single command implementation: `/alert <СТРИМ/ВИДЕО> <Ссылка>`
   - Type parameter: Distinguishes between stream and video notifications
   - URL parameter: Contains the YouTube link to be shared
   - Command execution restricted by permission nodes

2. **Permission System**
   - Two-tier permission structure:
     - `ytalert.admin` - For server administrators (receive admin notifications)
     - `ytalert.youtube` - For content creators (receive creator notifications)
   - Permission-based notification routing ensures targeted message delivery

3. **Configuration System**
   - YAML-based configuration (`config.yml`)
   - Customizable message templates for different notification types
   - Support for Minecraft color codes (`&0` through `&7`, `&a`, `&e`, `&l`)
   - Placeholder system: `{streamer}` for the channel name and `{link}` for the YouTube URL

4. **Message Broadcasting**
   - Server-wide notification system
   - Color-formatted messages using Bukkit's color code translation
   - Template-based messaging with dynamic placeholder replacement

### Design Patterns

1. **Configuration Management**
   - Centralized configuration in `plugins/mYouTube/config.yml`
   - Hot-reloadable message templates (standard Bukkit pattern)
   - Separation of configuration from code logic

2. **Permission-Based Access Control**
   - Bukkit permission nodes for granular access management
   - Role-based notification system (admin vs. content creator)

3. **Message Templating**
   - Placeholder-based string substitution
   - Support for Minecraft formatting codes
   - Separate templates for different content types (stream/video)

### Build and Deployment

- **Compilation:** Maven `clean package` produces `mYouTube-1.0.0.jar`
- **Installation:** Drop-in plugin deployment to `plugins/` directory
- **Auto-configuration:** Creates default config on first run

## External Dependencies

### Minecraft Platform
- **Paper 1.21.11** - Server implementation (extends Spigot/Bukkit API)
- Requires Paper-specific or Bukkit-compatible server

### Build Dependencies
- **Maven** - Build automation and dependency management
- **Java Development Kit 21** - Required for Paper 1.21.11

### Runtime Environment
- Minecraft Paper server version 1.21.11
- File system access for configuration storage in `plugins/mYouTube/` directory
- Bukkit/Spigot permission system integration

**Note:** No external APIs, databases, or third-party services are currently integrated. The plugin operates entirely within the Minecraft server environment using built-in Bukkit APIs.
# API Documentation

> KamiHub Developer API Reference

This document provides the API reference for the KamiHub plugin.

## API Overview

### Core Architecture

KamiHub adopts a modular architecture design, with each functional module providing independent API interfaces:

```java
// Get KamiHubAPI instance
KamiHubAPI api = KamiHubAPI.getInstance();
// Get KamiHub plugin instance
KamiHub plugin = api.getPlugin();
```

#### Event API
```java
@EventHandler
public void onAgreementChange(AgreementChangeEvent event) {
    // When the agreement is changed
    // In the current version, changing the agreement actually requires you to modify it in the configuration file
    // Changing the agreement essentially just resets the player's agreement status
}

@EventHandler
public void onPlayerAgreement(PlayerAgreementEvent event) {
    Player player = event.getPlayer();
    boolean accepted = event.isAccepted();
}
```

> This API is being improved, more content coming soon.

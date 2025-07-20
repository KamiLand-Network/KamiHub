package net.kamiland.kamihub.api;

import lombok.Getter;
import net.kamiland.kamihub.KamiHub;

public class KamiHubAPI {

    @Getter
    private static KamiHubAPI instance;
    @Getter
    private final KamiHub plugin;

    public KamiHubAPI(KamiHub kamiHub) {
        instance = this;
        this.plugin = kamiHub;
    }

}

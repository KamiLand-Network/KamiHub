package net.kamiland.kamihub.data.model.player;

import lombok.Data;

import java.util.UUID;

@Data
public class PlayerData {

    private final UUID id;
    private String name;
    private int loginTimes;
    private boolean agreement;

}

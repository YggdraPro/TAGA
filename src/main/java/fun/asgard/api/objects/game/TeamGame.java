package fun.asgard.api.objects.game;

import fun.asgard.api.objects.managers.PlayersManager;

public interface TeamGame extends BaseGame {

    PlayersManager getPlayersManager();

}

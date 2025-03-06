package task3.network;

import java.io.Serializable;

public enum ClientCommand implements Serializable {
    START,
    GET_INPUT,
    PLAY_SHOT,
    END
}

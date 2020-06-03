package org.ooad_dws4;

import java.util.HashMap;

public abstract class DWSObject {
    protected Message encodingMessage(int destination, String action, HashMap<String, String> arg) {
        return new Message(destination, action, arg);
    }

    protected boolean decodingMessage(Message msg) {
        return true;
    }


}

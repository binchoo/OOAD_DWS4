package org.ooad_dws4;

import java.util.Dictionary;

public class DWSObject {
    protected Message encodingMessage(int destination, String action, Dictionary<String, String> arg) {
        return new Message(destination, action, arg);
    }

    protected boolean decodingMessage(Message msg) {
        return true;
    }

    public void linkObject(DWSObject obj) {

    }
}

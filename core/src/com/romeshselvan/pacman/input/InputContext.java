package com.romeshselvan.pacman.input;

import com.badlogic.gdx.Input;

import java.util.Map;
import java.util.Optional;

/**
 * Represents a context
 *
 * @author Romesh Selvan
 */
public class InputContext {
    private final String contextType;
    private Map<Input.Keys, String> keyToActionMap;
    private Map<Input.Keys, String> keyToStatesMap;

    public InputContext(String contextType) {
        this.contextType = contextType;
    }

    public final Optional<String> getAction(Input.Keys key) {
        return (keyToActionMap == null) ? Optional.empty() : Optional.of(keyToActionMap.get(key));
    }

    public final Optional<String> getState(Input.Keys key) {
        return (keyToStatesMap == null) ? Optional.empty() : Optional.of(keyToStatesMap.get(key));
    }

    public final String getContextType() {
        return contextType;
    }
}

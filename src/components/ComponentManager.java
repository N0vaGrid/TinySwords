package components;

import java.util.*;
import ecs.*;

public class ComponentManager {

    private Map<Class<? extends Component>, Map<Integer, Component>> components = new HashMap<>();


    public <T extends Component> void addComponent(int entity, T component) {

        components
            .computeIfAbsent(component.getClass(), k -> new HashMap<>())
            .put(entity, component);
    }


    public <T extends Component> T getComponent(int entity, Class<T> type) {

        Map<Integer, Component> map = components.get(type);

        if(map == null) return null;

        return type.cast(map.get(entity));
    }


    public <T extends Component> Map<Integer, Component> getComponents(Class<T> type) {

        return components.getOrDefault(type, Collections.emptyMap());
    }
}
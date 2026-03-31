package ecs;

import java.util.*;

public class ComponentManager {

    private Map<Class<? extends Component>, Map<Integer, Component>> componentStores = new HashMap<>();

    private Map<Integer, Set<Class<? extends Component>>> entityComponents = new HashMap<>();


    public <T extends Component> void addComponent(int entity, T component) {

        Class<? extends Component> type = component.getClass();

        componentStores
                .computeIfAbsent(type, k -> new HashMap<>())
                .put(entity, component);

        entityComponents
                .computeIfAbsent(entity, k -> new HashSet<>())
                .add(type);
    }


    public <T extends Component> T getComponent(int entity, Class<T> type) {

        Map<Integer, Component> store = componentStores.get(type);

        if (store == null) return null;

        return type.cast(store.get(entity));
    }


    public <T extends Component> void removeComponent(int entity, Class<T> type) {

        Map<Integer, Component> store = componentStores.get(type);

        if (store != null) {

            store.remove(entity);
        }

        Set<Class<? extends Component>> types = entityComponents.get(entity);

        if (types != null) {

            types.remove(type);
        }
    }


    public void destroyEntity(int entity) {

        Set<Class<? extends Component>> types = entityComponents.get(entity);

        if (types == null) return;

        for (Class<? extends Component> type : types) {

            Map<Integer, Component> store = componentStores.get(type);

            if (store != null) {

                store.remove(entity);
            }
        }

        entityComponents.remove(entity);
    }


    public boolean hasComponent(int entity, Class<? extends Component> type) {

        Set<Class<? extends Component>> types = entityComponents.get(entity);

        return types != null && types.contains(type);
    }


    public <T extends Component> Map<Integer, Component> getComponents(Class<T> type) {

        return componentStores.getOrDefault(type, Collections.emptyMap());
    }

    public Set<Integer> getEntitiesWith(Query query) {

        Set<Integer> result = new HashSet<>();

        for (int entity : entityComponents.keySet()) {

            Set<Class<? extends Component>> types = entityComponents.get(entity);

            if (types.containsAll(query.getRequired())) {

                result.add(entity);
            }
        }

        return result;
    }
}
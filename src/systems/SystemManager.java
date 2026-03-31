package ecs;

import java.util.ArrayList;
import java.util.List;

public class SystemManager {

    private List<SystemBase> updateSystems = new ArrayList<>();
    private List<SystemBase> renderSystems = new ArrayList<>();

    public void addUpdateSystem(SystemBase system) {

        updateSystems.add(system);
    }

    public void addRenderSystem(SystemBase system) {

        renderSystems.add(system);
    }

    public void updateSystems() {

        for (SystemBase system : updateSystems) {

            system.update();
        }
    }

    public void renderSystems() {

        for (SystemBase system : renderSystems) {

            system.update();
        }
    }

    public List<SystemBase> getRenderSystems() {

        return renderSystems;
    }
}
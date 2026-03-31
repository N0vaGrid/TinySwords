package ecs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Query {

    private Set<Class<? extends Component>> required = new HashSet<>();

    public Query(Class<? extends Component>... components) {

        required.addAll(Arrays.asList(components));
    }

    public Set<Class<? extends Component>> getRequired() {

        return required;
    }
}
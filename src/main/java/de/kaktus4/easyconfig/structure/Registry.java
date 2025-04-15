package de.kaktus4.easyconfig.structure;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class Registry<T> {

    protected final List<T> objects;

    public Registry() {
        this.objects = new ArrayList<>();
    }

    @SafeVarargs
    public final void register(T... object) {
        this.objects.addAll(Arrays.asList(object));
    }

    @SafeVarargs
    public final void unregister(T... objects) {
        this.objects.removeAll(Arrays.asList(objects));
    }

    public final T getByClass(Class<? extends T> c) {
        return this.objects.stream().filter(object -> object.getClass().equals(c)).findFirst().orElse(null);
    }

}
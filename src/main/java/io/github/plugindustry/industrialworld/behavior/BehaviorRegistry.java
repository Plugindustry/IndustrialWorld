package io.github.plugindustry.industrialworld.behavior;

import lombok.SneakyThrows;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class BehaviorRegistry {
    @SneakyThrows
    public static void activeBehaviors() {
        Reflections reflections = new Reflections(
                new ConfigurationBuilder().forPackage(Behavior.class.getPackage().getName(),
                        Behavior.class.getClassLoader()));
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        for (Class<? extends Behavior> behaviorClass : reflections.getSubTypesOf(Behavior.class)) {
            ((Behavior) lookup.findStatic(behaviorClass, "getInstance", MethodType.methodType(Behavior.class))
                    .invokeExact()).onActivate();
        }
    }
}
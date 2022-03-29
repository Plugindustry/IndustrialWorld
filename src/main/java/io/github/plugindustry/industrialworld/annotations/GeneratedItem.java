package io.github.plugindustry.industrialworld.annotations;

import io.github.plugindustry.wheelcore.interfaces.item.ItemBase;
import org.bukkit.Material;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GeneratedItem {
    Material type();

    Class<? extends ItemBase> instance();

    String id() default "";

    String[] oreDictionary() default {};

    boolean hasDisplayName() default true;

    boolean hasLore() default false;

    CustomModelDataType customModelDataType() default CustomModelDataType.NONE;

    int customModelData() default 0;

    enum CustomModelDataType {
        NONE, ID_GENERATED, MANUAL
    }
}

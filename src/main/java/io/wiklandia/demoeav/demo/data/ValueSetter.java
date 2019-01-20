package io.wiklandia.demoeav.demo.data;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.BiConsumer;

@Getter
@AllArgsConstructor
public abstract class ValueSetter<T> implements BiConsumer<Eav, T> {

    private Class clazz;

    @Getter
    public static class StringSetter extends ValueSetter<String> {

        public StringSetter(Class clazz) {
            super(clazz);
        }

        @Override
        public void accept(Eav eav, String s) {
            eav.setStringValue(s);
        }
    }


}

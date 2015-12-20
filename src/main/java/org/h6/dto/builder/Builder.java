package org.h6.dto.builder;

public interface Builder<T, V> {

    V build(T t);
}

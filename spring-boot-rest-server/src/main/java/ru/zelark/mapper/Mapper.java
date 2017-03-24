package ru.zelark.mapper;

import java.util.List;

public interface Mapper<E, T> {

    T toDTO(E entity);

    List<T> toDTOs(List<E> entities);

    void update(E entity, T dto);

}

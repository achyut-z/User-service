package com.achyut.spd.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GenericDynamicMapper<S, T> implements GenericMapper<S, T> {

	private final Class<S> sourceClass;
	private final Class<T> targetClass;

	@Override
	public T toDto(S entity) {

		try {

			T dto = targetClass.getDeclaredConstructor().newInstance();

			copyProperties(entity, dto);

			return dto;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public S toEntity(T dto) {

		try {

			S entity = sourceClass.getDeclaredConstructor().newInstance();

			copyProperties(dto, entity);

			return entity;

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	private void copyProperties(Object source, Object target) {

		Field[] fields = source.getClass().getFields();

		for (Field field : fields) {
			field.setAccessible(true);

			try {

				Object value = field.get(source);

				Field targetField = target.getClass().getDeclaredField(field.getName());

				targetField.setAccessible(true);
				targetField.set(target, value);

			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
	}

}

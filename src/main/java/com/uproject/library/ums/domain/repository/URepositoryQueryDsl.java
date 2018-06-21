package com.uproject.library.ums.domain.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.MultiValueBinding;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.NoRepositoryBean;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.StringPath;

@NoRepositoryBean
public interface URepositoryQueryDsl<E, P extends Serializable> extends
JpaRepository<E, P>,
QueryDslPredicateExecutor<E>,
QuerydslBinderCustomizer<EntityPath<E>>{
	@Override
	default void customize(QuerydslBindings bindings, EntityPath<E> root) {
		/**
		 * Bindings by String
		 */
		bindings.bind(String.class).all(new MultiValueBinding<StringPath, String>() {
			@Override
			public Predicate bind(StringPath path, Collection<? extends String> values) {
				BooleanBuilder predicate = new BooleanBuilder();
				values.forEach(value -> predicate.or(path.containsIgnoreCase(value)));
				return predicate;
			}
		});
		/**
		 * Bindings by Date. If It have 2 dates then show values between them.
		 */
		bindings.bind(Date.class).all(new MultiValueBinding<DatePath<Date>, Date>() {
			@Override
			public Predicate bind(DatePath<Date> path, Collection<? extends Date> values) {
				BooleanBuilder predicate = new BooleanBuilder();
				if (values.size() == 2) {
					List<Date> dates = new ArrayList<>();
					values.forEach(value -> dates.add(value));
					return path.between(dates.get(0), dates.get(1));
				} else {
					values.forEach(value -> predicate.or(path.after(value)));
					return predicate;
				}
			}
		});
	}
}

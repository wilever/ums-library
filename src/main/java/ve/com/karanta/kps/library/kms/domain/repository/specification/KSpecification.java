package ve.com.karanta.kps.library.kms.domain.repository.specification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.google.common.base.Joiner;

/**
 * The Class KSpecification.
 *
 * @param <T> the generic type
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 */
public class KSpecification<T> implements Specification<T>{

	/** The criteria, +info: {@link KSpecificationCriteria} */
	private KSpecificationCriteria criteria;

	/**
	 * Instantiates a new k specification.
	 *
	 * @param searchCriteria The search criteria.
	 */
	public KSpecification(KSpecificationCriteria searchCriteria) {
		this.criteria= searchCriteria;
	}

	/**
	 * Generate predicate of specification.
	 */
	@Override
	public Predicate toPredicate(
		Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		     
	        switch (criteria.getOperation()) {
	        case EQUALITY:
	            return builder.equal(root.get(criteria.getKey()), criteria.getValue());
	        case NEGATION:
	            return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
	        case GREATER_THAN:
	            return builder.greaterThan(root.<String> get(
	              criteria.getKey()), criteria.getValue().toString());
	        case LESS_THAN:
	            return builder.lessThan(root.<String> get(
	              criteria.getKey()), criteria.getValue().toString());
	        case STARTS_WITH:
	            return builder.like(root.<String> get(criteria.getKey()), criteria.getValue() + "%");
	        case ENDS_WITH:
	            return builder.like(root.<String> get(criteria.getKey()), "%" + criteria.getValue());
	        case CONTAINS:
	            return builder.like(root.<String> get(
	              criteria.getKey()), "%" + criteria.getValue() + "%");
	        default:
	            return null;
	        }
	    }
	
	/**
	 * Gets the params.
	 *
	 * @param <T> The generic type
	 * @param params The params
	 * @param function The function: SEARCH or FILTER
	 * @return The params
	 */
	private static <T> Specification<T> getParams(String params, String function){
		 KSpecificationBuilder builder = new KSpecificationBuilder();
		    String operationSetExper = Joiner.on("|").join(KSpecificationOperator.SIMPLE_OPERATION_SET);
		    Pattern pattern = Pattern.compile(
		    		"(\\w+?)("+ operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
		    Matcher matcher = pattern.matcher(params + KSpecificationOperator.SEPARATOR);
		    while (matcher.find()) {
		        builder.with(
		          matcher.group(1), 
		          matcher.group(2), 
		          matcher.group(4), 
		          matcher.group(3), 
		          matcher.group(5));
		    }
		    return  builder.build(function);
	}
	
	/**
	 * Gets the specifications. 
	 * 
	 * <p>It can be:<ul>
	 * <li> SEARCH: Many specification separated by AND.
	 * <li> FILTER: Many specification separated by OR.
	 * <li> BOTH: SEARCH and FILTER specifications separated by AND
	 * 
	 * @param <T> The generic type
	 * @param search The search params
	 * @param filter The filter params
	 * @return {@link Specification}
	 */
	public static <T> Specification<T> getSpecifications(String search, String filter){
		Specification<T> specification;
		if (search!=null && filter!=null) {
			Specification<T> specificationToSearch= KSpecification
					.getParams(
							search, 
							KSpecificationOperator.SEARCH);
			Specification<T> specificationToFilter= KSpecification
					.getParams(
							filter, 
							KSpecificationOperator.FILTER);
			specification = Specifications
					.where(specificationToSearch)
					.and(specificationToFilter);
		} else if (search!=null) {
			specification = KSpecification
					.getParams(search, KSpecificationOperator.SEARCH);
		} else if (filter!=null) {
			specification = KSpecification
					.getParams(filter, KSpecificationOperator.FILTER);
		} else {
			specification = null;
		}
		return specification;
	}
}

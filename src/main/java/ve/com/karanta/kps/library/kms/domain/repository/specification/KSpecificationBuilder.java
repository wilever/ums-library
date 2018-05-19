package ve.com.karanta.kps.library.kms.domain.repository.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

/**
 * The Class KSpecificationsBuilder. 
 * It generate specifications to found data on database.
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 */
public class KSpecificationBuilder {
	
	/** The params. */
	private List<KSpecificationCriteria> params = new ArrayList<>();
	 
    /**
     * Indicate param to construct specifications.
     * 
     * @param key The key or field to find
     * @param operation The operation, for example: =, <, >
     * @param value Value to find
     * @param prefix Prefix to find by StartWith 
     * @param suffix Suffix to find by EndWith
     * @return The k specifications builder
     */
    public KSpecificationBuilder with(
      String key, String operation, Object value, String prefix, String suffix) {
     
        KSpecificationOperator op = KSpecificationOperator.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == KSpecificationOperator.EQUALITY) {
                boolean startWithAsterisk = prefix.contains("*");
                boolean endWithAsterisk = suffix.contains("*");
 
                if (startWithAsterisk && endWithAsterisk) {
                    op = KSpecificationOperator.CONTAINS;
                } else if (startWithAsterisk) {
                    op = KSpecificationOperator.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = KSpecificationOperator.STARTS_WITH;
                }
            }
            params.add(new KSpecificationCriteria(key, op, value));
        }
        return this;
    }
 
    /**
     * Builds the specification. 
     * Concatenate many specifications by AND, OR.
     *
     * @param <T> The generic type
     * @param function The function: SEARCH or FILTER
     * @return The specification
     */
    public <T> Specification<T> build(String function) {
        if (params.size() == 0) {
            return null;
        }
 
        List<Specification<T>> specs = new ArrayList<Specification<T>>();
        for (KSpecificationCriteria param : params) {
            specs.add(new KSpecification<T>(param));
        }
 
        Specification<T> result = specs.get(0);
        if (function==KSpecificationOperator.SEARCH) {
        	for (int i = 1; i < specs.size(); i++) {
                result = Specifications.where(result).or(specs.get(i));
            }
        } else if (function==KSpecificationOperator.FILTER) {
        	for (int i = 1; i < specs.size(); i++) {
                result = Specifications.where(result).and(specs.get(i));
            }
        }
        
        return result;
    }
}

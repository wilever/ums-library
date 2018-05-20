package com.uproject.library.ums.domain.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uproject.library.ums.domain.repository.entity.Rule;
import com.uproject.library.ums.domain.util.ApiMethod;

/**
 * The Interface RuleRepository. 
 * It allow to perform CRUD operation over {@link Rule}.
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 * 
 */
@Repository
public interface RuleRepository extends JpaRepository<Rule, Serializable> {

	/**
	 * Find by table and condition.
	 *
	 * @param condition The condition, +info: {@link ApiMethod}
	 * @param table The table to find on database.
	 * @return The list of entities
	 */
	@Query(value = "select r from Rule r where "
	        + "r.condition = ?1 and "
	        + "r.table = ?2")
    List<Rule> findByTable(
    		String condition,
    		String table);
    
    /**
     * Obtain all available rules that match interest column.
     *
     * @param condition  Condition of use of rule.
     * More info: {@link ApiMethod}
     * @param table  Name of interest table.
     * @param column  Name of interest column.
     * Example: ACTIVE_IND
     * @return List of available rules
     */
	@Query(value = "select r from Rule r where "
        + "r.condition = ?1 and "
        + "r.table = ?2 and "
		+ "r.column = ?3")
	List<Rule> findByColumn(
			String condition,
			String table, 
			String column);
}

package ve.com.karanta.kps.library.kms.domain.util;

import org.springframework.hateoas.Link;

/**
 * Page position for link of Spring Hateoas
 * <p> Positions can be: <ul>
 * <li> NEXT when another page is available after expected
 * <li> PREV when another page is available before expected
 * <li> BOTH when another page is available after and before expected
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 */
public enum PagePosition {
	
	/**
	 * Another page is available after expected.
	 */
	NEXT,
	
	/**
	 * Another page is available before expected.
	 */
	PREV,
	
	/**
	 * Another page is available after and before expected.
	 */
	BOTH;
	
	/**
	 * Value of position. Value expected by {@link Link}.
	 */
	private String value;
	
	/**
	 * Assign values for positions.
	 */
	static {
		NEXT.value="next";
		PREV.value="prev";
	}
	
	/**
	 * Get value of position.
	 * 
	 * @return {@link #value}
	 */
	public String getValue() {
		return value;
	}
}

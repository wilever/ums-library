package com.uproject.library.ums.domain.util;

/**
 * The Class ControllerConstant for SwaggerDocumentation.
 */
public class ControllerConstant {
	
	//DEFAULT_RESPONSE
	
	/** The Constant RESPONSE_SUCESS_CODE. */
	public static final int RESPONSE_SUCESS_CODE =200;
	
	/** The Constant RESPONSE_SUCESS_MESSAGE. */
	public static final String RESPONSE_SUCESS_MESSAGE = "Operation successfully!";
	
	/** The Constant RESPONSE_ERROR_CODE. */
	public static final int RESPONSE_ERROR_CODE =400;
	
	/** The Constant RESPONSE_ERROR_MESSAGE. */
	public static final String RESPONSE_ERROR_MESSAGE = "Request had at least one error. "
															+ "Many exception generate this response for different HttpStatus (4XX or 5XX). "
															+ "Details of catched exception are at headers.";
	
	/** The Constant RESPONSE_VALIDATOR_ERROR_CODE. */
	public static final int RESPONSE_VALIDATOR_ERROR_CODE =420;
	
	/** The Constant RESPONSE_VALIDATOR_ERROR_MESSAGE. */
	public static final String RESPONSE_VALIDATOR_ERROR_MESSAGE = "Request had at least one error detected by validator.";
	
	// DEFAULT_HEADER
	
	/** The Constant HEADER_ERROR_NAME_NAME. */
	public static final String HEADER_ERROR_NAME_NAME = "ExceptionName";
		
	/** The Constant HEADER_ERROR_NAME_DESCRIPTION. */
	public static final String HEADER_ERROR_NAME_DESCRIPTION = "Name of exception catched";
		
	/** The Constant HEADER_ERROR_NAME_RESPONSE. */
	public static final Class<String> HEADER_ERROR_NAME_RESPONSE = String.class;
		
	/** The Constant HEADER_ERROR_MESSAGE_NAME. */
	public static final String HEADER_ERROR_MESSAGE_NAME = "ExceptionMessage";
		
	/** The Constant HEADER_ERROR_MESSAGE_DESCRIPTION. */
	public static final String HEADER_ERROR_MESSAGE_DESCRIPTION = "Message of exception catched";
		
	/** The Constant HEADER_ERROR_MESSAGE_RESPONSE. */
	public static final Class<String> HEADER_ERROR_MESSAGE_RESPONSE = String.class;
	
	// DEFAULT_PARAMETER
	
	/** The Constant SEARCH_NAME. */
	public static final String SEARCH_NAME = "search";
	
	/** The Constant SEARCH_DATA_TYPE. */
	public static final String SEARCH_DATA_TYPE = "string";
	
	/** The Constant SEARCH_PARAM_TYPE. */
	public static final String SEARCH_PARAM_TYPE = "query";
	
	/** The Constant SEARCH_VALUE. */
	public static final String SEARCH_VALUE = 	"Search params. "
												+ "Operators: "
												+ "(:) Equal, "
												+ "(!) diferent, "
												+ "(>) Greather than, "
												+ "( ) Less than. "
												+ "Multiple params need to be separeted by \",\". "
												+ "Example: anyId 3,anyId>1";
								
	/** The Constant SEARCH_REQUIRED. */
	public static final boolean SEARCH_REQUIRED = false;
	
	/** The Constant FILTER_NAME. */
	public static final String FILTER_NAME = "filter";
	
	/** The Constant FILTER_DATA_TYPE. */
	public static final String FILTER_DATA_TYPE = "string";
	
	/** The Constant FILTER_PARAM_TYPE. */
	public static final String FILTER_PARAM_TYPE = "query";
	
	/** The Constant FILTER_VALUE. */
	public static final String FILTER_VALUE = 	"Filter params. "
												+ "Operators: same for search.";
								
	/** The Constant FILTER_REQUIRED. */
	public static final boolean FILTER_REQUIRED = false;
	
	/** The Constant PAGE_NAME. */
	public static final String PAGE_NAME = "page";
	
	/** The Constant PAGE_DATA_TYPE. */
	public static final String PAGE_DATA_TYPE = "int";
	
	/** The Constant PAGE_PARAM_TYPE. */
	public static final String PAGE_PARAM_TYPE = "query";
	
	/** The Constant PAGE_VALUE. */
	public static final String PAGE_VALUE = 	"Result page that you want to retrieve (0,1,..., n)";
								
	/** The Constant PAGE_REQUIRED. */
	public static final boolean PAGE_REQUIRED = false;
	
	
	/** The Constant SIZE_NAME. */
	public static final String SIZE_NAME = "size";
	
	/** The Constant SIZE_DATA_TYPE. */
	public static final String SIZE_DATA_TYPE = "int";
	
	/** The Constant SIZE_PARAM_TYPE. */
	public static final String SIZE_PARAM_TYPE = "query";
	
	/** The Constant SIZE_VALUE. */
	public static final String SIZE_VALUE = 	"Number of records per page. "
    											+ "By default: 20 elements per page";
								
	/** The Constant SIZE_REQUIRED. */
	public static final boolean SIZE_REQUIRED = false;
	
	
	/** The Constant SORT_NAME. */
	public static final String SORT_NAME = "sort";
	
	/** The Constant SORT_ALLOW_MULTIPLE. */
	public static final boolean SORT_ALLOW_MULTIPLE = true;
	
	/** The Constant SORT_DATA_TYPE. */
	public static final String SORT_DATA_TYPE = "string";
	
	/** The Constant SORT_PARAM_TYPE. */
	public static final String SORT_PARAM_TYPE = "query";
	
	/** The Constant SORT_VALUE. */
	public static final String SORT_VALUE = 	"Sorting criteria in the format: property,(asc|desc). " +
									            "Data only can sort by reference and alias. "+
									    		"Multiple sort creteria are supported."+
									            "By default: Sort is ascending. "+ 
									    		"Example: sort=id,asc&sort=id.activeInd,asc";
								
	/** The Constant SORT_REQUIRED. */
	public static final boolean SORT_REQUIRED = false;

}

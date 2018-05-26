package com.uproject.library.ums.domain.util;

public class ControllerConstant {
	
	//DEFAULT_RESPONSE
	
	public static final int RESPONSE_SUCESS_CODE =200;
	
	public static final String RESPONSE_SUCESS_MESSAGE = "Operation successfully!";
	
	public static final int RESPONSE_ERROR_CODE =400;
	
	public static final String RESPONSE_ERROR_MESSAGE = "Request had at least one error. "
															+ "Many exception generate this response for different HttpStatus (4XX or 5XX). "
															+ "Details of catched exception are at headers.";
	public static final int RESPONSE_VALIDATOR_ERROR_CODE =420;
	
	public static final String RESPONSE_VALIDATOR_ERROR_MESSAGE = "Request had at least one error detected by validator.";
	
	// DEFAULT_HEADER
	
	public static final String HEADER_ERROR_NAME_NAME = "ExceptionName";
		
	public static final String HEADER_ERROR_NAME_DESCRIPTION = "Name of exception catched";
		
	public static final Class<String> HEADER_ERROR_NAME_RESPONSE = String.class;
		
	public static final String HEADER_ERROR_MESSAGE_NAME = "ExceptionMessage";
		
	public static final String HEADER_ERROR_MESSAGE_DESCRIPTION = "Message of exception catched";
		
	public static final Class<String> HEADER_ERROR_MESSAGE_RESPONSE = String.class;
	
	// DEFAULT_PARAMETER
	
	public static final String SEARCH_NAME = "search";
	
	public static final String SEARCH_DATA_TYPE = "string";
	
	public static final String SEARCH_PARAM_TYPE = "query";
	
	public static final String SEARCH_VALUE = 	"Search params. "
												+ "Operators: "
												+ "(:) Equal, "
												+ "(!) diferent, "
												+ "(>) Greather than, "
												+ "( ) Less than. "
												+ "Multiple params need to be separeted by \",\". "
												+ "Example: anyId 3,anyId>1";
								
	public static final boolean SEARCH_REQUIRED = false;
	
	public static final String FILTER_NAME = "filter";
	
	public static final String FILTER_DATA_TYPE = "string";
	
	public static final String FILTER_PARAM_TYPE = "query";
	
	public static final String FILTER_VALUE = 	"Filter params. "
												+ "Operators: same for search.";
								
	public static final boolean FILTER_REQUIRED = false;
	
	public static final String PAGE_NAME = "page";
	
	public static final String PAGE_DATA_TYPE = "int";
	
	public static final String PAGE_PARAM_TYPE = "query";
	
	public static final String PAGE_VALUE = 	"Result page that you want to retrieve (0,1,..., n)";
								
	public static final boolean PAGE_REQUIRED = false;
	
	
	public static final String SIZE_NAME = "size";
	
	public static final String SIZE_DATA_TYPE = "int";
	
	public static final String SIZE_PARAM_TYPE = "query";
	
	public static final String SIZE_VALUE = 	"Number of records per page. "
    											+ "By default: 20 elements per page";
								
	public static final boolean SIZE_REQUIRED = false;
	
	
	public static final String SORT_NAME = "sort";
	
	public static final boolean SORT_ALLOW_MULTIPLE = true;
	
	public static final String SORT_DATA_TYPE = "string";
	
	public static final String SORT_PARAM_TYPE = "query";
	
	public static final String SORT_VALUE = 	"Sorting criteria in the format: property,(asc|desc). " +
									            "Data only can sort by reference and alias. "+
									    		"Multiple sort creteria are supported."+
									            "By default: Sort is ascending. "+ 
									    		"Example: sort=id,asc&sort=id.activeInd,asc";
								
	public static final boolean SORT_REQUIRED = false;

}

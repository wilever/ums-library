package ve.com.karanta.kps.library.kms.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import ve.com.karanta.kps.library.kms.controller.apimodel.KApiResource;
import ve.com.karanta.kps.library.kms.domain.exception.KValidatorException;
import ve.com.karanta.kps.library.kms.domain.exception.KException;
import ve.com.karanta.kps.library.kms.domain.response.KHandlerResponse;

/**
 * It indicate what methods need to be implemented by controllers.
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 * @param <T> Entity to control
 */
public interface KController<T> {

	/**
	 * Gets data from database.
	 * It can return on element of data by id 
	 * or search and filter data by content.
	 * This call {@link AnyService#get(String, String, String, Pageable, PagedResourcesAssembler)}.
	 *
	 * @param id The identifier
	 * @param search The search
	 * @param filter The filter
	 * @param pageable The pageable
	 * @param assembler The assembler
	 * @return The response entity
	 * @throws KValidatorException 
	 * @throws IllegalAccessException 
	 * @throws KException the k exception.
	 */
	@ApiOperation(
			value=	"Get data from database",
			notes= 	"It can return data by \n" + 
					"search and filter parameters.")
	@ApiImplicitParams({
	    @ApiImplicitParam(
	    		name = "search", 
	    		dataType = "string",
	    		paramType = "query",
	            value = "Search params. "
						+ "Operators: "
						+ "(:) Equal, "
						+ "(!) diferent, "
						+ "(>) Greather than, "
						+ "( ) Less than. "
						+ "Multiple params need to be separeted by \",\". "
						+ "Example: anyId 3,anyId>1",
	            required = false),
	    @ApiImplicitParam(
	    		name = "filter",
	    		dataType = "string",
	    		paramType = "query",
	            value = "Filter params. "
						+ "Operators: same for search.",
	            required = false),
	    @ApiImplicitParam(
	    		name = "page", 
	    		dataType = "int",
	    		paramType = "query",
	            value = "Result page that you want to retrieve (0,1,..., n)",
	            required = false),
	    @ApiImplicitParam(
	    		name = "size", 
	    		dataType = "int", 
	    		paramType = "query",
	            value = "Number of records per page. " +
	            		"By default: 20 elements per page",
	            required = false),
	    @ApiImplicitParam(
	    		name = "sort",
	    		allowMultiple = true, 
	    		dataType = "string",
	    		paramType = "query",
	            value = "Sorting criteria in the format: property,(asc|desc). " +
	                    "Data only can sort by reference and alias. "+
	            		"Multiple sort creteria are supported."+
	                    "By default: Sort is ascending. "+ 
	            		"Example: sort=id,asc&sort=id.activeInd,asc",
	            required = false)})
	@ApiResponses(value= {
			@ApiResponse(
					code= 200, 
					response= KApiResource.class,
					message = "Data retrieved successfully!"),
			@ApiResponse(
					code= 400, 
					response= KHandlerResponse.class, 
					message = "Request had at least one error. "
							+ "Many exception generate this response for different HttpStatus (4XX or 5XX). "
							+ "Details of catched exception are at headers.",
					responseHeaders= {
							@ResponseHeader(name="ExceptionName",description="Name of exception catched",response=String.class),
							@ResponseHeader(name="ExceptionMessage",description="Message of exception catched",response=String.class)
						
					})
					
	})
	@GetMapping("")
	@ResponseBody
	public ResponseEntity<Object> get(
			@RequestParam(
					value = "search",
					required=false)
			String search,
			@RequestParam(
					value = "filter",
					required=false)
			String filter,
			Pageable pageable,
			PagedResourcesAssembler<T> assembler) 
					throws 
						IllegalAccessException, 
						KException, 
						KValidatorException;
}

package ve.com.karanta.kps.library.kms.domain.util;

import java.io.IOException;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ve.com.karanta.kps.library.kms.controller.KHandlerController;
import ve.com.karanta.kps.library.kms.domain.exception.KException;
import ve.com.karanta.kps.library.kms.domain.exception.KValidatorException;
import ve.com.karanta.kps.library.kms.domain.repository.RuleRepository;
import ve.com.karanta.kps.library.kms.domain.repository.entity.Rule;
import ve.com.karanta.kps.library.kms.domain.response.KHandlerResponse;
import ve.com.karanta.kps.library.kms.domain.response.KSuccessResponse;
import ve.com.karanta.kps.library.kms.domain.response.KValidatorResponse;
import ve.com.karanta.kps.library.kms.domain.util.validator.KValidatorMessage;
import ve.com.karanta.kps.library.kms.domain.util.validator.KValidatorOperator;
import ve.com.karanta.kps.library.kms.service.KValidator;

/**
 * KTestAssistan is design for assist integration tests for Karanta MicroServices. 
 * It is necessary indicate RestTemplate and PathToController before tests.
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 * @param <T> Entity to be tested
 */
@Component
public class KTestAssistant<T> {
	
	/**
	 * Template for execute CRUD methods over MicroService.
	 */
	private RestTemplate restTemplate;
	
	/**
	 * This allow to convert an object to  a json.
	 */
	@Autowired
	private ObjectMapper mapper;
	
	/**
	 * Object for handling exceptions from controllers.
	 */
	@Autowired
	private KHandlerController handler;
	
	/**
	 * Rule repository for data validation
	 */
	@Autowired
	private RuleRepository ruleRepository;
	
	/**
	 * Rule for data validation
	 */
	private Rule rule = new Rule();
	
	/**
	 * Path to controller evaluated
	 */
	private String pathToController;
	
	/**
	 * Set RestTemplate. This required to be established before any test.
	 * @param restTemplate +info: {@link #restTemplate}
	 */
	public void setRestTemplate(
			RestTemplate restTemplate) {
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		this.restTemplate = 
				restTemplate;
	}
	
	/**
	 * Set PathController. This required to be established before any test.
	 * @param pathToController +info: {@link #pathToController}
	 */
	public void setPathToController(
			String pathToController) {
		this.pathToController = 
				pathToController;
	}
	
	/**
	 * Generate a json with a page of resources of entity evaluated.
	 * 
	 * @param entities Entities expected
	 * @return Json page resources
	 * @throws IOException
	 */
	public String getContent(
			List<T> entities) 
					throws IOException {
		List<Resource<T>> 
			resourceList = 
				new ArrayList<>();
		for (T entity : entities) {
			resourceList.add(
					new Resource<T>(
							entity, 
							new ArrayList<>()));
		}
		return "\"content\":"+
					mapper.
						writeValueAsString(
								resourceList);
	}
	
	/**
	 * Generate a json with a links for another pages of resources of entity evaluated.
	 * 
	 * @param position Position expected, +info: {@link PagePosition}
	 * @param metadata Metadata expected.
	 * @param sort Sort: It required field, follow by "," and order. For example: id,asc or id,desc
	 * @return Json links
	 * @throws IOException
	 */
	public String getLinks(	
			PagePosition position,
			PageMetadata metadata,
			String sort) 
					throws IOException {
		List<Link> links = new ArrayList<>();
		if (position!=null && sort!=null) {
			links.add(new Link(path()+
					getPagination(
							0, 
							metadata.getSize(), 
							sort), "first"));
			switch (position) {
				case NEXT:
					links.add(new Link(path()+
							getPagination(
									metadata.getNumber(), 
									metadata.getSize(), 
									sort),
							"self"));
					links.add(new Link(path()+
							getPagination(
									metadata.getNumber()+1, 
									metadata.getSize(), 
									sort),
							position.getValue()));	
					break;
					
				case PREV:
					links.add(new Link(path()+
							getPagination(
									metadata.getNumber()-1, 
									metadata.getSize(), 
									sort),
							position.getValue()));
					links.add(new Link(path()+
							getPagination(
									metadata.getNumber(), 
									metadata.getSize(), 
									sort),
							"self"));
					break;
	
				case BOTH:
					links.add(new Link(path()+
							getPagination(
									metadata.getNumber()-1, 
									metadata.getSize(), 
									sort),
							PagePosition.PREV.getValue()));
					links.add(new Link(path()+
							getPagination(
									metadata.getNumber(), 
									metadata.getSize(), 
									sort),
							"self"));
					links.add(new Link(path()+
							getPagination(
									metadata.getNumber()+1, 
									metadata.getSize(), 
									sort),
							PagePosition.NEXT.getValue()));	
					break;
				default:
					break;
			}
			links.add(new Link(path()+
					getPagination(
							metadata.getTotalPages()-1, 
							metadata.getSize(), 
							sort),
					"last"));
		} else {
			links.add(new Link(path()+
					getPagination(
							metadata.getNumber(), 
							metadata.getSize(), 
							null),
					"self"));
		}
		return "\"links\":"+
					mapper.
						writeValueAsString(
								links);
	}
	
	/**
	 * Generate a json with a Metadata from pages of resources of entity evaluated.
	 * @param metadata Metadata expected
	 * @return Json metadata
	 * @throws JsonProcessingException
	 */
	public String getMetadata(
			PageMetadata metadata) 
					throws JsonProcessingException {
		return "\"metadata\":"+
					mapper.
						writeValueAsString(
								metadata);
	}
	
	/**
	 * Get json pagination.
	 * 
	 * @param page Page actual
	 * @param size Size of page actual
	 * @param sort Sort: It required field, follow by "," and order. For example: id,asc or id,desc.
	 * @return Json pagination
	 */
	public String getPagination(
			long page, 
			long size, 
			String sort) {
		if (sort==null) {
			return "?page="+page+
					"&size="+size;	
		} else {
		return "?page="+page+
				"&size="+size+
				"&sort="+sort;
		}
	}
	
	/**
	 * Get json body. This is body of api get method.
	 * 
	 * @param content Page resources
	 * @param links Links of pages
	 * @param metadata metadata of page
	 * @return Json body
	 */
	public String getBody(
			String content, 
			String links, 
			String metadata) {
		return "{"+content+","
				+links+","
				+metadata+"}";
	}
	
	/**
	 * Generate a json entity. It is necessary for compare responses.
	 * 
	 * @param entity Entity to convert
	 * @return Json entity
	 * @throws JsonProcessingException
	 */
	public String getEntity(
			T entity) 
					throws JsonProcessingException {
		return mapper.
				writeValueAsString(
						entity);
	}
	
	/**
	 * Generate a response from an Exception. 
	 * It provide a default response from {@link #handler} 
	 * +info: {@link KHandlerController#KHDefault(Exception)}
	 * 
	 * @param exception Exception expected
	 * @return {@link KHandlerResponse}
	 * @throws JsonProcessingException
	 */
	public ResponseEntity<String> getResponse(
				Exception exception) 
					throws JsonProcessingException {
		return getResponse(handler.
					Default(exception));
	}
	
	/**
	 * Generate a response from an KException. 
	 * It provide a response from {@link #handler} 
	 * +info: {@link KHandlerController#KHException(KException)}
	 * 
	 * @param exception KException, +info:  {@link KException}
	 * @return {@link KHandlerResponse}
	 * @throws JsonProcessingException
	 */
	public ResponseEntity<String> getResponse(
				KException exception) 
					throws JsonProcessingException {
		return getResponse(handler.
					kException(exception));
	}
	
	/**
	 * Generate a response from an KValidatorException. 
	 * It provide a response from {@link #handler} 
	 * +info: {@link KHandlerController#KHValidator(KValidatorException)}
	 * 
	 * @param exception KValidatorException, +info: {@link KValidatorException}
	 * @return {@link KValidatorResponse}
	 * @throws JsonProcessingException
	 */
	public ResponseEntity<String> getResponse(
				KValidatorException exception) 
					throws JsonProcessingException {
		return getResponse(handler.
					kEValidator(exception));
	}
	
	/**
	 * Generate a response from an KValidatorException. 
	 * It provide a response for a rule from {@link #handler} 
	 * +info: {@link KHandlerController#KHValidator(KValidatorException)}
	 * 
	 * @param rule Rule to validated
	 * @param value Value to validate
	 * @return {@link KValidatorResponse}
	 * @throws JsonProcessingException
	 */
	public ResponseEntity<String> getResponse(
			Rule rule, String value) 
				throws JsonProcessingException {
		List<KValidatorMessage> 
			messageList = 
				new ArrayList<>();
		messageList.add(
				new KValidatorMessage(
						rule.getCode(), 
						rule.getMessage(value)));
	return getResponse(handler.
				kEValidator(
						new KValidatorException(
								ErrorCode.ERROR_MULTIPLE, 
								messageList)));
	}
	
	/**
	 * Generate a response from an KException. 
	 * It provide a response for an ErrorCode from {@link #handler} 
	 * +info: {@link KHandlerController#KHException(KException)}
	 * 
	 * @param error ErroCode expected, +info: {@link ErrorCode}
	 * @return {@link KHandlerResponse}
	 * @throws JsonProcessingException
	 */
	public ResponseEntity<String> getResponse(
			ErrorCode error) 
				throws JsonProcessingException {
	return getResponse(handler.
				kException(
						new KException(error)));
	}
	
	/**
	 * Generate a ResponseEntity type String from another ResponseEntity of any type.
	 * 
	 * @param response ResponseEntity  of any type
	 * @return ResponseEntity type String
	 * @throws JsonProcessingException
	 */
	public ResponseEntity<String> getResponse(
				ResponseEntity<?> response) 
					throws JsonProcessingException{
		return new ResponseEntity<>(
				mapper.
					writeValueAsString(
							response.getBody()),
				response.getStatusCode());
	}
	
	/**
	 * Generate a KSuccessResponse. It is generated when api response is success.
	 * 
	 * @param <E> Data expected
	 * @param method Api method that generate the response, +info: {@link ApiMethod}
	 * @param data Data expected
	 * @return {@link KSuccessResponse}
	 * @throws JsonProcessingException
	 */
	public ResponseEntity<String> getResponse(ApiMethod method, Object content) throws JsonProcessingException{
		HttpStatus status;
		switch (method) {
		case GET:
			if(content!=null) {
				status = HttpStatus.OK;
			}else {
				status = HttpStatus.NO_CONTENT;
			}
			break;
		case ADD:
			status = HttpStatus.CREATED;
			content = new KSuccessResponse<>(
								method, 
								content);
			break;
		default:
			status=HttpStatus.OK;
			content = new KSuccessResponse<>(
					method, 
					content);
			break;
		}
		return new ResponseEntity<>(
				mapper.
					writeValueAsString(content),
							status);
	}
	
	/**
	 * Generate a response from a json
	 * 
	 * @param body Json expected
	 * @param status {@link HttpStatus}
	 * @return ResponseEntity type String
	 * @throws JsonProcessingException
	 */
	public ResponseEntity<String> getResponse(
			 String body, HttpStatus status) 
				throws JsonProcessingException{
	return new ResponseEntity<>(body, status);
	}
	
	/**
	 * Generate a response from a any data
	 * 
	 * @param data Data expected
	 * @param status {@link HttpStatus}
	 * @return ResponseEntity type String
	 * @throws JsonProcessingException
	 */
	public ResponseEntity<String> getResponse(
			 T data, HttpStatus status) 
				throws JsonProcessingException{
	return new ResponseEntity<>(
					mapper.
						writeValueAsString(data), 
					status);
	}
	
	/**
	 * Generate a response from a list of Entities
	 * 
	 * @param entities Entities expected.
	 * @param metadata Metadata expected.
	 * @param position {@link PagePosition}
	 * @param sort Sort: It required field, follow by "," and order. For example: id,asc or id,desc
	 * @return ResponseEntity type String
	 * @throws Exception
	 */
	public ResponseEntity<String> getResponse(
			List<T> entities, 
			PageMetadata metadata, 
			PagePosition position, 
			String sort) 
					throws Exception{
		return 
			new ResponseEntity<>(
					getBody(getContent(entities),
							getLinks(position, 
									 metadata, 
									 sort),
							getMetadata(metadata)),
					HttpStatus.OK);
	}
	
	/**
	 * Path to controller evaluated.
	 * 
	 * @return Path to controller.
	 */
	public String path () {
		RootUriTemplateHandler root = 
				(RootUriTemplateHandler)  
					restTemplate.
							getUriTemplateHandler();
		return root.getRootUri()+pathToController;
	}
	
	/**
	 * Call api GET method.
	 * 
	 * @param pathParams It can be query params.
	 * @return ResponseEntity type String.
	 * @throws Exception
	 */
	public ResponseEntity<String> get(
			String pathParams) 
					throws Exception{
		return restTemplate.
					getForEntity(
							path()+
							pathParams, 
					String.class);
	}
	
	/**
	 * Call api POST method.
	 * 
	 * @param data Data to be posted.
	 * @return ResponseEntity type String.
	 * @throws Exception
	 */
	public ResponseEntity<String> post(T data) 
			throws Exception{
		return callRequest(
					data, 
					HttpMethod.POST, 
					"");
	}
	
	/**
	 * Call api PUT method.
	 * 
	 * @param data Data to be put
	 * @return ResponseEntity type String
	 * @throws Exception
	 */
	public ResponseEntity<String> put(T data) 
			throws Exception{
		return callRequest(
					data, 
					HttpMethod.PUT, 
					"");
	}
	
	/**
	 * Call api PATCH method.
	 * 
	 * @param pathParams It can be query params.
	 * @return ResponseEntity type String.
	 * @throws Exception
	 */
	public ResponseEntity<String> patch(
			String pathParams) 
					throws Exception{
		return callRequest(null, HttpMethod.PATCH, pathParams);
		/*
		return getResponse(restTemplate.
					patchForObject(
							path()+
							pathParams,
							null,
							String.class,
							new Object()),
				HttpStatus.OK);
				*/
	}
	
	/**
	 * Call api DELETE method.
	 * 
	 * @param pathParams It can be query params.
	 * @return ResponseEntity type String.
	 * @throws Exception
	 */
	public ResponseEntity<String> delete(
			String pathParams) 
					throws Exception{
		return callRequest(
					null, 
					HttpMethod.DELETE, 
					pathParams);
	}
	
	/**
	 * Call HttpMethod for generate a response.
	 * 
	 * @param data Data to be use.
	 * @param method Method to be call
	 * @param pathParams It can be query params.
	 * @return ResponseEntity type String.
	 * @throws Exception
	 */
	private ResponseEntity<String> callRequest(
				T data, 
				HttpMethod method, 
				String pathParams) 
			throws Exception{
		RequestEntity<T> 
			request = 
				new RequestEntity<>(
						data, 
						method, 
						new URI(path()+pathParams));
		return restTemplate.
				exchange(
						request, 
						String.class);
	}
	
	/**
	 * Set a rule to be validate by {@link KValidator}.
	 * 
	 * @param table Table to be validate.
	 * @param column Column to be validate.
	 * @param condition Condition to be validate.
	 * @param expected Value expected.
	 * @param operator Operator to be validate.
	 * @return {@link Rule}
	 */
	public Rule setRule(
			String table,
			String column,
			ApiMethod method,
			String expected,
			KValidatorOperator operator) {
		Rule newRule = new Rule(
				"ruleId",
				"code",
				"message",
				table,
				column,
				method.toString(),
				expected,
				operator.toString());
		ruleRepository.save(newRule);
		return ruleRepository.findOne(newRule.getId());
	}
	
	/**
	 * Generate data not valid for generate a {@link KValidatorException}
	 * 
	 * @param data Data to be convert.
	 * @param column Column to be process.
	 * @param method Api method called.
	 * @return Data fail.
	 * @throws Exception
	 */
	public T getDataNotValid(
					T data,
					String column,
					ApiMethod method) 
								throws Exception {
		Class<?> classType = data.getClass();
		Table table = classType.getAnnotation(Table.class);
		if (table != null) {
			String tableName = table.name();
			this.rule = setRule(
							tableName, 
							column, 
							method, 
							"valueExpected", 
							KValidatorOperator.EQUAL);
			Field field = data.getClass().getDeclaredField(column);
			field.setAccessible(true);
			field.set(data, "valueNotExpected");
			field.setAccessible(false);
			return data;
		} else {
			throw new KException(ErrorCode.NOT_TABLE);
		}
	}
	
	/**
	 * Get rule for fail. It is for generate a {@link KValidatorException}
	 * 
	 * @return {@link Rule}
	 */
	public Rule getRule() {
		return rule;
	}
}

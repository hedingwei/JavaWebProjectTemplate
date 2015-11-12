package com.ambimmort.app.framework.springsecurity;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashMap;

public class FilterInvocationSecurityMetadataSourceFactoryBean extends
		JdbcDaoSupport implements FactoryBean<FilterInvocationSecurityMetadataSource> {
	private String resourceQuery;

	public boolean isSingleton() {
		return true;
	}

	public Class<FilterInvocationSecurityMetadataSource> getObjectType() {
		return FilterInvocationSecurityMetadataSource.class;
	}

	public FilterInvocationSecurityMetadataSource getObject() {
		// return new DefaultFilterInvocationSecurityMetadataSource(this.getUrlMatcher(), this.buildRequestMap());
//		return new DefaultFilterInvocationSecurityMetadataSource(this.getUrlMatcher(), this.buildRequestMap301()); // 3.01 derepcated
//		return new DefaultFilterInvocationSecurityMetadataSource(this.buildRequestMap313()); // 3.1.3 derepcated
		return new DefaultFilterInvocationSecurityMetadataSource(this.buildRequestMap326()); // 3.2.6
	}
	
	private LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> buildRequestMap326(){
		LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<>();


		requestMap.put(new AntPathRequestMatcher("/f/index.do"), SecurityConfig.createList("ROLE_USER"));

		ResourceMapping resourceMapping = new ResourceMapping(getDataSource(), resourceQuery);
		for (Resource resource : resourceMapping.execute()) {
			String url = resource.getUrl() + "*";// db 中不存储通配符
			String role = "ROLE_" + resource.getRole();
			
			RequestMatcher key = new AntPathRequestMatcher(url);
			
			if (requestMap.containsKey(key)) {
				requestMap.get(key).add(new SecurityConfig(role));
			} else {
				requestMap.put(key, SecurityConfig.createList(role));
			}
		}

//		requestMap.put(new AntPathRequestMatcher("/**"), SecurityConfig.createList("ROLE_ADMIN"));
		
		logger.info("SecurityMetadataSource resourceQuery : " + resourceQuery);
		logger.info("SecurityMetadataSource requestMap : " + requestMap);
		
		return requestMap;
	}


/*
	@Deprecated
	private LinkedHashMap<RequestKey, Collection<ConfigAttribute>> buildRequestMap301(){
		LinkedHashMap<RequestKey, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<RequestKey, Collection<ConfigAttribute>>();

		ResourceMapping resourceMapping = new ResourceMapping(getDataSource(), resourceQuery);
		for (Resource resource : (List<Resource>) resourceMapping.execute()) {
			String url = resource.getUrl();
			String role = resource.getRole();

			RequestKey key = new RequestKey(url, null);

			if (requestMap.containsKey(key)) {
				requestMap.get(key).add(new SecurityConfig(role.trim()));
			} else {
				requestMap.put(new RequestKey(url, null), SecurityConfig.createList(role));
			}
		}

		return requestMap;
	}

	@Deprecated
	private LinkedHashMap<RequestKey, Collection<ConfigAttribute>> buildRequestMap301(){
		LinkedHashMap<RequestKey, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<RequestKey, Collection<ConfigAttribute>>();
		
		ResourceMapping resourceMapping = new ResourceMapping(getDataSource(), resourceQuery);
		for (Resource resource : (List<Resource>) resourceMapping.execute()) {
			String url = resource.getUrl();
			String role = resource.getRole();
			
			RequestKey key = new RequestKey(url, null);
			
			if (requestMap.containsKey(key)) {
				requestMap.get(key).add(new SecurityConfig(role.trim()));
			} else {
				requestMap.put(new RequestKey(url, null), SecurityConfig.createList(role));
			}
		}
		
		return requestMap;
	}

	@Deprecated
	protected Map<String, String> findResources() {
		ResourceMapping resourceMapping = new ResourceMapping(getDataSource(), resourceQuery);

		Map<String, String> resourceMap = new LinkedHashMap<String, String>();

		for (Resource resource : (List<Resource>) resourceMapping.execute()) {
			String url = resource.getUrl();
			String role = resource.getRole();

			if (resourceMap.containsKey(url)) {
				String value = resourceMap.get(url);
				resourceMap.put(url, value + "," + role);
			} else {
				resourceMap.put(url, role);
			}
		}

		return resourceMap;
	}

	@Deprecated
	protected LinkedHashMap<RequestKey, Collection<ConfigAttribute>> buildRequestMap() {
		LinkedHashMap<RequestKey, Collection<ConfigAttribute>> requestMap = null;
		requestMap = new LinkedHashMap<RequestKey, Collection<ConfigAttribute>>();

		Map<String, String> resourceMap = this.findResources();

		for (Map.Entry<String, String> entry : resourceMap.entrySet()) {
			RequestKey key = new RequestKey(entry.getKey(), null);
			requestMap.put(key, SecurityConfig.createListFromCommaDelimitedString(entry.getValue()));
		}

		return requestMap;
	}
*/


	public void setResourceQuery(String resourceQuery) {
		this.resourceQuery = resourceQuery;
	}

	private class Resource {
		private String url;
		private String role;

		public Resource(String url, String role) {
			this.url = url;
			this.role = role;
		}

		public String getUrl() {
			return url;
		}

		public String getRole() {
			return role;
		}
	}

	private class ResourceMapping extends MappingSqlQuery<Resource> {
		protected ResourceMapping(DataSource dataSource, String resourceQuery) {
			super(dataSource, resourceQuery);
			compile();
		}

		protected Resource mapRow(ResultSet rs, int rownum) throws SQLException {
			String url = rs.getString(1);
			String role = rs.getString(2);
			Resource resource = new Resource(url, role);

			return resource;
		}
	}
}
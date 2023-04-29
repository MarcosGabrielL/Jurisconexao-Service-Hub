package br.com.jurisconexao.hub;


import org.springframework.boot.context.properties.ConfigurationProperties;



@ConfigurationProperties
public class Configuration {
	
	private String httpbin = "http://httpbin.org:80";

	public String getHttpbin() {
		return httpbin;
	}

	public void setHttpbin(String httpbin) {
		this.httpbin = httpbin;
	}
}



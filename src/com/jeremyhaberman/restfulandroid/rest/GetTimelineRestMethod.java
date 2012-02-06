package com.jeremyhaberman.restfulandroid.rest;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.jeremyhaberman.restfulandroid.rest.resource.TwitterTimeline;
import com.jeremyhaberman.restfulandroid.security.AuthorizationManager;

public class GetTimelineRestMethod extends AbstractRestMethod<TwitterTimeline>{
	
	private static final URI TIMELINE_URI = URI.create("https://api.twitter.com/posts/");
	
	private Map<String, List<String>> headers;
	
	public GetTimelineRestMethod(Map<String, List<String>> headers){
		this.headers = headers;
	}

	@Override
	protected Request buildRequest() {
		
		AuthorizationManager authManager = AuthorizationManager.getInstance();
		Request request = new Request(TIMELINE_URI, headers, null);
		authManager.signRequest(request);
		
		return request;
	}

	@Override
	protected TwitterTimeline parseResponseBody(String responseBody) throws Exception {
		
		JSONObject json = new JSONObject(responseBody);
		return new TwitterTimeline(json);
		
	}

}

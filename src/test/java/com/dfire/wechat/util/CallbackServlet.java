package com.dfire.wechat.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.common.io.CharStreams;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CallbackServlet extends HttpServlet{
	private static Logger logger = Logger.getLogger(HttpServlet.class);
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
		logger.info("jetty server is handling the callback information ");
		JsonObject callbackJson = new JsonParser().parse(getRequestContent(request)).getAsJsonObject();
		logger.info("the request body is : " + callbackJson.toString());
		
		Long vid = callbackJson.get("vid").getAsLong();
		int status = callbackJson.get("status").getAsInt();
		logger.info("jobID is : " + vid + ", job status is : " + status);
		
	}
	
    private String getRequestContent(HttpServletRequest request) throws IOException {
 
        BufferedReader bReader = request.getReader();
        String str = CharStreams.toString(bReader);
        bReader.close();
        return str;
    }


}

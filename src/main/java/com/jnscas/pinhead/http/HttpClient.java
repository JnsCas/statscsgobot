package com.jnscas.pinhead.http;

import com.jnscas.pinhead.exceptions.HttpClientException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.IOException;

public abstract class HttpClient {

    private static Logger logger = LoggerContext.getContext().getLogger(HttpClient.class.getSimpleName());

    public String get(String url) {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(url);

            logger.info("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            logger.info("----------------------------------------");
            logger.info(responseBody); //FIXME remove
            httpclient.close();
            return responseBody;
        } catch (IOException e) {
          logger.error("GET url: " + url);
          throw new HttpClientException(e);
        }
    }

}

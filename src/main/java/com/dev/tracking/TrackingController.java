package com.dev.tracking;

import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/tracking")
@RequiredArgsConstructor
public class TrackingController {

    @GetMapping("/doX")
    public Map<String, Object> doX() {

        JSONObject json = getTrackingDataFromShippo();

        return json.toMap();
    }

    private static JSONObject getTrackingDataFromShippo() {
        StringBuffer urlBuffer = new StringBuffer("https://api.goshippo.com/tracks/shippo/SHIPPO_TRANSIT");
        HttpGet request = new HttpGet( urlBuffer.toString() );
    	request.setHeader("Authorization","ShippoToken shippo_test_2bfda3d881a4c7fa073da255971981258c02a8e8");
        CloseableHttpClient client = HttpClientBuilder.create().build();

        String responseString = null;
        try {
			HttpResponse response = client.execute(request);
	
            InputStream inputStr = response.getEntity().getContent();
            responseString = IOUtils.toString(inputStr, "UTF-8");

			JSONObject responseJson = new JSONObject(responseString);
			
			return responseJson;
		} catch (Exception e) {
            System.out.println("erroooorr");
			return null;
        }
    }

    private static getTrackingDataFromParcel() {
        return null;
    }

}

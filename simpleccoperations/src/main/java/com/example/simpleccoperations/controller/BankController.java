package com.example.simpleccoperations.controller;

import org.springframework.http.*;

import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


import javax.servlet.http.HttpSession;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BankController {
private static final String accountid = "14537780" ;

    @GetMapping("/user")
    public String getUser() throws MalformedURLException {
       HttpHeaders headers = new HttpHeaders();
        headers.add("Auth-Schema","S2S");
        headers.add("Api-Key" ,"FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        RestTemplate rst = new RestTemplate();
        ResponseEntity<String> response = rst.exchange("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/"+accountid+"/balance", HttpMethod.GET ,entity , String.class );
        return response.getBody();

    }

    @GetMapping("/postprova")

    public String postMoneyTransfer(){
        HttpHeaders headers = new HttpHeaders();
        //    headers.add("accountid","14537780");
        headers.set("X-Time-Zone", "Europe/Rome");
        headers.add("Auth-Schema","S2S");
        headers.add("Api-Key" ,"FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");

        Map<String,Object> body = new HashMap<>();
        body.put("receiverName","IT23A0336844430152923804660");
        body.put("description","descrizione");
        body.put("currency","EUR");
        body.put("amount",800L);
        body.put("executionDate","executionDate");



        String url = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/"+accountid+"/payments/money-transfers";
        RestTemplate rst = new RestTemplate();

        HttpEntity<Map<String,String>> request = new HttpEntity<>(headers);
        System.out.println(request.getBody());
        ResponseEntity<String> response = rst.exchange(url, HttpMethod.POST ,request ,  String.class , body);
        System.out.println(response.getBody());
        return "";
    }



    @GetMapping("/listatrasferimenti")
    public String getTraferimenti() throws MalformedURLException {
        //  System.out.println("ciao");
        HttpHeaders headers = new HttpHeaders();
        //    headers.add("accountid","14537780");
        headers.add("Auth-Schema","S2S");
        headers.add("Api-Key" ,"FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");

        Map<String, Object> map = new HashMap<>();

        HttpEntity<Map<String,Object>> entity = new HttpEntity<>(headers);

        RestTemplate rst = new RestTemplate();
        ResponseEntity<String> response = rst.exchange("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/"+accountid+"/transactions ?fromAccountingDate=2019-01-01&toAccountingDate=2019-12-01", HttpMethod.GET ,entity , String.class );
        //  System.out.println(response.getBody());
        return response.getBody();

    }
}

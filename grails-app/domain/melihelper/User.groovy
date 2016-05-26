package melihelper

import com.mercadolibre.sdk.*
import com.ning.http.client.FluentStringsMap
import com.ning.http.client.Response

import groovy.json.JsonSlurper


class User {
	public Meli meli = new Meli(6842447735937754,"yyAn31z207XeKnd6cYUnZ02JHYpyK7Ip")
	String firstName
	String accessToken
    String questions
    String unanswered

    static constraints = {
    }

    def getAuthUrl() {
    	return meli.getAuthUrl("http://localhost:9000/user/callback");
    }

    def authorize(String code){
    	meli.authorize(code,"http://localhost:9000/user/callback")
    }

    def getAccessToken(){
    	accessToken = meli.getAccessToken()
    }

    def getQuestions(){
    	FluentStringsMap meli_params = new FluentStringsMap();
        meli_params.add("access_token", accessToken);
    	String response = meli.get("/my/received_questions/search", meli_params).getResponseBody()

        def slurper = new JsonSlurper().parseText(response)
        questions = slurper.questions
    }


    def getUnanswered(){
        FluentStringsMap meli_params = new FluentStringsMap();
        meli_params.add("access_token", accessToken);
        meli_params.add("status","UNANSWERED")
        String response = meli.get("/my/received_questions/search", meli_params ).getResponseBody()

        def slurper = new JsonSlurper().parseText(response)
        unanswered = slurper.questions
    }

    def getFirstName(){
    	FluentStringsMap meli_params = new FluentStringsMap();
        meli_params.add("access_token", accessToken);
       	String response = meli.get("/users/me", meli_params).getResponseBody()

       	def slurper = new JsonSlurper().parseText(response)
       	firstName =  slurper.first_name
      
    }

 

 
}

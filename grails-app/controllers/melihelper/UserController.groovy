package melihelper

import com.ning.http.client.FluentStringsMap
import com.ning.http.client.Response

class UserController {

	static defaultAction = "login"

    def welcome(){
        render "hello"
    }


    def login(){
        session.user = new User()
        String redirectUrl = session.user.getAuthUrl()

        redirect (url: redirectUrl)
    }

    def callback() {

        String code  = params.code
        User user = session.user
        
        user.authorize(code)
        session.accessToken = user.getAccessToken()

        render view: "question" 
      
        
    }


    def postResponse() {


        FluentStringsMap meli_params = new FluentStringsMap();
        meli_params.add("access_token", session.accessToken);
        String response = session.user.meli.post("/answers", meli_params, "{\"question_id\":\"${params.questionid}\",\"text\":\"${params.textfield}\"}").getResponseBody();

        render response
        render view: "question"

        
    }


}

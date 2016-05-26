<!doctype html>

<html>
	
    <head>
        <meta http-equiv="Content-type" content="text/html; charset=utf-8">
	</head>

    <body>

        <div id="loginBox" class="loginBox">
            <g:if test="${session?.user}">
                <div style="margin-top:20px">
                    <div style="float:right;">
                        <span id="userFirstName"> Hello ${session?.user?.firstName}!</span>
                    </div>
            	</div>
          	</g:if>
            <g:else>
                <div id="registerPane">
                    <g:link controller="user" action="login" >Please login</g:link> to manage your ML questions!
                </div>
              </g:else>
        </div>


        <g:if test="${session?.user}">
            <br> 
            <p>YOUR UNANSWERED QUESTIONS</p>
            <g:each in="${session?.user?.unanswered}">
                <g:if test="${it.status.equals('UNANSWERED')}">
                    <div style="border:solid; margin:20px 20px 20px 20px; padding: 10px 10px 10px 10px">  
                    
                        <p style="color:red" class="answer"> ${it.status} -- ${it.text}</p>
                            <g:form name="myform" controller="user" action="postResponse">
                                <!-- name of texfield will be the name of the param passed. i.e 'textfield'-->
                                <g:textField name="textfield" maxlength="300" style="width:600px; height:50px"></g:textField>
                                <input type="hidden" name="questionid" value="${it.id}" />
                                <g:submitButton name="post response to this question" value="Update" />
                            </g:form>
                    </div>
                </g:if>
            </g:each>
            <br>
            <p>YOUR ANSWERED QUESTIONS (${session?.user?.questions.size})</p>

            <g:each in="${session?.user?.questions}">
                <g:if test="${it.status.equals('ANSWERED')}">
                    <div style="border:solid; margin:20px 20px 20px 20px; padding: 10px 10px 10px 10px">
                            <p style="color:red" class="answer"> ${it.status} -- ${it.text}</p>
                            <p style="color:green" class="answer"> ${it.answer}</p>
                    </div>
                </g:if> 
            </g:each>

        </g:if>
    </body>
</html>










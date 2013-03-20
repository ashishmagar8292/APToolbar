<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>my browser</title>
</head>
<body>
<form action="Main_servlet" method="post">
URL : <input type="text" name="main_url" >
Submit : <input type="submit" name="S1" onclick = "myReq()">
<!--<input type="submit" value="S1">  --> 
<br>
Result is : <%=  request.getParameter("result") %>

<Script type="text/javascript">

/* function abcd() {
    if (this.readyState === 4) {

          // onSuccess
          if (this.status === 200 && typeof onSuccess == 'function') {
              onSuccess(this.responseText);
              alert(responseText);
              alert("response header is " +request.getResponseHeader("name"));
          }

          // onError
          else if(typeof onError == 'function') {
              onError();
          }

      }
 */
 function myReq()
{
	try
	{
		var stringParameter = "https://login.yahoo.com/config/login_verify2?&.src=ym";
		var request=new XMLHttpRequest();
		alert("i am here inside try");
		request.open("GET", 'http://localhost:7001/APToolbar/Main_servlet?stringParameter=' + stringParameter, true);
		request.send();
	//	xmlHttp.onreadystatechange = abcd();
		//alert(request.getAttribute("Resullt"));
		return true;
  	}
	catch(err)
	{
		alert(err.message);
	}
} 
</Script>
</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Fail Page</title>
    </head>
    <body>
        <%=request.getAttribute("error") %>
        Choose correct file format <br />
        <a href="uploadform.jsp">Upload file</a>
    </body>
    </html>
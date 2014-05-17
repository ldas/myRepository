<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Upload file</title>
    </head>
    <body>
        <form action="upload" enctype="multipart/form-data" method="post">
            <table border="1">
                <tr>
                    <td>CSV or TXT file:</td>
                    <td><input type="file" name="csvfile" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="submit"></td>
                </tr>
            </table>            
        </form>
    </body>
    </html>

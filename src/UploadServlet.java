import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class UploadServlet extends HttpServlet
{

    private static final long serialVersionUID = 1L;
    PrintWriter out;

    public UploadServlet()
    {
    }

    private void setOut(PrintWriter out)
    {
        this.out = out;
    }

    private void println(String content)
    {
        out.print((new StringBuilder()).append(content).append("\n").toString());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        setOut(response.getWriter());
        boolean done = false;
        String contentType = request.getContentType();
        if(contentType != null && contentType.indexOf("multipart/form-data") >= 0)
        {
            DataInputStream in = new DataInputStream(request.getInputStream());
            int formDataLength = request.getContentLength();
            byte dataBytes[] = new byte[formDataLength];
            int byteRead = 0;
            for(int totalBytesRead = 0; totalBytesRead < formDataLength; totalBytesRead += byteRead)
            {
                byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
            }

            String file = new String(dataBytes);
            String saveFile = file.substring(file.indexOf("filename=\"") + 10);
            saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
            saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1, saveFile.indexOf("\""));
            int lastIndex = contentType.lastIndexOf("=");
            String boundary = contentType.substring(lastIndex + 1, contentType.length());
            int pos = file.indexOf("filename=\"");
            pos = file.indexOf("\n", pos) + 1;
            pos = file.indexOf("\n", pos) + 1;
            pos = file.indexOf("\n", pos) + 1;
            int boundaryLocation = file.indexOf(boundary, pos) - 4;
            int startPos = file.substring(0, pos).getBytes().length;
            int endPos = file.substring(0, boundaryLocation).getBytes().length;
            if(saveFile.endsWith(".txt") || saveFile.endsWith(".csv"))
            {
                FileOutputStream fileOut = new FileOutputStream(new File(saveFile));
                fileOut.write(dataBytes, startPos, endPos - startPos);
                fileOut.flush();
                fileOut.close();
                done = true;
                getServletContext().setAttribute("fileName", saveFile);
                request.setAttribute("fileName", saveFile);
            } else
            {
                request.setAttribute("error", "Unsupported file format");
            }
        }
        if(done)
        {
            request.getRequestDispatcher("Confirm.jsp").forward(request, response);
        } else
        {
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }
    }
}

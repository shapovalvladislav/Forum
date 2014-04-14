package ua.net.forum.servlets;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;


/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor. 
     */
    public UploadServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	        if (isMultipart) {
	        	// Create a factory for disk-based file items
	        	FileItemFactory factory = new DiskFileItemFactory();
	        	// Create a new file upload handler
	        	ServletFileUpload upload = new ServletFileUpload(factory);
	            try {
	            	// Parse the request
					List<FileItem> items = upload.parseRequest(request);
					Iterator iterator = items.iterator();
					System.out.println("before");
					while (iterator.hasNext()) {
						System.out.println("after");
	                    FileItem item = (FileItem) iterator.next();
	                    if (!item.isFormField()) {
	                        String fileName = item.getName();
	                        System.out.println("Filename " + fileName);
	                        String root = getServletContext().getRealPath("/");
	                        System.out.println("Root " + root);
	                        File path = new File("/var/tmp/uploads");
	                        if (!path.exists()) {
	                        	System.out.println("Path doesn't exist");
	                        	boolean status = path.mkdirs();
	                        }
	                        File uploadedFile = new File(path + "/" + fileName);
	                        System.out.println(uploadedFile.getAbsolutePath());
	                        item.write(uploadedFile);
	                        BufferedImage img = ImageIO.read(uploadedFile);
	                        BufferedImage scaledImg = Scalr.resize(img, Mode.FIT_EXACT, 128, 128);
	                        String ext = FilenameUtils.getExtension(uploadedFile.getCanonicalPath());
	                        ImageIO.write(scaledImg, ext, uploadedFile);
	                    }
	                }
	            } catch (FileUploadException e) {
	                e.printStackTrace();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	}

}

package com.wouterv.twatter.Controller;

/**
 * Created by Wouter Vanmulken on 13-3-2017.
 */

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
//import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/files")
public class FileUploadController {
    String localFolder ="";
    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces("application/json")
    public Response uploadFile(File file) {
        throw new NotImplementedException();

//        FileWriter fw;
//        try {
//            fw = new FileWriter(file);
//            BufferedWriter bw = new BufferedWriter(fw);
//            bw.write("C:\\testfiles\\" + file.getName());
//            bw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return Response.status(200).entity(file.getName()).build();
    }
}
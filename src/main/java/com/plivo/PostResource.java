package com.plivo;

import com.codahale.metrics.annotation.Timed;
import com.plivo.com.plivo.test.TestRecording;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


@Path("/app")

public class PostResource {


    public PostResource() {
        //Do Nothin
    }

    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_XML)
    @POST
    @Path("/hello")
    @Timed
    public String sayMe(@Context HttpServletRequest httpRequest) {

        System.out.println("Got this request");
        System.out.println(httpRequest.getHeader("Content-Type"));
        //return new String("<Response><Play>https://s3.amazonaws.com/plivocloud/Trumpet.mp3</Play></Response>");

        //return new String("<Response><Record action=\"http://b212638a.ngrok.io/app/action\"  redirect=\"false\" recordSession=\"true\" /><Play></Play></Response>");
        return new String("<Response><Record callbackUrl=\"http://b212638a.ngrok.io/app/action\"  redirect=\"false\" recordSession=\"true\" fileFormat=\"wav\" /><Play>http://www.signalogic.com/melp/EngSamples/Orig/male.wav</Play></Response>");

    }

    @POST
    @Path("/action")
    @Timed
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_FORM_URLENCODED)
    // public String action(MultivaluedHashMap map)  {
    public String action(String querParam) {
        System.out.println("map:" + querParam);

        Recording recording = new Recording();

        String str[] = querParam.split("&");
        for (String keyValue : str) {

            String nameValue[] = keyValue.split("=");

            if (nameValue[0].equals("RecordFile")) {

                recording.setRecordFile(nameValue[1]);
            }
            if (nameValue[0].equals("RecordingDuration")) {

                recording.setRecordingDuration(nameValue[1]);
            }
            if (nameValue[0].equals("RecordingDurationMs")) {
                recording.setRecordingDurationMs(nameValue[1]);
            }
            if (nameValue[0].equals("BillRate")) {
                recording.setBillRate(nameValue[1]);
            }


        }
        System.out.println(recording.toString());
        TestRecording.testRecord(recording);
        return recording.toString();
    }

}

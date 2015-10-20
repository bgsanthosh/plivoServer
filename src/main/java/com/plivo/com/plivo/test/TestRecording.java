package com.plivo.com.plivo.test;

import com.jayway.restassured.response.Response;
import com.plivo.Recording;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;

import java.io.*;
import java.net.URLDecoder;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by santhosh.b on 20/10/15.
 */
public class TestRecording {

    public static final String actualRecordedFile = "/Users/santhosh.b/recorded.wav";

    public static void testRecord(Recording recording) {

        Assert.assertEquals(Integer.parseInt(recording.getRecordingDuration()), 51, "Recording Duration is more");
        Assert.assertEquals(recording.getBillRate(), "0.03570", "Recording BillRate is more");

       // downloadRecordedFile(recording);
       String score = null;
        try {

            Process proc = Runtime.getRuntime().exec("/Users/santhosh.b/plivo/Software/P862_annex_A_2005_CD/source/PESQ +16000 /Users/santhosh.b/plivo/Software/P862_annex_A_2005_CD/source/expected.wav /Users/santhosh.b/recorded.wav");
            InputStream stdin = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(stdin);
            BufferedReader br = new BufferedReader(isr);

            String line = null;
            System.out.println("<OUTPUT>");

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                if(line.startsWith("P.862 Prediction")) {
                    score = line;
                }
            }
            System.out.println("</OUTPUT>");
            int exitVal = proc.waitFor();
            System.out.println("Process exitValue: " + exitVal);
        } catch (Exception e) {

            e.printStackTrace();
        }

        String bothScore[] = score.split("=");
        String mosScore[] = null;
        if(!StringUtils.isEmpty(bothScore[1])) {
            String expr = "\\s+";
            mosScore = bothScore[1].split(expr);
        }

        if(mosScore[1] != null) {
            double actual = Double.valueOf(mosScore[1]);
            double startMOS = 3;
            double endMOS = 5;
            Assert.assertEquals(startMOS < actual, true, "MOS Less than expected");
            Assert.assertEquals(endMOS > actual, true, "MOS Greater than expected");
        }
        else    {

            Assert.fail("Test Failed MOS Not Found!!");
        }
    }

    public static void downloadRecordedFile(Recording recording) {

        FileOutputStream fileOutputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {

            System.out.println("Calling URL : " + URLDecoder.decode(recording.getRecordFile(), "UTF-8"));
            Response response = given().get(URLDecoder.decode(recording.getRecordFile(), "UTF-8"));

            fileOutputStream = new FileOutputStream(actualRecordedFile);
            fileOutputStream.write(response.getBody().asByteArray());

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

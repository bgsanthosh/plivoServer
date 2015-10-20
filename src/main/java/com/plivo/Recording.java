package com.plivo;

/**
 * Created by santhosh.b on 20/10/15.
 */
public class Recording {

    String recordFile;
    String recordingDuration;
    String recordingDurationMs;
    String billRate;

    public void setRecordFile(String recordFile)    {

        this.recordFile = recordFile;
    }


    public void setRecordingDuration(String recordingDuration)    {

        this.recordingDuration = recordingDuration;

    }

    public void setRecordingDurationMs(String recordingDurationMs)    {

        this.recordingDurationMs = recordingDurationMs;
    }

    public void setBillRate(String rate)    {

        this.billRate = rate;
    }

    public String getBillRate() {

        return billRate;
    }

    public String getRecordFile() {

        return recordFile;
    }

    public String getRecordingDuration() {

        return recordingDuration;
    }


    public String getRecordingDurationMs() {

        return recordingDurationMs;
    }




    @Override
    public String toString()    {

        return "recordFile=" + recordFile + "&recordDuration=" + recordingDuration +"&BillRate=" + billRate + "&recordDurationMs=" + recordingDurationMs;
    }
}

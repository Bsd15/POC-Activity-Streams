package com.stackrout.succour.pocactivitystreams.controller;

import com.ibm.common.activitystreams.Activity;
import com.ibm.common.activitystreams.IO;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

import static com.ibm.common.activitystreams.IO.makeDefaultPrettyPrint;
import static com.ibm.common.activitystreams.Makers.activity;
import static com.ibm.common.activitystreams.Makers.object;

@RestController
public class ActivityStreamController {

    private static final IO io = makeDefaultPrettyPrint();
    @GetMapping("/demo-activity-stream")
    public Activity demoActivityStream(){
        Activity activity =
                activity()
                        .actor(object("person").id("acct:joe@example.org"))
                        .object(object("note").content("my note"))
                        .verb("post")
                        .get();
        ByteArrayOutputStream out =
                new ByteArrayOutputStream();
        activity.writeTo(out, io);
        System.out.println(activity);
        JSONObject testV = null;
        try {
            testV=new JSONObject(out.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return activity;
    }
}

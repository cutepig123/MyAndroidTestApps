package com.example.test8_intentservice;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

public class DownloadService extends IntentService {

  private int result = Activity.RESULT_CANCELED;

  public DownloadService() {
    super("DownloadService");
  }

  // Will be called asynchronously be Android
  @Override
  protected void onHandleIntent(Intent intent) {
    Uri data = intent.getData();
    String urlPath = intent.getStringExtra("urlpath");
    String fileName = data.getLastPathSegment();
    File output = new File(Environment.getExternalStorageDirectory(),
        fileName);
    Log.d("JsHe","Log to file: "+output.getPath() + " Abs Path: " + output.getAbsolutePath());
    if (output.exists()) {
      output.delete();
    }

    InputStream stream = null;
    FileOutputStream fos = null;
    try {
      Log.d("JsHe","Download from " + urlPath);
      URL url = new URL(urlPath);
      stream = url.openConnection().getInputStream();
      BufferedInputStream reader = new BufferedInputStream(stream);
      fos = new FileOutputStream(output.getPath());
      int offset = 0;
      int bufLength =10240;
      int readLength =0;
      byte []buffer =new byte[bufLength];
      while ((readLength = reader.read(buffer, 0, bufLength)) != -1) {
    	  Log.d("JsHe","Download position " + offset);
    	  fos.write(buffer, offset, readLength);
    	  offset +=readLength;
      }
      Log.d("JsHe","Total download size " + offset);
      // Sucessful finished
      result = Activity.RESULT_OK;

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (stream != null) {
        try {
          stream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (fos != null) {
        try {
          fos.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    Bundle extras = intent.getExtras();
    if (extras != null) {
      Messenger messenger = (Messenger) extras.get("MESSENGER");
      Message msg = Message.obtain();
      msg.arg1 = result;
      msg.obj = output.getAbsolutePath();
      try {
        messenger.send(msg);
      } catch (android.os.RemoteException e1) {
        Log.w(getClass().getName(), "Exception sending message", e1);
      }

    }
  }
} 
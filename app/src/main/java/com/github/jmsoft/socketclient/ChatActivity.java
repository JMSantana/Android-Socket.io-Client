package com.github.jmsoft.socketclient;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.jmsoft.socketclient.adapter.ChatAdapter;
import com.github.jmsoft.socketclient.interfaces.ActivityGenericsInterface;
import com.github.jmsoft.socketclient.model.Message;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import socketclient.lg.com.socketclient.R;

/**
 * Chat Activity to send and receive messages
 */
public class ChatActivity extends AppCompatActivity implements ActivityGenericsInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

    }

    /**
     * Get UI components references
     */
    public void initializeUIComponents() {
    }

    /**
     * Retrieve values passed through intent
     */
    public void getIntentValues() {
    }

}

package com.github.jmsoft.socketclient;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
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

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import socketclient.lg.com.socketclient.R;

/**
 * Chat Activity to send and receive messages
 */
public class ChatActivity extends AppCompatActivity implements ActivityGenericsInterface {

    private EditText etMessage;
    private RecyclerView mRecList;
    private ChatAdapter mChatAdapter;
    private Button btnSend;

    private Socket mSocket;
    private Handler mTypingHandler = new Handler();

    private String mIdentification;
    private String mAddress;
    private int mPort;
    private List<Message> messages = new ArrayList<Message>();
    private boolean mTyping = false;

    private static final int TYPING_TIMER_LENGTH = 600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //Get values coming from MainActivity
        getIntentValues();

        //Get the reference to the UI components
        initializeUIComponents();

    }

    /**
     * Get UI components references
     */
    public void initializeUIComponents() {
        etMessage = (EditText) findViewById(R.id.etMessage);
        btnSend = (Button) findViewById(R.id.btnSend);
        mRecList = (RecyclerView) findViewById(R.id.recyclerView);
        mRecList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecList.setLayoutManager(llm);
        mChatAdapter = new ChatAdapter(messages, mIdentification, this);
        mRecList.setAdapter(mChatAdapter);

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        } catch (Exception e){
            Log.e("chat application", e.getMessage());
            finish();
        }
    }

    /**
     * Retrieve values passed through intent
     */
    public void getIntentValues() {
        mIdentification = getIntent().getStringExtra("identification");
        mAddress = getIntent().getStringExtra("address");
        mPort = getIntent().getIntExtra("port", 1234);
    }


    private void notifyAdapter() {
        Handler handler = new Handler(Looper.getMainLooper());

        final Runnable r = new Runnable() {
            public void run() {
                //This is crashing due to a bug on Android. See https://code.google.com/p/android/issues/detail?id=77846
                //mChatAdapter.notifyItemInserted(messages.size() - 1);
                //Using notifyDataSetChanged instead. This kills animation and performance, but...
                mChatAdapter.notifyDataSetChanged();
            }
        };

        handler.post(r);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

package com.lmmt.easytrash;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.lmmt.easytrash.R;

import java.util.ArrayList;

public class Chatbot extends AppCompatActivity {
    private ListView chatListView;
    private EditText messageEditText;
    private Button sendButton;
    private ArrayList<String> chatMessages;
    private ArrayAdapter<String> chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        chatListView = findViewById(R.id.chatListView);
        messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);

        chatMessages = new ArrayList<>();
        chatAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, chatMessages);
        chatListView.setAdapter(chatAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = messageEditText.getText().toString();
                if (!message.isEmpty()) {
                    sendMessage(message);
                    messageEditText.setText("");
                }
            }
        });
    }

    private void sendMessage(String message) {
        chatMessages.add("Usuario: " + message);
        chatAdapter.notifyDataSetChanged();
        // Aquí puedes implementar la lógica para enviar el mensaje a un servidor o a otra persona.
        String respuestaAleatoria = "Easy Trash: Hola buenas, esperando que te encuentres bien, en que te podemos ayudar??";
        chatMessages.add(respuestaAleatoria);
        chatAdapter.notifyDataSetChanged();
        //chatMessages.add("Usuario: que dias recogen cartones?");
        //String respuestaAleatoria1 = "Easy Trash: Los dias martes, las rangos de horarios son (18:00-18:20), en que mas te podemos ayudar??";
        //chatMessages.add(respuestaAleatoria1);
        //chatAdapter.notifyDataSetChanged();
    }
}
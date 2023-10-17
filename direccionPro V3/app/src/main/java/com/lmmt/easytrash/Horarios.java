package com.lmmt.easytrash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Horarios extends AppCompatActivity {
    private Button btnnot;
    private static final String CHANNEL_ID = "canal";
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);
        btnnot = findViewById(R.id.btnAlarma);
        btnnot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    showNotifiacion();
                }
                else {
                    showNuevoNotifiacion();
                }
            }

        });
    }
    private void showNotifiacion() {
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "NEW", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        showNuevoNotifiacion();

    }

    private void showNuevoNotifiacion() {
        setPendingIntent(Horarios.class);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID).setSmallIcon(R.drawable.logo).setContentTitle("Ya llegó su camión de basura").setContentText("Estimado(a) pobladora del distrito del tambo ya puede sacar su basura que su camión recolector esta cerca.").setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(pendingIntent);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
        managerCompat.notify(1, builder.build());
    }

    private void setPendingIntent(Class<?> clsActivity) {
        Intent intent = new Intent(this, clsActivity);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(clsActivity);
        stackBuilder.addNextIntent(intent);
        pendingIntent = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);


    }


    public void HPerfil(View view) {
        Intent m = new Intent(Horarios.this, Perfil.class);
        startActivity(m);
    }

    public void HDireccion(View view) {
        Intent n = new Intent(Horarios.this, MapsActivity.class);
        startActivity(n);
    }

    public void Hhorarios(View view) {
        Intent o = new Intent(Horarios.this, Horarios.class);
        startActivity(o);
    }
}
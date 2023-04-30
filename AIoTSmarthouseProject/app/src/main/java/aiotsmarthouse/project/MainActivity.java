package aiotsmarthouse.project;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MainActivity extends AppCompatActivity {
    MQTTHelper mqttHelper;
    TextView txtTemp, txtHumi, txtLight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTemp = findViewById(R.id.txtTemp);
        txtHumi = findViewById(R.id.txtHumidity);
        txtLight = findViewById(R.id.txtLight);

        startMQTT();
    }

    public void startMQTT(){
        mqttHelper = new MQTTHelper(this);
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {

            }

            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                if(topic.contains("temp")) txtTemp.setText(message.toString()+ "°C");
                else if(topic.contains("humi")) txtHumi.setText(message.toString() + "%");
                else if(topic.contains("light")) txtLight.setText(message.toString() + "%");
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }
}
package residence.kholy.mobeewaveinterview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView CommandAPDUTextOutput;
    EditText CommandAPDUTextInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CommandAPDUTextInput = (EditText) findViewById(R.id.CommandAPDUTextInput);
        CommandAPDUTextOutput = (TextView) findViewById(R.id.CommandAPDUTextOutput);

    }

    public void onAPDUButtonClick (View v) {

        String userAPDUInput = CommandAPDUTextInput.getText().toString();
        byte[] userAPDUInputAsByte = userAPDUInput.getBytes();

        CommandAPDUTextOutput.setText(userAPDUInputAsByte.toString());
    }
}

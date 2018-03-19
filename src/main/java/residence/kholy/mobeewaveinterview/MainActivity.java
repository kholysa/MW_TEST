package residence.kholy.mobeewaveinterview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView CommandAPDUTextOutput;
    TextView CommandEncryptionTextOutput;

    EditText CommandAPDUHeader;
    EditText CommandAPDULc;
    EditText CommandAPDUData;
    EditText CommandAPDULe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CommandAPDUHeader = (EditText) findViewById(R.id.commandADPUHeader);
        CommandAPDULc = (EditText) findViewById(R.id.commandADPULc);
        CommandAPDUData = (EditText) findViewById(R.id.commandADPUData);
        CommandAPDULe = (EditText) findViewById(R.id.commandADPULe);

        CommandAPDUTextOutput = (TextView) findViewById(R.id.commandAPDUOutput);
        CommandEncryptionTextOutput = (TextView) findViewById(R.id.EncryptionTextOutput);

    }

    public void onAPDUButtonClick (View v) {

        //TODO: Method to split one byte stream into header, Lc, data, Le byte arrays

        byte[] userAPDUHeader = hexStringToByteArray(CommandAPDUHeader.getText().toString());
        byte[] userAPDULc = hexStringToByteArray(CommandAPDULc.getText().toString());
        byte[] userAPDUData = hexStringToByteArray(CommandAPDUData.getText().toString());
        byte[] userAPDULe = hexStringToByteArray(CommandAPDULe.getText().toString());
        CommandAPDU commandAPDU = commandAPDUFactory(userAPDUHeader, userAPDULc, userAPDUData, userAPDULe);
        if (commandAPDU.isValid()) {
            CommandAPDUTextOutput.setText(byteArrayToHex(userAPDUHeader));

        } else {
            CommandAPDUTextOutput.setText("INVALID APDU CLASS");
        }

    }
    public void onEncryptButtonClick (View v) {
        byte[] userAPDUData = hexStringToByteArray(CommandAPDUData.getText().toString());
        Encryption encryption = new Encryption();
        encryption.decryptBytes(userAPDUData);
        encryption.encryptBytes(userAPDUData);
        String userAPDUDataAsString = "";
        for (byte singleByte : userAPDUData) {
            userAPDUDataAsString += " "+ String.format("%02X", singleByte);
        }

        CommandEncryptionTextOutput.setText(userAPDUDataAsString);
    }


    /**
     * Method which takes as input the hexadecimal string representation of an APDU section, and returns a byte array
     * taken from https://stackoverflow.com/questions/140131/convert-a-string-representation-of-a-hex-dump-to-a-byte-array-using-java
     * @param s APDU section as a hex representation
     * @return byte array
     */
    private byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    /**
     * Method to convert byte array to hex string, taken from https://stackoverflow.com/questions/9655181/how-to-convert-a-byte-array-to-a-hex-string-in-java
     * @param a byte array
     * @return string of hex representation of byte array
     */
    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }


    /**
     * Factory for handling the different possibilities of APDU class
     * @param header byte array header
     * @param lc byte array lc
     * @param data byte array data
     * @param le byte array le
     * @return validated CommandAPDU object
     */
    private CommandAPDU commandAPDUFactory(byte[] header, byte[] lc, byte[] data, byte[] le){
        CommandAPDU commandAPDU;
        if (header.length > 0 && lc.length + data.length + le.length == 0){
            commandAPDU = new CommandAPDU(header);
        } else if (header.length > 0 && le.length > 0 && lc.length + data.length == 0){
            commandAPDU = new CommandAPDU(header, le);
        } else if (header.length > 0 && lc.length > 0 && data.length > 0 && le.length == 0){
            commandAPDU = new CommandAPDU(header, lc, data);
        } else if (header.length > 0 && lc.length > 0 && data.length > 0 && le.length > 0){
            commandAPDU = new CommandAPDU(header, lc, data, le);
        } else {
            byte[] invalidateByteArray = {};
            commandAPDU = new CommandAPDU(invalidateByteArray);
        }
        return commandAPDU;
    }
}

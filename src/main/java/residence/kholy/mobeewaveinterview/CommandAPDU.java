package residence.kholy.mobeewaveinterview;

/**
 * ComandAPDU class contains fields for each of the headers of the CommandAPDU object
 * Maximum size of 250 Bytes
 * 4-header
 * 0,1-Lc
 * 243-Data
 * 0,1,2,3-Le
 */
class CommandAPDU {

    private byte[] header;
    private byte[] lc;
    private byte[] data;
    private byte[] le;
    private boolean valid = true;

    CommandAPDU(byte[] header){
        checkAndValidateHeader(header);
    }

    CommandAPDU(byte[] header, byte[] le){
        checkAndValidateLe(le);
        checkAndValidateHeader(header);
    }

    CommandAPDU(byte[] header, byte[] lc, byte[] data){

        checkAndValidateHeader(header);
        checkAndValidateDataLength(lc, data);
    }

    CommandAPDU(byte[] header, byte[] lc, byte[] data, byte[] le){

        checkAndValidateHeader(header);
        checkAndValidateDataLength(lc,data);
        checkAndValidateLe(le);
    }

    private void checkAndValidateHeader(byte[] header) {
        byte headerLength = (byte) header.length;
        if(headerLength != 4){
            invalidateAPDUInstance();
        }else
            this.header = header;
    }

    private void checkAndValidateLe(byte[] le) {
        byte leLength = (byte) le.length;
        if (leLength > 3){
            invalidateAPDUInstance();
        } else
            this.le = le;
    }

    private void checkAndValidateDataLength(byte[] lc, byte[] data) {
        byte lcLength = (byte) lc.length;
        short dataLength = (short) data.length;

        if(lcLength == 0 && dataLength > 0){
            invalidateAPDUInstance();
            return;
        } else if ( lcLength != 1 || dataLength > 243){
            invalidateAPDUInstance();
            return;
        }
        short expectedLengthOfData = (short) lc[0];
        if (dataLength != expectedLengthOfData){
            invalidateAPDUInstance();
            return;
        }

        this.lc = lc;
        this.data = data;

    }
    private void invalidateAPDUInstance() {
        this.valid = false;
    }

    public boolean isValid() {
        return valid;
    }

    public byte[] getHeader() {
        return header;
    }

    public byte[] getLc() {
        return lc;
    }

    public byte[] getData() {
        return data;
    }

    public byte[] getLe() {
        return le;
    }
}

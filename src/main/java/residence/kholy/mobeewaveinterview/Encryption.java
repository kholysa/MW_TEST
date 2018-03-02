package residence.kholy.mobeewaveinterview;

class Encryption implements EncryptionInterface{
    Encryption () {}
    public void encryptBytes(byte[] readableByteStream)
    {
        int lastIndex = readableByteStream.length - 1;
        if(readableByteStream.length == 1){
            handleEncryptionOneByteInArray(readableByteStream, lastIndex);
        } else if (readableByteStream.length > 1 ){
            handleEncryptionManyBytesInArray(readableByteStream,  lastIndex);
        }
    }

    public void decryptBytes(byte[] encryptedByteStream)
    {
        int lastIndex = encryptedByteStream.length - 1;
        if(encryptedByteStream.length == 1){
            handleDecryptionOneByteInArray(encryptedByteStream, lastIndex);
        } else if (encryptedByteStream.length > 1 ){
            handleDecryptionManyBytesInArray(encryptedByteStream, lastIndex);
        }
    }

    private static void handleDecryptionOneByteInArray(byte[] encryptedStream, int lastIndex) {

        //store first two bits
        byte leftmostBit = getBit(encryptedStream[0], (byte) 7);
        byte secondLeftmostBit = getBit(encryptedStream[0], (byte) 6);

        encryptedStream[lastIndex] = (byte) (encryptedStream[lastIndex] << 2);

        if (leftmostBit == 1) {
            encryptedStream[0] = (byte)(encryptedStream[0] | (1 << 1));
        } else {
            encryptedStream[0] = (byte)(encryptedStream[0] & ~(1 << 1));
        }
        if (secondLeftmostBit == 1) {
            encryptedStream[0] = (byte)(encryptedStream[0] | (1));
        } else {
            encryptedStream[0] = (byte)(encryptedStream[0] & ~(1));
        }
    }

    private static void handleDecryptionManyBytesInArray(byte[] encryptedStream, int lastIndex) {

        //store first two bits
        byte leftMostBit = getBit(encryptedStream[0], (byte) 7);
        byte secondLeftMost = getBit(encryptedStream[0], (byte) 6);


        for (int i = 0; i < lastIndex ; i++) {
            byte tempLeftMost = getBit(encryptedStream[i+1], (byte) 7);
            byte tempSecondLeftmost = getBit(encryptedStream[i+1], (byte) 6);

            //shift left by two bits
            encryptedStream[i] = (byte) (encryptedStream[i] << 2);
            if (tempLeftMost == 1) {
                encryptedStream[i] = (byte)(encryptedStream[i] | (1 << 1));
            } else {
                encryptedStream[i] = (byte)(encryptedStream[i] & ~(1 << 1));
            }
            if (tempSecondLeftmost == 1) {
                encryptedStream[i] = (byte)(encryptedStream[i] | (1));
            } else {
                encryptedStream[i] = (byte)(encryptedStream[i] & ~(1));
            }
        }

        encryptedStream[lastIndex] = (byte) (encryptedStream[lastIndex] << 2);
        if (leftMostBit == 1) {
            encryptedStream[lastIndex] = (byte)(encryptedStream[lastIndex] | (1 << 1));
        } else {
            encryptedStream[lastIndex] = (byte)(encryptedStream[lastIndex] & ~(1 << 1));
        }
        if (secondLeftMost == 1) {
            encryptedStream[lastIndex] = (byte)(encryptedStream[lastIndex] | (1));
        } else {
            encryptedStream[lastIndex] = (byte)(encryptedStream[lastIndex] & ~(1));
        }
    }

    private static void handleEncryptionOneByteInArray(byte[] readableByteStream, int lastIndex) {

        //store last two bits
        byte rightmostBit = getBit(readableByteStream[lastIndex], (byte) 0);
        byte secondRightmostBit = getBit(readableByteStream[lastIndex], (byte) 1);

        readableByteStream[lastIndex] = (byte) (readableByteStream[lastIndex] >> 2);

        if (rightmostBit == 1) {
            readableByteStream[0] = (byte)(readableByteStream[0] | (1 << 6));
        } else {
            readableByteStream[0] = (byte)(readableByteStream[0] & ~(1 << 6));
        }
        if (secondRightmostBit == 1) {
            readableByteStream[0] = (byte)(readableByteStream[0] | (1 << 7));
        } else {
            readableByteStream[0] = (byte)(readableByteStream[0] & ~(1 << 7));
        }
    }

    private static void handleEncryptionManyBytesInArray(byte[] readableByteStream, int lastIndex) {
        //store last two bits
        byte rightmostBit = getBit(readableByteStream[lastIndex], (byte) 0);
        byte secondRightmostBit = getBit(readableByteStream[lastIndex], (byte) 1);


        for (int i = lastIndex; i > 0 ; i--) {
            byte tempRightmost = getBit(readableByteStream[i-1], (byte) 0);
            byte tempSecondRightmost = getBit(readableByteStream[i-1], (byte) 1);

            //shift right by two bits
            readableByteStream[i] = (byte) (readableByteStream[i] >> 2);
            if (tempRightmost == 1) {
                readableByteStream[i] = (byte)(readableByteStream[i] | (1 << 6));
            } else {
                readableByteStream[i] = (byte)(readableByteStream[i] & ~(1 << 6));
            }
            if (tempSecondRightmost == 1) {
                readableByteStream[i] = (byte)(readableByteStream[i] | (1 << 7));
            } else {
                readableByteStream[i] = (byte)(readableByteStream[i] & ~(1 << 7));
            }
        }

        readableByteStream[0] = (byte) (readableByteStream[0] >> 2);
        if (rightmostBit == 1) {
            readableByteStream[0] = (byte)(readableByteStream[0] | (1 << 6));
        } else {
            readableByteStream[0] = (byte)(readableByteStream[0] & ~(1 << 6));
        }
        if (secondRightmostBit == 1) {
            readableByteStream[0] = (byte)(readableByteStream[0] | (1 << 7));
        } else {
            readableByteStream[0] = (byte)(readableByteStream[0] & ~(1 << 7));
        }
    }

    /**
     * from https://stackoverflow.com/questions/9354860/how-to-get-the-value-of-a-bit-at-a-certain-position-from-a-byte/9354899
     * @param number byte value
     * @param position bit position we want to get
     * @return bit value at position of byte number
     */
    private static byte getBit(byte number, byte position)
    {
        return (byte) ((number >> position) & 1);
    }
}

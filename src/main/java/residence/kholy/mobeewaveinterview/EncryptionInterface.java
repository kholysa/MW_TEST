package residence.kholy.mobeewaveinterview;

interface EncryptionInterface {
    /**
     * @param readableByteStream Non-encrypted byte stream, will encrypt in place
     */
    void encryptBytes(byte[] readableByteStream);

    /**
     * @param encryptedByteStream encrypted byte stream, will decrypt in-place
     */
    void decryptBytes(byte[] encryptedByteStream);
}

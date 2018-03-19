package residence.kholy.mobeewaveinterview;

class TLVContainer {

    private TLV[] tlvs;

    public TLVContainer(byte[] inputStream)
    {
        this.tlvs = makeTLVs(inputStream);
    }

    private TLV[] makeTLVs(byte[] inputStream)
    {

    }

    public TLV[] getTlvs() {
        return tlvs;
    }

    public TLVContainer setTlvs(TLV[] tlvs) {
        this.tlvs = tlvs;
        return this;
    }
}

package residence.kholy.mobeewaveinterview;


public enum TAGS {
    applicationTemplate((byte)97),
    applicationDedicatedFileName((byte)79),
    MWCustomTag((byte)(-66));
    private byte tag;

    TAGS(byte value) {
        this.tag = value;
    }

    public byte tag() {
        return tag;
    }
}

package residence.kholy.mobeewaveinterview;

public class TLV {
    private byte tagType;
    private byte length;
    private byte[] value;

    public TLV() {
    }

    public byte getTagType() {
        return tagType;
    }

    public TLV setTagType(byte tagType) {
        this.tagType = tagType;
        return this;
    }

    public byte getLength() {
        return length;
    }

    public TLV setLength(byte length) {
        this.length = length;
        return this;
    }

    public byte[] getValue() {
        return value;
    }

    public TLV setValue(byte[] value) {
        this.value = value;
        return this;
    }
}

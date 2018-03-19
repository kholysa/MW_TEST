package residence.kholy.mobeewaveinterview;

import java.util.ArrayList;

class TLVContainer {

    private ArrayList<TLV> tlvs;
    private boolean valid;

    TLVContainer(byte[] inputStream)
    {
        this.tlvs = makeTLVs(inputStream);
    }

    private ArrayList<TLV> makeTLVs(byte[] inputStream)
    {
        byte length = 0;
        byte index = 0;
        ArrayList<TLV> tlvs= new ArrayList<>();
        boolean tagSet, lengthSet, valueSet;
        tagSet = lengthSet = valueSet = false;
        tagSet = true;
        TLV temp = null;
        byte[] tlvValue = new byte[0];
        int bytesLeftInValue = 0;
        for (byte singleByte: inputStream) {
            if(tagSet){
                temp = new TLV();
                if ( !TAGScontains(inputStream[index])){
                    this.valid = false;
                    return null;
                }
                temp.setTagType(singleByte);
                tagSet = false;
                lengthSet= true;
            } else if (lengthSet){
                temp.setLength(singleByte);
                tlvValue = new byte[singleByte];
                bytesLeftInValue = singleByte;
                lengthSet= false;
                valueSet = true;
            } else if (valueSet){
                tlvValue[tlvValue.length- bytesLeftInValue] = singleByte;
                bytesLeftInValue--;
                if (bytesLeftInValue == 0){
                    temp.setValue(tlvValue);
                    tlvs.add(temp);
                    valueSet = false;
                    tagSet = true;
                }
            }
        }
        return tlvs;
    }

    private boolean TAGScontains(byte test) {
        for (TAGS c : TAGS.values()) {
            if (c.tag() == test) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<TLV> getTlvs() {
        return tlvs;
    }

    public boolean isValid() {
        return valid;
    }
}

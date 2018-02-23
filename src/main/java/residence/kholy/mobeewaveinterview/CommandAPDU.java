package residence.kholy.mobeewaveinterview;

public class CommandAPDU {

    private byte[] header = new byte[4];

    public CommandAPDU(byte[] header){
        this.header = header;
//        return this;
    }

}

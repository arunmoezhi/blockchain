class Block
{
    String hash;
    String previousHash;
    String data;
    long timeStamp;
    int nonce;

    Block(String hash, String previousHash, String data, long timeStamp, int nonce)
    {
        this.hash = hash;
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = timeStamp;
        this.nonce = nonce;
    }
}

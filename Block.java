class Block
{
    String hash;
    String previousHash;
    String data;
    long timeStamp;

    Block(String hash, String previousHash, String data, long timeStamp)
    {
        this.hash = hash;
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = timeStamp;
    }
}

import java.util.List;
import java.util.LinkedList;
import java.security.SecureRandom;
import java.security.MessageDigest;

class BlockChain
{
    List<Block> blockChain;

    void addBlock(Block block)
    {
        blockChain.add(block);
    }
    
    BlockChain()
    {
        blockChain = new LinkedList<>();
        Block genesisBlock = new Block("0000000000000000000000000000000000000000000000000000000000000000", "", "genesis block", System.nanoTime());
        blockChain.add(genesisBlock);
    }
    
    String computeHash(String previousHash, String data, int nonce)
    {
        try
        {
            String merged = previousHash + data + nonce;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(merged.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for(int i=0;i<hash.length;i++)
            {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

    Block mineBlock(String data)
    {
        int blockLength = blockChain.size();
        String previousHash = blockChain.get(blockLength-1).hash;
        SecureRandom sRandom = new SecureRandom();
        int nonce = sRandom.nextInt(); 
        String currentHash = computeHash(previousHash, data, nonce);
        return new Block(currentHash, previousHash, data, System.nanoTime());
    }

    void printBlockChain()
    {
        for(int i=0;i<blockChain.size();i++)
        {
            Block b = blockChain.get(i);
            System.out.println("Block #" + i + "\t" + b.hash + "\t" + b.previousHash + "\t" + b.data + "\t" + b.timeStamp);
        }
    }
}

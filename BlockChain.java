import java.util.List;
import java.util.LinkedList;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.util.regex.*;

class BlockChain
{
    List<Block> blockChain;
    int difficulty;

    void addBlock(Block block)
    {
        blockChain.add(block);
    }

    BlockChain()
    {
        blockChain = new LinkedList<>();
        Block genesisBlock = new Block("0000000000000000000000000000000000000000000000000000000000000000", "0000000000000000000000000000000000000000000000000000000000000000", "genesis block", System.nanoTime(), 0);
        blockChain.add(genesisBlock);
        difficulty = 5;
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
        Pattern pattern = Pattern.compile("\\b0{" + difficulty +",}.*");
        while(true)
        {
          String currentHash = computeHash(previousHash, data, nonce);
          if(pattern.matcher(currentHash).matches())
          {
            return new Block(currentHash, previousHash, data, System.nanoTime(), nonce);
          }
          nonce++;
        }
    }

    void printBlockChain()
    {
        System.out.format("%10s%70s%70s%15s%15s%15s\n", "Block #", "hash", "previousHash", "data", "timeStamp", "nonce");
        for(int i=0;i<blockChain.size();i++)
        {
            Block b = blockChain.get(i);
            System.out.format("%10d%70s%70s%15s%15d%15d\n", i, b.hash, b.previousHash, b.data, b.timeStamp, b.nonce);
        }
    }

    boolean verifyBlock(Block b)
    {
        return b.hash.equals(computeHash(b.previousHash, b.data, b.nonce));
    }

    boolean verifyBlockChain()
    {
        for(int i=1;i<blockChain.size();i++)
        {
            if(!verifyBlock(blockChain.get(i)))
            {
                System.out.println("Block #" + i + " is invalid");
                return false;
            }
        }
        System.out.println("Block chain is valid");
        return true;
    }
}

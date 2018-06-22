import java.io.*;

class TestBlockChain
{
    public static void main(String[] args)
    {
        BlockChain bc = new BlockChain();
        for(int i=0;i<10;i++)
        {
            Block b = bc.mineBlock("data " + i); 
            bc.addBlock(b);
        }
        bc.printBlockChain();
    }
}

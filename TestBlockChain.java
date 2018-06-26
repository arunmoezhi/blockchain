import java.io.*;

class TestBlockChain
{
    public static void main(String[] args)
    {
        BlockChain bc = new BlockChain();
        for(int i=0;i<10;i++)
        {
            Block b = bc.mineBlock("data " + i); 
            System.out.print(".");
            bc.addBlock(b);
        }
        System.out.println();
        bc.printBlockChain();
        bc.verifyBlockChain();
    }
}

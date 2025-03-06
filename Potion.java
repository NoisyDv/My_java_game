public class Potion extends Item{
    Potion(int x,int y){
        super(x,y);
        

    }
    @Override
    boolean spawnChange(int change){
            if(change>70){
                return true;
            }
            return false;
            

    }
    
}

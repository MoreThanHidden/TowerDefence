public class Main {

    public static void main(String[] args) {

        //Well I printed somthing...
        System.out.println("Hello Game");

        //Now what? I Suppose we need a map
        Integer[] Map = new Integer[]{
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                2,1,0,1,1,1,1,1,1,0,0,0,0,0,0,
                0,1,0,1,0,0,0,0,1,0,0,1,1,1,0,
                0,1,0,1,0,0,0,0,1,0,0,1,0,1,0,
                0,1,0,1,0,0,0,0,1,0,0,1,0,1,0,
                0,1,0,1,0,0,1,1,1,0,0,1,0,1,2,
                0,1,0,1,0,0,1,0,0,0,0,1,0,0,0,
                0,1,1,1,0,0,1,1,1,1,1,1,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
        };

        //Not auto generated yet but we have a map...

        //Suppose I need to identify map tiles, I'll do this better later
        final int Grass = 0; //building zone
        final int StartEnd = 2; //maze ends
        final int Dirt = 1; //stuff can't get placed here
        final int BasicTower = 3; // the fun begins

        //I think we need an enemy?
        BadGuy testenemy = new BadGuy(1,2);

        //To Be Continued...

    }
}

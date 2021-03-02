
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tester
{
    // instance variables - replace the example below with your own
    private int x;
    private boolean is2;

    /**
     * Constructor for objects of class Tester
     */
    public Tester()
    {
        is2 = true;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void sampleMethod()
    {
        for (int i = 0; i < 10; i++){
            if ( i % 2 == 0){
                is2 = true;
                
            }
            else{
                is2 = false;
            }
            System.out.println(is2);
        }
    }
}

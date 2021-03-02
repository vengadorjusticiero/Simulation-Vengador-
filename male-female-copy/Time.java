
/**
 * Write a description of class Time here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Time
{
    private final static Simulator time = new Simulator();
    //Boolean value to give time
    private boolean isDay;
    
    public boolean getTime(){
        if ( time.getStep() % 2 == 0){
            isDay = true;
        }
        else{
            isDay = false;
        }
        return isDay;
    }
}

/**
 * Write a description of class Time here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Time
{
    //Boolean value to give time
    private boolean isDay;
    
    public boolean getTime(int steps){
        if ( steps % 2 == 0){
            isDay = true;
        }
        else{
            isDay = false;
        }
        return isDay;
    }
}
import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A simple model of a rabbit.
 * Mouses age, move, breed, and die.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29 (2)
 */
public class Mouse extends Animal
{
    // Characteristics shared by all Mouses (class variables).

    // The age at which a rabbit can start to breed.
    private static final int BREEDING_AGE = 5;
    // The age to which a rabbit can live.
    private static final int MAX_AGE = 50;
    // The likelihood of a rabbit breeding.
    private static final double BREEDING_PROBABILITY = 0.4;
    //
    private static final double MALE_PROBABILITY = 0.5;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 3;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();


    // Individual characteristics (instance fields).

    // The rabbit's age.
    private int age;
    //
    private boolean isMale;

    /**
     * Create a new rabbit. A rabbit may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the rabbit will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Mouse(boolean randomAge, Field field, Location location, boolean randomGender)
    {
        super(field, location);
        isMale = true;
        age = 0;
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
        }
        if(randomGender) {
            if (rand.nextDouble() <= MALE_PROBABILITY){
                isMale = false;
            }
        }
    }

    /**
     * This is what the rabbit does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param newMouses A list to return newly born Mouse.
     */
    public void act(List<Animal> newMouses)
    {
            incrementAge();
            if(isAlive()) {
                giveBirth(newMouses);            
                // Try to move into a free location.
                Location newLocation = getField().freeAdjacentLocation(getLocation());
                if(newLocation != null) {
                    setLocation(newLocation);
                }
                else {
                    // Overcrowding.
                    setDead();
                }
            }
        
    }

    /**
     * Increase the age.
     * This could result in the rabbit's death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }

    /**
     * Check whether or not this rabbit is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newMouses A list to return newly born Mouse.
     */
    private void giveBirth(List<Animal> newMouses)
    {
        // New Mouse are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Mouse young = new Mouse(false, field, loc, true);
            newMouses.add(young);
        }
    }

    private boolean findPartner()
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if(animal instanceof Mouse) {
                Mouse Mouse = (Mouse) animal;
                if(isMale != Mouse.getGender() && rand.nextDouble() <= BREEDING_PROBABILITY) { 
                    return true;
                }
                else { 
                    return false; 
                }
            }
        }
        return false;
    }

    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    private int breed()
    {
        int births = 0;
        if(canBreed() && findPartner()) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    /**
     * A rabbit can breed if it has reached the breeding age.
     * @return true if the rabbit can breed, false otherwise.
     */
    private boolean canBreed()
    {
        return age >= BREEDING_AGE && isMale == false;
    }

    public boolean getGender()
    {
        return isMale;
    }
}
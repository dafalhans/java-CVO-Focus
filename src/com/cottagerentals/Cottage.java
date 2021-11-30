package com.cottagerentals;


import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.IntStream;

public class Cottage {

    private String name;
    private String address;
    private int dayPrice;
    private Intermediair intermediair;
    private Boolean[] Availability;
    //private List<Boolean> Avail;
    private List<Boolean> Avail; //= new ArrayList<>();

    // VRAAG!! Is dit een juiste manier om een variable via een methode te initialiseren? (hier is de variable een Boolean Array)
    // Waneer ik de initiateAvailability(); niet hier als instance initialized aanroep, maar in de constructor - dan spreekt ik een ander object aan??

    {
        //initiateAvailability();
        initiateAvail();
    }


    public Cottage(String name, String address, int dayPrice) {
        this.name = name;
        this.address = address;
        this.dayPrice = dayPrice;
        //initiateAvail();
        //initiateAvailability();  wanneer ik deze zou aanroepen ipv als instance initializatie block, krijg ik NPE in dayFree().

    }

    public Cottage(String name, String address, int dayPrice, Intermediair intermediair) {
        this.name = name;
        this.address = address;
        this.dayPrice = dayPrice;
        this.intermediair = intermediair;
        initiateAvail();
    }



    private void initiateAvail(){
        LocalDateTime today = LocalDateTime.now();

        if(today.toLocalDate().isLeapYear()){

            this.Avail = new ArrayList<Boolean>(Collections.nCopies(366,Boolean.TRUE));

        }else{
            this.Avail = new ArrayList<Boolean>(Collections.nCopies(365,Boolean.TRUE));

        }
    }

/*    private void initiateAvailability(){
        LocalDateTime today = LocalDateTime.now();
        //ZonedDateTime today2 = ZonedDateTime.now();
        if(today.toLocalDate().isLeapYear()){
            this.Availability = new Boolean[366];
            Arrays.fill(this.Availability,Boolean.TRUE);
        }else{
            this.Availability = new Boolean[365];
            Arrays.fill(this.Availability,Boolean.TRUE);
        }

    }
*/

//      WAS ADDED TO TEST A LEAP YEAR. method can be removed:

/*    private void initiateAvailability(int year){
        LocalDateTime today = LocalDateTime.of(year,1,1,1,1);
        //ZonedDateTime today2 = ZonedDateTime.now();
        if(today.toLocalDate().isLeapYear()){
            this.Availability = new Boolean[366];
            Arrays.fill(this.Availability,Boolean.TRUE);
        }else{
            this.Availability = new Boolean[365];
            Arrays.fill(this.Availability,Boolean.TRUE);
        }

    }*/

    public String getName() {
        return name;
    }


    public String getAddress() {
        return address;
    }

    public int getDayPrice() {
        return dayPrice;
    }

    public Intermediair getIntermediair() {
        return intermediair;
    }



    private boolean dayFree(int day){
        System.out.println(Avail.get(day));
        return Avail.get(day);

    }

    /*
    Arrival day of the year e.g. day 32 = Feb;
    Stay in days
      - MUST NOT cross current year
      - has full period available (true)
      - from arrival day already complete for full stay or partial available (false + output)
      - free on arrival day, but not the full stay (false + output of available days)
    */


    public boolean periodFree(int arrivalDayOfYear, int stayInDays) {
        int remaining;

        /// YIKES: waarom moet ik hier nog mijn index - 1 doen!?
        arrivalDayOfYear = arrivalDayOfYear - 1;

        if ((arrivalDayOfYear + stayInDays < this.Avail.size())){

            if(Avail.get(arrivalDayOfYear) == false){
                System.out.println("Huisje bezet voor deze periode");
                return false;
            }else{
                int freecounter = 0;

                for(int i = arrivalDayOfYear;i<arrivalDayOfYear+stayInDays;i++){

                    if(Avail.get(i).booleanValue() == true) {

                        freecounter = freecounter + 1;
                    }
                }
                if(freecounter == stayInDays){
                    return true;
                }else{
                    System.out.println("Het huisje is gedeeltelijk bezet voor deze periode.\n" +
                            "Met jouw opgegeven dag kun je " + freecounter + " dagen reserveren\n");
                    return false;
                }

            }





        } else {
            remaining = this.Avail.size() - arrivalDayOfYear;

            System.out.println("Jouw keuze overschrijdt de limiet van 31 december.\n" +
                    "Met jouw opgegeven dag kun je " + remaining + "dagen reserveren\n");
            return false;
        }

    }

    public void calculatePrice(int arrivalDayOfYear, int stayInDays){
        double total = 0;
        if(periodFree(arrivalDayOfYear,stayInDays)){
            total = stayInDays * this.dayPrice;
            if(this.intermediair != null){
                total = total + this.intermediair.getCommision();
            }
            System.out.println("De huurprijs voor de opgegeven periode is " + total + "Euro");
        }

    }

    public void reserveCottage(int arrivalDayOfYear, int stayInDays){
        if(periodFree(arrivalDayOfYear,stayInDays)){
            StringBuilder sb = new StringBuilder();
            sb.append("RESERVATIE");
            sb.append("\n");
            sb.append("We verwachten jullie op dag ");
            sb.append(arrivalDayOfYear);
            sb.append(" voor een periode van ");
            sb.append(stayInDays);
            sb.append("dagen.\n");
            System.out.println(sb);
            calculatePrice(arrivalDayOfYear,stayInDays);
            info();

            /// YIKES 2 : waarom moet ik hier nog mijn index - 1 doen!?  (zie ook Cottage.periodFree() waar ik index -1 moet doen)
            IntStream.range(arrivalDayOfYear-1,arrivalDayOfYear-1+stayInDays).forEach(d -> Avail.set(d,Boolean.FALSE));
        }
    }

    public String info(){
        StringBuilder sb = new StringBuilder();
        sb.append("GEGEVENS");
        sb.append("\n\t");
        sb.append(this.name);
        sb.append("\n\t");
        sb.append(this.address);
        sb.append("\n\tPrijs per dag: ");
        sb.append(this.dayPrice);
        sb.append(" EURO.");
        if(getIntermediair() != null){
            sb.append("\n\tIntermediair: ");
            sb.append(this.getIntermediair().getName());
        }

        System.out.println(sb);

        return null;
    }

    @Override
    public String toString() {
        return this.info();
    }
}

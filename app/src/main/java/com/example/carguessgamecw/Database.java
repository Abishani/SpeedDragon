package com.example.carguessgamecw;

import java.util.Random;

public class Database {


//    private final int NO_OF_BRANDS_MINUS_ONE = 29;
//    private static int lastRandomIndex;
//
//
//    private Integer[] cars = {R.drawable.alfa_romeo, R.drawable.amg, R.drawable.audi,
//                              R.drawable.bentley, R.drawable.bmw, R.drawable.cadillac,
//                              R.drawable.chevrolet,R.drawable.citroen, R.drawable.dacia,
//                              R.drawable.ferrari, R.drawable.fiat, R.drawable.genesis,
//                              R.drawable.hyundai, R.drawable.infiniti, R.drawable.kia,
//                              R.drawable.lamborgini, R.drawable.lotus, R.drawable.maserati,
//                              R.drawable.mazda, R.drawable.mercedes_benz, R.drawable.mini,
//                              R.drawable.mitsubishi, R.drawable.opel, R.drawable.proton,
//                              R.drawable.rolls_royce, R.drawable.saab, R.drawable.smart,
//                              R.drawable.tesla, R.drawable.vauxhall, R.drawable.volvo };
//
//
//
//    private String[] answers = {"Alfa Romeo", "AMG", "Audi", "Bentley", "bmw", "Cadillac",
//                                "Chevrolet", "Citroen", "Dacia", "Ferrari", "Fiat", "Genesis",
//                                "Hyundai", "Infiniti", "KIA", "Lamborgini", "Lotus", "Maserati",
//                                "Mazda", "Mercedes benz", "Mini", "Mitsubishi", "Opel", "Proton",
//                                "Rolls royce", "Saab", "Smart", "Tesla", "Vauxhall", "Volvo"};
//
//
//    public Database(){
//
//    }
//
//
//    // takes index to return associated country name
//    public String getBrandName(int indexInArray){
//        //Preventing array index out of bounds error
//        if ((indexInArray <= NO_OF_BRANDS_MINUS_ONE) && (indexInArray >= 0)){
//            return answers[indexInArray];
//        }
//        else {
//            System.out.println("Index out of bounds <-- Database.class");
//            return "Index out of bounds <-- Database.class";
//        }
//    }
//
//    // takes country name to return index
//    public int getIndex(String country){
//        for(int i = 0; i < NO_OF_BRANDS_MINUS_ONE; i++){
//            if(country.equals(answers[i])) return i;
//        }
//        System.out.println("Brand not found, return -1 <-- Database.class");
//        return -1;
//    }
//
//    // returns random flag
//    public Integer getRandomCar(){
//
//        Random rand = new Random();
//        int randomNumber = rand.nextInt((NO_OF_BRANDS_MINUS_ONE) + 1);
//
//        lastRandomIndex = randomNumber;
//        return cars[randomNumber];
//    }
//
//
//
//    public static int getLastRandomIndex(){
//        return lastRandomIndex;
//    }


    private final int NO_OF_COUNTRIES_MINUS_ONE = 29;
    private static int lastRandomIndex;
    private Integer[] cars = {R.drawable.alfa_romeo, R.drawable.amg, R.drawable.audi,
                                  R.drawable.bentley, R.drawable.bmw, R.drawable.cadillac,
                              R.drawable.chevrolet,R.drawable.citroen, R.drawable.dacia,
                              R.drawable.ferrari, R.drawable.fiat, R.drawable.genesis,
                              R.drawable.hyundai, R.drawable.infiniti, R.drawable.kia,
                              R.drawable.lamborgini, R.drawable.lotus, R.drawable.maserati,
                              R.drawable.mazda, R.drawable.mercedes_benz, R.drawable.mini,
                              R.drawable.mitsubishi, R.drawable.opel, R.drawable.proton,
                              R.drawable.rolls_royce, R.drawable.saab, R.drawable.smart,
                              R.drawable.tesla, R.drawable.vauxhall, R.drawable.volvo };



    private String[] answers = {"Alfa Romeo", "AMG", "Audi", "Bentley", "bmw", "Cadillac",
                                "Chevrolet", "Citroen", "Dacia", "Ferrari", "Fiat", "Genesis",
                                "Hyundai", "Infiniti", "KIA", "Lamborgini", "Lotus", "Maserati",
                                "Mazda", "Mercedes benz", "Mini", "Mitsubishi", "Opel", "Proton",
                                "Rolls royce", "Saab", "Smart", "Tesla", "Vauxhall", "Volvo"};



    public Database(){

    }


    // takes index to return associated country name
    public String getBrandName(int indexInArray){
        //Preventing array index out of bounds error
        if ((indexInArray <= NO_OF_COUNTRIES_MINUS_ONE) && (indexInArray >= 0)){
            return answers[indexInArray];
        }
        else {
            System.out.println("Index out of bounds <-- Database.class");
            return "Index out of bounds <-- Database.class";
        }
    }

    // takes country name to return index
    public int getIndex(String country){
        for(int i = 0; i < NO_OF_COUNTRIES_MINUS_ONE; i++){
            if(country.equals(answers[i])) return i;
        }
        System.out.println("Country not found, return -1 <-- Database.class");
        return -1;
    }

    // returns random flag
    public Integer getRandomCar(){

        Random rand = new Random();
        int randomNumber = rand.nextInt((NO_OF_COUNTRIES_MINUS_ONE) + 1);

        lastRandomIndex = randomNumber;
        return cars[randomNumber];
    }

    public String[] getAnswersArray(){
        return answers;
    }

    public static int getLastRandomIndex(){
        return lastRandomIndex;
    }




}

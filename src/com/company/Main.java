package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int [ ] heroesHealth = {250,250,250};
    public static int [ ] heroesDamage = {20,20,20};
    public static int  medicHealth = 230;
    public static int medicHeal = 30;
    public static String[] heroesAttackType = {"Physical","Magical","Mental"};


    public static void main(String[] args) {
        fightInfo();
        while (!isFinished()){
            round();
        }


    }

    public static void round(){
        changeBossDefence();
        bossHit();
        heroesHit();
        medicHealing();
        fightInfo();
    }
    public static void changeBossDefence(){
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefenceType =heroesAttackType[randomIndex];
    }


    public static boolean isFinished(){
        if (bossHealth <= 0){
            System.out.println("Heroes won");
            return true;
        }
        if (heroesHealth[0]<=0 && heroesHealth[1]<=0 && heroesHealth[2] <=0){
            System.out.println("Boss won!");
            return true;
        }
        return false;

    }
    public static void bossHit(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0){
                if (heroesHealth[i] - bossDamage < 0){
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }



        }
    }
    public static  void  medicHealing() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if ( medicHealth > 0) {
                heroesHealth[i] = heroesHealth[i] + medicHeal;
                System.out.println(heroesAttackType[i] + " healing" + medicHeal) ;
            }
                if (medicHealth - bossDamage < 0){
                    medicHealth = 0;
                } else {
                    medicHealth = medicHealth - bossDamage;
                }

            }
        }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    Random random = new Random();
                    int koeff = random.nextInt(9) + 2;
                    if (bossHealth - heroesDamage[i] * koeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * koeff;
                    }
                    System.out.println(heroesAttackType[i] + " critical hit " + heroesDamage[i] * koeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }

            }
        }
    }
    public static void fightInfo(){
        System.out.println("________________________________");
        System.out.println("Boss Health: "+ bossHealth +" " + bossDefenceType );
        System.out.println("Warrior health: " + heroesHealth[0] );
        System.out.println("Magic health: " + heroesHealth[1] );
        System.out.println("Mentalist health: " + heroesHealth[2] );
        System.out.println("Medic health: " + medicHealth);
        System.out.println("________________________________");
    }

}

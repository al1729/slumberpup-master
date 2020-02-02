package com.example.sleephealthdog;

//import org.apache.commons-math3-3.6.1.*;
import org.apache.commons.math3.*;
import org.apache.commons.math3.distribution.NormalDistribution;

public class SleepScore {

    public static double zScoreToPercentile(double zScore)
    {
        double percentile = 0;

        NormalDistribution dist = new NormalDistribution();
        percentile = dist.cumulativeProbability(zScore) * 100;
        return percentile;
    }

    public static double sleep_score(double sleep, double light, double deep, double rem, double awake) {

        deep = deep/60;
        rem = rem/60;
        sleep = sleep/60;

        if(deep>0 && rem>0){
            double efficiency = 100*(light+deep+rem)/(light+deep+rem+awake);
            double deep_z = (deep-84.3)/(.01*34.2*84.3);
            double rem_z = (rem-93.8)/(.01*22.3*93.8);
            double sleep_z = (light+deep+rem-439.3)/(.01*439.3*8.4);
            double efficiency_z = (efficiency-91.3)/(.01*91.3*8.15);

            double[] L = new double[] {deep_z, rem_z, sleep_z, efficiency_z};
            double score = 0;
            for (int i = 0; i < L.length; i++) {score += zScoreToPercentile(L[i]);}
            score = score * 25;
            score = 3*score/5 + 40;
            return(score);
        }

        else{
            double efficiency = 100*(sleep)/(sleep + awake);
            double sleep_z = (sleep - 439.3)/(.01*439.3*8.4);
            double efficiency_z = (efficiency-91.3)/(.01*91.3*8.15);

            double[] L = new double[] {sleep_z, efficiency_z};
            double score = 0;
            for (int i = 0; i < L.length; i++) {score += zScoreToPercentile(L[i]);}
            score = score * 50;
            score = 7*score/10 + 30;
            int final_score = (int) score;
            return(final_score);
        }

    }
}
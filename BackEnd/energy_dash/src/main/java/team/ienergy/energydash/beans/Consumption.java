package team.ienergy.energydash.beans;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Mingchao Sima
 * @date: 07 April 2021
 * @throws
 */
@Data
public class Consumption {
    private int uid;

    private String pid;

    private String p0;
    private String p1;
    private String p2;
    private String p3;
    private String p4;
    private String p5;
    private String p6;
    private String p7;
    private String p8;
    private String p9;
    private String p10;
    private String p11;
    private String p12;
    private String p13;
    private String p14;
    private String p15;
    private String p16;
    private String p17;
    private String p18;
    private String p19;
    private String p20;
    private String p21;
    private String p22;
    private String p23;


    public List<String> getList(){
        List<String> res = new ArrayList<>();
        res.add(p0);
        res.add(p1);
        res.add(p2);
        res.add(p3);
        res.add(p4);
        res.add(p5);
        res.add(p6);
        res.add(p7);
        res.add(p8);
        res.add(p9);
        res.add(p10);
        res.add(p11);
        res.add(p12);
        res.add(p13);
        res.add(p14);
        res.add(p15);
        res.add(p16);
        res.add(p17);
        res.add(p18);
        res.add(p19);
        res.add(p20);
        res.add(p21);
        res.add(p22);
        res.add(p23);
        return res;
    }
    public float getUsage(int start, int end, List<String> res){
        float sum = 0;
        for (int i = start; i < end; i++) {
            sum+=Float.parseFloat(res.get(i));
        }
        return sum;
    }
}

package com.manufacture.expertservice.service.impl;

import com.manufacture.expertservice.model.ExpertTest;
import com.manufacture.expertservice.repository.ExpertTestRepository;
import com.manufacture.expertservice.service.ExpertTestService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;


@Service
public class ExpertTestServiceImpl implements ExpertTestService {
    public static JSONObject jsonobj = new JSONObject();
    @Autowired
    private ExpertTestRepository expertTestRepository;

    @Override
    public List<ExpertTest> findAll() {

        return expertTestRepository.findAll();
    }
    @Override
    public void addTest(ExpertTest test) {

        try {
            Field changeMap;
            try {
                changeMap = jsonobj.getClass().getDeclaredField("map");
                changeMap.setAccessible(true);

                try {
                    changeMap.set(jsonobj, new LinkedHashMap<>());
                } catch (IllegalArgumentException e) {

                    e.printStackTrace();
                } catch (IllegalAccessException e) {

                    e.printStackTrace();
                }

                changeMap.setAccessible(false);
            } catch (NoSuchFieldException e) {

                e.printStackTrace();
            } catch (SecurityException e) {

                e.printStackTrace();
            }

            jsonobj.put("bendrosop1_min", test.getBendrosop1_min());
            jsonobj.put("bendrosop1_true", test.getBendrosop1_true());
            jsonobj.put("bendrosop1_max", test.getBendrosop1_max());
            jsonobj.put("bendrosop2_min", test.getBendrosop2_min());
            jsonobj.put("bendrosop2_true", test.getBendrosop2_true());
            jsonobj.put("bendrosop2_max", test.getBendrosop2_max());
            jsonobj.put("bendrosop3_min", test.getBendrosop3_min());
            jsonobj.put("bendrosop3_true", test.getBendrosop3_true());
            jsonobj.put("bendrosop3_max", test.getBendrosop3_max());
            jsonobj.put("bendrosop4_min", test.getBendrosop4_min());
            jsonobj.put("bendrosop4_true", test.getBendrosop4_true());
            jsonobj.put("bendrosop4_max", test.getBendrosop4_max());

            jsonobj.put("opkoef1_min", test.getOpkoef1_min());
            jsonobj.put("opkoef1_true", test.getOpkoef1_true());
            jsonobj.put("opkoef1_max", test.getOpkoef1_max());
            jsonobj.put("opkoef2_min", test.getOpkoef2_min());
            jsonobj.put("opkoef2_true", test.getOpkoef2_true());
            jsonobj.put("opkoef2_max", test.getOpkoef2_max());
            jsonobj.put("opkoef3_min", test.getOpkoef3_min());
            jsonobj.put("opkoef3_true", test.getOpkoef3_true());
            jsonobj.put("opkoef3_max", test.getOpkoef3_max());
            jsonobj.put("opkoef4_min", test.getOpkoef4_min());
            jsonobj.put("opkoef4_true", test.getOpkoef4_true());
            jsonobj.put("opkoef4_max", test.getOpkoef4_max());

            jsonobj.put("kainos1_min", test.getKainos1_min());
            jsonobj.put("kainos1_true", test.getKainos1_true());
            jsonobj.put("kainos1_max", test.getKainos1_max());
            jsonobj.put("kainos2_min", test.getKainos2_min());
            jsonobj.put("kainos2_true", test.getKainos2_true());
            jsonobj.put("kainos2_max", test.getKainos2_max());
            jsonobj.put("kainos3_min", test.getKainos3_min());
            jsonobj.put("kainos3_true", test.getKainos3_true());
            jsonobj.put("kainos3_max", test.getKainos3_max());
            jsonobj.put("kainos4_min", test.getKainos4_min());
            jsonobj.put("kainos4_true", test.getKainos4_true());
            jsonobj.put("kainos4_max", test.getKainos4_max());

            jsonobj.put("darbai1_min", test.getDarbai1_min());
            jsonobj.put("darbai1_true", test.getDarbai1_true());
            jsonobj.put("darbai1_max", test.getDarbai1_max());
            jsonobj.put("darbai2_min", test.getDarbai2_min());
            jsonobj.put("darbai2_true", test.getDarbai2_true());
            jsonobj.put("darbai2_max", test.getDarbai2_max());
            jsonobj.put("darbai3_min", test.getDarbai3_min());
            jsonobj.put("darbai3_true", test.getDarbai3_true());
            jsonobj.put("darbai3_max", test.getDarbai3_max());
            jsonobj.put("darbai4_min", test.getDarbai4_min());
            jsonobj.put("darbai4_true", test.getDarbai4_true());
            jsonobj.put("darbai4_max", test.getDarbai4_max());


        } catch (JSONException e) {

            e.printStackTrace();
        }

        System.out.println(jsonobj.toString());
    }
}

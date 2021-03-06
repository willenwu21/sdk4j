package com.ximalaya.sdk4j;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.ximalaya.sdk4j.model.Paging;
import com.ximalaya.sdk4j.model.XimalayaException;
import com.ximalaya.sdk4j.model.dto.live.City;
import com.ximalaya.sdk4j.model.dto.live.Program;
import com.ximalaya.sdk4j.model.dto.live.Province;
import com.ximalaya.sdk4j.model.dto.live.Radio;
import com.ximalaya.sdk4j.model.dto.live.RadioList;
import com.ximalaya.sdk4j.model.dto.live.Schedule;

public class LivesTest {

    Lives lives = new Lives();

    @Test
    public void testGetProvinces() throws XimalayaException {
        List<Province> provinces = lives.getProvinces();
        Assert.assertTrue(provinces != null && !provinces.isEmpty());
    }

    @Test
    public void testGetRadioList() throws XimalayaException {
        RadioList radioList = lives.getRadioList(1, "110000", new Paging());
        Assert.assertTrue(radioList != null && radioList.getRadios() != null
                && !radioList.getRadios().isEmpty());
    }

    @Test
    public void testGetSchedules() throws XimalayaException {
        List<Schedule> threeDaySchedules = lives.getSchedules(75);
        Assert.assertTrue(threeDaySchedules != null && threeDaySchedules != null
                && !threeDaySchedules.isEmpty());
    }

    @Test
    public void testGetProgram() throws XimalayaException {
        Program program = lives.getPlayingProgram(75);
        Assert.assertNotNull(program != null && program.getProgramName() != null);
    }


	@Test
    public void testGetCitiesByProvince() throws XimalayaException {
        List<City> cities = lives.getCityByProvince("320000");
        Assert.assertNotNull(cities != null && cities.size() != 0);
    }

    @Test
    public void testGetRadiosByCity() throws XimalayaException {
        RadioList radioList = lives.getRadiosByCity(3201, new Paging(1, 10));
        Assert.assertNotNull(radioList != null && radioList.getRadios().size() != 0);
    }

    @Test
    public void testBatchGetRadios() throws XimalayaException {
    	List<Radio> radios = lives.batchGetRadios(new long[] { 12 });
    	Assert.assertTrue(radios != null && radios.size() == 1 && !radios.get(0).getRadioName().isEmpty());
    }
}

package com.example.demo.Services;

import com.example.demo.model.CityModel;
import com.example.demo.model.ListModel;
import com.example.demo.model.MainTemperatureDataModel;
import com.example.demo.model.OpenWeatherAPIModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TemperatureServiceTest {

    @Mock
    private TemperatureService mockedTempService;

    @Mock
    private ListModel mockedListModel;

    @Mock
    private OpenWeatherAPIModel mockedOpenAPIModel;

    @Before
    public void beforeMethod() {
        mockedTempService = mock(TemperatureService.class);
        mockedListModel = mock(ListModel.class);
        mockedOpenAPIModel = mock(OpenWeatherAPIModel.class);
    }

    @Test
    public void getTemperatureDataForPeriodTest() {

        //setup
        mockedOpenAPIModel.setCityData(new CityModel());

        List<ListModel> listModelList = new ArrayList<ListModel>();
        listModelList.add(mockedListModel);

        Date dt = new Date();
        MainTemperatureDataModel mockedMainTempData = new MainTemperatureDataModel();
        mockedMainTempData.setTemp(20.0);
        mockedMainTempData.setFeels_like(24.0);
        mockedMainTempData.setPressure(1000);
        mockedMainTempData.setTemp_max(28.0);
        mockedMainTempData.setTemp_min(18.0);


        when(mockedOpenAPIModel.getFulldDataList()).thenReturn(listModelList);
        when(mockedListModel.getDt()).thenReturn(dt);
        when(mockedListModel.getMainTemperatureDataModel()).thenReturn(mockedMainTempData);

        TemperatureService tm = new TemperatureService();
        //Act
        Map<Date, Map<String, MainTemperatureDataModel>> result = tm.getTemperatureDataForPeriod(mockedOpenAPIModel, 1);

        //Assert
        Assert.assertTrue(result.containsKey(dt));
        Assert.assertTrue(result.get(dt).containsKey("temp"));
        Assert.assertTrue(result.get(dt).containsKey("temp_min"));
        Assert.assertTrue(result.get(dt).containsKey("pressure"));
        Assert.assertEquals(result.get(dt).get("pressure"), 1000.0);
        Assert.assertEquals(result.get(dt).get("temp_max"), 28.0);

    }
}


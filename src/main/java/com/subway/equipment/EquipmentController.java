package com.subway.equipment;

import com.subway.controller.common.BaseController;
import com.subway.domain.app.MyPage;
import com.subway.service.app.ResourceService;
import com.subway.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.subway.object.ReturnObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * �豸��Ϣ
 *
 * @author huangbin
 * @generate by autoCode
 * @Date 2018-3-1
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/equipment")
public class EquipmentController extends BaseController {

    @Autowired
    ResourceService resourceService;
    @Autowired
    EquipmentService equipmentService;
    @Autowired
    EquipmentSearchService equipmentSearchService;


    /**
     * @param session
     * @param request
     * @param current
     * @param rowCount
     * @param searchPhrase
     * @return
     */
    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public MyPage data(HttpSession session, HttpServletRequest request, @RequestParam(value = "current", defaultValue = "0") int current, @RequestParam(value = "rowCount", defaultValue = "10") Long rowCount, @RequestParam(value = "searchPhrase", required = false) String searchPhrase) {
        Map
                <String, String[]> parameterMap = request.getParameterMap();
        Pageable pageable = new PageRequest(current - 1, rowCount.intValue(), super.getSort(parameterMap));
        return new PageUtils().searchBySortService(equipmentSearchService, searchPhrase, 1, current, rowCount, pageable);
    }


    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Equipment findById(@PathVariable("id") Long id) {
        return equipmentService.findById(id);
    }


    /**
     * @param id
     * @return ɾ����Ϣ
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnObject delete(@PathVariable("id") Long id) {
        return equipmentService.delete(id);
    }


    /**
     * @param equipment ��Ϣ
     * @return ������Ϣ
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject save(Equipment equipment) {
        return equipmentService.save(equipment);
    }


    /**
     * @param request
     * @param response
     * @param param
     * @param docName
     * @param titles
     * @param colNames
     */
    @ResponseBody
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam("param") String param, @RequestParam("docName") String docName, @RequestParam("titles") String titles[], @RequestParam("colNames") String[] colNames) {
        List<Equipment> dataList = equipmentSearchService.findByConditions(param, 2);
        equipmentService.setDataList(dataList);
        equipmentService.exportExcel(request, response, docName, titles, colNames);
    }


}

package com.subway.eqClass;

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
 * 设备分类
 *
 * @author huangbin
 * @generate by autoCode
 * @Date 2018-3-1
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/eqClass")
public class EqClassController extends BaseController {

    @Autowired
    ResourceService resourceService;
    @Autowired
    EqClassService eqClassService;
    @Autowired
    EqClassSearchService eqClassSearchService;


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
        Map<String, String[]> parameterMap = request.getParameterMap();
        Pageable pageable = new PageRequest(current - 1, rowCount.intValue(), super.getSort(parameterMap));
        return new PageUtils().searchBySortService(eqClassSearchService, searchPhrase, 2, current, rowCount, pageable);
    }


    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public EqClass findById(@PathVariable("id") Long id) {
        return eqClassService.findById(id);
    }


    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnObject delete(@PathVariable("id") Long id) {
        return eqClassService.delete(id);
    }


    /**
     * @param eqClass
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject save(EqClass eqClass) {
        return eqClassService.save(eqClass);
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
        List<EqClass> dataList = eqClassSearchService.findByConditions(param, 2);
        eqClassService.setDataList(dataList);
        eqClassService.exportExcel(request, response, docName, titles, colNames);
    }


    /**
     * @param classId
     * @param unitId
     * @return
     */
    @RequestMapping(value = "/addUnit", method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject addUnit(@RequestParam("classId") Long classId, @RequestParam("unitId") String unitId) {
        return eqClassService.addUnit(classId, unitId);
    }


    /**
     * @return 查询显示的选择的设备分类列表
     */
    @RequestMapping(value = "/findEqClasses", method = RequestMethod.GET)
    @ResponseBody
    public List<EqClass> findEqClasses() {
        return eqClassService.findEqClasses();
    }


}

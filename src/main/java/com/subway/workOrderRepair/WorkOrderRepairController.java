package com.subway.workOrderRepair;

import com.subway.controller.common.BaseController;
import com.subway.domain.app.MyPage;
import com.subway.object.ReturnObject;
import com.subway.service.app.ResourceService;
import com.subway.utils.ConstantUtils;
import com.subway.utils.PageUtils;
import com.subway.workOrder.WorkOrder;
import com.subway.workOrder.WorkOrderSearchService;
import com.subway.workOrder.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 维修单信息
 *
 * @author huangbin
 * @generate by autoCode
 * @Date 2018-3-14
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/workOrderRepair")
public class WorkOrderRepairController extends BaseController {

    @Autowired
    ResourceService resourceService;
    @Autowired
    WorkOrderRepairService  workOrderRepairService;
    @Autowired
    WorkOrderRepairSearchService workOrderRepairSearchService;


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
        return new PageUtils().searchBySortService(workOrderRepairSearchService, searchPhrase, 4, current, rowCount, pageable);
    }


    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public WorkOrder findById(@PathVariable("id") Long id) {
        return workOrderRepairService.findById(id);
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
        List<WorkOrder> dataList = workOrderRepairSearchService.findByConditions(param, 4);
        workOrderRepairService.setDataList(dataList);
        workOrderRepairService.exportExcel(request, response, docName, titles, colNames);
    }


    /**
     * @param type      报修类别
     * @param id        id
     * @param orderDesc 报修描述
     * @param reporter  报修人
     * @return
     */
//    @ResponseBody
//    @RequestMapping(value = "/reportFix", method = RequestMethod.POST)
//    public ReturnObject reportFix(@RequestParam("type") String type, @RequestParam("id") Long id, @RequestParam("orderDesc") String orderDesc, @RequestParam("reporter") String reporter) {
//        WorkOrder workOrder = workOrderRepairService.reportFix(type, id, orderDesc, reporter);
//        return commonDataService.getReturnType(workOrder != null, ConstantUtils.REPORT_FIX_SUCCESS, ConstantUtils.REPORT_FIX_FAILURE);
//    }


}

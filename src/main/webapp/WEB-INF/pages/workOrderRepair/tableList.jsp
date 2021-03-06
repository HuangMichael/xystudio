<%@ page contentType="text/html;charset=UTF-8" %>
<th data-column-id="id" data-type="numeric" data-identifier="true" data-visible="false" data-width="10%">ID</th>
<th data-column-id="id" data-width="5%">序号</th>
<th data-column-id="orderLineNo" data-width="10%">工单行号</th>
<th data-column-id="equipment" data-width="10%" data-converter="showEqName">设备名称</th>
<th data-column-id="equipment" data-width="10%" data-converter="showLocName">设备位置</th>
<th data-column-id="eqClass" data-width="10%" data-converter="showEqClassName">设备分类</th>
<th data-column-id="orderDesc" data-width="15%">报修单描述</th>
<th data-column-id="reporter" data-width="5%" data-visible="false">报修人</th>
<th data-column-id="reportType" data-width="6%" data-converter="showReportType" data-visible="false">报修类型</th>
<th data-column-id="reportTime" data-width="10%" data-sortable="true">报修时间</th>
<th data-column-id="creator" data-width="5%">记录人</th>
<th data-column-id="orderState" data-width="6%" data-converter="showOrderState">工单状态</th>
<th data-column-id="status" data-width="5%" data-converter="showStatus">状态</th>
<th data-column-id="commands" data-formatter="commands" data-sortable="false" data-width="6%">编辑|删除</th>


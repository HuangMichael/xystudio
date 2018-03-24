package com.subway.columns;

import com.subway.object.ReturnObject;
import com.subway.service.app.BaseService;
import com.subway.service.commonData.CommonDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.subway.utils.ConstantUtils.*;


/**
 * 栏目信息业务类
 *
 * @author huangbin
 * @generate by autoCode
 * @Date 2018-3-1
 */
@Service
public class ColumnsService extends BaseService {


    @Autowired
    ColumnsRepository columnsRepository;


    @Autowired
    CommonDataService commonDataService;

    public List<Columns> findAll() {
        return columnsRepository.findAll();
    }


    public Page<Columns> findAll(Pageable pageable) {
        return columnsRepository.findAll(pageable);
    }


    /**
     * @param id id
     * @return 根据id删除对象
     */
    public ReturnObject delete(Long id) {
        columnsRepository.delete(id);
        Columns columns = columnsRepository.getOne(id);
        return commonDataService.getReturnType(columns == null, DELETE_SUCCESS, DELETE_FAILURE);
    }


    /**
     * @param columns
     * @return 保存信息
     */
    public ReturnObject save(Columns columns) {

        columns = columnsRepository.save(columns);
        return commonDataService.getReturnType(columns != null, SAVE_SUCCESS, SAVE_FAILURE);
    }


    public Columns findById(Long id) {
        return columnsRepository.getOne(id);
    }

}

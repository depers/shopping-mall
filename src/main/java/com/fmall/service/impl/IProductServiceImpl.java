package com.fmall.service.impl;

import com.fmall.common.ResponseCode;
import com.fmall.common.ServerResponse;
import com.fmall.dao.ProductMapper;
import com.fmall.pojo.Product;
import com.fmall.service.IProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 冯晓 on 2017/6/26.
 */
@Service("iProductService")
public class IProductServiceImpl implements IProductService{


    @Autowired
    private ProductMapper productMapper;

    public ServerResponse saveOrUpdateProduct(Product product){
        if(product != null){
            if(StringUtils.isNotBlank(product.getSubImages())){
                String[] subImageArray = product.getSubImages().split(",");
                if(subImageArray.length > 0){
                    product.setMainImage(subImageArray[0]);
                }
            }
            if(product.getId() != null){
                int resultCount = productMapper.updateByPrimaryKey(product);
                if(resultCount > 0){
                    return ServerResponse.createBySuccess("更新产品成功");
                } else{
                    return ServerResponse.createByErrorMessage("更新产品失败");
                }
            } else{
                int resultCount = productMapper.insert(product);
                if(resultCount > 0){
                    return ServerResponse.createBySuccess("新增产品成功");
                } else{
                    return ServerResponse.createByErrorMessage("新增产品失败");
                }
            }
        }
        return ServerResponse.createByErrorMessage("新增或更新产品参数不正确");
    }

    public ServerResponse<String> setSaleStatus(Integer productId, Integer status){
        if(productId == null || status == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int rowCount = productMapper.updateByPrimaryKeySelective(product);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("修改产品销售状态成功");
        }
        return ServerResponse.createByErrorMessage("修改产品销售状态失败");
    }


}

package org.amused.function;

import org.amused.model.ProductData;

public class GetAllObjects {

    public boolean isAllObjetsHaveID(ProductData[] product) {

        Boolean isAllObjetsHaveId = true;
        for (ProductData productData : product) {
            if (productData.getId() == null) {

                isAllObjetsHaveId=  false;
                break;

            }
        }

        return isAllObjetsHaveId;
    }

    public boolean isAllObjetsHaveName(ProductData[] product) {

        Boolean isAllObjetsHaveName = true;
        for (ProductData productData : product) {
            if (productData.getName() == null) {

                isAllObjetsHaveName=  false;
                break;

            }
        }

        return isAllObjetsHaveName;
    }
}

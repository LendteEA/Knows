package com.bs.knows.utils;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

@SmartTable(name = "识别情况表")
public class ScanDataTable {

    public ScanDataTable(String Data, float textprob, int prob) {
        this.Data = Data;
        this.textprob = textprob;
        this.prob = prob;
    }

    //    name：版块名称，count：目标值，restaurant：餐饮数量，ka：KA数量，wholesale：流通批发数量，industry：工业加工数量，other：其它数量
    @SmartColumn(id = 0, name = "识别值", autoMerge = true)
    private String Data;
    @SmartColumn(id = 1, name = "文本置信度")
    private float textprob;
    @SmartColumn(id = 2, name = "OCR置信度")
    private int prob;
}
